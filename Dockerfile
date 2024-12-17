FROM maven:3.8.6-openjdk-11-slim

WORKDIR /app

# Копируем исходный код
COPY ./carShowroomManagementSystem .

# Собираем проект и создаем jar файл
RUN mvn clean package -DskipTests && \
    mv target/$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout).jar app.jar

# Создаем непривилегированного пользователя
ARG UID=10001
RUN adduser --disabled-password --gecos "" --home "/nonexistent" --shell "/sbin/nologin" --no-create-home --uid "${UID}" appuser
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
