--Klinicki centri
insert into klinicki_centar (id,naziv) values (1,'Klinicki centar Beograd');
insert into klinicki_centar (id,naziv) values (2,'Klinicki centar Novi Sad');
insert into klinicki_centar (id,naziv) values (3,'Klinicki centar Nis');
--Klinike
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (1,'Beograd Viktora Igora 1','Bel Medic','Nema opisa',1);
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (2,'Beograd Presevska 31','KBC Zvezdara','Nema opisa',1);
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (3,'Beograd Golsvordijeva 6','Ginekoloska ordinacija Raovic','Nema opisa',1);
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (4,'Novi Sad Bulevar cara Lazara 81','Ordinacija Limana','Nema opisa',2);
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (5,'Novi Sad Vase Stajica 25','Poliklinika Nada Diva','Nema opisa',2);
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (6,'Nis Bulevar Nemanjica 85a','AnimaPlus','Nema opisa',3);
--Sale
insert into sala (klinika_id,broj_sale,naziv) values (1,1,'Operaciona A');
insert into sala (klinika_id,broj_sale,naziv) values (1,2,'Sala za pregled A');
insert into sala (klinika_id,broj_sale,naziv) values (1,3,'Sala za pregled B');
insert into sala (klinika_id,broj_sale,naziv) values (1,4,'Operaciona B');
insert into sala (klinika_id,broj_sale,naziv) values (2,1,'Operaciona A');
insert into sala (klinika_id,broj_sale,naziv) values (2,2,'Sala za pregled A');
insert into sala (klinika_id,broj_sale,naziv) values (3,1,'Operaciona A');
insert into sala (klinika_id,broj_sale,naziv) values (4,1,'Operaciona A');
insert into sala (klinika_id,broj_sale,naziv) values (4,2,'Operaciona B');
insert into sala (klinika_id,broj_sale,naziv) values (5,1,'Operaciona A');
--Tipovi pregleda
insert into tip_pregleda (id,naziv,klinika) values (1,'Sistematski',1);
insert into tip_pregleda (id,naziv,klinika) values (2,'Klinicki',1);
insert into tip_pregleda (id,naziv,klinika) values (3,'Oftamoloski',1);
insert into tip_pregleda (id,naziv,klinika) values (4,'Ginekoloski',1);
insert into tip_pregleda (id,naziv,klinika) values (5,'Klinicki',2);
insert into tip_pregleda (id,naziv,klinika) values (6,'Oftamoloski',2);
insert into tip_pregleda (id,naziv,klinika) values (7,'Sistematski',3);
insert into tip_pregleda (id,naziv,klinika) values (8,'Klinicki',4);
insert into tip_pregleda (id,naziv,klinika) values (9,'Oftamoloski',4);
insert into tip_pregleda (id,naziv,klinika) values (10,'Oftamoloski',5);
--Cenovnik
insert into cenovnik (id,klinika_id) values (1,1);


insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (1,1,2000,'Opsti pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (1,2,2500,'Sistematski pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (1,3,2300,'Klinicki pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (1,4,1200,'Oftamolski pregled');
insert into cenovnik (id,klinika_id) values (2,2);
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (2,5,2200,'Klinicki pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (2,6,2700,'Oftamoloski pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (2,7,2600,'Opsti pregled');
insert into cenovnik (id,klinika_id) values (3,3);
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (3,8,1000,'Opsti pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (3,9,15000,'Sistematski pregled');
insert into cenovnik (id,klinika_id) values (4,4);
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (4,10,1000,'Klinicki pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (4,11,3000,'Oftamoloski pregled');
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (4,12,5000,'Opsti pregled');
insert into cenovnik (id,klinika_id) values (5,5);
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (5,13,10000,'Oftamoloski pregled');
--Korisnici
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (1,'Ivan','Ivanovic','AdminKC@gmail.com','adminkc1',4,2322432322400,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (2,'Jovan','Jovanovic','AdminKC2@gmail.com','admink1',4,3242543242501,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (3,'Marko','Markovic','AdminKC3@gmail.com','lekar1',4,3424234242202,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (4,'Ivan','Ivanovic','srbislav30111998@gmail.com','admink1',0,2322432322403,true);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (5,'Jovan','Jovanovic','adminK2@gmail.com','admink1',0,3242543242504,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (6,'Marko','Markovic','adminK3@gmail.com','lekar1',0,3424234242205,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (7,'Marko','Simonovic','adminK4@gmail.com','adminkc1',0,2322432322406,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (8,'Nemanja','Bjelica','adminK5@gmail.com','admink1',0,3242543242507,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (9,'Nikola','Jokic','adminK6@gmail.com','lekar1',0,3424234242208,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (10,'Borisa','Simanic','lekar@gmail.com','lekar1',1,3242543242509,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (11,'Milos','Teodosic','lekar2@gmail.com','lekar1',1,3424234242210,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (12,'Jovan','Krasic','lekar3@gmail.com','adminkc1',1,2322432322411,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (13,'Jovan','Jovanovic','lekar4@gmail.com','admink1',1,3242543242512,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (14,'Marko','Pantelic','lekar5@gmail.com','lekar1',1,3424234242213,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (15,'Ana','Ivanovic','lekar6@gmail.com','adminkc1',1,2322432322414,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (16,'Marko','Pecarski','lekar7@gmail.com','admink1',1,3242543242515,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (17,'Janko','Tipsarevic','lekar8@gmail.com','lekar1',1,3424234242216,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (18,'Nemanja','Dangubic','lekar9@gmail.com','adminkc1',1,2322432322417,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (19,'Branko','Lazic','lekar10@gmail.com','admink1',1,3242543242518,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (20,'Nikola','Milutinov','lekar11@gmail.com','lekar1',1,3424234242219,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (21,'Luka','Doncic','lekar12@gmail.com','admink1',1,3242543242520,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (22,'Marko','Markovic','sestra@gmail.com','sestra1',2,3424234242221,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (23,'Ivan','Ivanovic','sestra2@gmail.com','adminkc1',2,2322432322422,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (24,'Jovan','Jovanovic','sestra3@gmail.com','admink1',2,3242543242523,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (25,'Marko','Markovic','sestra4@gmail.com','lekar1',2,3424234242224,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (26,'Ivan','Ivanovic','sestra5@gmail.com','adminkc1',2,2322432322425,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (27,'Jovan','Jovanovic','sestra6@gmail.com','admink1',2,3242543242526,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (28,'Marko','Markovic','sestra7@gmail.com','lekar1',2,3424234242227,false);
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (29,'Sasa','Pavlovic','pacijent@gmail.com','pacijent',3,2322432322428,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (30,'Kevin','Durant','pacijent2@gmail.com','admink1',3,3242543242529,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (31,'Marko','Guduric','pacijent3@gmail.com','lekar1',3,3424234242230,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (32,'Borisa','Simanic','pacijent4@gmail.com','lekar1',3,3424234242231,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (33,'Vladimir','Stimac','pacijent5@gmail.com','adminkc1',3,2322432322432,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (34,'Lazar','Miletic','pacijent6@gmail.com','admink1',3,3242543242533,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (35,'Luka','Jovic','pacijent7@gmail.com','adminkc1',3,2322432322434,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (36,'Luka','Lukic','jovansvorcan14@gmail.com','adminkc1',3,3322432322435,false,'Radiceva 6','Novi Sad','Srbija');
insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put,adresa,grad,drzava) values (37,'Jova','Jovic','pacijent9@gmail.com','adminkc1',3,4322432322437,false,'Radiceva 6','Novi Sad','Srbija');
--Administratori klinickih centara
insert into administrator_klinickog_centra (id,kc) values (1,1);
insert into administrator_klinickog_centra (id,kc) values (2,2);
insert into administrator_klinickog_centra (id,kc) values (3,3);
--Administratori klinika
insert into administrator_klinike (id,klinika_id) values (4,1);
insert into administrator_klinike (id,klinika_id) values (5,2);
insert into administrator_klinike (id,klinika_id) values (6,3);
insert into administrator_klinike (id,klinika_id) values (7,4);
insert into administrator_klinike (id,klinika_id) values (8,5);
insert into administrator_klinike (id,klinika_id) values (9,6);
--Lekari
insert into lekar (id,od,do,klinika) values (10,'1998-12-31 10:59:59','1999-12-31 19:59:59',1);
insert into lekar (id,od,do,klinika) values (11,'1998-12-31 10:59:59','1999-12-31 19:59:59',1);
insert into lekar (id,od,do,klinika) values (12,'1998-12-31 10:59:59','1999-12-31 19:59:59',1);
insert into lekar (id,od,do,klinika) values (13,'1998-12-31 10:59:59','1999-12-31 19:59:59',1);
insert into lekar (id,od,do,klinika) values (14,'1998-12-31 10:59:59','1999-12-31 19:59:59',1);
insert into lekar (id,od,do,klinika) values (15,'1998-12-31 10:59:59','1999-12-31 19:59:59',2);
insert into lekar (id,od,do,klinika) values (16,'1998-12-31 10:59:59','1999-12-31 19:59:59',2);
insert into lekar (id,od,do,klinika) values (17,'1998-12-31 10:59:59','1999-12-31 19:59:59',3);
insert into lekar (id,od,do,klinika) values (18,'1998-12-31 10:59:59','1999-12-31 19:59:59',3);
insert into lekar (id,od,do,klinika) values (19,'1998-12-31 10:59:59','1999-12-31 19:59:59',4);
insert into lekar (id,od,do,klinika) values (20,'1998-12-31 10:59:59','1999-12-31 19:59:59',5);
insert into lekar (id,od,do,klinika) values (21,'1998-12-31 10:59:59','1999-12-31 19:59:59',1);
--Medicinske sestre
insert into medicinska_sestra (id,od,do,klinika) values (22,'1998-12-31 23:59:59','1999-12-31 12:59:59',1);
insert into medicinska_sestra (id,od,do,klinika) values (23,'1998-12-31 23:59:59','1999-12-31 12:59:59',1);
insert into medicinska_sestra (id,od,do,klinika) values (24,'1998-12-31 23:59:59','1999-12-31 12:59:59',2);
insert into medicinska_sestra (id,od,do,klinika) values (25,'1998-12-31 23:59:59','1999-12-31 12:59:59',3);
insert into medicinska_sestra (id,od,do,klinika) values (26,'1998-12-31 23:59:59','1999-12-31 12:59:59',4);
insert into medicinska_sestra (id,od,do,klinika) values (27,'1998-12-31 23:59:59','1999-12-31 12:59:59',5);
insert into medicinska_sestra (id,od,do,klinika) values (28,'1998-12-31 23:59:59','1999-12-31 12:59:59',2);
--Pacijenti
insert into pacijent (id,odobren) values (29,TRUE);
insert into pacijent (id,odobren) values (30,TRUE);
insert into pacijent (id,odobren) values (31,TRUE);
insert into pacijent (id,odobren) values (32,TRUE);
insert into pacijent (id,odobren) values (33,TRUE);
insert into pacijent (id,odobren) values (34,TRUE);
insert into pacijent (id,odobren) values (35,TRUE);
insert into pacijent (id,odobren) values (36,FALSE);
insert into pacijent (id,odobren) values (37,FALSE);
--Kartoni
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa,istorija_bolesti,visina,tezina,alergije,propisano) values (1,'Sasa','Pavlovic',0,'1998-12-31 23:59:59','A+','Nije bilo nikakvih bolesti','180cm','80g','nema alergije','nema propisane lekove');
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa,istorija_bolesti,visina,tezina,alergije,propisano) values (2,'Kevin','Durant',0,'1998-12-31 23:59:59','A+','Nije bilo nikakvih bolesti','180cm','80g','nema alergije','nema propisane lekove');
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa,istorija_bolesti,visina,tezina,alergije,propisano) values (3,'Marko','Guduric',0,'1998-12-31 23:59:59','A+','Nije bilo nikakvih bolesti','180cm','80g','nema alergije','nema propisane lekove');
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa,istorija_bolesti,visina,tezina,alergije,propisano) values (4,'Borisa','Simanic',0,'1998-12-31 23:59:59','A+','Nije bilo nikakvih bolesti','180cm','80g','nema alergije','nema propisane lekove');
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa,istorija_bolesti,visina,tezina,alergije,propisano) values (5,'Vladimir','Stimac',0,'1998-12-31 23:59:59','A+','Nije bilo nikakvih bolesti','180cm','80g','nema alergije','nema propisane lekove');
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa,istorija_bolesti,visina,tezina,alergije,propisano) values (6,'Lazar','Miletic',0,'1998-12-31 23:59:59','A+','Nije bilo nikakvih bolesti','180cm','80g','nema alergije','nema propisane lekove');
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa,istorija_bolesti,visina,tezina,alergije,propisano) values (7,'Luka','Jovic',0,'1998-12-31 23:59:59','A+','Nije bilo nikakvih bolesti','180cm','80g','nema alergije','nema propisane lekove');
--Dodavanje kartona pacijentu
update pacijent set karton_id = 1 where id = 29;
update pacijent set karton_id = 2 where id = 30;
update pacijent set karton_id = 3 where id = 31;
update pacijent set karton_id = 4 where id = 32;
update pacijent set karton_id = 5 where id = 33;
update pacijent set karton_id = 6 where id = 34;
update pacijent set karton_id = 7 where id = 35;
--Dodavanje tip pregleda lekaru
update lekar set tip_pregleda = 1 where id = 10;
update lekar set tip_pregleda = 2 where id = 11;
update lekar set tip_pregleda = 3 where id = 12;
update lekar set tip_pregleda = 4 where id = 13;
update lekar set tip_pregleda = 1 where id = 14;
update lekar set tip_pregleda = 5 where id = 15;
update lekar set tip_pregleda = 6 where id = 16;
update lekar set tip_pregleda = 7 where id = 17;
update lekar set tip_pregleda = 7 where id = 18;
update lekar set tip_pregleda = 8 where id = 19;
update lekar set tip_pregleda = 10 where id = 20;
update lekar set tip_pregleda = 1 where id = 21;
--Dijagnoze 
insert into dijagnoza (id,opis) values (1,'Pacijent je zdrav.');
insert into dijagnoza (id,opis) values (2,'Redovno dolaziti na preglede svakih godinu dana.');
insert into dijagnoza (id,opis) values (3,'Redovno dolaziti na preglede svakih mesec dana.');
insert into dijagnoza (id,opis) values (4,'Trebalo bi samo da konzumira propisane lekove.');
insert into dijagnoza (id,opis) values (5,'Trebalo bi da ode na operaciju.');
--Pregledi koje pacijenti mogu zakazati (prepoznajemo ih jer nemaju ni karton a imaju salu)
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id) values (1,'2020-07-10 17:00:00',50,1,10,1,1,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id) values (2,'2020-07-10 17:00:00',50,1,11,1,2,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id) values (3,'2020-07-10 17:00:00',50,1,12,1,3,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id) values (4,'2020-07-10 17:00:00',50,5,15,2,1,5);
--Pregledi koji su zavrseni (imaju dijagnozu)
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (5,'2019-05-02 15:00:00',50,1,10,1,1,1,1,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (6,'2019-05-10 15:00:00',50,1,10,1,1,1,2,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (7,'2019-05-10 16:00:00',50,1,10,1,1,1,3,2);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (8,'2019-05-30 15:00:00',50,1,10,1,1,1,4,2);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (9,'2019-05-30 16:00:00',50,1,10,1,1,1,5,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (10,'2019-06-08 13:00:00',50,1,10,1,1,1,6,2);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (11,'2019-06-08 14:00:00',50,1,10,1,1,1,7,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (12,'2019-06-08 15:00:00',50,5,15,2,1,5,1,2);
--Pregledi koji su zakazani i koje je administrator odobrio (imaju karton i salu,ali ne dijagnozu)
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (13,'2020-07-09 12:00:00',50,1,10,1,1,2,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (14,'2020-07-09 13:00:00',50,1,10,1,1,2,2);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (15,'2020-07-09 14:00:00',50,1,10,1,1,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (16,'2020-07-09 15:00:00',50,1,10,1,1,2,4);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (17,'2020-07-09 15:00:00',50,1,11,1,2,2,5);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (18,'2020-07-09 15:00:00',50,1,12,1,3,2,6);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (19,'2020-07-09 15:00:00',50,1,13,1,4,2,7);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (20,'2020-07-09 12:00:00',50,5,15,2,1,5,2);
--Pregledi koji su zakazani i administrator ih treba odobriti (imaju karton ali ne salu)
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (21,'2020-07-09 13:30:00',50,1,10,2,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (22,'2020-07-09 15:30:00',50,1,10,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (23,'2020-07-09 17:00:00',50,1,10,2,2);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (24,'2020-07-09 17:30:00',50,5,15,5,5);
--Operacije koji su zakazane i administrator ih treba odobriti (nemaju salu)
insert into operacija(id,datum,trajanje,cena_id,karton_id) values (1,'2020-07-13 14:30:00',50,1,1);
insert into operacija_lekari(operacije_id,lekari_id) values(1,10);
insert into operacija(id,datum,trajanje,cena_id,karton_id) values (2,'2020-07-12 12:00:00',50,5,5);
insert into operacija_lekari(operacije_id,lekari_id) values(2,15);
--Lekovi
insert into lek (id,naziv,sifra) values (1,'lek1','x1t103s2');
insert into lek (id,naziv,sifra) values (2,'lek2','x2t2s3ss');
insert into lek (id,naziv,sifra) values (3,'lek3','x2t2673f');
insert into lek (id,naziv,sifra) values (4,'lek4','t567x2t2');
insert into lek (id,naziv,sifra) values (5,'lek5','yuyu6s31');
insert into lek (id,naziv,sifra) values (6,'lek6','x2ts335f');
insert into lek (id,naziv,sifra) values (7,'lek7','x2s42dd3');
insert into lek (id,naziv,sifra) values (8,'lek8','x23zt2s3');
insert into lek (id,naziv,sifra) values (9,'lek9','x2t2xrs3');
insert into lek (id,naziv,sifra) values (10,'lek10','x2wt2rs3');
--Ocene lekara
insert into lekar_ocene (lekar_id,ocene) values (10,4);
insert into lekar_ocene (lekar_id,ocene) values (10,5);
--Ocene klinika
insert into klinika_ocene (klinika_id,ocene) values (1,4);
insert into klinika_ocene (klinika_id,ocene) values (1,2);
--Stavke sifarnika
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (1,'x1t103s2',1,1);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (2,'d2t67',2,0);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (3,'28sfftg',1,0);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (4,'x2t2s3ss',2,1);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (5,'x2t2673f',3,1);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (6,'t567x2t2',4,1);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (7,'yuyu6s31',5,1);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (8,'x2ts335f',6,1);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (9,'d99at3',3,0);
insert into stavka_sifarnika(id,sifra,stavka_id,tip) values (10,'99yy2',4,0);
--Godisnji odmori
insert into godisnji_odmor(id,datum_pocetka,datum_kraja,med_osoblje_id) values(1,'2020-07-11 12:00:00','2020-07-21 12:00:00',10);
insert into godisnji_odmor(id,datum_pocetka,datum_kraja,med_osoblje_id) values(2,'2020-07-12 12:00:00','2020-07-22 12:00:00',11);
insert into godisnji_odmor(id,datum_pocetka,datum_kraja,med_osoblje_id) values(3,'2020-07-13 12:00:00','2020-07-23 12:00:00',12);
--Recepti

