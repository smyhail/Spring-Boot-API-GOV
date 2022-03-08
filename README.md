# Spring-Boot-API-GOV
## Server Rest Api 

Backend web app.
## Features

- Verification of the company's account number in the Polish tax office using GOV.PL
- Save the result in the database
- Generating the list
- Single verification preview
- Single PDF download
- Multiple PDF download (zip)


## Tech
- Java
- Spring Boot
- Mysql
- Thymeleaft

## What does (the most importent)
1. After sending bank account numbers, the application sends a request to an external API, the answer is saved in the MySQL database
2. Create and download a single PDF.
3. Create multiple PDFs, package to ZIP and download all
