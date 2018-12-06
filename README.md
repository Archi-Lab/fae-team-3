
docker-compose up

docker-compose ps

docker-compose scale ms-n=4

docker-compose start stop



docker image ls
docker container ls --all

docker build -t my_image .

docker save -o <path for generated tar file> <image name>
docker save image > /home/save.tar
	
docker load -i <path to image tar file>
