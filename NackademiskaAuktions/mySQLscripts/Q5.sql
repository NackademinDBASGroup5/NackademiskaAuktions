-- Vilka auktioner avslutas under ett visst datumintervall? Samt vad blir provisionen för varje auktion inom det intervallet?
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
