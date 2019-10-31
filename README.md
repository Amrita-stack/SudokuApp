# SudokuApp

This program returns a solved sudoku puzzle everytime we invoke the rest endpoint.

In addition, there is an option to Reload the Sudoku solution by pressing the 'Reload' button.

# Instructions to run the code

The following are the pre-requisites for building and running the code

Download and install Java
https://www.java.com/en/download/

Maven set up
https://maven.apache.org/install.html

Git set up
https://git-scm.com/downloads

npm set up
https://www.npmjs.com/get-npm

nginx set up
https://docs.nginx.com/nginx/admin-guide/installing-nginx/installing-nginx-open-source/

Docker set up
https://docs.docker.com/docker-for-windows/install/

# Instructions to build and run the java app

Once you download the source code from github, go inside randomsudoku folder and run the following command:
```
mvn clean install

```
This command will also run the built-in tests. You can also run the tests alone by executing the command:

```
mvn test

```
Once the build is successful, execute the below command to run the java application:
```
mvn spring-boot:run

```
The application will be accessible at:

```
localhost:8080:/sudoku/board

```
# Instructions to build and run the ui client
The UI client is an angular based app. The main code is present inside the sudokuclient folder.

To run the UI Client run the below command from inside sudokuclient folder

```
ng serve

```
It is also possible to create the distribution using the npm command:
```
ng build --prod
```

The UI will be running at : http://localhost:4200

# nginx configuration
The nginx configuration file is added to the project. Basically the configuration has been modified by setting the property:

```
proxy_pass http://localhost:4200/;

```
The proxy_pass is now pointing to the URL on which the UI is loading. This configuration can be modified as required.
You can also specify the property:
```
server_name  localhost;

```
This property will eventually be the URL which we will hit to get the UI loaded.

# Docker Image (Instructions to build and run)
The docker file is present inside the randomsudoku folder and we can run the following command:

```
docker build -f Dockerfile -t sudoku-ws:level-4 .

```
You can see the created docker image by using the below command:

```
docker image ls

```
To run the image, use the below command:
```
docker run –p 8080:8080 sudoku-ws:level-4

```

Note:- I have used docker tool-box , the comunity version on windows, docker does not map the localhost, it uses the default IP

curl http://192.168.99.100:8080/sudoku/board



