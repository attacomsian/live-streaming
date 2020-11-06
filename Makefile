.PHONY: up down build clean

up:
	docker-compose up -d --build

down:
	docker-compose down

build:
	./gradlew build

clean:
	./gradlew clean