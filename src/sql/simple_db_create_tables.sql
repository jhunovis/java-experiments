drop table if exists simple.songs;
drop table if exists simple.albums;

create table simple.albums (
	id integer primary key auto_increment,
	title varchar(20),
	publisher varchar(20)
);

create table simple.songs (
	id integer primary key auto_increment,
	title varchar(40),
	artist varchar(20),
	performer varchar(20),
	album integer,
 	
 	foreign key (album) references albums (id)
);

