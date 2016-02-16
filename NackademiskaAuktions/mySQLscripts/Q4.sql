-- Se budhistoriken på en viss auktion, samt vilka kunder som lagt buden.
CREATE VIEW GamlaBud AS 
SELECT auktionshistorik.auktion, Kronor, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , tid FROM auktionshistorik
INNER JOIN Kund ON kund.personnummer=auktionshistorik.kund
ORDER BY kronor ASC;

CREATE VIEW AktivaBud AS
SELECT bud.auktion, kund,CONCAT(kund.förnamn,' ',kund.efternamn) AS 'Namn' , tid from bud
INNER JOIN Kund ON kund.personnummer=bud.kund
ORDER BY kronor ASC;