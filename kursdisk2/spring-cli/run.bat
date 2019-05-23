docker rm -f sb2-hello-world
docker build -t sb2-hello-world .
docker run -p 8080:8080 --name sb2-hello-world sb2-hello-world