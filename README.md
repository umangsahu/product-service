# Product Service

A Spring Boot REST API for managing products and categories. Products belong to one category. Spring Data JPA persists both in MySQL.

## Run locally

1. Install Java 17 and start MySQL.
2. Create a database and give your MySQL user access to it. The default database is `productservicenov14`.
3. Run the app with your database settings:

   ```bash
   export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/productservicenov14
   export SPRING_DATASOURCE_USERNAME=your_mysql_user
   export SPRING_DATASOURCE_PASSWORD=your_mysql_password
   ./mvnw spring-boot:run
   ```

The API runs at `http://localhost:8080`.

## Endpoints

| Method | Path | Operation |
| --- | --- | --- |
| GET | `/product` | List products |
| GET | `/product/{id}` | Fetch a product by ID |
| POST | `/product` | Create a product |
| PATCH | `/product/{id}` | Update a product |
| DELETE | `/product/{id}` | Delete a product |
| POST | `/category` | Create a category |

## Examples

Create a category:

```bash
curl -i -X POST http://localhost:8080/category \
  -H 'Content-Type: application/json' \
  -d '{"categoryName":"Electronics","description":"Electronic devices"}'
```

Create a product. Replace the category ID with the ID returned above, or supply a category name and description to create a new category with the product.

```bash
curl -i -X POST http://localhost:8080/product \
  -H 'Content-Type: application/json' \
  -d '{"productName":"Keyboard","productPrice":1500,"category":{"id":1}}'
```

Fetch a product using the product ID returned by the create request:

```bash
curl -i http://localhost:8080/product/1
```
