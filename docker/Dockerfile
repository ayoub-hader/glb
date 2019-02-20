FROM openjdk:11.0.2-jre-slim-stretch

VOLUME /tmp

ADD ../ldap_release/ldapgroup*.jar /app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
