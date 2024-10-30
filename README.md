
# API de Ordenación de Productos
Este proyecto proporciona un endpoint para ordenar productos según las ventas recientes y el stock disponible, con criterios configurables.

## Requisitos
- Java 11 o superior
- Maven

## Ejecución

1. Clona el repositorio.
2. Ejecutar mediante el Bood Dashboard
3. Realiza pruebas con **curl**:
- La llamada a la API se hará al endpoint `/sort-products` y deberá ser mediante el método **POST**.
- Esta llamada requerirá los siguientes parámetros:
	- **salesWeight**: La importancia que se le quiere dar a las ventas, con un valor entre 0 y 1.
	- **stockWeight**: La importancia que se le quiere dar al stock disponible, con un valor entre 0 y 1.
	- **productSales**: Un array de productos vendidos, que incluirá el **id** de cada producto y el número de ventas realizadas, por ejemplo:
```json
"productSales":[
	{"productId": "1", "sales": 50000}
]
```
-  **productStock**: Un array de productos en stock, que incluirá el **id** de cada producto y la cantidad disponible en stock, por ejemplo:
```json
"productStock":[
	{"productId": "1", "stock": 100000}
]
```

### Pruebas con curl:
#### En Linux:
En caso de usar Linux, el comando curl será el siguiente:
		
```bash
curl -X POST 'http://localhost:8080/sort-products' -H 'Content-Type: application/json' -d '{
	"salesWeight": 0.5,
	"stockWeight": 0.5,
	"productSales":[
		{"productId": "1", "sales": 50000},
		{"productId": "2", "sales": 100000},
		{"productId": "3", "sales": 100000},
		{"productId": "4", "sales": 75000}
	],
	"productStock": [
		{"productId": "1", "stock": 100000},
		{"productId": "2", "stock": 400000},
		{"productId": "3", "stock": 200000},
		{"productId": "4", "stock": 300000}
	]
}' 
```
	
#### Windows:
Para ejecutar el comando en PowerShell de Windows, se deben hacer algunas modificaciones:

1. Establecer los headers:
```powershell
$headers = @{"Content-Type" = "application/json"}
```
	
2. Establecer el body:
```powershell
$body = @{
	salesWeight = 0.5
	stockWeight = 0.5
	productSales = @(
		@{productId = "1"; sales = 50000 },
		@{productId = "2"; sales = 100000 },
		@{productId = "3"; sales = 100000 },
		@{productId = "4"; sales = 75000 }
	)
	productStock = @(
		@{productId = "1"; stock = 100000 },
		@{productId = "2"; stock = 400000 },
		@{productId = "3"; stock = 200000 },
		@{productId = "4"; stock = 300000 }
	)
}
```

3. Convertir el body en un json:
```powershell
$jsonBody = $body | ConvertTo-Json
```

4. Finalmente, para probar el metodo se usaría el siguiente comando:
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/sort-products" -Method POST -Headers $headers -Body $jsonBody
```

5. Si deseas cambiar los ejemplos, simplemente crea un nuevo body (o reemplaza el existente) y define qué body convertirás a JSON. Por ejemplo:
```powershell
$jsonBody = $body2 | ConvertTo-Json
```

## Ejemplos
### Ejemplo 1
```
{
	"salesWeight": 0.5,
	"stockWeight": 0.5,
	"productSales": [
		{"productId": "1", "sales": 50000},
		{"productId": "2", "sales": 100000}
	],
	"productStock": [
		{"productId": "1", "stock": 100000},
		{"productId": "2", "stock": 400000}
	]
}
```
Resultado: 2, 4, 3, 1
### Ejemplo 2
```json
{
	"salesWeight": 0.7,
	"stockWeight": 0.3,
	"productSales": [
		{"productId": "1", "sales": 30000},
		{"productId": "2", "sales": 60000},
		{"productId": "3", "sales": 90000},
		{"productId": "4", "sales": 20000}
	],
	"productStock": [
		{"productId": "1", "stock": 150000},
		{"productId": "2", "stock": 250000},
		{"productId": "3", "stock": 350000},
		{"productId": "4", "stock": 50000}
	]
}
```
Resultado: 3, 2, 1, 4

### Ejemplo 3
```json
{
	"salesWeight": 0.4,
	"stockWeight": 0.6,
	"productSales": [
		{"productId": "1", "sales": 80000},
		{"productId": "2", "sales": 120000},
		{"productId": "3", "sales": 40000},
		{"productId": "4", "sales": 100000}
	],
	"productStock": [
		{"productId": "1", "stock": 50000},
		{"productId": "2", "stock": 600000},
		{"productId": "3", "stock": 100000},
		{"productId": "4", "stock": 300000}
	]
}
```
Resultado: 2, 4, 3, 1

### Ejemplo 4
```json
{
	"salesWeight": 0.6,
	"stockWeight": 0.4,
	"productSales": [
		{"productId": "1", "sales": 40000},
		{"productId": "2", "sales": 30000},
		{"productId": "3", "sales": 150000},
		{"productId": "4", "sales": 50000}
	],
	"productStock": [
		{"productId": "1", "stock": 200000},
		{"productId": "2", "stock": 150000},
		{"productId": "3", "stock": 300000},
		{"productId": "4", "stock": 400000}
	]
}
```
Resultado: 3, 4, 1, 2

## Dependencias utilizadas:
- Spring boot starter
	- Dependencia basica para poder inicializar Spring
- Spring boot starter web
	- Dependencia basica de Spring para aplicaciones web