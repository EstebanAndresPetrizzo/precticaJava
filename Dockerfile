FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/precticaJava-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "/app.war"]