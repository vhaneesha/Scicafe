insert into users values(1, "Anne", "Cooper", "Student","undeclared","ac23@gmail.com","acooper", "1234","None",true,true,false,false);
insert into users values(2, "Emma", "Smith", "Faculty","Computer Science","es24@gmail.com","esmith", "es1234","VP of ACM",true,true,true,true);
insert into users values(3, "Jane", "Doe", "Student","Biological Scince","jd27@gmail.com","jdoe", "jd1234","None",true,false,false,false);
insert into users values(4, "John", "Doe", "Student","Communication","jd@gmail.com","johndoe", "doe77","None",true,false,false,true);

insert into events(id,name,description,location,starttime,submitter) values(1,"ACM Meetup", "Free food", "Salazar Hall","2018-10-30 12:00:00",1);
insert into events(id,name,description,location,starttime, endtime, submitter) values(2,"Career Center", "Resume tips", "Career Center","2018-11-12 14:00:00","2018-10-30 16:00:00",3);
insert into events(id,name,description,location,starttime, endtime, submitter) values(3,"New Test Event", "Description","ECST","2018-10-31 15:00:00","2018-10-30 17:00:00",3);

insert into rewards(id,title,description,organisation,submitter) values(2,"Career Center", "Resume tips", "Career Center",4);

insert into  affliations values(4,1);
insert into affliations values(2,3);

insert into programs values(1,"FYrE@ECST","First-Year Experience Program at ECST", "This is fyep");
insert into programs values(2,"LSAMP","Louis Stokes Alliance for Minority Participation", "Participation");
insert into programs values(3,"ACM","Association for Computer Machinery", "CS related");
insert into programs values(4, "TestCase", "Name", "Description");
insert into programs values(5,"Delete this program", "New Program", "Description");

insert into attendees values(1, 1);
insert into attendees values(1, 2);
insert into attendees values(1, 4);
insert into attendees values(2, 3);
insert into attendees values(2, 4);

insert into hibernate_sequence values ( 50 );