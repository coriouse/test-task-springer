create table t_document(
	id INT PRIMARY KEY,
   	title VARCHAR(255),
   	author VARCHAR(255),
   	watermark VARCHAR(255),
   	type_content INT );
   	
create table t_type_content(
	id INT PRIMARY KEY,
	content VARCHAR(255),
	nick VARCHAR(32)
);   	
   	
create table t_document_topic(
	id INT PRIMARY KEY,
	document INT,
	topic INT
);

create table t_topic(
	id INT PRIMARY KEY,
	topic VARCHAR(255)
);
   	