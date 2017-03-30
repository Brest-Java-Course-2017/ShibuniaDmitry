Start REST: cd rest-app mvn && jetty:run

Start WEB app: cd js-client && firefox index.html

Try CURL requests like:

curl -v "localhost:8088/teams/"

curl -H "Content-Type: application/json" -X POST -d '{"id":null,"name":"newTeam"}' -v "localhost:8088/team"

curl -H "Content-Type: application/json" -X PUT -d '{"id":1,"name":"updatedTeam"}' -v "localhost:8088/team"

curl -X DELETE localhost:8088/team/id{id}

curl -v "localhost:8088/team/{teamId}/players"

curl -H "Content-Type: application/json" -X POST -d '{"id":null,"teamId":1,"name":"Jack1","surname":"Riche1r","acceptanceDate":"2014-10-10"}' -v "localhost:8088/team/player"

curl -H "Content-Type: application/json" -X PUT -d '{"id":1,"teamId":1,"name":"Jack1","surname":"Riche1r","acceptanceDate":"2014-10-10"}' -v "localhost:8088/team/player"

curl -X DELETE localhost:8088/team/player/{id}