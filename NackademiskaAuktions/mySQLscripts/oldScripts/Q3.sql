USE auktion; 

-- Lista pågående auktioner samt kunna se det högsta budet och vilken kund som lagt det.
CREATE VIEW AktuellaAuktioner AS
SELECT auktion.auktionsnummer AS Auktion, produkt.namn AS Produkt, MAX(KRONOR) AS 'Högsta Bud', CONCAT(Kund.Förnamn, " ", Kund.Efternamn) AS Kund
FROM Bud
INNER JOIN auktion ON Bud.Auktion = Auktion.auktionsnummer
INNER JOIN Kund ON Bud.kund = Kund.Personnummer
INNER JOIN Produkt ON Auktion.produkt = Produkt.id
GROUP BY auktion.auktionsnummer;

SELECT * FROM AktuellaAuktioner;