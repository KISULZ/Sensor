# syntax=docker/dockerfile:1
FROM maven:3.5-jdk-11 AS builder
COPY ../.git /.git
COPY ../Sensor /Sensor
RUN sed -i "s~TAG_PLACEHOLDER~$(git tag --points-at HEAD)~g" /Sensor/src/main/resources/application.properties
RUN sed -i "s~COMMIT_PLACEHOLDER~$(git log --pretty=format:'%h' -n 1)~g" /Sensor/src/main/resources/application.properties
RUN sed -i "s~BRANCH_PLACEHOLDER~$(git rev-parse --abbrev-ref HEAD)~g" /Sensor/src/main/resources/application.properties
RUN sed -i "s~BUILD_DATE_PLACEHOLDER~$(date '+%d-%m-%y %H:%M')~g" /Sensor/src/main/resources/application.properties
WORKDIR /Sensor/
RUN --mount=type=cache,target=/root/.m2 mvn dependency:purge-local-repository -DmanualInclude=com.agsr.lib:errorcode:1.0.0
RUN --mount=type=cache,target=/root/.m2 mvn dependency:get -Dartifact=com.agsr.lib:errorcode:1.0.0 -DremoteRepositories=gitlab-maven::::https://gitlab.agsr.by/api/v4/projects/24/packages/maven -s settings.xml --update-snapshots
RUN --mount=type=cache,target=/root/.m2 mvn package

FROM openjdk:11.0.16-slim
ENV TZ="Europe/Minsk"
WORKDIR /Sensor/
COPY --from=builder /Sensor/web/target/web.jar ./
COPY --from=builder /Sensor/dockerfiles/egr-gov-by-chain.pem /usr/local/share/ca-certificates/egr-gov-by-chain.pem
RUN keytool -import -noprompt -storepass changeit -alias egr  -keystore $JAVA_HOME/lib/security/cacerts -file /usr/local/share/ca-certificates/egr-gov-by-chain.pem
ENTRYPOINT ["java","-jar","web.jar"]