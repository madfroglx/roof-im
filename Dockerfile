FROM tomcat:8.0-jre8
MAINTAINER liht
COPY roof-im-webapp/target/roof-im.war /usr/local/tomcat/webapps/
