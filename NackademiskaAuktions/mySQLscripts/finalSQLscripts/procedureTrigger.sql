USE Auktion;


/*testad funkar*/
DROP PROCEDURE IF EXISTS registreraProdukt;

DELIMITER //
CREATE PROCEDURE registreraProdukt(IN leverantor int,in namn varchar(50), IN beskrivning Text, IN bild blob, IN registreringsdatum Date)
BEGIN

INSERT INTO produkt (leverantör,namn, beskrivning,bild,registreringsDatum) VALUES (leverantor, namn, beskrivning,bild,registreringsdatum);

END //
DELIMITER ;

CALL registreraProdukt(1198620963,'Hammare', 'En helt ny hammare från clas ohlsson', null, now());
SELECT * from produkt;

-- ----------------------------------------
/*testad funkar*/
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

call startAuktion(8,50,500,now(),'2016-02-19 10:55:50');
select * from auktion;

-- ------------------------------------------
/*testad funkar*/
drop procedure if exists datumintervall;
DELIMITER //
CREATE PROCEDURE datumintervall(IN startTiden date, IN slutTiden datetime)
BEGIN

SELECT Produkt, produkt.namn,max(bud.kronor)*(leverantör.provisionsprocent/100) AS 'Provision', sluttid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN Leverantör ON leverantör.orgnummer=produkt.leverantör
INNER JOIN Bud ON auktion.auktionsnummer=bud.auktion
WHERE  auktion.sluttid BETWEEN startTiden AND slutTiden
GROUP BY produkt
UNION ALL
SELECT DISTINCT Produkt, produkt.namn,null,sluttid FROM Auktion
INNER JOIN Produkt ON produkt.id=produkt
WHERE NOT EXISTS(SELECT * FROM bud WHERE auktion.auktionsnummer=bud.auktion)
AND auktion.sluttid BETWEEN startTiden AND slutTiden
GROUP BY produkt;
END //
DELIMITER ;

-- -----------------------------

/*testad funkar*/
SET GLOBAL event_scheduler = ON;
SHOW PROCESSLIST;

DROP EVENT IF EXISTS `auktion_slut`; 

DELIMITER $$

CREATE 
	EVENT IF NOT EXISTS auktion_slut  
	ON SCHEDULE EVERY 1 MINUTE STARTS now()
	
	DO BEGIN
        
        DELETE FROM auktion
        WHERE auktion.sluttid <= current_timestamp();
        
	END $$

DELIMITER ;

-- --------------------
/*testad funkar*/
delimiter //
create trigger köpdirekt after insert on bud

for each row
begin 

delete from auktion where new.auktion = auktion.auktionsnummer and new.kronor >= auktion.AcceptPris;

end// 

delimiter ;

Select * from auktion;
INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(9,500,198802080112, now());
DELETe from auktion where auktionsnummer = 8;

-- -------------------------------------
-- ska funka
DROP TRIGGER IF EXISTS auktionTasBort;
DELIMITER //
CREATE TRIGGER AuktionTasbort BEFORE DELETE ON
Auktion
 FOR EACH ROW
 BEGIN

IF EXISTS (SELECT DISTINCT auktionsnummer, produkt, utropspris, acceptPris, starttid,sluttid FROM auktion
WHERE NOT EXISTS(SELECT * FROM Bud WHERE auktion = auktion.auktionsnummer)
HAVING auktionsnummer=Old.auktionsnummer) THEN

insert into auktionshistorik(leverantör,kund, auktionsnummer, produktnamn, Produktregistreringsdatum,Produktbeskrivning, bild, utropspris, acceptpris, starttid, sluttid, bud, budtid)
select  produkt.leverantör, kund.personnummer, auktion.auktionsnummer, produkt.namn, produkt.registreringsdatum,produkt.beskrivning, produkt.bild, auktion.utropspris, auktion.AcceptPris, auktion.Starttid, auktion.sluttid, bud.kronor, bud.tid from produkt
 inner join auktion on auktion.produkt = produkt.id
 inner join bud on bud.auktion = auktion.auktionsnummer
 inner join kund on kund.personnummer = bud.kund
 where auktion.auktionsnummer = old.auktionsnummer;

ELSE 

insert into auktionshistorik(leverantör,kund, auktionsnummer, produktnamn, Produktregistreringsdatum,Produktbeskrivning, bild, utropspris, acceptpris, starttid, sluttid, bud, budtid)
select  produkt.leverantör, kund.personnummer, auktion.auktionsnummer, produkt.namn, produkt.registreringsdatum,produkt.beskrivning, produkt.bild, auktion.utropspris, auktion.AcceptPris, auktion.Starttid, auktion.sluttid, null, null from produkt
 inner join auktion on auktion.produkt = produkt.id
 inner join bud on bud.auktion = auktion.auktionsnummer
 inner join kund on kund.personnummer = bud.kund
 where auktion.auktionsnummer = old.auktionsnummer;

DELETE FROM Produkt where produkt.id = Old.produkt;

END IF;
 
 END //
 
DELIMITER ;