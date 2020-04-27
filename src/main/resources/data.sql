insert into tip_pregleda (id,naziv) values (1l,'Sistematski');
insert into tip_pregleda (id,naziv) values (2l,'Klinicki');
insert into tip_pregleda (id,naziv) values (3l,'Oftamoloski');
insert into sala (id,naziv) values (1,'Operaciona');


insert into user (id, email,  password,  ime,  prezime,uloga,broj_osiguranika, adresa, grad, drzava) values (4l,'pacijent','pacijent','Marko','Markovic',3,'11111','Radiceva 6','Novi Sad','Srbija');
insert into pacijent (id) values (4l);

insert into klinicki_centar (id,naziv) values (1,'Klinicki centar Novi Sad');

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika) values (1,'Ivan','Ivanovic','AdminKC1','adminkc1',4,232243);
insert into administrator_klinickog_centra (id,kc) values (1,1);

insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (1,'Novi Sad','Klinika Centar','Nema opisa',1);

insert into cenovnik (id,klinika_id) values (1,1);
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (1,1,2000,'Opsti pregled');

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika) values (2,'Jovan','Jovanovic','AdminK1','admink1',0,324254);
insert into administrator_klinike (id,klinika_id) values (2,1);

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika) values (3,'Marko','Markovic','Lekar1','lekar1',1,34242);
insert into lekar (id,od,do,klinika_id) values (3,'1998-12-31 23:59:59','1999-12-31 12:59:59',1);

insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa) values (1,'Marko','Markovic',0,'1998-12-31 23:59:59','A+');
update pacijent set karton_id = 1 where id = 4l;
