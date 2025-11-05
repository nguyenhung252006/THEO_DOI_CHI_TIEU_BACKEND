# ==============================
# Step 1: Build stage với Maven 4 + JDK 21
# ==============================
FROM maven:4.0.0-rc-4-amazoncorretto-21-debian AS build

WORKDIR /app

# Copy pom.xml trước để cache dependencies
COPY pom.xml .

# Copy toàn bộ source code
COPY src ./src

# Build project, bỏ qua test để nhanh
RUN mvn clean package -DskipTests

# ==============================
# Step 2: Run stage với JRE nhẹ
# ==============================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy file JAR từ stage build
COPY --from=build /app/target/theo_doi_chi_tieu_backend-0.0.1-SNAPSHOT.jar ./app.jar

# Cổng mà ứng dụng Java chạy
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
