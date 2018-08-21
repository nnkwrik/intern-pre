FROM java:8-alpine

MAINTAINER nnkwrik 16t2133h@shinshu-u.ac.jp

ADD target/*.war app.war

ADD launcher-1.0.1.jar launcher-1.0.1.jar

CMD java -jar launcher-1.0.1.jar --deploy app.war --generate app.jar && java -jar app.jar




