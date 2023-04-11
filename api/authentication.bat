@echo off
curl -X POST -d "{\"email\":\"test@example.com\",\"password\":\"password\"}" http://localhost:7000/login 

echo:
curl -X POST -d "{\"email\":\"test@example.com\",\"password\":\"password\"}" http://localhost:7000/signup 

echo:
curl -X POST -d "{\"email\":\"test@example.com\",\"password\":\"password\"}" http://localhost:7000/login
