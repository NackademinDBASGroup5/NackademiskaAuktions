-- Registrera en produkt
DROP Procedure registreraProdukt;

DELIMITER //
CREATE PROCEDURE registreraProdukt(IN leverantor int, IN beskrivning Text, IN bild blob, IN registreringsdatum Date, IN sald boolean)
BEGIN

INSERT INTO produkt (leverantör,beskrivning,bild,registreringsDatum,såld) VALUES (leverantor,beskrivning,bild,registreringsdatum,sald);

END //
DELIMITER ;

