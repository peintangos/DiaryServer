alter table diary_history add userId int not null;
alter table people add userId int not null;
alter table people add primary key (userId);
alter table diary_history add foreign key (userId) references people (userId);
rename table people to users;

