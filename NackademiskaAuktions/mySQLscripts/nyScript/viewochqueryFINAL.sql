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

----------------------------------------------
---- 9

CREATE VIEW Provision AS
<<<<<<< HEAD
SELECT auktionsnummer, max(bud.kronor)*(leverantör.provisionsprocent/100) AS 'Provision', sluttid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN Leverantör ON leverantör.orgnummer=produkt.leverantör
INNER JOIN Bud ON auktionsnummer=bud.auktion
GROUP BY auktionsnummer
UNION ALL
SELECT auktionsnummer,max(Bud)*(leverantör.provisionsprocent/100) AS 'Provision', Budtid FROM auktionshistorik

INNER JOIN Leverantör ON auktionshistorik.leverantör=leverantör.OrgNummer
GROUP BY auktionshistorik.auktionsnummer;

select * from provision;
SELECT date_format(sluttid,'%b %Y') AS 'Månad', ROUND(sum(provision),2) AS 'Provision' FROM provision group by month(sluttid);
=======
SELECT auktionsnummer, max(bud.kronor)*(leverantÃ¶r.provisionsprocent/100) AS 'Provision', sluttid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN LeverantÃ¶r ON leverantÃ¶r.orgnummer=produkt.leverantÃ¶r
INNER JOIN Bud ON auktionsnummer=bud.auktion
GROUP BY auktionsnummer
UNION ALL
SELECT auktionsnummer,max(Bud)*(leverantÃ¶r.provisionsprocent/100) AS 'Provision', Budtid FROM auktionshistorik

INNER JOIN LeverantÃ¶r ON auktionshistorik.leverantÃ¶r=leverantÃ¶r.OrgNummer
GROUP BY auktionshistorik.auktionsnummer;

select * from provision;
SELECT date_format(sluttid,'%b %Y') AS 'MÃ¥nad', ROUND(sum(provision),2) AS 'Provision' FROM provision group by month(sluttid);
>>>>>>> branch 'master' of https://github.com/NackademinDBASGroup5/NackademiskaAuktions.git
