sourceviewer-rest applicatie:

angular (copy of sourceviewer)

fileupload
spring
mongodb

startup met tomcat 6

- upload file (no rest, upload servlet)
- rest endpoint 'treestructure'
- rest endpoint 'project'

html url:
http://localhost:8081/sourceviewer-rest/

heroku
======

cd E:\java\STS-workspace\sourceviewer

heroku login

commit push changes to repo

git push heroku master

heroku open 

https://sourceviewer.herokuapp.com/index.html

local opstarten:
- mvn clean install
java -jar target/dependency/jetty-runner.jar --port 3000 target/*.war



