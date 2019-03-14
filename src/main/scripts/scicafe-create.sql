create table users (
	id  bigint primary key,
	fname varchar(32) not null,
	lname varchar(32) not null,
	position varchar(32) not null,
    organisation varchar(255) not null,
	email varchar(255) not null unique,
    username   varchar(255) not null unique,
    password  varchar(255) not null,
	title varchar(255),
  	enabled bit not null default true,
	isadministrator bit default false,
	iseventorganiser bit default false,
	isrewardprovider bit default false
);

create table events(
id bigint primary key,
name varchar(64) not null unique,
description varchar(255) not null,
location varchar(255) not null,
starttime timestamp not null default current_timestamp,
endtime timestamp default current_timestamp,
approvedby bigint references users(id),
submitter bigint not null references users(id),
isapproved bit default false
);

create table rewards(
id bigint primary key,
title varchar(100) not null,
description varchar(255) not null,
organisation varchar(255) not null,
submitter bigint not null references users(id),
rewardstart timestamp default current_timestamp,
rewardend timestamp default current_timestamp,
mineventsattended int default 1
);

create table programs(
id bigint primary key,
name varchar(100) not null,
fullname varchar(255) not null,
description varchar(255) not null
);

create table affliations(
user_id bigint not null references users(id),
program_id bigint not null references programs(id),
  primary key (user_id, program_id)
);

create table attendees(
	event_id bigint not null references events(id),
    user_id bigint not null references users(id),
  primary key (event_id, user_id)
);

create table tags(
id bigint primary key,
name varchar(255) not null
);

create table eventtags(
event_id bigint not null references events(id),
    tag_id bigint not null references tags(id),
  primary key (event_id, tag_id)

);

create table eventrewards(
event_id bigint not null references events(id),
    reward_id bigint not null references rewards(id),
  primary key (event_id, reward_id)
);

create table hibernate_sequence (
    next_val bigint
);