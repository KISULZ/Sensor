#!/bin/bash

#!/bin/bash

docker compose stop

if [[ -d "./data/" ]]; then
    sudo rm -Rf ./data/
    mkdir ./data/
else
    mkdir ./data/
fi
psql -h localhost -p 5432 -U postgres -d postgres

docker compose rm --force sensor-api
docker compose build --no-cache sensor-api
docker compose up
