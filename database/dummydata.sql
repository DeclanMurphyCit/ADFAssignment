use app_dev_assignment1;
INSERT INTO program  ( program_name, year_number, program_code) VALUES("BEST",1,"code1"), ("DCOM",4,"code2");
INSERT INTO `lecturer` (`firstName`,`lastName`,`email`,`phoneNumber`,`roomNumber`,`idManagedProgram`) VALUES ("Imogene","Marks","quam.dignissim@necleo.org","(968) 385-9399","B204K",1),("Zelda","Bates","interdum.libero.dui@orciin.co.uk","(312) 500-3632","B204Q",1),("Sybil","Garza","senectus@diam.net","(898) 925-5762","B204M",1),("Unity","Booth","rhoncus.Donec.est@tinciduntDonecvitae.org","(436) 183-7628","B204F",1),("Sophia","Mcbride","nec.ante.Maecenas@elementumategestas.edu","(696) 359-9971","B204K",1),("Glenna","Chen","sem@gravidanuncsed.edu","(108) 485-6189","B204K",2),("Zelda","Aguilar","sit.amet@augueSed.net","(953) 716-7431","B204F",2),("Emma","Guzman","molestie@in.com","(572) 557-8147","B204X",1),("Dominique","Bruce","erat@ametdiam.net","(941) 395-1300","B204O",1),("Patricia","Clay","sit.amet@sedduiFusce.com","(822) 803-6713","B204Q",1);
INSERT INTO module(code, crn, name, semester)VALUES("comp123","1234","computer sci",2),("comp234","98765","computers",1);
insert into student(firstName, lastName, studentNumber, addressLine1, addressLine2, email, phoneNumber)VALUES ("a", "a", "r1","aa","aa","a@a", "123"), ("b", "b", "r2","bb","bb","b@b", "345");
insert into student_enrolls_for (id_module, id_student, enroment_date) values("1", "1", now()),("2", "1", now()),("3", "1", now());
INSERT INTO lecturer_teaches_module(idLecturer, idModule) VALUES ("11","1"),("15","2");
INSERT INTO program_has_semseters(id_program, semester, id_module)values ("1","8"), ("2","8");
INSERT INTO defferal_status_types(id_deferral_status, defferal_status_name)VALUES(1, "UPLOADED"),(2,"APPROVED"),(3, "DELETED");
INSERT INTO deferral(deferral_date, id_program, id_student, program_deferred, id_deferral_status)VALUES("2014-11-01", 1, 1, false, 1),("2014-11-01", 2, 2, true, 1);
insert into deferred_modules(id_module, id_deferral)VALUES("1","7"),("2","7"),("2","8");
INSERT INTO module(code, crn, name, semester)VALUES("cod21","crn2","com34",2),("moreMods","crn345","bakning",1);
