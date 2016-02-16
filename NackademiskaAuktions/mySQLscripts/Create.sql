Create Database Auktion;
-- drop database auktion;
use  auktion; 

Create table Leverantör(
OrgNummer int,
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
leverantör int not null,
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
foreign key (produkt) references produkt(id),
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
foreign key (produkt) references produkt(id)
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
telefon varchar(25),
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
foreign key (auktion) references auktion(auktionsnummer),
foreign key (kund) references kund(personnummer) 
);
create index aktuellabud on bud(kronor);

create table auktionshistorik(
auktion int not null,
kronor int not null,
produkt int,
kund char(12),
tid datetime,
primary key (auktion, kronor),
foreign key (produkt) references produkt(id),
foreign key (kund)  references kund(personnummer)
);
create index kundhistorik on auktionshistorik(kund);
create index penghistorik on auktionshistorik(kronor);

create table produktHistorik (
id int,
leverantör int not null,
beskrivning Text,
bild blob,
registreringsDatum Date not null,
primary key (id),
foreign key (leverantör) references Leverantör(orgnummer)
);
create index produktleverantör on produktHistorik(leverantör);
create index produktregistrering on produktHistorik(registreringsdatum);
