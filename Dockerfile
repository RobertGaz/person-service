FROM amazoncorretto:17
COPY /build/libs/person-service.jar app.jar
CMD ["java","-jar","app.jar"]