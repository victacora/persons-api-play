## Listar personas

  curl --request GET \
  --url http://localhost:9000/api/v1/persons
  
## Buscar persona por id 
  curl --request GET \
  --url http://localhost:9000/api/v1/persons/2
  
## Crear persona por id 
  curl --request POST \
  --url http://localhost:9000/api/v1/persons \
  --header 'content-type: application/json' \
  --data '{
 "id":"2",
 "firts_name":"Primero",
 "second_name":"Segundo",
 "last_name":"Apellidos",
 "id_card_number":"1061733040",
 "phone_number":"83636363",
 "email":"correo@prueba.com.co"
}'

## Actualizar persona

curl --request PUT \
  --url http://localhost:9000/api/v1/persons/2 \
  --header 'content-type: application/json' \
  --data '{
 "id":"2",
 "firts_name":"Primero III",
 "second_name":"Segundo IIII",
 "last_name":"Apellidos",
 "id_card_number":"1061733040",
 "phone_number":"83636363",
 "email":"correoIIII@prueba.com.co"
}'

## Eliminar Actualizar persona por id 

curl --request DELETE \
  --url http://localhost:9000/api/v1/persons/2 \
  --header 'content-type: application/json'
