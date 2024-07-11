#!/bin/bash


sudo docker rm -f backend


sudo docker rmi -f backend


sudo docker build -t backend .


sudo docker run --name backend -d -p 8080:8080 backend