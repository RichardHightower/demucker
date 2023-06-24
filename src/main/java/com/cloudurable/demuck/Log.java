package com.cloudurable.demuck;

import java.time.LocalDateTime;

/**
 * The Log class represents a log entry with various fields such as level, logger, thread, message, and timestamp.
 */
public class Log {
    private final String level;
    private final String logger;
    private final String thread;
    private final String message;
    private final LocalDateTime timestamp;

    /**
     * Constructs a Log object with the specified fields.
     *
     * @param level     the log level
     * @param logger    the logger name
     * @param thread    the thread name
     * @param message   the log message
     * @param timestamp the timestamp of the log entry
     */
    public Log(String level, String logger, String thread, String message, LocalDateTime timestamp) {
        this.level = level;
        this.logger = logger;
        this.thread = thread;
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * Returns the log level.
     *
     * @return the log level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Returns the logger name.
     *
     * @return the logger name
     */
    public String getLogger() {
        return logger;
    }

    /**
     * Returns the thread name.
     *
     * @return the thread name
     */
    public String getThread() {
        return thread;
    }

    /**
     * Returns the log message.
     *
     * @return the log message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the timestamp of the log entry.
     *
     * @return the timestamp of the log entry
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of the Log object.
     *
     * @return a string representation of the Log object
     */
    @Override
    public String toString() {
        return "Log{" +
                "level='" + level + '\'' +
                ", logger='" + logger + '\'' +
                ", thread='" + thread + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * Creates a new instance of the Log.Builder for building Log objects.
     *
     * @return a new instance of the Log.Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Builder class for constructing Log objects.
     */
    public static class Builder {
        private String level;
        private String logger;
        private String thread;
        private String message;
        private LocalDateTime timestamp;

        // Private constructor to enforce using the builder through Log.builder() method
        private Builder() {
        }

        /**
         * Sets the log level.
         *
         * @param level the log level
         * @return the builder instance
         */
        public Builder level(String level) {
            this.level = level;
            return this;
        }

        /**
         * Sets the logger name.
         *
         * @param logger the logger name
         * @return the builder instance
         */
        public Builder logger(String logger) {
            this.logger = logger;
            return this;
        }

        /**
         * Sets the thread name.
         *
         * @param thread the thread name
         * @return the builder instance
         */
        public Builder thread(String thread) {
            this.thread = thread;
            return this;
        }

        /**
         * Sets the log message.
         *
         * @param message the log message
         * @return the builder instance
         */
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * Sets the timestamp of the log entry.
         *
         * @param timestamp the timestamp of the log entry
         * @return the builder instance
         */
        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        /**
         * Builds a Log object with the configured values.
         *
         * @return a new Log object
         */
        public Log build() {
            return new Log(level, logger, thread, message, timestamp);
        }
    }


}
