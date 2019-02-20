FROM openjdk:11.0.2-jre-slim-stretch

VOLUME /tmp

ADD ./target/ldapgroup*.jar /app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
