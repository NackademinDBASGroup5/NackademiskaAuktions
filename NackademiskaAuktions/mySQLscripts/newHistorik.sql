create table auktionshistorik(
auktionsnummer int,
utropspris int not null,
AcceptPris int,
Starttid datetime,
sluttid datetime,
kronor int,
kund char(12),
tid datetime,
Produktid int,
leverantör varchar(25) not null,
namn varchar(50),
beskrivning Text,
bild blob,
registreringsDatum Date,
foreign key (leverantör) references Leverantör(orgnummer),
foreign key (kund)  references kund(personnummer)
);
create index kundhistorik on auktionshistorik(kund);
create index penghistorik on auktionshistorik(kronor);

insert into auktionshistorik(Produktid, leverantör, namn, beskrivning, bild, registreringsDatum , auktionsnummer, utropspris, AcceptPris, Starttid, sluttid, kronor, kund, tid)
 select id, leverantör, namn, beskrivning, bild, registreringsdatum, auktionsnummer, utropspris, AcceptPris, Starttid, sluttid, kronor, kund, tid from produkt
 inner join auktion on auktion.produkt = produkt.id
 inner join bud on bud.auktion = auktion.auktionsnummer
 where auktion.auktionsnummer = 1 ;
