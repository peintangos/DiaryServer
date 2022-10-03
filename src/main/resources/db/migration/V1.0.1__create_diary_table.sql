create table if not exists diary_history(
id not null auto_increment,
subject varchar(255) not null,
studyHour double not null,
subjectDetail varchar(255) not null,
tag varchar(255) not null,
input_datetime datetime not null
);
