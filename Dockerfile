FROM openjdk:11
EXPOSE 8080
ADD target/dog-info-mgmt-0.0.1-SNAPSHOT.jar dog-info-mgmt-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dog-info-mgmt-0.0.1-SNAPSHOT.jar"]