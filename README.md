# Small reproducer for saaj-error

This little project should use **java 11** and uses the soap WS exposed from https://www.dataaccess.com/webservicesserver/TextCasing.wso?WSDL .
The code generating for the SOAP client happens at build time.

The main controller [MyController](src/main/java/it/redhat/reproducer/saaj/controller/MyController.java) does, essentially, two things:
 - create a `SAAJMetaFactory` instance in order to discover possibile exceptions 
 - invoke the SOAP ws using the model client generated at build time.

After a successful invocation of the service logs the classloaders recursively. 

Just point to http://localhost:9080/api/test?word=touppercase in order to test the invocation to the remote SOAP server.

## Java application

Build:

```shell
./mvnw clean package
```

Run:

```shell
./mvnw spring-boot:run -Dspring-boot.run.fork=false
```

## Container

Build:

```shell
podman build -t reproducer-saaj .
```

Run:

```shell
podman run -it -p 9080:9080 localhost/reproducer-saaj:latest 
```



## Test

```shell
curl -v http://localhost:9080/api/test?word=touppercase
```