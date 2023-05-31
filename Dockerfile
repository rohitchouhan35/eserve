FROM openjdk:11

COPY target/Spring-Email-Demo-0.0.1-SNAPSHOT.jar SpringBootEmail.jar

ENTRYPOINT ["java", "-jar", "/SpringBootEmail.jar"]