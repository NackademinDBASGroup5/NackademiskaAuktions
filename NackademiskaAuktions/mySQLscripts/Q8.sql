-- Lista alla vunna auktioner och dess vinnare
CREATE VIEW VunnaAuktioner AS 
SELECT auktion, kronor as VinnandeBud, CONCAT(kund.förnamn, ' ', kund.efternamn) as vinnare FROM auktionshistorik 
inner join kund on kund.personnummer = auktionshistorik.kund
WHERE (auktion, kronor) IN 
( SELECT auktion, MAX(kronor)
  FROM auktionshistorik
  GROUP BY auktion
) order by auktion;


-- lista summan på vad som varje vinnare totalt ska pröjsa
SELECT SUM(vinnandebud), vinnare from VunnaAuktioner
GROUP BY Vinnare;
