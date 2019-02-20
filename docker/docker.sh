#!/bin/sh

sudo docker build -t ldapgroup .
sudo docker run -d -p 8000:8000 ldapgroup
