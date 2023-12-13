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
	"color" VARCHAR(10) NOT NULL,
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
