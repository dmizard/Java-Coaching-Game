# Используем официальный образ OpenJDK 11
FROM openjdk:11-jdk

# Устанавливаем зависимости для GUI (общие для всех ОС)
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    libx11-6 \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    xauth \
    && rm -rf /var/lib/apt/lists/*

# Рабочая директория
WORKDIR /app

# Копируем JAR-файл (ресурсы должны быть внутри JAR)
COPY app/build/libs/app-1.0-SNAPSHOT.jar .

# Добавляем текстуры плит и объектов
COPY app/src/main/resources/ ./resources/

# Универсальные настройки DISPLAY для всех ОС
ENV DISPLAY=host.docker.internal:0
ENV DISPLAY_LINUX=:0
ENV XAUTHORITY=/tmp/.Xauthority

# Скрипт для автоматического выбора DISPLAY
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
