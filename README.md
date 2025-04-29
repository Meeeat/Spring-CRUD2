# Todo List приложение в Docker

## Описание

Приложение Todo List, упакованное в Docker-контейнеры. Включает в себя:

- Spring MVC приложение
- MySQL базу данных
- Docker Compose для управления контейнерами

## Требования

- Docker
- Docker Compose

## Структура файлов

```
.
├── Dockerfile                # Dockerfile для сборки приложения
├── docker-compose.yml        # Конфигурация Docker Compose
├── init-sql/                 # Скрипты инициализации БД
│   └── init.sql              # Инициализация таблиц и тестовых данных
├── src/                      # Исходный код приложения
└── pom.xml                   # Maven конфигурация
```

## Как запустить

1. Убедитесь, что Docker и Docker Compose установлены
2. Клонируйте репозиторий
3. Перейдите в корневую директорию проекта
4. Выполните:

```bash
docker-compose up -d
```

5. Дождитесь запуска всех сервисов
6. Откройте браузер и перейдите по адресу: http://localhost:8080

## Остановка приложения

```bash
docker-compose down
```

Для остановки и удаления всех данных (включая базу данных):

```bash
docker-compose down -v
```

## Диагностика

### Проверка логов приложения

```bash
docker logs todo-app
```

### Проверка логов базы данных

```bash
docker logs todo-mysql
```

### Подключение к базе данных

```bash
docker exec -it todo-mysql mysql -uroot -proot todo
```

## Примечания

- Приложение использует порт 8080
- База данных использует порт 3306
- Данные базы сохраняются в Docker volume