# Square and Strings
Spring MVC web application for solving “magic square” and “filter the strings” tasks.
## Description
In this application a user can find the minimal “cost” of converting a square 3*3 of integers into a magic[1] or semi-magic[2] one and filter an array of strings so as to leave only strings which are substrings of the strings in the second array. The application is written using Spring MVC framework and Maven for external dependency management.
## Link to the app on Heroku
[Strings and Square Solver](https://strings-and-square.herokuapp.com/home)
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
1. To choose the task of which type to solve, user should click on “Select” combobox
2. To solve the task user needs to insert the values and click “Make magic”, “Make semi-magic” or “Filter”
3. To save the task data to the database user should click “Save”
4. To solve the task already saved to the database, user needs to click “Load” and then choose the task in the dialog window by clicking “Select”. Now the task fields are filled with the chosen values, and the task can be solved as described in the second step
5. To export the task as json file, user should click “export”

[1] a magic square is a matrix of distinct positive integers from 1 to n^2 where the sum of any row, column, or diagonal is always equal to the same number

[2] a semi-magic square is a square that fails to be  magic  only because of both main diagonals
