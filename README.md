# RandomSudoku

This program returns a solved sudoku puzzle everything we invoke the rest endpoint

# Instructions to run the code

>>Go to the main folder and run the below command

mvn spring-boot:run

Access URL:
http://localhost:8080/sudoku/board


To test the application: mvn test




>>To run the UI Client run the below command at the main folder

ng serve

The UI is running at :

http://localhost:4200

The nginx configuration file is added to the project


>> To create docker image:-

At the randomsudoku webservice location , run the below command:

>docker build -f Dockerfile -t sudoku-ws:level-4 .

To run:

>docker run –p 8080:8080 sudoku-ws:level-4

Note:- I have used docker tool-box , the comunity version on windows, docker does not map the localhost, it uses the default IP

> curl http://192.168.99.100:8080/sudoku/board



