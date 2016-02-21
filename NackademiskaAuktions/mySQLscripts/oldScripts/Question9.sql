-- Vad den totala provisionen är per månad.

-- drop view provisionpermånad;
SELECT max(bud.kronor), auktion.produkt FROM Auktion
INner join produkt ON auktion.produkt=produkt.id
Inner join bud ON bud.auktion = Auktion.auktionsnummer;


CREATE VIEW ProvisionPerMånad AS
SELECT Auktion.auktionsnummer, Produkt.id AS Produkt, produkt.namn AS Namn,
 ROUND(max(bud.kronor)*(leverantör.provisionsprocent/100),2) AS 'Provision',
 date_format(sluttid,'%b %Y') AS Tid FROM auktion
INNER JOIN Produkt ON produkt.id=produkt
INNER JOIN Leverantör ON leverantör.orgnummer=produkt.leverantör
INNER JOIN Bud ON auktionsnummer=bud.auktion
GROUP BY auktionsnummer
UNION

SELECT Auktionshistorik.auktion,produkthistorik.id AS Produkt, produkthistorik.namn AS Namn,
 ROUND(Kronor*(leverantör.provisionsprocent/100),2) AS Provision,
  DATE_FORMAT(tid, '%b %Y') AS Tid FROM AuktionsHistorik
INNER JOIN  ProduktHistorik ON auktionshistorik.produkt = produkthistorik.id
INNER JOIN Leverantör ON leverantör.OrgNummer=produkthistorik.leverantör
GROUP BY auktion;

-- korrekt med sorteringen?
SELECT SUM(provision), tid FROM ProvisionPerMånad GROUP BY tid;