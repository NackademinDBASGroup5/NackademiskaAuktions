USE auktion;

SET GLOBAL event_scheduler = ON;
SHOW PROCESSLIST;

DROP EVENT IF EXISTS `auktion_slut`; 

DELIMITER $$

CREATE EVENT IF NOT EXISTS auktion_slut ON SCHEDULE EVERY 1 second STARTS now()
	
	DO BEGIN
	    
        -- exempelkod för test
        INSERT INTO auktionshistorik (auktion, kronor, produkt, kund, tid)
        SELECT auktion.auktionsnummer, MAX(bud.kronor), auktion.produkt, bud.kund, auktion.sluttid-- now()
        FROM auktion
        INNER JOIN bud
        ON auktion.auktionsnummer = bud.auktion
        WHERE auktion.sluttid >= now()
        GROUP BY auktionsnummer;
        
	END $$

DELIMITER ;
