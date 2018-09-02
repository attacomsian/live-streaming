# Live Streaming API
REST API for uploading video media, and then for listing and streaming the videos. Login is required.

## Usage
In order to build this application, run the following Gradle command:
```ssh
./gradlew build
```

To run this application, use the following Gradle command:
```ssh
./gradlew bootRun
```

## Documentation
When the application is running, the documentation is available at the following link:
```text
http://localhost:8080/swagger-ui.html
```

## Database
Following SQL script is used to create a MySQL database for storing users and videos data.
```sql
#create database
CREATE DATABASE live-stremaing;

#switch to newly created database
USE live-streaming;

#create users table
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

#create videos table
CREATE TABLE `videos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `size` double DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_videos_users_idx` (`user_id`),
  CONSTRAINT `fk_videos_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

## License
MIT
