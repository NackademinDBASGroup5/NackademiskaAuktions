use auktion;

/*funkar inte*/
CREATE VIEW AktuellaAuktioner AS

SELECT bud.auktion, bud.kronor, CONCAT(kund.förnamn," ", kund.efternamn) AS Kund
FROM bud
inner join kund ON bud.kund = kund.personnummer
 WHERE (auktion,kronor) IN 
( SELECT auktion, MAX(kronor)
  FROM bud
  GROUP BY auktion
);

SELECT * FROM AktuellaAuktioner;

-- -----------------------
/*funkar*/
CREATE VIEW GamlaBud AS 
SELECT auktionsnummer, Bud, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , Budtid FROM auktionshistorik
INNER JOIN Kund ON kund.personnummer=auktionshistorik.kund
ORDER BY Bud ASC;

select * from gamlabud;
select * from auktionshistorik;


-- ----------------------
/*funkar*/
CREATE VIEW AktivaBud AS
SELECT produkt.namn as 'Vara', bud.auktion, bud.kronor, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , tid from bud
INNER JOIN Kund ON kund.personnummer=bud.kund
inner join auktion on auktion.auktionsnummer = bud.auktion
inner join produkt on produkt.id = auktion.produkt 
ORDER BY kronor ASC;

select * from aktivabud;

-- --------------------------------
/*funkar inte*/
drop view if exists VunnaAuktioner;
CREATE VIEW VunnaAuktioner AS 
SELECT auktionsnummer, bud as VinnandeBud, CONCAT(kund.förnamn, ' ', kund.efternamn) as vinnare, kund.personnummer  FROM auktionshistorik 
inner join kund on kund.personnummer = auktionshistorik.kund
WHERE (auktionsnummer, bud) IN 
( SELECT auktionsnummer, MAX(Bud)
  FROM auktionshistorik
  GROUP BY auktionsnummer
) order by auktionsnummer;


-- lista summan på vad som varje vinnare totalt ska pröjsa
SELECT * from VunnaAuktioner
;
SELECT auktionsnummer, MAX(Bud)
  FROM auktionshistorik
  GROUP BY auktionsnummer, bud;
select * from auktionshistorik;



-- -------------------
-- fråga 7 + avslutad auktion utan bud till historik, produkt kvar
-- listar alla auktioner utan bud, having pekar på specifikt auktionsnummer
SELECT DISTINCT auktionsnummer, produkt, utropspris, acceptPris, starttid,sluttid FROM auktion
WHERE NOT EXISTS(SELECT * FROM Bud WHERE auktion = auktion.auktionsnummer)
HAVING auktionsnummer=4;

select * from auktion;
SELECT * from bud;
