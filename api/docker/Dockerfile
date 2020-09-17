FROM openjdk:11-jre-slim

ARG appJar

ENV JAVA_OPTS=""

RUN adduser --quiet --gecos "" --disabled-login --shell /bin/sh stub
WORKDIR /home/stub

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown stub:stub entrypoint.sh
USER stub

ADD $appJar web.jar

EXPOSE 8080

ENTRYPOINT ["./entrypoint.sh"]
