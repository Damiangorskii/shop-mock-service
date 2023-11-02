FROM openjdk:17-jdk

WORKDIR /app

LABEL maintainer="damian" \
      version="1.0" \
      description="Docker image for the shop-mock-service"

COPY target/shop-mock-service-0.0.1-SNAPSHOT.jar /app/shop-mock-service.jar

EXPOSE 8082

CMD ["java", "-jar", "/app/shop-mock-service.jar"]