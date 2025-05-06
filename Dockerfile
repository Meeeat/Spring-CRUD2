FROM maven:3.9.8-sapmachine-21 AS build
LABEL authors="Siberia"

#COPY /target/todo-list-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

WORKDIR /app

COPY pom.xml .

COPY src ./src/

RUN mvn clean package -DskipTests

FROM tomcat:10-jdk21

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

ENV DB_URL=jdbc:mysql://mysql:3306/todo
ENV DB_USER=root
ENV DB_PASSWORD=root

EXPOSE 8080

CMD ["catalina.sh", "run"]