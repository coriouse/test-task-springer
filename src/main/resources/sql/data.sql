insert into t_type_content(id,content,nick) values(1, 'book', 'book' );
insert into t_type_content(id,content,nick) values(2, 'journal', 'journal' );


insert into t_document(id, title, author, type_content) values(1, 'The Dark Code', 'Bruce Wayne',1);
insert into t_document(id, title, author, type_content) values(2, 'How to make money', 'Dr. Evil',1);
insert into t_document(id, title, author, type_content) values(3, 'Journal of human flight routes', 'Clark Kent',2);

insert into t_topic(id, topic) values(1, 'Science');
insert into t_topic(id, topic) values(2, 'Business');

insert into  t_document_topic(id, document, topic) values(1,1,1);
insert into  t_document_topic(id, document, topic) values(2,2,2);
