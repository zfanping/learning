FROM ubuntu:18.04
MAINTAINER mangmang "zfanping@gmail.com"
ENV REFRESHED_AT 2014-06-01
RUN rm /etc/apt/sources.list
COPY sources.list /etc/apt/

RUN apt-get -yqq update && apt-get -yqq install ruby ruby-dev build-essential redis-tools

RUN gem install --no-rdoc --no-ri sinatra json redis

RUN mkdir -p /opt/webapp

EXPOSE 4567

CMD ["/opt/webapp/bin/webapp"]

