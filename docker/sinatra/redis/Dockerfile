FROM ubuntu:18.04
MAINTAINER mangmang "zfanping@gmail.com"
ENV REFRESHED_AT 2014-06-01
RUN rm /etc/apt/sources.list
COPY sources.list /etc/apt/

RUN apt-get -yqq update && apt-get -yqq install redis-server redis-tools


EXPOSE 6379

ENTRYPOINT ["/usr/bin/redis-server"]
CMD []

