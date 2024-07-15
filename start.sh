#!/bin/bash

log() {
    echo "$(date +'%Y-%m-%d %H:%M:%S') - $1"
}

log "Starting Maven clean and package..."
./mvnw clean package

if [ $? -eq 0 ]; then
    log "Maven build successful. Starting Docker Compose..."
    
    docker compose up -d --build

    if [ $? -eq 0 ]; then
        log "Docker Compose executed successfully."
    else
        log "Error: Docker Compose failed." >&2
        exit 1
    fi
else
    log "Error: Maven build failed." >&2
    exit 1
fi
