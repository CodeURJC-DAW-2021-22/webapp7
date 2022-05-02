FROM maven as builder
COPY src/ /backend/src
COPY pom.xml /backend
WORKDIR /backend
RUN mvn package -f /backend/pom.xml

FROM openjdk:17
COPY --from=builder /backend/target/*.jar /usr/src/
WORKDIR /usr/src
EXPOSE 8443
CMD ["java","-jar","FoodU-0.0.1-SNAPSHOT.jar"]