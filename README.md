# Processing System Stub

Заглушка для эмуляции системы процессинга в системе выдачи микрозаймов.

## Технологии
- Java 17
- Spring Boot 3.2.0
- Maven
- H2 Database
- SpringDoc OpenAPI
- Micrometer
- Lombok
- MapStruct

## Сборка проекта

```bash
mvn clean package
```

После сборки JAR файл будет находиться в папке target/:

    target/ibs-stub-task-0.0.1-SNAPSHOT.jar

## Запуск

1. Без указания профиля (default)

- Порт: 8080
- Задержка DELETE: 1 секунда

```bash
java -Xms2g -Xmx4g -jar target/ibs-stub-task-0.0.1-SNAPSHOT.jar
```

2. С профилем test1

- Порт: 7770
- Задержка DELETE: 2 секунды

```bash
java -Xms2g -Xmx4g -jar target/ibs-stub-task-0.0.1-SNAPSHOT.jar --spring.profiles.active=test1
```

3. С профилем test2

- Порт: 7775
- Задержка DELETE: 4 секунды

```bash
java -Xms2g -Xmx4g -jar target/ibs-stub-task-0.0.1-SNAPSHOT.jar --spring.profiles.active=test2
```

## API Документация

### Swagger UI

После запуска доступен по адресу:

| Профиль | URL |
|--------|-----|
| default | http://localhost:8080/swagger-ui.html |
| test1 | http://localhost:7770/swagger-ui.html |
| test2 | http://localhost:7775/swagger-ui.html |

---

### H2 Console

Интерфейс базы данных доступен по адресу:

 - http://localhost:{port}/h2-console

Параметры подключения:

| Поле | Значение |
|------|----------|
| JDBC URL | jdbc:h2:mem:processingdb |
| User Name | user |
| Password | (оставить пустым) |

---

### Мониторинг и метрики
Метрики доступны через Spring Boot Actuator:

- Health check: http://localhost:{port}/actuator/health

- Все метрики: http://localhost:{port}/actuator/metrics

- JVM память: http://localhost:{port}/actuator/metrics/jvm.memory.used

- HTTP запросы: http://localhost:{port}/actuator/metrics/http.server.requests

Логи пишутся в файл: logs/app.log


## Структура проекта


## Структура проекта

```text
ibs-stub-task/
├── src/
│   ├── main/
│   │   ├── java/com/ibs/
│   │   │   ├── config/        # конфиги
│   │   │   ├── controller/    # REST контроллер
│   │   │   ├── dto/           # DTO
│   │   │   ├── entity/        # сущности
│   │   │   ├── mapper/        # мапперы
│   │   │   ├── repository/    # репозитории
│   │   │   ├── service/       # бизнес-логика
│   │   │   └── util/          # Утилиты
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-test1.yml
│   │       └── application-test2.yml
│   └── test/                  # тесты
├── target/
│   └── ibs-stub-task-0.0.1-SNAPSHOT.jar
├── logs/                      # логи приложения
├── pom.xml
├── requests.http              # запросы
└── README.md
