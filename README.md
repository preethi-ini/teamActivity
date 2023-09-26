# Dine Decide
**To begin:**
1. Clone the code
2. Open command prompt and **change directory to the team folder.**

# To Build Backend and build docker images
4. execute mvn clean package This will build the application to a jar file.
5. execute  "**docker build -t teamactivity .**"

# To Create Docker image of front end application
8. navigate to the folder named react-frontend
9. execute "**docker build -t react-frontend .**"

# Starting the containers
10. change directory to /teamactivity.
11. execute command "**docker-compose up -d**"

# Testing
12. Open broswer and go to url "**http://localhost:3000/**" and start testing the application

