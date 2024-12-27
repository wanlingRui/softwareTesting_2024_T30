# 使用OpenJDK作为基础镜像
FROM openjdk:21-jdk-alpine

# 设置工作目录
WORKDIR /app


COPY target/fuzz.jar app.jar

# 暴露应用运行的端口（根据你的应用配置）
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]