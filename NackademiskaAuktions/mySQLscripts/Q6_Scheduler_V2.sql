USE auktion;

SET GLOBAL event_scheduler = ON;
SHOW PROCESSLIST;

DROP EVENT IF EXISTS `auktion_slut`; 

DELIMITER $$

CREATE 
	EVENT IF NOT EXISTS auktion_slut  
	ON SCHEDULE EVERY 1 MINUTE STARTS now()
	
	DO BEGIN
        
        DELETE FROM auktion
        WHERE auktion.sluttid <= now();
        
	END $$

DELIMITER ;