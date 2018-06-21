-- 数据库 heindex
-- 应用类别表
-- drop table if exists app_category;
create table app_category(
    id int not null auto_increment,
    name varchar(32) not null comment '类别名',
    atime datetime not null comment '记录添加时间',
    primary key(id),
    unique key uk_name(name)
);

insert into app_category values(null, '移动视频类', '2018-05-24');
insert into app_category values(null, '移动支付类', '2018-05-24 8:40:05');

-- 应用信息表
create table app_info(
    id int not null auto_increment,
    name varchar(32) not null comment '应用名',
    category int not null comment '应用类别id',
    logo varchar(4096) not null comment 'app logo 图片的base64字符串',
    atime datetime not null comment '记录添加时间',
    primary key(id),
    unique key uk_app_category(name,category)
);

insert into app_info values(null, '腾讯视频', '1', '', '2018-05-24' );
insert into app_info values(null, '支付宝', '2', '', '2018-05-24' );
insert into app_info values(null, '爱奇艺', '1', '', '2018-05-24' );

-- 市场月活表
-- DROP TABLE if EXISTS app_mau;
create table app_mau(
    id int not null auto_increment,
    app int not null comment '应用id',
    month char(6) not null comment '月份',
    mau bigint not null comment '月活跃用户数',
    basis double not null comment '环比增幅',
    atime datetime not null comment '记录添加时间',
    primary key(id),
    unique key uk_app_mau(app, month)
);

insert into app_mau values(null, '1', '201712', 581770205, 0.05, '2018-05-24');
insert into app_mau values(null, '2', '201712', 447152212, 0.05, '2018-05-24');
insert into app_mau values(null, '3', '201712', 375532308, 0.05, '2018-05-24');
