from openjdk:8u181-jdk-slim-stretch

RUN apt-get update
RUN apt-get install -y curl unzip
RUN mkdir -p /temp
WORKDIR /temp
RUN curl -o cli.zip https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/2.0.5.RELEASE/spring-boot-cli-2.0.5.RELEASE-bin.zip
RUN unzip -d cli cli.zip

ADD app.groovy app.groovy

CMD cli/spring-2.0.5.RELEASE/bin/spring run app.groovy