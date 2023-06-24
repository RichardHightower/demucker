# DE-MUCKER

Let's say you want to look at log files coming from kubernetes kubectl. 
They come out in JSONL format and some just come out with no format.
Some have log levels and some do not. 

How do you handle this?

You de-muck your log files. 

This was designed to either take a command line argument or use it with a pipe with standard input.

# Goals

Demuck JSONL log format from kubectl.

# License

Apache 2.

# Status

Example program, probably will never be released. 

### Maven


Here are instructions on how to include your project with Maven.

```xml
<!-- See for latest version https://mvnrepository.com/artifact/cloudurable/jparse -->
<dependency>
    <groupId>com.cloudurable</groupId>
    <artifactId>demucker</artifactId>
    <version>1.0.0</version>
</dependency>
```

Example program, probably will never be released. Probably will never be in maven. 

### Gradle

Here are instructions on how to include your project with Gradle.

```java
//See for latest version  https://mvnrepository.com/artifact/cloudurable/jai
implementation group: 'com.cloudurable', name: demucker, version: '1.0.0'
```

Example program, probably will never be released. Probably will never be in maven.

```rgb(173, 216, 230) 
💡 De-mucker has not been released yet and is probably just an example, so it is not yet in any public maven repos and likely won't be.

```

# What is Demucker?

A utility for de-mucking logs from kubectl in JSONL format. 

## Using Demucker

```
./gradlew clean installDist

# Use with a file
 ./build/install/demucker/bin/demucker test.txt
 
 # Use with an input stream 
 cat test.txt | ./build/install/demucker/bin/demucker
```

This hard to read output. 

```shell 

% kubectl logs -f job/testRun13
secrets-template: processing /jobs/certs.hcl
secrets-template: └ templating file /app/cacerts.jks
env command: "java -XX:MaxRAMPercentage=50 -XX:InitialRAMPercentage=50 -jar app.jar csvBatchLoader config.yaml"
2023/06/22 21:03:33.812656 looking at secrets uat-platform/shared/kms/config
2023/06/22 21:03:33.812668 looking at secrets uat-platform/shared/kms/secrets
Picked up _JAVA_OPTIONS: -XX:MaxRAMPercentage=50 -XX:InitialRAMPercentage=50
WARNING: An illegal reflective access operation has occurred
{"level":"ERROR","logger":"com.abc.corp.projectx.job.IDResolutionIdResolverJob","thread":"main","message":"IDResolution IdResolver was unable to find a matching id id due to an ERROR","timestamp":"2023-06-22T21:03:55.388+0000"}
{"level":"INFO","logger":"com.abc.corp.projectx.job.IDResolutionIdResolverJob","thread":"main","message":"Unable to call id service for idmatch sample6+abc@example.com 5105551212","timestamp":"2023-06-22T21:03:55.401+0000"}
{"exception":"java.lang.IllegalStateException: 400 {\n \"idmatch\" : \"646CDF475C9B46E49BC81674ADF7509C\",\n \"idmatch\" : \"c51b53ce-11fb-11ee-be56-0242ac120002\",\n \"message\" : \"Error: Attempt to de-reference a null objectStack: Class.ServiceStub_UpdateIdMeta.reidmatchMember: line 127, column 1\\nClass.REST_UpdateMetaForID.updateMeta: line 80, column 1\"\n}\n\tat com.abc.corp.projectx.service.impl.IdMetaServiceImpl.updateSalesForceGateway(IdMetaServiceImpl.java:78)\n\tat com.abc.corp.projectx.service.impl.IdMetaServiceImpl.reidmatch(IdMetaServiceImpl.java:39)\n\tat com.abc.corp.projectx.job.IDResolutionIdResolverJob.checkIfGoodForMetaAdjustment(IDResolutionIdResolverJob.java:124)\n\tat com.abc.corp.projectx.job.IDResolutionIdResolverJob.lambda$processEvent$0(IDResolutionIdResolverJob.java:82)\n\tat java.base/java.util.Optional.ifPresent(Unknown Source)\n\tat com.abc.corp.projectx.job.IDResolutionIdResolverJob.processEvent(IDResolutionIdResolverJob.java:82)\n\tat com.abc.corp.projectx.job.IDResolutionIdResolverJob.run(IDResolutionIdResolverJob.java:51)\n\tat com.abc.corp.projectx.dw.command.IDResolutionLoaderCommand.run(IDResolutionLoaderCommand.java:55)\n\tat com.abc.corp.projectx.dw.command.IDResolutionLoaderCommand.run(IDResolutionLoaderCommand.java:22)\n\tat io.config.cli.ConfiguredCommand.run(ConfiguredCommand.java:87)\n\tat io.config.cli.Cli.run(Cli.java:78)\n\tat io.config.Application.run(Application.java:93)\n\tat com.abc.corp.projectx.dw.application.IDResolutionApplication.main(IDResolutionApplication.java:25)\n","level":"ERROR","logger":"com.abc.corp.projectx.service.impl.IdMetaServiceImpl","thread":"main","message":"Unable to update ID Meta Service with new idmatch for idmatch 646CDF475C9B46E49BC81674ADF7509C c51b53ce-11fb-11ee-be56-0242ac120002 (400 {\n \"idmatch\" : \"646CDF475C9B46E49BC81674ADF7509C\",\n \"idmatch\" : \"c51b53ce-11fb-11ee-be56-0242ac120002\",\n \"message\" : \"Error: Attempt to de-reference a null objectStack: Class.ServiceStub_UpdateIdMeta.reidmatchMember: line 127, column 1\\nClass.REST_UpdateMetaForID.updateMeta: line 80, column 1\"\n})","timestamp":"2023-06-22T21:03:57.095+0000"}
{"level":"ERROR","logger":"com.abc.corp.projectx.job.IDResolutionIdResolverJob","thread":"main","message":"IDResolution IdResolver was unable to find a matching id id due to an ERROR","timestamp":"2023-06-22T21:03:55.406+0000"}
{"level":"INFO","logger":"com.abc.corp.projectx.job.IDResolutionIdResolverJob","thread":"main","message":"IDResolution IdResolver determined that a idmatch was Good.","timestamp":"2023-06-22T21:03:56.924+0000"}

```

Becomes this slightly more readable output. 

```shell 
kubectl logs -f job/testRun13 | ./build/install/demucker/bin/demucker

%secrets-template: processing /jobs/certs.hcl
%secrets-template: └ templating file /app/cacerts.jks
%env command: "java -XX:MaxRAMPercentage=50 -XX:InitialRAMPercentage=50 -jar app.jar csvBatchLoader config.yaml"
%2023/06/22 21:03:33.812656 looking at secrets uat-platform/shared/kms/config
%2023/06/22 21:03:33.812668 looking at secrets uat-platform/shared/kms/secrets
%Picked up _JAVA_OPTIONS: -XX:MaxRAMPercentage=50 -XX:InitialRAMPercentage=50
%WARNING: An illegal reflective access operation has occurred
ERROR           2023-06-22T21:03:55.388
com.abc.corp.projectx.job.IDResolutionIdResolverJob
        IDResolution IdResolver was unable to find a matching id id due to an ERROR

INFO            2023-06-22T21:03:55.401
com.abc.corp.projectx.job.IDResolutionIdResolverJob
        Unable to call id service for idmatch sample6+abc@example.com 5105551212

FATAL           EXCEPTION
java.lang.IllegalStateException: 400 {
 "idmatch" : "646CDF475C9B46E49BC81674ADF7509C",
 "idmatch" : "c51b53ce-11fb-11ee-be56-0242ac120002",
 "message" : "Error: Attempt to de-reference a null objectStack: Class.ServiceStub_UpdateIdMeta.reidmatchMember: line 127, column 1\nClass.REST_UpdateMetaForID.updateMeta: line 80, column 1"
}
        at com.abc.corp.projectx.service.impl.IdMetaServiceImpl.updateSalesForceGateway(IdMetaServiceImpl.java:78)
        at com.abc.corp.projectx.service.impl.IdMetaServiceImpl.reidmatch(IdMetaServiceImpl.java:39)
        at com.abc.corp.projectx.job.IDResolutionIdResolverJob.checkIfGoodForMetaAdjustment(IDResolutionIdResolverJob.java:124)
        at com.abc.corp.projectx.job.IDResolutionIdResolverJob.lambda$processEvent$0(IDResolutionIdResolverJob.java:82)
        at java.base/java.util.Optional.ifPresent(Unknown Source)
        at com.abc.corp.projectx.job.IDResolutionIdResolverJob.processEvent(IDResolutionIdResolverJob.java:82)
        at com.abc.corp.projectx.job.IDResolutionIdResolverJob.run(IDResolutionIdResolverJob.java:51)
        at com.abc.corp.projectx.dw.command.IDResolutionLoaderCommand.run(IDResolutionLoaderCommand.java:55)
        at com.abc.corp.projectx.dw.command.IDResolutionLoaderCommand.run(IDResolutionLoaderCommand.java:22)
        at io.config.cli.ConfiguredCommand.run(ConfiguredCommand.java:87)
        at io.config.cli.Cli.run(Cli.java:78)
        at io.config.Application.run(Application.java:93)
        at com.abc.corp.projectx.dw.application.IDResolutionApplication.main(IDResolutionApplication.java:25)

ERROR           2023-06-22T21:03:55.406
com.abc.corp.projectx.job.IDResolutionIdResolverJob
        IDResolution IdResolver was unable to find a matching id id due to an ERROR

INFO            2023-06-22T21:03:56.924
com.abc.corp.projectx.job.IDResolutionIdResolverJob
        IDResolution IdResolver determined that a idmatch was Good.
```

