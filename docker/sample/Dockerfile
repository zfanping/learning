FROM ubuntu:18.04
MAINTAINER frank "zfanping@gmail.com"
ENV REFRESHED_AT 2014-06-01
RUN rm /etc/apt/sources.list
COPY sources.list /etc/apt/
RUN apt-get -yqq update && apt-get -yqq install nginx
RUN mkdir -p /var/www/html/website
ADD https://raw.githubusercontent.com/jamtur01/dockerbook-code/master/code/5/sample/nginx/global.conf /etc/ngigx/conf.d/
ADD https://raw.githubusercontent.com/jamtur01/dockerbook-code/master/code/5/sample/nginx/nginx.conf /etc/ngigx/ngigx.conf
EXPOSE 80