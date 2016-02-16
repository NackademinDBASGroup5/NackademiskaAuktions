

delimiter //
create trigger köpdirekt after insert on bud

for each row
begin 

delete from auktion where new.auktion = auktion.auktionsnummer and new.kronor >= auktion.AcceptPris;

end// 

delimiter ;


delimiter //
create trigger gamlaBudTrigger after delete on bud
for each row
begin
 
insert into auktionshistorik(auktion, kronor,  kund, tid)
values (old.auktion, old.kronor,  old.kund, old.tid);

end//
delimiter ;


delimiter //
create trigger såldVara after delete on produkt

for each row
begin 
insert into produktHistorik(id, leverantör, namn, beskrivning, bild, registreringsDatum)
values (old.id, old.leverantör, old.namn, old.beskrivning, old.bild, old.registreringsDatum);

end//
delimiter ;



-- drop trigger köpdirektAuktion;

INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(1,690,196808190697, '2015-02-18 22:30:50');
select * from auktionshistorik;
INSERT INTO bud (Auktion, Kronor, kund, tid) VALUES(2,61000,196808190697, '2015-02-18 22:30:50');
select * from auktionshistorik;

delete from produkt where id = 1;

select * from produkt;
select * from auktion;
select * from bud;
select * from produkthistorik;

