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

INSERT INTO users (email, password, first_name, last_name) VALUES ('admin@storynet.com', 'Test-1234', 'Админ', 'Админ');
INSERT INTO users (email, password, first_name, last_name) VALUES ('test1@gmail.com', 'Test-1234', 'Петър', 'Иванов');

CREATE TABLE roles(
	rolename varchar(50) not null primary key,
	created_on timestamp not null default NOW()
);

INSERT INTO roles (rolename) VALUES ('ADMIN');
INSERT INTO roles (rolename) VALUES ('USER');

CREATE SEQUENCE public.s_user_roles
	INCREMENT 1
	START 1;
	
ALTER SEQUENCE public.s_user_roles
	OWNER TO storynet_user;

CREATE TABLE user_roles(
	user_role_id int not null primary key default nextval('s_user_roles'),
	user_id int not null references users(user_id),
	rolename varchar(50) not null references roles(rolename),
	created_on timestamp not null default NOW()
);

INSERT INTO user_roles (user_id, rolename) VALUES (1, 'ADMIN');
INSERT INTO user_roles (user_id, rolename) VALUES (2, 'USER');