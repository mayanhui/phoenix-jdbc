# Phoenix-jdbc

##Aimed @
Provide the jdbc access example on phoenix.

Make convenient to query on phoenix.

##Link
Phoenix: https://github.com/forcedotcom/phoenix

Maven: http://maven.apache.org/

Jackson Json: http://jackson.codehaus.org/



##Trouble Shouting
1. Can not find phoenix-client from maven repos.
In your phoenix home directory, execute this command:

mvn install:install-file -DgroupId=com.salesforce -DartifactId=phoenix -Dversion=1.2.1 -Dclassifier=client -Dpackaging=jar -Dfile=phoenix-1.2.1-client.jar

