curl --request PUT \
  --url http://localhost:8080/helden/{id} \
  --header 'content-type: application/json' \
  --data @klempner-modifizieren.json
  -v