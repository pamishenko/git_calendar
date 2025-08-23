#!/bin/bash

# Скрипт для установки и управления Git Calendar Filler сервисом

SERVICE_NAME="com.gitcalendar.filler"
PLIST_FILE="com.gitcalendar.filler.plist"
LAUNCH_AGENTS_DIR="$HOME/Library/LaunchAgents"
FULL_PLIST_PATH="$LAUNCH_AGENTS_DIR/$PLIST_FILE"

echo "=== Git Calendar Filler Service Manager ==="

case "$1" in
    "install")
        echo "Устанавливаем сервис..."
        
        # Проверяем, что мы в правильной директории
        if [ ! -f "GitCalendarFiller.jar" ]; then
            echo "Ошибка: GitCalendarFiller.jar не найден в текущей директории"
            exit 1
        fi
        
        # Создаем директорию LaunchAgents если её нет
        mkdir -p "$LAUNCH_AGENTS_DIR"
        
        # Копируем plist файл
        cp "$PLIST_FILE" "$FULL_PLIST_PATH"
        
        # Загружаем сервис
        launchctl load "$FULL_PLIST_PATH"
        
        echo "Сервис установлен и загружен!"
        echo "Скрипт будет запускаться каждый день в 9:00"
        echo "Логи будут сохраняться в git_calendar.log"
        ;;
        
    "uninstall")
        echo "Удаляем сервис..."
        
        # Выгружаем сервис
        launchctl unload "$FULL_PLIST_PATH" 2>/dev/null
        
        # Удаляем plist файл
        rm -f "$FULL_PLIST_PATH"
        
        echo "Сервис удален!"
        ;;
        
    "start")
        echo "Запускаем сервис..."
        launchctl load "$FULL_PLIST_PATH"
        echo "Сервис запущен!"
        ;;
        
    "stop")
        echo "Останавливаем сервис..."
        launchctl unload "$FULL_PLIST_PATH"
        echo "Сервис остановлен!"
        ;;
        
    "status")
        echo "Проверяем статус сервиса..."
        if launchctl list | grep -q "$SERVICE_NAME"; then
            echo "✅ Сервис активен"
        else
            echo "❌ Сервис не активен"
        fi
        
        if [ -f "git_calendar.log" ]; then
            echo "📄 Последние записи в логе:"
            tail -10 git_calendar.log
        else
            echo "📄 Лог файл не найден"
        fi
        ;;
        
    "test")
        echo "Тестируем скрипт..."
        java -jar GitCalendarFiller.jar
        ;;
        
    "logs")
        echo "Показываем логи..."
        if [ -f "git_calendar.log" ]; then
            tail -f git_calendar.log
        else
            echo "Лог файл не найден"
        fi
        ;;
        
    *)
        echo "Использование: $0 {install|uninstall|start|stop|status|test|logs}"
        echo ""
        echo "Команды:"
        echo "  install   - Установить и запустить сервис"
        echo "  uninstall - Удалить сервис"
        echo "  start     - Запустить сервис"
        echo "  stop      - Остановить сервис"
        echo "  status    - Показать статус сервиса"
        echo "  test      - Протестировать скрипт"
        echo "  logs      - Показать логи в реальном времени"
        ;;
esac
