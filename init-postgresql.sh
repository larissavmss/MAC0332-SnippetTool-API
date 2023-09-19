#!/bin/sh
set -e

USER_PASSWORD=$(cat /run/secrets/sql-password)
PGPASSWORD=$(cat /run/secrets/sql-password)
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER snippet_user WITH ENCRYPTED PASSWORD  '${USER_PASSWORD}';
	CREATE DATABASE "snippet_db";
	GRANT ALL PRIVILEGES ON DATABASE "snippet_db" TO "snippet_user";
  ALTER DATABASE "snippet_db" OWNER TO "snippet_user";
EOSQL
psql -v ON_ERROR_STOP=1 --username snippet_user --dbname snippet_db <<-EOSQL
  CREATE TYPE COLOR AS ENUM('RED', 'YELLOW', 'BLUE', 'GREEN', 'ORANGE', 'PURPLE');
  
  CREATE TABLE public."user"(
  	"id" SERIAL PRIMARY KEY,
  	"username" VARCHAR(40) NOT NULL UNIQUE, 
  	"email" VARCHAR(80) NOT NULL UNIQUE,
  	"password" VARCHAR(80) NOT NULL,
  	registration_date DATE NOT NULL
  );
  
  CREATE TABLE public."folder"(
  	"id" SERIAL PRIMARY KEY,
  	"name" VARCHAR(60) NOT NULL,
  	"creation_date" DATE NOT NULL,
  	"user_id" INTEGER,
  	CONSTRAINT "FK_user_id" FOREIGN KEY("user_id") 
  		REFERENCES public."user"("id")
  		ON UPDATE CASCADE
  		ON DELETE CASCADE
  );
  
  CREATE TABLE public."tag"(
  	"id" SERIAL PRIMARY KEY,
  	"name" VARCHAR(60) NOT NULL,
  	"color" COLOR NOT NULL,
  	"user_id" INTEGER,
  	CONSTRAINT "FK_user_id" FOREIGN KEY("user_id") 
  		REFERENCES public."user"("id")
  		ON UPDATE CASCADE
  		ON DELETE CASCADE
  );
  
  CREATE TABLE public."snippet"(
  	"id" SERIAL PRIMARY KEY,
  	"name" VARCHAR(60) NOT NULL,
  	"content" TEXT NOT NULL,
  	"creation_date" DATE NOT NULL,
  	"folder_id" INTEGER,
  	CONSTRAINT "FK_folder_id" FOREIGN KEY("folder_id") 
  		REFERENCES public."folder"("id")
  		ON UPDATE CASCADE
  		ON DELETE RESTRICT
  );
  
  CREATE TABLE public."tag_snippet"(
  	"tag_id" INTEGER,
  	"snippet_id" INTEGER,
  	CONSTRAINT "PK_tag_snippet" PRIMARY KEY("tag_id", "snippet_id"),
  	CONSTRAINT "FK_tag_id" FOREIGN KEY("tag_id")
  		REFERENCES public."tag"("id")
  		ON UPDATE CASCADE
  		ON DELETE RESTRICT,
  	CONSTRAINT "FK_snippet_id" FOREIGN KEY("snippet_id")
  		REFERENCES public."snippet"("id")
  		ON UPDATE CASCADE
  		ON DELETE CASCADE
  );
EOSQL