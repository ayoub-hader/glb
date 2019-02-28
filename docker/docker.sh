#!/bin/sh

webappRunning=$(sudo docker inspect -f {{.State.Running}} $(sudo docker-compose ps -q webapp))

if [ "$webappRunning" = true ];then
	echo "Web app is already running, restarting ..."
	sudo docker-compose stop webapp
fi

sudo docker-compose up -d
echo "Done"
