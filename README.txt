
building:
mvn -Pdevelopment,wildfly,postgresql clean package
mvn -Pdevelopment,wildfly,mariadb clean package
mvn -Pdevelopment,tomee,postgresql clean package
mvn -Pdevelopment,tomee,mariadb clean package

