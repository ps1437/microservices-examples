DROP TABLE IF EXISTS `USERS`;

create table USERS
(
   USER_ID varchar(12) not null,
   EMAIL_ID varchar(50) not null,
   PASSWORD varchar(100) not null,
   BIRTH_DATE varchar(12) not null,
   GENDER varchar(1) not null,
    primary key(USER_ID)
);

