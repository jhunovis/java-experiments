drop table if exists songs;
drop table if exists albums;

create table albums (
	id integer primary key auto_increment,
	title varchar(20),
	publisher varchar(20)
);

create table songs (
	id integer primary key auto_increment,
	title varchar(40),
	artist varchar(20),
	perfomer varchar(20),
	album integer,
 	
 	foreign key (album) references albums (id)
);

