# IMAGEN BASE
FROM ubuntu:focal-20240427

ENV DEBIAN_FRONTEND=noninteractive

# CREADOR DE LA IMAGEN
LABEL Author="EDGAR ALVARADO EDGARJALV@GMAIL.COM"

# ACTUALIZACION DE SISTEMA
RUN apt-get -y update && apt-get -y upgrade

# INSTALACION DE APACHE2 y MYSQL
RUN apt-get install -y apache2 
RUN apt-get install -y apache2-utils
RUN apt-get install -y php
RUN apt-get install -y libapache2-mod-php
RUN apt-get install -y php-mysql
RUN apt-get clean 
RUN rm -rf /var/lib/apt/lists/*
RUN sh -c "echo 'ServerName 127.0.0.1' >> /etc/apache2/apache2.conf"


#EXPONE EL PUERTO 80 PARA CONECTARSE A LA WEB
EXPOSE 80

# DIRECTORIO PARA TRABAJO
WORKDIR /var/www/html/



ENTRYPOINT ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"]
