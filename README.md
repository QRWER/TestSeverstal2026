<h1 style="text-align: center;">API для создание отчетов по поставкам</h1>

Это приложение сделано как тестовое задание для прохождения на стажировку в IT HUB «Северсталь»\
API позволяет хранить, добавлять, удалять данные о сущностях (Товары - Product, Поставщиках - Provider, Поставках - Supply)\
Основные Endpoint'ы:\
GET "/providers" - информация о всех поставщиках, их товарах и поставках в которых они участвуют\
GET "/products" - информация о всех товарах\
GET "/supplies" - информаци о всех поставках\
GET "/provider/{providerId}/products" - информация о товаре в наличии у поставщика c id = providerId\
GET "/provider/{providerId}/supplies" - информация о потсавках в которых участвует поставщик c id = providerId\
\
POST "/provider" - добавить нового поставщика\
POST "/provider/{providerId}/product" - добавить новый товар поставщику с id = providerId\
POST "/provider/{providerId}/supply" - Добавить поставку от поставщика с id = providerId\

## Список технологий
- **Jdk 17**
- **Spring**
- **PostgreSQL**
- **Maven**
- **Docker**

## Инструкция по распаковке

 __Обязательно убедитесь что у вас установлен Docker и запущен Docker Engine__

 1. Откройте cmd/bash и склонируете проект с гит репозитория
    ``` cmd 
    git clone https://github.com/QRWER/TestSeverstal2026
    cd TestSeverstal2026
 2. Постройте проект в Docker контейнере
    ``` cmd
    docker-compose build
    ```
    После построения Docker контейнера, запустите его.
    ``` cmd
    docker-compose up
    ```


