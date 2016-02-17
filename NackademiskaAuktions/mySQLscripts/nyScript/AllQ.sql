

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
