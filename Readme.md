# Тестовое задание 

## Реализация API для сервиса Quotes

Методы описаны в файле "instructions.xlsx"

Ссылка на Docker image: https://hub.docker.com/repository/docker/aasukhov/test-task-08/general

При запуске приложения в базу данных in-memory H2 сохраняется User с данными: 
```
  String username = "alex";
  String password = "0000";
  String email = "alex@mail.com";
```
Другие данные User (int id, Date creationDate) формируются автоматически при создании экземпляра класса

Кроме того, в базу при запуске приложения вводятся несколько Quotes.