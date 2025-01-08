FROM registry.access.redhat.com/ubi8/openjdk-11-runtime@sha256:577d4f9957da9882ea66a61fd8e5f8b2cf3b131b486de1f0d179cdaebd32d40f

COPY target/test-saaj-1.0.0-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
