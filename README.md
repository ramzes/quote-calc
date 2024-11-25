# Overview

Application is written with purpose to complete exercise assessment as a requirement for Algoteque job offer.
In task specification there isn't description of what type of application it should be and because of that I've decided
to make it a simple Rest Web Application to remind myself how to do small applications.

This is Java SpringBoot application, written in two hours and test myself how much I can produce in that time.

# Guidelines

1. Code built with Java Corretto version 21
2. Maven bundled version 3.9.5
3. Lombok plugin should be installed for correct display of code
4. Building by executing maven goals clean verify install
5. Run by plugin or mvn spring-boot:run
6. Application port is set to 2137
7. Two properties to play with json vendor files are available vendor.file-path & vendor.file-name (didn't test them yet
   without default values)

# What is missing

- Definitely more integration tests
- Docker plugin to create image to shit is easily
- Swagger/openApi

# Api

GET Endpoint to check provider file is /api/provider/list
GET Endpoint to calculate quote is /api/quote, json body is expected ex. :
{"topics": {
"reading": 20,
"math": 50,
"science": 30,
"history": 15,
"art": 10
}
}