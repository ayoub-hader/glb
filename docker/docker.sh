#!/bin/sh

services=(backend frontend)
for service in "${services[@]}"; do
	serviceRunning=$(sudo docker inspect -f {{.State.Running}} $(sudo docker-compose ps -q "$service"))
	if [ "$serviceRunning" = true ]; then
		echo "Service $service was running, restarting ..."
		sudo docker-compose stop "$service"
	fi
done

sudo docker-compose up --remove-orphans --build -d
echo "Containers UP !"
