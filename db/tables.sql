CREATE SEQUENCE public.s_users
	INCREMENT 1
	START 1;
	
ALTER SEQUENCE public.s_users
	OWNER TO storynet_user;

CREATE TABLE users(
	user_id int not null primary key default nextval('s_users'),
	email varchar(200) not null unique,
	password varchar(100) not null,
	first_name varchar(100) not null,
	last_name varchar(100),
	created_on timestamp not null default NOW(),
	edited_on timestamp
);

CREATE TABLE roles(
	rolename varchar(50) not null primary key,
	created_on timestamp not null default NOW()
);

INSERT INTO roles (rolename) VALUES ('ADMIN');
INSERT INTO roles (rolename) VALUES ('USER');