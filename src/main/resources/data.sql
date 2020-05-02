
insert into user (id, email,  password,  ime,  prezime,uloga,broj_osiguranika, adresa, grad, drzava ,prvi_put) values (4l,'pacijent','pacijent','Marko','Markovic',3,'1111123224321','Radiceva 6','Novi Sad','Srbija',false);
insert into pacijent (id) values (4l);

insert into klinicki_centar (id,naziv) values (1,'Klinicki centar Novi Sad');

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (1,'Ivan','Ivanovic','AdminKC1','adminkc1',4,2322432322432,false);
insert into administrator_klinickog_centra (id,kc) values (1,1);

insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (1,'Novi Sad','Klinika Centar','Nema opisa',1);
insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (2,'Liman','Klinika Liman','Nema opisa',1);

insert into sala (klinika_id,broj_sale,naziv) values (1,1,'Operaciona');
insert into sala (klinika_id,broj_sale,naziv) values (2,1,'Operaciona');
insert into sala (klinika_id,broj_sale,naziv) values (2,2,'Operaciona');

insert into tip_pregleda (id,naziv,klinika) values (1,'Sistematski',1);
insert into tip_pregleda (id,naziv,klinika) values (2,'Klinicki',1);
insert into tip_pregleda (id,naziv,klinika) values (3,'Oftamoloski',2);

insert into cenovnik (id,klinika_id) values (1,1);
insert into stavka_cenovnika (cenovnik_id,id,cena,usluga) values (1,1,2000,'Opsti pregled');

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (2,'Jovan','Jovanovic','AdminK1','admink1',0,3242543242541,true);
insert into administrator_klinike (id,klinika_id) values (2,1);

insert into user (id,ime,prezime,email,password,uloga,broj_osiguranika,prvi_put) values (3,'Marko','Markovic','Lekar1','lekar1',1,3424234242212,true);
insert into lekar (id,od,do,klinika) values (3,'1998-12-31 23:59:59','1999-12-31 12:59:59',1);

insert into karton (id,ime,prezime,pol,datum_rodjenja,krvna_grupa) values (1,'Marko','Markovic',0,'1998-12-31 23:59:59','A+');
update pacijent set karton_id = 1 where id = 4l;


insert into klinika_ocene (klinika_id,ocene) values (1,4);
insert into klinika_ocene (klinika_id,ocene) values (1,2);

insert into lekar_ocene (lekar_id,ocene) values (3,4);
insert into lekar_ocene (lekar_id,ocene) values (3,5);

insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id) values (1,'2020-10-10 17:00:00',50,1,3,1,1,1);
insert into pregled(id,datum,trajanje,cena_id,lekar_id,sala_klinika_id,sala_broj_sale,tip_id,karton_id) values (2,'2020-10-10 19:00:00',50,1,3,1,1,2,1);

