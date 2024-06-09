FROM openjdk:17
ADD target/FinnyBuddy-0.0.1-SNAPSHOT.jar finnybuddy.jar
ENTRYPOINT ["java", "-jar", "finnybuddy.jar"]