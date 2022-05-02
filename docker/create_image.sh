#!/bin/bash

echo "Compiling Angular frontend"
cd ../frontend/
sudo docker run --rm -v "$PWD":/usr/src/app -w /usr/src/app node:16.0.0 /bin/bash -c "npm install && npm run build --prod --base-href='/new/'"
mkdir ../../backend/src/main/resources/public
cd ../../backend/src/main/resources/public
rm -rf new
mkdir new
cd new
cp -r ../../../../../../frontend/spa/dist/angular/* .

echo

echo "Compiling Maven project"
cd ../../../../..
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
echo "Maven project compiled"

echo "Building docker image from Dockerfile"
cp target/*.jar ../docker
cd ../docker
sudo docker build -t foodandu .
rm *.jar
echo "Image built sucessfully, if you want to run the app use"
sudo docker-compose up
echo "Exiting..."
 
exit 0
