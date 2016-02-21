use auktion;

delimiter //
create procedure startAuktion (in produktin int, in utropsprisin int, in acceptprisin int, in starttidin datetime, in sluttidin datetime) 

begin 
if  acceptprisin is not null then
insert into auktion(produkt, utropspris, acceptpris, starttid, sluttid)
values (produktin, utropsprisin, acceptprisin, starttidin, sluttidin);
else 
insert into auktion(produkt, utropspris, starttid, sluttid)
values (produktin, utropsprisin, starttidin, sluttidin);
end if;
end//

delimiter ;


call startAuktion(1, 1000, 2500, now(), now());
call startauktion(1, 1000, null, now(), now());

select * from auktion;
