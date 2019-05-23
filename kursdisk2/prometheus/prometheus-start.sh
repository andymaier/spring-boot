docker build -t prometheus .
docker rm -f prometheus
docker run -d -p 9090:9090 --add-host helden:192.168.2.163 --name prometheus prometheus
