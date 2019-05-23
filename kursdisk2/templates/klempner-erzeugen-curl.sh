curl --request POST \
  --url http://localhost:8080/helden \
  --header 'content-type: application/json' \
  --data @klempner-erzeugen.json \
  -v