#!/bin/sh

backendRunning=$(sudo docker inspect -f {{.State.Running}} $(sudo docker-compose ps -q backend))
backendRunning=$(sudo docker inspect -f {{.State.Running}} $(sudo docker-compose ps -q frontend))

services=(backend frontend)
for service in "${services[@]}"; do
	serviceRunning=$(sudo docker inspect -f {{.State.Running}} $(sudo docker-compose ps -q "$service"))
	if [ "$serviceRunning" = true ]; then
		echo "Service $service was running, restarting ..."
		sudo docker-compose stop "$service"
	fi
done

sudo docker-compose up -d --remove-orphans
echo "Containers UP !"
