
#### Развертывание проекта

1 Скачайте docker по ссылке https://docs.docker.com/docker-for-windows/install/

2 Установите docker, при установке выберите "использовать Linux container"

3.0 Откройте терминал и выполните следующую команду
```
docker run --name oracle_container -p 1521:1521 -v /opt/oracle/oradata -P store/oracle/database-enterprise:12.2.0.1
```
3.1 Если возникла ошибка access denied, Пройдите по ссылке https://hub.docker.com/_/oracle-database-enterprise-edition
зарегистрируйтесь в docker, и примите соглашение на использование Oracle database

3.2 Войдите в docker аккаунт в терминале
```
docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
```
3.3 Повторите шаг 3.0

4.0 Если контейнер успешно создан, выполните команду
```
docker exec -it oracle_container bash
```
4.1.0 В терминале bash введите 
```
sqlplus / as sysdba
```

4.1.1 если на шаге 4.1.0 возникла ошибка 'sqlplus not found' введите следующие команды:
```
PATH=$ORACLE_HOME/bin:$PATH
export PATH
export ORACLE_HOME=/apps/oracle/product/11.2.0
exit
```
4.1.2 Повторите шаг 4.0 и затем 4.1.0

4.2 Если вход в базу данных успешно выполнен, введите команды
```
conn / as sysdba
alter session set "_ORACLE_SCRIPT"=true;
```
4.3 Создайте пользователя базы данных
```
create user client identified by 123;
grant create session to client;
grant resource, connect, dba to client;
```
4.4 Выйдите из базы данных, через команду
```xml
exit
```
4.5 Войдите под своим аккаунтом, введя
```xml
sqlplus
"username"
"password"
```
5.0 Создайте базу данных, для этого откройте следующий файл, который находится в проекте по адрессу

\NewsManagement\src\main\resources\createDatabase.txt

Скопируйте скрипт и вставьте в терминал sql

6.1 Так как Oracle не допускает прямого скачивания драйвера jdbc через maven,
(подробнее с проблемой вы можете ознакомиться по ссылке https://stackoverflow.com/questions/1074869/find-oracle-jdbc-driver-in-maven-repository/9779295)
то нам нужно выполнить следующий скрипт чтобы добавить jar в проект

```xml
mvn install:install-file -Dfile=C:\{$YOUR_PATH}\NewsManagement\src\main\resources\ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar
```

7 Откройте в проекте application-context.xml, и замените на ваши параметры базы данных:
```xml
  <property name="username" value="oleg"></property>
  <property name="password" value="password"></property>
```

8 выполните maven compile, maven package, полученный war файл добавьте в Tomcat

9 Программа готова к использованию.








