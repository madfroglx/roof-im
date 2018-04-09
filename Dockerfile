FROM tomcat:8.0-jre8
MAINTAINER liht
RUN echo 'nameserver 8.8.8.8' >> /etc/resolv.conf
COPY roof-im-webapp/target/roof-im.war /usr/local/tomcat/webapps/
CMD ["echo","'nameserver 8.8.8.8'",">>","/etc/resolv.conf"]