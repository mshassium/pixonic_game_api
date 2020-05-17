# pixonic_game_api

### For local start
```
1) git clone <current repo> 
2) cd to folder current repo
3) mvn clean install
4) docker-compose up
5) Open browser tab localhost:8080
6) PROFIT!
```

### Description

We have a Spring Boot application.
Our Application connect to PostgresDB via jdbc driver (all connect configuration in to application.yml file)

If you use `docker-compose` you have 3 database instance 
(to demonstrate sharding functionality) and 
3 application instance (for demonstrate scaling functionality)

#### Sharding
We have 3 important SQL files `main_db_init.sql`, `shard1_init.sql`, `shard2_init.sql`
This files contains all scripts for init and configure our db stack.
1) Create rules for sharding
2) Create all tables and keys
3) Insert test data

our table `user_table` subject to sharding by condition

``
if gold value >=0 and <=1000 then our data store to shard1_db instance, 
else store to shard2_db 
``

#### Scaling
For support this point i use docker compose tool. 
But this does not completely cover the scaling problem.
need to finish the following:
1) add load balancing
2) add service discovery

#### Business logic
It is very simple 😄
1) Create Entity (data model for user, stock, etc...)
2) Create Repository (CRUDRepository and 1 more custom repository
just for correct store new user via sharding dbs)
3) Create Services (most interesting services is GameService 😅 )
4) Create RestControllers
5) Little bit logs
6) Add Swagger config and UI (i like this tool for documentation and easy test)
7) Profit!!!

### Tests
will appear soon 😆

### Any questions
1) telegram: @rail_khamitov
2) vk: http://vk.com/rail.khamitov
3) skype: lunar180
4) e-mail: mshassium@gmail.com
5) git: http://github.com/mshassium  


 
