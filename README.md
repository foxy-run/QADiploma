# Дипломный проект по курсу «Тестировщик ПО»
## О проекте
В рамках данного проекта необходимо автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Покупка тура возможна с помощью карты и в кредит. Данные по картам обрабатываются отдельными сервисами (Payment Gate, Credit Gate)

Полные условия и исходные данные описанного кейса можно посмотреть [здесь](https://github.com/netology-code/qa-diploma)

## Документация 

[План автоматизации тестирования веб-формы сервиса покупки туров интернет-банка](https://github.com/foxy-run/QADiploma/blob/master/documents/Plan.md)

[Отчёт о проведенном тестировании](https://github.com/foxy-run/QADiploma/blob/master/documents/Report.md)

[Комплексный отчёт о проведённой автоматизации тестирования](https://github.com/foxy-run/QADiploma/blob/master/documents/Summary.md)

## Запуск приложения

Перед запуском необходимо выполнить следующие предусловия. Если у вас уже есть необходимое ПО, то понадобится только п.1 и запуск Docker.

*Предусловия:*
1. Необходимо склонировать репозиторий или скачать архив по [ссылке](https://github.com/foxy-run/QADiploma). Или воспользоваться VCS Git, встроенной в 
IntelliJ IDEA.
2. Установать и запустить Docker Desktop. Это можно сделать [здесь](https://docs.docker.com/desktop/) в зависимости от операционной системы. Дополнительные инструкции можно найти по [ссылке](https://github.com/netology-code/aqa-homeworks/blob/aqa4/docker/installation.md)
3. Открыть проект в IntelliJ IDEA

### Запуск
1. Запустить необходимые базы данных (MySQL и PostgreSQL), а также NodeJS. Параметры для запуска хранятся в файле `docker-compose.yml`. Для запуска необходимо ввести в терминале команду:
```
docker-compose up
```
2. В новой вкладке терминала ввести следующую команду в зависимости от базы данных
- `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar` - для MySQL
- `java -Dspring.datasource-postgresql.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar` - для PostgreSQL
3. Приложение должно запуститься 
![app run](https://user-images.githubusercontent.com/67016228/99146975-0077e100-268e-11eb-90d3-425239976d8f.jpg)
работать по адресу http://localhost:8080/

## Запуск тестов
1. В новой вкладке терминала ввести команду в зависимости от запущенной БД в п.2 Запуска:
- `gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app` - для MySQL
- `gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app` - для PostgreSQL

## Запуск отдельных тестовых классов
Чтобы не запускать все тесты разом, предусмотрено два варианта запуска отдельных тестовых классов:

**Вариант 1**

1. В `build.gradle` изменить адрес БД. Для этого нужно заменить строчку `systemProperty 'db.url', System.getProperty('db.url')` на:
- `systemProperty 'db.url', System.getProperty('db.url', 'jdbc:mysql://localhost:3306/app')` - для MySQL
- `systemProperty 'db.url', System.getProperty('db.url', 'jdbc:postgresql://localhost:5432/app')` - для PostgreSQL

2. Запустить приложение (раздел [Запуск](https://github.com/foxy-run/QADiploma/blob/master/README.md#%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D0%BA), в зависимости от БД)
3. Запустить необходимый тестовый класс командой в терминале: 

  ```
  gradlew clean test --tests PayHappyPathTest
  ``` 
  где `PayHappyPathTest` - тестовый класс, подлежащий запуску. Или запустить необходимый тестовый класс через IDE с помощью команды Run.

**Вариант 2**

1. В `build.gradle` в раздел test добавить следующее:
  ```
  filter {
      includeTestsMatching('*PayHappyPathTest')
      }
  ```
где `PayHappyPathTest` - тестовый класс, подлежащий запуску.

2. Выполнить раздел [Запуск](https://github.com/foxy-run/QADiploma/blob/master/README.md#%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D0%BA)

3. Выполнить раздел [Запуск тестов](https://github.com/foxy-run/QADiploma/blob/master/README.md#%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D0%BA-%D1%82%D0%B5%D1%81%D1%82%D0%BE%D0%B2)

## Перезапуск приложения и тестов
Если необходимо перезапустить приложение и/или тесты (например, для другой БД), необходимо выполнить остановку работы в запущенных ранее вкладках терминала, нажав в них Ctrl+С

## Формирование отчета AllureReport по результатам тестирования
В новой вкладке терминала или нажав двойной Ctrl ввести команду:
```
gradlew allureServe
```
Сгенерированный отчет откроется в браузере автоматически. После просмотра и закрытия отчета можно остановить работу команды, нажав Ctrl+С или закрыть вкладку Run и нажать Disconnect.


