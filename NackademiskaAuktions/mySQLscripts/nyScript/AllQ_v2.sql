USE Auktion;
--  1

DELIMITER //
CREATE PROCEDURE registreraProdukt(IN leverantor int,in namn varchar(50), IN beskrivning Text, IN bild blob, IN registreringsdatum Date)
BEGIN

INSERT INTO produkt (leverantör,namn, beskrivning,bild,registreringsDatum) VALUES (leverantor, namn, beskrivning,bild,registreringsdatum);

END //
DELIMITER ;
select * from produkt;

-- 2
DROP PROCEDURE IF EXISTS startAuktion;
delimiter //

create procedure startAuktion (in produktin int, in utropsprisin int, in acceptprisin int, in starttidin datetime, in sluttidin datetime) 

begin 
if  not exists (select produkt from auktion where produkt = produktin) then
if  acceptprisin is not null then
insert into auktion(produkt, utropspris, acceptpris, starttid, sluttid)
values (produktin, utropsprisin, acceptprisin, starttidin, sluttidin);
else 
insert into auktion(produkt, utropspris, starttid, sluttid)
values (produktin, utropsprisin, starttidin, sluttidin);
end if;
end if;
end //

delimiter ;
call startAuktion (1, 100, null, now(), '2016-02-17 13:50:00');
call startAuktion (1, 200, null, '2015-02-02 12:00:00', '2015-02-22 12:00:00');
select * from auktion;


-- 3
CREATE VIEW AktuellaAuktioner AS
SELECT auktion.auktionsnummer AS Auktion, produkt.namn AS Produkt, MAX(KRONOR) AS 'Högsta Bud', CONCAT(Kund.Förnamn, " ", Kund.Efternamn) AS Kund
FROM Bud
INNER JOIN auktion ON Bud.Auktion = Auktion.auktionsnummer
INNER JOIN Kund ON Bud.kund = Kund.Personnummer
INNER JOIN Produkt ON Auktion.produkt = Produkt.id
GROUP BY auktion.auktionsnummer;

SELECT * FROM AktuellaAuktioner;
-- 4
CREATE VIEW GamlaBud AS 
SELECT auktionshistorik.auktionsnummer, Bud, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , Budtid FROM auktionshistorik
INNER JOIN Kund ON kund.personnummer=auktionshistorik.kund
ORDER BY Bud ASC;

select * from gamlabud;
select * from auktionshistorik;

-- INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(3,61000,196808190697, '2015-02-18 22:30:50');
-- select * from auktionshistorik;
-- 4
CREATE VIEW AktivaBud AS
SELECT produkt.namn as 'Vara', bud.auktion, bud.kronor, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , tid from bud
INNER JOIN Kund ON kund.personnummer=bud.kund
inner join auktion on auktion.auktionsnummer = bud.auktion
inner join produkt on produkt.id = auktion.produkt 
ORDER BY kronor ASC;

select * from aktivabud;

DROP PROCEDURE datumintervall;
-- 5
DELIMITER //
CREATE PROCEDURE datumintervall(IN startTid date, IN slutTid datetime)
BEGIN

SELECT Produkt, produkt.namn,max(bud.kronor)*(leverantör.provisionsprocent/100) AS 'Provision', sluttid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN Leverantör ON leverantör.orgnummer=produkt.leverantör
INNER JOIN Bud ON auktion.auktionsnummer=bud.auktion
WHERE  auktion.sluttid BETWEEN startTid AND slutTid
GROUP BY produkt
UNION ALL
SELECT DISTINCT Produkt, produkt.namn,null,sluttid FROM Auktion
INNER JOIN Produkt ON produkt.id=produkt
WHERE NOT EXISTS(SELECT * FROM bud WHERE auktion.auktionsnummer=bud.auktion)
AND auktion.sluttid BETWEEN startTid AND slutTid
GROUP BY produkt;
END //
DELIMITER ;




-- drop procedure datumintervall;
call datumintervall('2016-02-17', '2016-03-21 21:00:00');

-- 6
select * from auktion;
select * from auktionshistorik;

SET GLOBAL event_scheduler = ON;
SHOW PROCESSLIST;

DROP EVENT IF EXISTS `auktion_slut`; 

DELIMITER $$

CREATE 
	EVENT IF NOT EXISTS auktion_slut  
	ON SCHEDULE EVERY 1 MINUTE STARTS now()
	
	DO BEGIN
        
        DELETE FROM auktion
        WHERE auktion.sluttid <= now();
        
	END $$

DELIMITER ;


-- fråga 7 + avslutad auktion utan bud till historik, produkt kvar
-- listar alla auktioner utan bud, having pekar på specifikt auktionsnummer
SELECT DISTINCT auktionsnummer, produkt, utropspris, acceptPris, starttid,sluttid FROM auktion
WHERE NOT EXISTS(SELECT * FROM Bud WHERE auktion = auktion.auktionsnummer)
HAVING auktionsnummer=4;

-- drop table auktionshistorik;

-- drop trigger auktiontasbort;

/* Trigger som bestämmer vad som händer när en auktion tas bort. Om auktionen inte har något bud ligger produkten kvar och auktionen lagras
i historiken med null-värde på kund, bud och budtid. Om auktionen HAR bud så lagras alla buden i historiken samt att auktionen tas bort    */
DELIMITER //
CREATE TRIGGER AuktionTasbort BEFORE DELETE ON
Auktion
 FOR EACH ROW
 BEGIN

IF EXISTS (SELECT DISTINCT auktionsnummer, produkt, utropspris, acceptPris, starttid,sluttid FROM auktion
WHERE NOT EXISTS(SELECT * FROM Bud WHERE auktion = auktion.auktionsnummer)
HAVING auktionsnummer=Old.auktionsnummer) THEN

insert into auktionshistorik(Produktid, leverantör, namn, beskrivning, bild, registreringsDatum , auktionsnummer, utropspris, AcceptPris, Starttid, sluttid, kronor, kund, tid)
 select id, leverantör, namn, beskrivning, bild, registreringsdatum, auktionsnummer, utropspris, AcceptPris, Starttid, sluttid, null, null, null from produkt
 inner join auktion on auktion.produkt = produkt.id
 where auktion.auktionsnummer = Old.auktionsnummer;

ELSE 


insert into auktionshistorik(Produktid, leverantör, namn, beskrivning, bild, registreringsDatum , auktionsnummer, utropspris, AcceptPris, Starttid, sluttid, kronor, kund, tid)
 select id, leverantör, namn, beskrivning, bild, registreringsdatum, auktionsnummer, utropspris, AcceptPris, Starttid, sluttid, kronor, kund, tid from produkt
 inner join auktion on auktion.produkt = produkt.id
 inner join bud on bud.auktion = auktion.auktionsnummer
 where auktion.auktionsnummer = Old.auktionsnummer;

DELETE FROM Produkt where produkt.id = Old.produkt;

END IF;



 
 END //
DELIMITER ;


-- Trigger som tar bort auktionen om acceptpriser uppnås
delimiter //
create trigger köpdirekt after insert on bud

for each row
begin 

delete from auktion where new.auktion = auktion.auktionsnummer and new.kronor >= auktion.AcceptPris;

end// 

delimiter ;


-- 8
CREATE VIEW VunnaAuktioner AS 
SELECT auktionsnummer, Bud as VinnandeBud, CONCAT(kund.förnamn, ' ', kund.efternamn) as vinnare FROM auktionshistorik 
inner join kund on kund.personnummer = auktionshistorik.kund
WHERE (auktionsnummer, Bud) IN 
( SELECT auktionsnummer, MAX(Bud)
  FROM auktionshistorik
  GROUP BY auktionsnummer
) order by auktionsnummer;

-- lista summan på vad som varje vinnare totalt ska pröjsa
SELECT SUM(vinnandebud), vinnare from VunnaAuktioner
GROUP BY Vinnare;

select * from vunnaauktioner;

-- 9
CREATE VIEW Provision AS
SELECT auktionsnummer, max(bud.kronor)*(leverantör.provisionsprocent/100) AS 'Provision', sluttid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN Leverantör ON leverantör.orgnummer=produkt.leverantör
INNER JOIN Bud ON auktionsnummer=bud.auktion
GROUP BY auktionsnummer
UNION ALL
SELECT auktionsnummer,max(Bud)*(leverantör.provisionsprocent/100) AS 'Provision', Budtid FROM auktionshistorik

INNER JOIN Leverantör ON auktionshistorik.leverantör=leverantör.OrgNummer
GROUP BY auktionshistorik.auktionsnummer;

-- select * from provision WHERE month(sluttid) = 2;

-- SELECT date_format(sluttid,'%b %Y') AS 'Månad', ROUND(sum(provision),2) AS 'Provision' FROM provision group by month(sluttid);

select * from auktion;

select * FROM bud;