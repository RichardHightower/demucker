package com.cloudurable.demuck;

import io.nats.jparse.Json;
import io.nats.jparse.node.ObjectNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Demucker {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    public static void main(String[] args) throws IOException {
        System.out.println(new File(".").getCanonicalFile());
        try {
            String filePath;
            if (args.length > 0) {
                filePath = args[0];
            } else {
                System.out.println("Reading system in");
                filePath = "/dev/stdin";
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("{")) {
                        processJSONObject(line);
                    } else {
                        processNonJSONObject(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String indent(String input, int numTabs) {
        StringBuilder indentedText = new StringBuilder();
        String[] lines = input.split("\n");

        Arrays.stream(lines).map(line-> line.trim()).forEach(line ->
        {

            if (line.length() > 100) {
                String[] words = line.split("\\s+");

                StringBuilder currentLine = new StringBuilder();
                Arrays.stream(words).map(w->w.trim()).forEach(word->
                        {
                            if (currentLine.length() + word.length() > 100) {
                                for (int i = 0; i < numTabs; i++) {
                                    indentedText.append("\t");
                                }
                                indentedText.append(currentLine.toString().trim()).append("\n");
                                currentLine.setLength(0);
                            }
                            currentLine.append(word).append(" ");
                        }
                );
                for (int i = 0; i < numTabs + 5; i++) {
                    indentedText.append("\t");
                }
                indentedText.append(currentLine.toString().trim()).append("\n");
            } else {
                for (int i = 0; i < numTabs; i++) {
                    indentedText.append("\t");
                }
                indentedText.append(line).append("\n");
            }
        });


        return indentedText.toString();
    }




    private static Log processJSONObject(String line) {
        ObjectNode objectNode = Json.toObjectNode(line);

        if (objectNode.get("exception") == null) {

            final Log log = Log.builder()
                    .level(objectNode.getString("level"))
                    .logger(objectNode.getString("logger"))
                    .thread(objectNode.getString("thread"))
                    .message(objectNode.getString("message"))
                    .timestamp(LocalDateTime.parse(objectNode.getString("timestamp"), TIMESTAMP_FORMATTER))
                    .build();


                System.out.printf("%s\t\t%s\n", log.getLevel(), log.getTimestamp());
                System.out.printf("%s\n", log.getLogger());
                System.out.printf("%s\n", indent(log.getMessage(), 1));
            return log;

        } else {

            System.out.printf("FATAL\t\tEXCEPTION\n");
            System.out.printf("%s\n", objectNode.getString("exception"));



            return null;
        }

    }

    private static void processNonJSONObject(String line) {
        System.out.println("%" + line);
        // Add your logic for processing non-JSON lines here
    }
}

