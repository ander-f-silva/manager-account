FROM ghcr.io/graalvm/graalvm-ce:latest

ENV APP_HOME "/opt/app"

ADD ./target/manager-account-0.1.jar $APP_HOME/manager-account-0.1.jar

CMD	java -Xmx512M -Xms256M -jar /opt/app/manager-account-0.1.jar