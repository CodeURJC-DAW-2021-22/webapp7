#!/bin/bash
#
# BUILD THE DOCKER IMAGE FROM DOCKER HUB BY DOCKER COMPOSE
# PORTHUB  - DAW GROUP 13
# AUTHORS: CRISTIAN DE GRACIA NUERO, JOSÉ JUSTO TENA AGUDO, JOSÉ MANUEAL AGU
# 




echo "Compiling Angular frontend"
cd ../frontend
sudo docker run --rm -v "$PWD":/usr/src/app -w /usr/src/app node:16.0.0 /bin/bash -c "npm install && npm run build --prod --base-href='/new/'"
mkdir ../backend/src/main/resources/static
cd ../backend/src/main/resources/static
rm -rf new
mkdir new
cd new
cp -r ../../../../../../frontend/dist/angular/* .

echo

echo "Compiling Maven project"
cd ../../../../..
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
echo "Maven project compiled"

echo "Building docker image from Dockerfile"
cp target/*.jar ../docker
cd ../docker
docker docker build -f ../docker/Dockerfile . -t registry.heroku.com/codeurjc-daw-webapp07/web
rm *.jar
echo "Image built sucessfully, if you want to run the app use"
echo "$ sudo docker-compose up"
echo "Exiting..."
 
exit 0

