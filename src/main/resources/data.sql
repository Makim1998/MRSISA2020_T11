
insert into user (id, email,  password,  ime,  prezime,uloga,broj_osiguranika, adresa, grad, drzava ,prvi_put) values (4l,'pacijent','pacijent','Marko','Markovic',3,'1111123224321','Radiceva 6','Novi Sad','Srbija',false);
insert into pacijent (id) values (4l);
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa) values (1,'Marko','Markovic',0,'1998-12-31 23:59:59','A+');
update pacijent set karton_id = 1 where id = 4l;

insert into user (id, email,  password,  ime,  prezime,uloga,broj_osiguranika, adresa, grad, drzava ,prvi_put) values (7,'pacijent1','pacijent','Marinko','Maricic',3,'2111123224321','Fruskogorska 2','Novi Sad','Srbija',false);
insert into pacijent (id) values (7);
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa) values (2,'Marinko','Maricic',0,'1990-8-31 23:59:59','A+');
update pacijent set karton_id = 2 where id = 7;

insert into user (id, email,  password,  ime,  prezime,uloga,broj_osiguranika, adresa, grad, drzava ,prvi_put) values (8,'pacijent2','pacijent','Milena','Mazic',3,'4111123224321','Fruskogorska 6','Novi Sad','Srbija',false);
insert into pacijent (id) values (8);
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa) values (3,'Milena','Mazic',0,'1978-7-31 23:59:59','A+');
update pacijent set karton_id = 3 where id = 8;

insert into user (id, email,  password,  ime,  prezime,uloga,broj_osiguranika, adresa, grad, drzava ,prvi_put) values (9,'pacijent3','pacijent','Marina','Marin',3,'5111123224321','Radiceva 9','Novi Sad','Srbija',false);
insert into pacijent (id) values (9);
insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa) values (4,'Marina','Marin',0,'1988-6-30 23:59:59','A+');
update pacijent set karton_id = 4 where id = 9;

insert into user (id, email,  password,  ime,  prezime,uloga,broj_osiguranika, adresa, grad, drzava ,prvi_put) values (11l,'ana@gmail.com','pacijent','Marko','Markovic',3,'1111123224320','Radiceva 6','Novi Sad','Srbija',false);
insert into pacijent (id) values (11l);

insert into klinicki_centar (id,naziv) values (1,'Klinicki centar Novi Sad');

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (1,'Ivan','Ivanovic','AdminKC1','adminkc1',4,2322432322432,false);
insert into administrator_klinickog_centra (id,kc) values (1,1);

insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (1,'Novi Sad','Klinika Centar','Nema opisa',1);
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (2,'Liman','Klinika Liman','Nema opisa',1);

insert into sala (klinika_id,broj_sale,naziv) values (1,1,'Operaciona');
insert into sala (klinika_id,broj_sale,naziv) values (1,2,'Sala za pregled');
insert into sala (klinika_id,broj_sale,naziv) values (2,1,'Operaciona');
insert into sala (klinika_id,broj_sale,naziv) values (2,2,'Operaciona');

insert into tip_pregleda (id,naziv,klinika) values (1,'Sistematski',1);
insert into tip_pregleda (id,naziv,klinika) values (2,'Klinicki',1);
insert into tip_pregleda (id,naziv,klinika) values (3,'Oftamoloski',2);

insert into cenovnik (id,klinika_id) values (1,1);
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (1,1,2000,'Opsti pregled');

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (2,'Jovan','Jovanovic','AdminK1','admink1',0,3242543242541,false);
insert into administrator_klinike (id,klinika_id) values (2,1);

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (3,'Marko','Markovic','Lekar1','lekar1',1,3424234242212,false);
insert into lekar (id,od,do,klinika) values (3,'1998-12-31 23:59:59','1999-12-31 12:59:59',1);
update lekar set tip_pregleda = 1 where id = 3;

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (6,'Marija','Maric','Lekar2','lekar2',1,4567298242212,true);
insert into lekar (id,od,do,klinika) values (6,'1998-12-31 23:59:59','1999-12-31 12:59:59',1);
update lekar set tip_pregleda = 2 where id = 6;

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (5,'Marica','Maric','Sestra1','sestra1',2,4567234242212,true);
insert into medicinska_sestra (id,od,do,klinika) values (5,'1998-12-31 23:59:59','1999-12-31 12:59:59',1);



insert into klinika_ocene (klinika_id,ocene) values (1,4);
insert into klinika_ocene (klinika_id,ocene) values (1,2);

insert into lekar_ocene (lekar_id,ocene) values (3,4);
insert into lekar_ocene (lekar_id,ocene) values (3,5);

insert into dijagnoza (id) values (1);

insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id) values (1,'2020-10-10 17:00:00',50,1,3,1,1,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (2,'2020-10-10 20:00:00',50,1,3,1,1,2,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (3,'2020-10-10 19:00:00',50,1,3,1,1,2,2,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (4,'2020-10-10 19:00:00',50,1,6,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (6,'2020-10-10 12:00:00',50,1,6,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (7,'2020-10-09 12:00:00',50,1,6,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (8,'2020-10-11 12:00:00',50,1,6,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (9,'2020-10-13 12:00:00',50,1,6,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (10,'2020-10-06 12:00:00',50,1,6,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,tip_id,karton_id) values (11,'2020-10-10 14:00:00',50,1,6,2,3);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id,dijagnoza_id) values (5,'2020-09-10 19:00:00',50,1,3,2,1,2,1,1);
