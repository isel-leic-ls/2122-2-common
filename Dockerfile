FROM openjdk:17
COPY ./build/libs /usr/app
COPY ./static-content /usr/app/static-content
WORKDIR /usr/app
CMD ["java", "-jar", "2122-2-common.jar"]
