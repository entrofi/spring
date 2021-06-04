FROM mysql:5.7
LABEL description="My Custom Mysql Docker Image"

# Add a database
ENV MYSQL_DATABASE spring_data_jpa_mysql_db

#Check out docker entry point for further configuration :
# https://github.com/docker-library/mysql
COPY ./scripts/ /docker-entrypoint-initdb.d/