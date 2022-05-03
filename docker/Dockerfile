FROM maven as builder
COPY src/ /backend/src
COPY pom.xml /backend
WORKDIR /backend
RUN mvn package -f /backend/pom.xml -DskipTests=true

FROM openjdk:17
COPY --from=builder /backend/target/*.jar /usr/src/
WORKDIR /usr/src
EXPOSE 8443
CMD ["java","-jar","webapp7urjc.jar"]