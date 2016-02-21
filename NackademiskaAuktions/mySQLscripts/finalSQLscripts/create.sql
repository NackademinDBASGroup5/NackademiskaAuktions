drop database auktion;
Create Database Auktion;

use  auktion; 

Create table Leverantör(
OrgNummer varchar(25),
Namn varchar(50) not null,
provisionsprocent float,
primary key (OrgNummer)
);
create index Leverantörsnamn on leverantör(Namn);

create table kategori(
kod int auto_increment,
namn varchar(50) not null,
primary key (kod)
);
create index kategorityp on kategori(namn);

create table produkt (
id int auto_increment,
leverantör varchar(25) not null,
namn varchar(50) not null, 
beskrivning Text,
bild blob,
registreringsDatum Date not null,
primary key (id),
foreign key (leverantör) references Leverantör(orgnummer)
);
create index produktleverantör on produkt(leverantör);
create index produktregistrering on produkt(registreringsdatum);

create table produktKategori(
produkt int,
kategori int,
primary key (produkt, kategori),
foreign key (produkt) references produkt(id) on delete cascade,
foreign key (kategori) references kategori(kod)
);


create table auktion (
auktionsnummer int auto_increment,
Produkt int,
utropspris int not null,
AcceptPris int,
Starttid datetime,
sluttid datetime,
primary key (auktionsnummer),
foreign key (produkt) references produkt(id) on delete cascade
);
create index acceptpris on auktion(acceptpris);
create index utroppris on auktion(utropspris);
create index sluttid on auktion(sluttid);
create index starttid on auktion(starttid);

create table kund(
personnummer char(12),
förnamn varchar(50) not null,
efternamn varchar(50) not null,
gatuadress varchar(50),
postnummer char(5) not null,
ort varchar(50),
epost varchar(50),
telefon varchar(15),
primary key (personnummer)
);
create index kundnamn on kund(efternamn);
create index kundort on kund(ort);

create table bud(
auktion int,
kronor int not null,
kund char(12),
tid datetime not null,
primary key (auktion, kronor),
foreign key (auktion) references auktion(auktionsnummer) on delete cascade,
foreign key (kund) references kund(personnummer) 
);
create index aktuellabud on bud(kronor);


create table auktionshistorik(
Arkiveringsnummer int auto_increment,
leverantör varchar(25) not null,
kund char(12),
auktionsnummer int,
Produktnamn varchar(50),
Produktregistreringsdatum Date,
Produktbeskrivning Text,
bild blob,
utropspris int not null,
AcceptPris int,
Starttid datetime,
sluttid datetime,
Bud int,
Budtid datetime,
primary key (Arkiveringsnummer),
foreign key (leverantör) references Leverantör(orgnummer),
foreign key (kund)  references kund(personnummer)
);
create index kundhistorik on auktionshistorik(kund);
create index penghistorik on auktionshistorik(Bud);
