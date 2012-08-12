/* The infile is read relative to the location whrere the
   executing command had been started. This pretty much spoils it
   for the Eclipse MySQL explorer, which uses the current working
   directory of Eclipse, i.e. the users home directory.
   One could also specify an absolute path, but that also breaks
   portability across installations.  
*/
load data local infile 'simple_db_albums.csv'
	ignore
	into table simple.albums
	fields terminated by ','
	optionally enclosed by '"'
	ignore 2 lines;

load data local infile 'simple_db_artists.csv'
	ignore
	into table simple.songs
	optionally enclosed by '"'	
	fields terminated by ','
	ignore 2 lines;
