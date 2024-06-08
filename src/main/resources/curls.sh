 curl -s -X POST localhost:8080/api/v1/records \
  -H "Content-Type: application/json" \
  -d '{"albumTitle": "foo", "artist": "bar", "year": "1999"}'
