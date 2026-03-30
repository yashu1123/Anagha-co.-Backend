FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Find actual jar name and run it
CMD ["sh", "-c", "java -jar target/*.jar"]