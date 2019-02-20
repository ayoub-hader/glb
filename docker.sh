#!/bin/sh

sudo docker build -t ldapgroup .
sudo docker run -d -p 8081:8081 ldapgroup
