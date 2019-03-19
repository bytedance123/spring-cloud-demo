#FROM openjdk:8-jdk-alpine
FROM java:8
VOLUME /tmp
# EXPOSE暴露端口，可以被其他container link到，但是需要外部环境访问到还需要通过-p指定
EXPOSE 8800
MAINTAINER chizongyang@mininglamp.com
CMD ["java", "-version"]
ADD service-demo-1.0.0.jar app.jar
# -c清除启动前的数据，否则会作为jvm参数而报错
ENTRYPOINT ["java","-Xmx1g", "-Xms512m","-jar","/app.jar", "--spring.profiles.active=prod" ,"-c"]