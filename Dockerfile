FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY target/NewsLetterService-1.0-SNAPSHOT.jar  /app/app.jar

# Environment variables for memory tuning
#ENV JAVA_TOOL_OPTIONS="-Xmx358m -Xss512k -XX:MaxMetaspaceSize=96m -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp"
#ENV JAVA_TOOL_OPTIONS="-Xms48m \
#                       -Xmx200m \
#                       -XX:MaxMetaspaceSize=64m \
#                       -XX:MaxRAMPercentage=55.0 \
#                       -Xss256k \
#                       -Djava.security.egd=file:/dev/./urandom"
ENV SERVER_TOMCAT_THREADS_MAX=20
ENV SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE=2
ENV SPRING_MAIN_LAZY_INITIALIZATION=true

EXPOSE 8080
CMD ["sh", "-c", "java -jar /app/app.jar"]
