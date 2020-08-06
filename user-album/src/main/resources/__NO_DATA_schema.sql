DROP TABLE IF EXISTS `USERS_ALBUM`;

create table USERS_ALBUM
(
   USER_ID varchar(12) not null,
   ALBUM_NAME varchar(50) not null,
   IMAGE_ID varchar(50) not null,
   IMAGE_URL varchar(50) not null,
   primary key(USER_ID,ALBUM_NAME,IMAGE_ID)
);

