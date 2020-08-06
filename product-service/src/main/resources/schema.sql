DROP TABLE IF EXISTS `em_product`;

create table em_product
(
   prod_Id varchar(40) not null,
   prod_name varchar(100) not null,
   prod_price INT not null,
   prod_qnty INT not null,
   prod_desc varchar(250) ,
   prod_category varchar(100) ,
   prod_img blob ,
   primary key(prod_Id)
);


create table em_product_images
(
   prod_Id varchar(40),
   prod_img1 blob ,
   prod_img2 blob ,
   prod_img3 blob ,
   primary key(prod_Id)
);
