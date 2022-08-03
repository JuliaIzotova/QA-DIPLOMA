#  Дипломный проект профессии "Тестировщик"
___
### Дипломный проект представляет собой комплексное автоматизированное тестирования веб-сервиса по покупке тура.
___
## Документация проекта:
[1. Задание к диплому](https://github.com/JuliaIzotova/QA-DIPLOMA/blob/main/docs/Ex_diplom.md)  
[2.План автоматизации](https://github.com/JuliaIzotova/QA-DIPLOMA/blob/main/docs/Plan.md)  
[3.Отчет по итогам тестирования]()  
[4.Отчет по итогам автоматизации]()  

___
## Запуск SUT, авто-тестов и генерация отчетов

1. Запустить Docker Desktop  
2. Открыть проект в IntelliJ IDEA  
3. В терминале IDEA запустить контейнеры:  
`docker-compose up-d`  
4. Запустить приложение:
* Для MySQL:  
`java -jar./artifacts/aqa-shop.jar -- spring.datasource.url=jdbc:mysql://localhost:3306/app`  
* Для PostgreSQL:  
`java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`  
5. В новой вкладке терминала в IDEA запустить автотесты
* Для MySQL:  
`./gradlew clean test -Durl=jdbc:mysql://localhost:3306/app`  
* Для PostgreSQL:  
`./gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/app`  
6. Создать отчёт Allure  
`.\gradlew allureServe`  
7. Для завершения работы allureServe выполнить команду  
`Ctrl+C`  
8. Для остановки работы контейнеров выполнить команду  
`docker-compose down`