FROM openjdk:8
ADD target/pixonic-game-api-2020.5.1.jar /app/pixonic-game-api-2020.5.1.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/pixonic-game-api-2020.5.1.jar"]
