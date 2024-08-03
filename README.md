#Multiple Datasource
Sample code to create multiple data source with their own transaction manager.



# Run MySQL db1
```shell
docker run --name mysql_db1 -d \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=pass123 \
-e MYSQL_DATABASE=db1 \
--restart unless-stopped \
mysql:latest

```

## Connect to MySQL db1

Asks for passwords and responds with mysql prompt:

```shell
docker exec -it mysql_db1 mysql -p
```
# Run MySQL db2
```shell
docker run --name mysql_db2 -d \
-p 3307:3306 \
-e MYSQL_ROOT_PASSWORD=pass123 \
-e MYSQL_DATABASE=db2 \
--restart unless-stopped \
mysql:latest

```

## Connect to MySQL db2

Asks for passwords and responds with mysql prompt:

```shell
docker exec -it mysql_db2 mysql -p 
```

# Run Postgres db1
```shell
docker run --name postgres_db1 -d \
-e POSTGRES_PASSWORD=pass123 \
-e POSTGRES_DB=db1 \
-e POSTGRES_USER=root \
-p 5430:5432 \
--restart unless-stopped \
postgres:latest
```

## Connect to Postgres db2

Asks for passwords and responds with mysql prompt:

```shell
docker exec -it postgres_db1 psql -U root -d db1

```
```shell
\dt

 select * from department;

```

