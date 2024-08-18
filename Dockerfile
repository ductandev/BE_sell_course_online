# Sử dụng hình ảnh base từ OpenJDK
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file JAR vào trong hình ảnh
COPY target/myapp.jar myapp.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "myapp.jar"]
