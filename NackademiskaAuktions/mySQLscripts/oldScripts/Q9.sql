
-- DROP VIEW Provision;

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

