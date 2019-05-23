#!/usr/bin/env bash

docker rm -f postgres
docker run -d --name postgres -e POSTGRES_PASSWORD=helden -e POSTGRES_USER=helden -p 5432:5432 postgres:9.5