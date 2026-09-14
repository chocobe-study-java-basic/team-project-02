FROM amazoncorretto:25

WORKDIR /app

COPY . .

RUN chmod +x gradlew

EXPOSE 8080

CMD ["./gradlew", "bootRun", "--no-daemon"]