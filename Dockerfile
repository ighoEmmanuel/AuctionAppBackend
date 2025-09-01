# Step 1: Build the app with Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# copy pom and download deps first (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# now copy source and build
COPY src ./src
RUN mvn clean package -DskipTests


# Step 2: Run the packaged app
FROM eclipse-temurin:21-jdk
WORKDIR /app

# copy only the runnable fat jar (not the original one)
COPY --from=build /app/target/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
