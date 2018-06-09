FROM java:8 

#install Spring Boot artifact
VOLUME /tmp
ADD target/stacklineapi-0.0.1-SNAPSHOT.jar stacklineapi.jar
RUN bash -c 'touch /stacklineapi.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /stacklineapi.jar"]
