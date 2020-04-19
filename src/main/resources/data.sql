insert into tip_pregleda (id,naziv) values (1l,'Sistematski');
insert into tip_pregleda (id,naziv) values (2l,'Klinicki');
insert into tip_pregleda (id,naziv) values (3l,'Oftamoloski');
insert into sala (id,naziv) values (1,'Operaciona');
insert into klinicki_centar (id,naziv) values (1,'Klinicki centar Novi Sad');

insert into administrator_klinickog_centra (id,ime,prezime,username,password,kc) values (1,'Ivan','Ivanovic','AdminKC1','adminkc1',1);

insert into klinika (id,adresa,naziv,opis,klinicki_centar_id) values (1,'Novi Sad','Klinika Centar','Nema opisa',1);

insert into administrator_klinike (id,ime,prezime,username,password,klinika_id) values (2,'Jovan','Jovanovic','AdminK1','admink1',1);

insert into lekar (id,ime,prezime,username,password,od,do,klinika_id) values (3,'Marko','Markovic','Lekar1','lekar1','1998-12-31 23:59:59','1999-12-31 12:59:59',1);