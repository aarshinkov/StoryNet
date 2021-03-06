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

INSERT INTO users (email, password, first_name, last_name) VALUES ('admin@storynet.com', '$2a$12$oE7xOZAM5LcdeYJ/oS9/d.OJdNygOCjtfhx/yhHspBBbZntO7T6Mu', 'Админ', 'Админ');
INSERT INTO users (email, password, first_name, last_name) VALUES ('test1@gmail.com', '$2a$12$oE7xOZAM5LcdeYJ/oS9/d.OJdNygOCjtfhx/yhHspBBbZntO7T6Mu', 'Петър', 'Иванов');

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

CREATE SEQUENCE public.s_categories
	INCREMENT 1
	START 1;
	
ALTER SEQUENCE public.s_categories
	OWNER TO storynet_user;
	
CREATE TABLE categories(
	category_id int not null primary key default nextval('s_categories'),
	name varchar(50) not null,
	created_on timestamp not null default NOW()
);

INSERT INTO categories (name) VALUES ('love');
INSERT INTO categories (name) VALUES ('teen');
INSERT INTO categories (name) VALUES ('family');
INSERT INTO categories (name) VALUES ('health');
INSERT INTO categories (name) VALUES ('education');
INSERT INTO categories (name) VALUES ('sport');

CREATE SEQUENCE public.s_stories
	INCREMENT 1
	START 1;
	
ALTER SEQUENCE public.s_stories
	OWNER TO storynet_user;
	
CREATE TABLE stories(
	story_id int not null primary key default nextval('s_stories'),
	title varchar(100) not null,
	story text not null,
	rating numeric not null default 0,
	visits int not null default 0,
	anonymous boolean not null default true,
	user_id int not null references users(user_id) ON DELETE CASCADE,
	category_id int not null references categories(category_id) ON DELETE CASCADE,
	created_on timestamp not null default NOW(),
	edited_on timestamp
);

CREATE SEQUENCE public.s_comments
	INCREMENT 1
	START 1;
	
ALTER SEQUENCE public.s_comments
	OWNER to storynet_user;
	
CREATE TABLE comments(
	comment_id int not null primary key default nextval('s_comments'),
	content text not null,
	story_id int not null references stories(story_id) ON DELETE CASCADE,
	user_id int not null references users(user_id) ON DELETE CASCADE,
	created_on timestamp not null default NOW(),
	edited_on timestamp
);