USE auktion;
INSERT INTO Leverantör (OrgNummer, Namn, provisionsprocent) VALUES (1198620963, 'Gertrud Larssons dödsbo', 25.6); 
INSERT INTO Leverantör (OrgNummer, Namn, provisionsprocent) VALUES (8639782087, 'Sumpans korp-pingislag', 15.9); 
INSERT INTO Leverantör (OrgNummer, Namn, provisionsprocent) VALUES (2693787896, 'Solna kommun', 35.6); 

INSERT INTO Kategori (Namn) VALUES ('Antikt');
INSERT INTO Kategori (Namn) VALUES ('Barrock');
INSERT INTO Kategori (Namn) VALUES ('Modernt');

INSERT INTO Kategori (Namn) VALUES ('Media');
INSERT INTO Kategori (Namn) VALUES ('Konst');
INSERT INTO Kategori (Namn) VALUES ('Kläder');
INSERT INTO Kategori (Namn) VALUES ('Möblemang');

INSERT INTO produkt (Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (1198620963,'Divan','En divan från franska barrocken', null, current_date());
INSERT INTO produkt (Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (1198620963,'SNES','Ett komplett supernintendo med 270 spel' ,null, current_date());

INSERT INTO produkt (Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (8639782087,'Lagtröjor','Fjolårets lagtröjor, signerad av Gävle snookers' ,null, current_date());
INSERT INTO produkt (Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (8639782087, 'Pingisbord','Pingisbordet från finalen i Gnarp 2007, signerat av vinnarlaget. Det var inte sumpan.' ,null, current_date());

INSERT INTO produkt (Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (2693787896, 'Tavla','En ful tavla på en röd hund, hängades i en av nackademins lokaler' ,null, current_date());
INSERT INTO produkt (Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (2693787896,'Kaffebryggare','Toppmodern kaffebryggare' ,null, current_date());

INSERT INTO produktKategori (produkt, kategori) VALUES (1,2);
INSERT INTO produktKategori (produkt, kategori) VALUES (1,7);
INSERT INTO produktKategori (produkt, kategori) VALUES (2,3);
INSERT INTO produktKategori (produkt, kategori) VALUES (2,4);
INSERT INTO produktKategori (produkt, kategori) VALUES (3,6);
INSERT INTO produktKategori (produkt, kategori) VALUES (4,3);
INSERT INTO produktKategori (produkt, kategori) VALUES (4,7);
INSERT INTO produktKategori (produkt, kategori) VALUES (5,1);
INSERT INTO produktKategori (produkt, kategori) VALUES (5,5);
INSERT INTO produktKategori (produkt, kategori) VALUES (6,3);
INSERT INTO produktKategori (produkt, kategori) VALUES (6,7);


INSERT INTO auktion (Produkt, utropspris, Acceptpris, Starttid, sluttid) VALUES (1,999, 4000, now(), '2015-02-21 21:00:00');
INSERT INTO auktion (Produkt, utropspris, Acceptpris, Starttid, sluttid) VALUES (2,5000, 12000, now(), '2015-02-21 21:00:00');
INSERT INTO auktion (Produkt, utropspris, Acceptpris, Starttid, sluttid) VALUES (3,1, 800, now(), '2015-02-21 21:00:00');
INSERT INTO auktion (Produkt, utropspris, Acceptpris, Starttid, sluttid) VALUES (4,150, 999, now(), '2015-02-21 21:00:00');
INSERT INTO auktion (Produkt, utropspris, Acceptpris, Starttid, sluttid) VALUES (5,1, 25, now(), '2015-02-21 21:00:00');

INSERT INTO kund(personnummer, förnamn, Efternamn, gatuadress, postnummer, ort, epost, telefon) VALUES (198802080112, 'Robin', 'Simonsson', 'Redskapsvägen 34', '16243','Vällingby' ,'robin.sjolund@gmail.com', '0730832244');
INSERT INTO kund(personnummer, förnamn, Efternamn, gatuadress, postnummer, ort, epost, telefon) VALUES (194503680748, 'Thobbe', 'Patriksson', 'Gatan 80', '19687', 'Härjedalen', 'hejochhå@gmail.com', '0789632541');
INSERT INTO kund(personnummer, förnamn, Efternamn, gatuadress, postnummer, ort, epost, telefon) VALUES (196808190697, 'Anton', 'Oscarsson', 'Vägen 18', '18759','Mumindalen', 'greger@gmail.com', '074235689');

INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(1,1500,198802080112, '2015-02-17 21:25:04');
INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(1,1501,194503680748,'2015-02-17 21:25:30');
INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(1,2000,198802080112, '2015-02-17 22:30:50');

INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(2,6000,196808190697, '2015-02-18 22:30:50');
INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(2,7000,194503680748, '2015-02-19 14:30:00');
INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(2,10000,196808190697, '2015-02-20 21:12:00');

INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(3,10,198802080112, '2015-02-17 22:30:50');
INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(3,25,194503680748, '2015-02-17 22:30:50');

INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(5,2,196808190697, '2015-02-17 22:30:50');

INSERT INTO Produkthistorik(id, Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (1000, 1198620963, 'Nattlinne', 'Gertruds gammla nattlinne', null, '2014-10-12');
INSERT INTO Produkthistorik(id, Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (2000, 8639782087, 'Hammare', 'En hammare', null, '2014-10-15');
INSERT INTO Produkthistorik(id, Leverantör, namn, beskrivning, bild, registreringsDatum) VALUES (3000, 1198620963, 'Dator', 'En antik dator', null, '2014-10-20');

INSERT INTO Auktionshistorik(auktion, kronor, produkt, kund, tid) VALUES (1000, 500,1000,196808190697,'2014-11-01 15:30:00');
INSERT INTO Auktionshistorik(auktion, kronor, produkt, kund, tid) VALUES (1000, 1000,1000,194503680748,'2014-11-01 15:34:33');
INSERT INTO Auktionshistorik(auktion, kronor, produkt, kund, tid) VALUES (1000, 2000,1000,196808190697,'2014-11-01 15:35:20');
INSERT INTO Auktionshistorik(auktion, kronor, produkt, kund, tid) VALUES (1000, 10000,1000,198802080112,'2014-11-01 15:36:00');

INSERT INTO Auktionshistorik(auktion, kronor, produkt, kund, tid) VALUES (2000, 500,2000,194503680748,'2014-11-01 15:36:00');
INSERT INTO Auktionshistorik(auktion, kronor, produkt, kund, tid) VALUES (2000, 550,2000,198802080112,'2014-11-01 15:36:00');
INSERT INTO Auktionshistorik(auktion, kronor, produkt, kund, tid) VALUES (3000, 8000,3000,196808190697,'2014-11-01 15:36:00');
