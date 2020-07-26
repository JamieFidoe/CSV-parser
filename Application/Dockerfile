FROM openjdk:13-alpine
COPY CSV_Parser-0.0.1-SNAPSHOT.jar /usr/app/
COPY data.csv /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "CSV_Parser-0.0.1-SNAPSHOT.jar"]
