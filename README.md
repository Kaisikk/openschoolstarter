Проект стартер для логирования http запросов в проекте. 
Логирует все http запросы в зависимости от паттерна заданного в настройках. 
Уровни логирования можно поменять.


Гайд как подключить: 
Просто выкачиваешь проект и собираешь через maven install, чтобы все загрузилось в твой m2 репозиторий. 
Потом можешь  подключать его такой зависимостью: 

<dependency>
            <groupId>com.kaisik</groupId>
            <artifactId>openschoolstarter</artifactId>
            <version>2.0.0</version>
        </dependency>

Версия зависимости может меняться (прошу смотреть на актуальную).

Пример настрок бинов для запуска из стартера смотреть тут: https://github.com/Kaisikk/openschoolaop/blob/main/src/main/java/com/kaisik/openscheoolaop/AppConfig.java
