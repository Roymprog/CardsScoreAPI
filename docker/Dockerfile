FROM gcr.io/distroless/java:11

ARG JAR

COPY appuser /etc/passwd
COPY ${JAR} /home/app

USER appuser

CMD ["-Dloader.path=/home/app/resources", "/home/app/app.jar"]
