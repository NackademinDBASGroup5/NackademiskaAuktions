DELIMITER //
CREATE PROCEDURE registreraProdukt(IN leverantor int, IN beskrivning Text, IN bild blob, IN registreringsdatum Date, IN sald boolean)
BEGIN

INSERT INTO produkt (leverantör,beskrivning,bild,registreringsDatum,såld) VALUES (leverantor,beskrivning,bild,registreringsdatum,sald);

END //
DELIMITER ;


delimiter //
create procedure startAuktion (in produktin int, in utropsprisin int, in acceptprisin int, in starttidin datetime, in sluttidin datetime) 

begin 
if  acceptprisin is not null then
insert into auktion(produkt, utropspris, acceptpris, starttid, sluttid)
values (produktin, utropsprisin, acceptprisin, starttidin, sluttidin);
else 
insert into auktion(produkt, utropspris, starttid, sluttid)
values (produktin, utropsprisin, starttidin, sluttidin);
end if;
end//

delimiter ;

CREATE VIEW AktuellaAuktioner AS
SELECT auktion.auktionsnummer AS Auktion, produkt.namn AS Produkt, MAX(KRONOR) AS 'Högsta Bud', CONCAT(Kund.Förnamn, " ", Kund.Efternamn) AS Kund
FROM Bud
INNER JOIN auktion ON Bud.Auktion = Auktion.auktionsnummer
INNER JOIN Kund ON Bud.kund = Kund.Personnummer
INNER JOIN Produkt ON Auktion.produkt = Produkt.id
GROUP BY auktion.auktionsnummer;

SELECT * FROM AktuellaAuktioner;

CREATE VIEW GamlaBud AS 
SELECT auktionshistorik.auktion, Kronor, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , tid FROM auktionshistorik
INNER JOIN Kund ON kund.personnummer=auktionshistorik.kund
ORDER BY kronor ASC;

CREATE VIEW AktivaBud AS
SELECT bud.auktion, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , tid from bud
INNER JOIN Kund ON kund.personnummer=bud.kund
ORDER BY kronor ASC;


DELIMITER //
CREATE PROCEDURE datumintervall(IN start date, IN slut date)
BEGIN
SELECT Produkt, produkt.namn,max(bud.kronor)*(leverantör.provisionsprocent/100) AS 'Provision', sluttid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN Leverantör ON leverantör.orgnummer=produkt.leverantör
INNER JOIN Bud ON auktionsnummer=bud.auktion
WHERE  auktion.sluttid>=start AND auktion.sluttid<=slut
GROUP BY produkt ;
END //
DELIMITER ;


CREATE VIEW VunnaAuktioner AS 
SELECT auktion, kronor as VinnandeBud, CONCAT(kund.förnamn, ' ', kund.efternamn) as vinnare FROM auktionshistorik 
inner join kund on kund.personnummer = auktionshistorik.kund
WHERE (auktion, kronor) IN 
( SELECT auktion, MAX(kronor)
  FROM auktionshistorik
  GROUP BY auktion
) order by auktion;


-- lista summan på vad som varje vinnare totalt ska pröjsa
SELECT SUM(vinnandebud), vinnare from VunnaAuktioner
GROUP BY Vinnare;

CREATE VIEW Provision AS
SELECT auktionsnummer, max(bud.kronor)*(leverantör.provisionsprocent/100) AS 'Provision', sluttid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN Leverantör ON leverantör.orgnummer=produkt.leverantör
INNER JOIN Bud ON auktionsnummer=bud.auktion
GROUP BY auktionsnummer
UNION ALL
SELECT auktion,max(kronor)*(leverantör.provisionsprocent/100) AS 'Provision', tid FROM auktionshistorik
INNER JOIN produkthistorik ON produkthistorik.id=produkt
INNER JOIN Leverantör ON produkthistorik.leverantör=leverantör.OrgNummer
GROUP BY auktionshistorik.auktion;


SELECT date_format(sluttid,'%b %Y') AS 'Månad', ROUND(sum(provision),2) AS 'Provision' FROM provision group by month(sluttid);
