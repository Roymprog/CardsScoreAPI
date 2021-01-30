FROM gcr.io/distroless/java:11

ARG JAR

COPY appuser /etc/passwd
COPY ${JAR} /home/app

USER appuser

CMD [
  "-Dloader.path=/home/app/resources",
# Required due to bug in JDK11 TLSv1.3(https://bugs.openjdk.java.net/browse/JDK-8236039)
# Java distroless version requires upgrade
  "-Djdk.tls.client.protocols=TLSv1.2",
  "/home/app/app.jar"
  ]
