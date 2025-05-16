#!/bin/bash

# Автоматическое определение DISPLAY для разных ОС
if grep -q Microsoft /proc/version; then
  # WSL2 (Windows)
  export DISPLAY=$(awk '/nameserver / {print $2; exit}' /etc/resolv.conf 2>/dev/null):0
elif [ "$(uname)" == "Darwin" ]; then
  # macOS
  export DISPLAY=host.docker.internal:0
else
  # Linux
  export DISPLAY=:0
fi

# Запуск Java-приложения
exec java -jar app-1.0-SNAPSHOT.jar "$@"
