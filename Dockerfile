# basic image
FROM openjdk:8-jre
# mount directory
VOLUME /home/app
# make directory
RUN mkdir -p /home/app
# set work directory
WORKDIR /home/app
# copy jar
COPY ruoyi-admin.jar /home/app/ruoyi-admin.jar
# run service
ENTRYPOINT ["java", "-jar", "-Duser.timezone=America/Vancouver", "/home/app/ruoyi-admin.jar"]

