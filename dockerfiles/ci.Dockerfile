FROM openjdk:11.0.16-slim
WORKDIR /app/
COPY ./web.jar ./
COPY ./egr-gov-by-chain.pem /usr/local/share/ca-certificates/egr-gov-by-chain.pem
RUN keytool -import -noprompt -storepass changeit -alias egr  -keystore $JAVA_HOME/lib/security/cacerts -file /usr/local/share/ca-certificates/egr-gov-by-chain.pem
ENTRYPOINT ["java","-jar","web.jar"]