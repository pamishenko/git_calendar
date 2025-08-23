# Git Calendar Filler

Автоматическая система для заполнения Git-календаря пиксельными буквами.

## Описание

Система автоматически заполняет Git-календарь, создавая пиксельные буквы из текста "HELLO WORLD". Каждая буква заполняется полностью за 49 дней, затем следует 6 дней отдыха.

## Логика работы

1. **Заполнение буквы**: 49 дней (7x7 пикселей)
   - Заполненные пиксели (O) → 30 пушей с интервалом 10 секунд
   - Пустые пиксели (пробел) → 1 пуш

2. **Отдых**: 6 дней по 1 пушу

3. **Переход к следующей букве**: H → E → L → L → O → пробел → W → O → R → L → D

## Файлы

- `GitCalendarFiller.kt` - основной скрипт
- `GitCalendarFiller.jar` - скомпилированный JAR файл
- `PixelPrinter.kt` - скрипт для вывода пиксельного текста
- `com.gitcalendar.filler.plist` - конфигурация launchd сервиса
- `install_service.sh` - скрипт управления сервисом

## Установка и настройка

### Автоматический запуск (рекомендуется)

```bash
# Установить сервис
./install_service.sh install

# Проверить статус
./install_service.sh status

# Просмотр логов
./install_service.sh logs
```

### Ручной запуск

```bash
# Запустить скрипт
java -jar GitCalendarFiller.jar

# Тестирование логики
java -jar TestNewLogic.jar
```

## Управление сервисом

```bash
./install_service.sh install    # Установить сервис
./install_service.sh uninstall  # Удалить сервис
./install_service.sh start      # Запустить сервис
./install_service.sh stop       # Остановить сервис
./install_service.sh status     # Показать статус
./install_service.sh test       # Протестировать скрипт
./install_service.sh logs       # Показать логи
```

## Расписание

- **Точка старта**: 24 августа 2025 года
- **Время запуска**: каждый день в 9:00
- **Логи**: сохраняются в `git_calendar.log`

## Расписание букв

| Буква | Период | Дни |
|-------|--------|-----|
| H | 24.08 - 11.10 | 0-54 |
| E | 18.10 - 05.12 | 55-109 |
| L | 12.12 - 04.02 | 110-164 |
| L | 05.02 - 31.03 | 165-219 |
| O | 01.04 - 25.05 | 220-274 |
| пробел | 26.05 - 19.07 | 275-329 |
| W | 20.07 - 12.09 | 330-384 |
| O | 13.09 - 06.11 | 385-439 |
| R | 07.11 - 31.12 | 440-494 |
| L | 01.01 - 24.02 | 495-549 |
| D | 25.02 - 20.04 | 550-604 |

## Требования

- macOS
- Java 21+
- Kotlin
- Git репозиторий в `/Users/pavelmisenko/repositories/git_calendar`

## Логи

- **Основной лог**: `git_calendar.log`
- **Лог ошибок**: `git_calendar_error.log`

## Перекомпиляция

При изменении кода:

```bash
# Перекомпилировать
kotlinc GitCalendarFiller.kt -include-runtime -d GitCalendarFiller.jar

# Перезапустить сервис
./install_service.sh stop
./install_service.sh start
```
