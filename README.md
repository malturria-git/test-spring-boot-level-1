# test-spring-boot-level-1



### - Dado que Todas las URLs del front apuntan a localhost:8080, se debe asegurar que java escuche en dicho puerto. Se deja el archivo application.yml configurado. Dicho archivo apunta a una db mysql "jdbc:mysql://localhost:3306/coop.tecso.examen". Se deja archivo para configurar la db en test-spring-boot-level-1/database

### - Se implementó autenticación por JWT. Al registrarse, se podrá loguear. Al loguearse, recibira el token para interactuar con la API. Se puede desactivar, editando el archivo "src\main\java\coop\tecso\examen\security\SecurityConfig.java". 

### - Todas las validaciones fueron realizadas en el backend para que sea posible probar desde curl. El back necesita que el json llegue completo, es decir con tudas las keys. Los values pueden ser nulls y seran validados.

### Utilice heroku para subir el front, apuntando a localhost:8080 https://test-spring-boot-level-1-altur.herokuapp.com/MenuPersona

### curl requests con token. Al momento de probar la app, deberá modificar ese dato con el token que reciba al hacer login.

# **Autentización**

## Registro
#### curl -L -X POST "http://localhost:8080/api/auth/registro" -H "Content-Type: application/json" --data-raw "{\"username\":\"HHH55555\",\"password\":\"HHH123123123\"}"
#### Ejemplo respuesta ok: {"OK": " Registrado. Puede loguear. "}

#### curl -L -X POST "http://localhost:8080/api/auth/registro" -H "Content-Type: application/json" --data-raw "{\"username\":\"\",\"password\":\"HHH123123123\"}"
#### Ejemplo validacion: {"user_null": " debe ingresar el usuario "}

## Login
#### curl -L -X POST "http://localhost:8080/api/auth/login" -H "Content-Type: application/x-www-form-urlencoded" --data-urlencode "user=eee" --data-urlencode "password=eee"
#### Ejemplo ok: {"token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzYwMjcsImV4cCI6MTYzMDkzOTYyN30.AaoABnQ0GF3fmh4imNFV6Hkghj0GY6d1PPYqULtN1OY"}

#### curl -L -X POST "http://localhost:8080/api/auth/login" -H "Content-Type: application/x-www-form-urlencoded" --data-urlencode "user=eee" --data-urlencode "password=eee"
#### Ejemplo validacion : {"user_inexistente": " ese usuario NO existe "}

# **Ejercicio 1**

## Buscar empleado
#### curl -L -X GET "http://localhost:8080/api/personas/buscarPorRUT?rut=222" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4"
#### Ejemplo: {"rut": "222","razonSocial": "coca cola","tipo": "Juridica","anioFundacion": "1922"}

## Buscar todas las personas
#### curl -L -X GET "http://localhost:8080/api/personas/buscarTodos" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4"

## Alta personas fisica
#### curl -L -X POST "http://localhost:8080/api/personas/guardarPersona" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4" -H "Content-Type: application/json" --data-raw "{\"rut\":\"asasddfgqwe\",\"tipo\":\"Fisica\",\"nombre\":\"1233\",\"apellido\":\"qweq\",\"cuentaCorriente\":\"qweqwe\"}"

## Alta persona Juridica
#### curl -L -X POST "http://localhost:8080/api/personas/guardarPersona" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4" -H "Content-Type: application/json" --data-raw "{\"rut\":\"20325457588\",\"tipo\":\"Juridica\",\"razonSocial\":\"Chevrolet\",\"anioFundacion\":\"1850\"}"

## Borrar persona
#### curl -L -X DELETE "http://localhost:8080/api/personas/borrarPorRUT" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4" -H "Content-Type: application/json"  --data-raw "{\"rut\":\"20325457588\"}"

## Actualizar persona
#### curl -L -X PUT "http://localhost:8080/api/personas/modificarPersona" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4" -H "Content-Type: application/json" --data-raw "{\"rut\":\"20325457588\",\"tipo\":\"Juridica\",\"razonSocial\":\"Chevrolee12ee\",\"anioFundacion\":\"1850\"}"

# **Ejercicio 2**

## Buscar todas las cuentas corrientes.
#### curl -L -X GET "http://localhost:8080/api/cuentascorrientes/buscarTodasCuentas" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4"

## Buscar todos los movimientos
#### curl -L -X GET "http://localhost:8080/api/movimientos/buscarMovimientoPorCuenta" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4"

## Buscar todos los movimientos de una cuenta en particular.
#### curl -L -X GET "http://localhost:8080/api/movimientos/buscarMovimientoPorCuenta?cuenta=123" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4"

## Alta de cuenta corriente
#### curl -L -X POST "http://localhost:8080/api/cuentascorrientes/guardarCuentaCorriente" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4" -H "Content-Type: application/json" --data-raw "{\"cuenta\":\"eeee1\",\"moneda\":\"DOLAR\",\"saldo\":\"1234\"}"

## Borrar cuenta
#### curl -L -X DELETE "http://localhost:8080/api/cuentascorrientes/borrarCuentaCorriente" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4" -H "Content-Type: application/json" --data-raw "{\"cuenta\":\"eeee1\"}"

## Alta de movimientos
#### curl -L -X POST "http://localhost:8080/api/movimientos/guardarMovimiento" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjb29wLnRlY3NvLmV4YW1lbiIsInN1YiI6ImVlZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MzA5MzU5NDAsImV4cCI6MTYzMDkzOTU0MH0.qRo8RoAa7uswdugwwNVeH4Ri2jR-e6Nsz7TZziOWwT4" --data-raw "{\"cuenta\":\"123\",\"fecha\":\"2021-09-06T10:53\",\"tipo\":\"CREDITO\",\"descripcion\":\"descripcion\",\"importe\":\"11.10\"}"

