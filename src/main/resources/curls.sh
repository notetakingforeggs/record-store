 curl -s -X POST localhost:8080/api/v1/records \
  -H "Content-Type: application/json" \
  -d '{
           "albumTitle": "ATLiens",
           "artist": "Outcast",
           "genre": "ROCK",
           "releaseYear": 1996,
           "pricePence": 2000
       }'
 curl -s -X POST localhost:8080/api/v1/records \
  -H "Content-Type: application/json" \
  -d '{
           "albumTitle": "Kind Of Blue",
           "artist": "Miles Davis",
           "genre": "HIPHOP",
           "releaseYear": 1956,
           "pricePence": 3000
       }'
 curl -s -X POST localhost:8080/api/v1/records \
  -H "Content-Type: application/json" \
  -d '{
           "albumTitle": "SomeOtherAlbum",
           "artist": "Outcast",
           "genre": "POP",
           "releaseYear": 1998,
           "pricePence": 2000
       }'