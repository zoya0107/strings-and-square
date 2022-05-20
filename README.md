# Square and Strings
Spring MVC web application for solving “magic square” and “filter the strings” tasks.
## Description
In this application a user can find the minimal “cost” of converting a square 3*3 of integers into a magic[1] or semi-magic[2] one and filter an array of strings so as to leave only strings which are substrings of the strings in the second array. The application is written using Spring MVC framework and Maven for external dependency managment.
## Database & configuration
- PostgreSQL
- Maven
- Git
## Backend technologies
- Java
- Spring MVC, Spring Boot
## Frontend technologies
- HTML, Thymeleaf
## Database Diagram
![image](https://github.com/zoya0107/zoya0107/blob/main/sas_db.png)
## Usage
1. To choose the task of which type to solve, user should click on “select” combobox
2. To solve the task user needs to insert the values and click “make magic”, “make semi-magic”or “filter”
3. To save the task data to the database user should click “save”
4. To solve the task already saved to the database, user needs to click “load” and then choose the task in the dialog window by clicking “select”. Now the task fields are filled with the chosen values, and the task can be solved as desribed in the second step
5. To export the task as json file, user should click “export”. A file “square.json” or “strings.json” will be created in the “target” folder

[1] a magic square is a matrix of distinct positive integers from 1 to n2 where the sum of any row, column, or diagonal is always equal to the same number

[2] a semimagic square is a square that fails to be a magic square only because one or both of the main diagonal sums
