#!/bin/bash
#docker exec cons-be-course bash -c 'git pull && exit' && docker restart cons-be-course

# Cập nhật mã nguồn từ GitHub
docker exec cons-be-course bash -c "git pull"

# Sao chép file JAR mới vào container
docker cp target/course.jar cons-be-course:/app/course.jar

# Khởi động lại container để sử dụng file JAR mới
docker restart cons-be-course
