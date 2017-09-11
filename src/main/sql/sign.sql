CREATE table  user(
  open_id VARCHAR(50) PRIMARY KEY,
  avatar_url VARCHAR(1000),
  nick_name VARCHAR(100),
  type int DEFAULT 0,
  grede int DEFAULT 0,
  class_type VARCHAR(50),
  stream_id VARCHAR(100),
  motton VARCHAR(100),
  rank_score VARCHAR(10),
  class_content VARCHAR(255),
  update_time TIMESTAMP
);


CREATE table sign(
  sign_id int PRIMARY KEY auto_increment,
  open_id VARCHAR(50),
  teach_time VARCHAR(50),
  teach_date TIMESTAMP
);


CREATE table sign_student(
  sign_id int,
  open_id VARCHAR(50),
  status VARCHAR(50),
  evaluate VARCHAR(255),
  PRIMARY key(sign_id,open_id)
);

alter table `sign` Add column updatetime TIMESTAMP;
alter table `sign_student` Add column updatetime TIMESTAMP;
alter table `sign` add column teach_time_int DOUBLE;
alter table `sign` modify column teach_date DATE;
alter table `user` add column nick_name_p VARCHAR(50);


#*****************************************************v3


alter table `sign` add COLUMN sign_class varchar(50);
alter table `sign_student` add COLUMN pictrues text;
alter table `user` add COLUMN class_name VARCHAR(50);
ALTER TABLE `user` add COLUMN gender VARCHAR(50);
ALTER table `user` add COLUMN country VARCHAR(50);
alter TABLE `user` add COLUMN province VARCHAR(50);
alter TABLE `user` add COLUMN city VARCHAR(50);

update table user set class_type='萌新班' WHERE class_type = '初级班';
UPDATE user set class_type='提高班' WHERE class_type = '中级班' or class_type='高级班';


CREATE table class_name(
  type VARCHAR(50),
  name varchar(50),
  update_time TIMESTAMP,
  orders int,
  PRIMARY KEY(name)
);

insert into class_name values('萌新','萌新(1)班',now(),1);
insert into class_name values('萌新','萌新(2)班',now(),2);
insert into class_name values('萌新','萌新(3)班',now(),3);
insert into class_name values('萌新','萌新(4)班',now(),4);
insert into class_name values('萌新','萌新(5)班',now(),5);
insert into class_name values('萌新','萌新(6)班',now(),6);
insert into class_name values('萌新','萌新(7)班',now(),7);
insert into class_name values('萌新','萌新(8)班',now(),8);
insert into class_name values('萌新','萌新(9)班',now(),9);
insert into class_name values('萌新','萌新(10)班',now(),10);
insert into class_name values('萌新','萌新(11)班',now(),11);


insert into class_name values('提高','提高(1)班',now(),1);
insert into class_name values('提高','提高(2)班',now(),2);
insert into class_name values('提高','提高(3)班',now(),3);
insert into class_name values('提高','提高(4)班',now(),4);
insert into class_name values('提高','提高(5)班',now(),5);
insert into class_name values('提高','提高(6)班',now(),6);
insert into class_name values('提高','提高(7)班',now(),7);
insert into class_name values('提高','提高(8)班',now(),8);
insert into class_name values('提高','提高(9)班',now(),9);
insert into class_name values('提高','提高(10)班',now(),10);
insert into class_name values('提高','提高(11)班',now(),11);
