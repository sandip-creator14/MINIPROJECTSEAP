# MiniProjectDevops
End-to-end DevOps pipeline for a Java shopping web app (Servlets + JSP, Maven WAR, JUnit, Selenium, Docker, Jenkins).

## Features
Home, product list with categories, cart (add/remove/+/-), auto total in ₹, register, login, profile,
checkout, orders, logout. Data is in memory (demo). Demo login: `demo@gmail.com` / `demo123`.

## Build & test
```bash
mvn clean package            # unit tests + target/shopping-app.war
mvn test -Pselenium          # Selenium tests (app must be running, Chrome installed)
```

## Run with Docker
```bash
docker build -t shopping-app .
docker run -p 8080:8080 shopping-app
# open http://localhost:8080/shopping-app/
```

## Pipeline (Jenkinsfile)
Checkout -> Build & unit tests -> Archive WAR -> Docker build -> Deploy -> Selenium tests.

## Next improvement
MySQL + JDBC so users, orders and carts survive restarts.
