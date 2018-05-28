create database sap

create table CZESC (
idczesc int primary key not null,
numer_materialu bigint ,
opis varchar(max)
)

create table ZLECENIE(
idzlecenie int primary key not null,
numer bigint
)

create table CZESC_ZLECENIE(
idczesc int not null,
idzlecenie int not null
)

create table UZYTKOWNIK(
iduzytkownik int primary key not null,
nazwa varchar(max)
)

create table ZEZLOMOWANIE(
idzezlomowanie int primary key not null,
iduzytkownik int not null,
idczesc int not null,
idzlecenie int not null,
data_zlomowania date,
tydzien int,
rok int,
liczba_zezlomowanych decimal(38,3),
wartosc varchar(max),
cena decimal(38,4),
waluta varchar(max)
)

create table ZMYWARKA(
idzmywarka int primary key not null,
nazwa varchar(max)
)

create table PRODUKCJA(
idprodukcja int primary key not null,
idtydzien int,
idrok int,
idlinia int
)

create table ROK(
idrok int primary key not null,
rok int
)

create table TYDZIEN(
idtydzien int primary key not null,
tydzien int
)

create table OBSZAR(
idobszar int primary key not null,
obszar varchar(max)
)

create table PRODUKCJA_ZLECENIE(
idprodukcja int not null,
idzlecenie int not null
)

create table PODSUMOWANIE_ZEZLOMOWANIE(
idpodsumowanie int not null,
idzezlomowanie int not null
)

create table ZMYWARKA_PRODUKCJA(
idzmywarka int not null,
idprodukcja int not null
)

create table PODSUMOWANIE(
idpodsumowanie int primary key not null,
idprodukcja int not null,
liczba_zmywarek int,
liczba_zezl decimal(38,3),
tydzien int,
rok int,
zezl_na_liczba_zmyw float,
jedn_na_sztuki varchar(max),
koszt_zlomowania decimal(38,2),
cena_na_liczba_zm decimal(38,2),
wal_na_szt varchar(max)
)

create table PODSUMOWANIE_SKUMULOWANE(
idpodsumowanie_skum int primary key not null,
idprodukcja int not null,
liczba_zmywarek_skum int,
liczba_zezl_skum decimal(38,3),
tydzien int,
rok int,
zezl_na_liczba_zmyw_calk float,
jedn_na_sztuki varchar(max),
koszt_zlomowania_skum decimal(38,2),
cena_na_liczba_calk decimal(38,2),
wal_na_szt varchar(max)
)

select * from UZYTKOWNIK
select * from CZESC
select * from ZLECENIE
select * from CZESC_ZLECENIE
select * from ZEZLOMOWANIE
select * from PODSUMOWANIE
select * from PODSUMOWANIE_ZEZLOMOWANIE
select * from PRODUKCJA_ZLECENIE
select * from PRODUKCJA
select * from PODSUMOWANIE_SKUMULOWANE
select * from ROK
select * from TYDZIEN
select * from OBSZAR

INSERT INTO PODSUMOWANIE_ZEZLOMOWANIE
SELECT * FROM PODSUMOWANIE_zlomowanie$ where f1 is not null

SELECT * FROM ZLECENIE$ where f1 is not null order by f1 

select ZEZLOMOWANIE.idzezlomowanie,czesc.numer_materialu,czesc.opis,UZYTKOWNIK.nazwa,ZLECENIE.numer,ZEZLOMOWANIE.data_zlomowania,ZEZLOMOWANIE.liczba_zezlomowanych,
ZEZLOMOWANIE.wartosc,ZEZLOMOWANIE.cena,ZEZLOMOWANIE.waluta from ZEZLOMOWANIE
inner join UZYTKOWNIK on UZYTKOWNIK.iduzytkownik=ZEZLOMOWANIE.iduzytkownik
inner join CZESC on czesc.idczesc=ZEZLOMOWANIE.idczesc
inner join ZLECENIE on ZLECENIE.idzlecenie=ZEZLOMOWANIE.idzlecenie
order by ZEZLOMOWANIE.idzezlomowanie

select liczba_zezlomowanych from ZEZLOMOWANIE

select * from UZYTKOWNIK where iduzytkownik=(
SELECT top 1 iduzytkownik From ZEZLOMOWANIE where MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017 GROUP By iduzytkownik
order by Count(tydzien) asc)

select top 10 * from ZEZLOMOWANIE where liczba_zezlomowanych >0 and liczba_zezlomowanych<10 and
MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017
order by liczba_zezlomowanych 

select * from ZEZLOMOWANIE where idczesc=(select min(idczesc) from ZEZLOMOWANIE) 

select * from UZYTKOWNIK where iduzytkownik=(
SELECT top 1 iduzytkownik From ZEZLOMOWANIE where MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017 GROUP By iduzytkownik
order by Count(idczesc) asc)

SELECT iduzytkownik,Count(iduzytkownik) From ZEZLOMOWANIE where MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017 GROUP By iduzytkownik
order by Count(idzezlomowanie) asc

select iduzytkownik ,count(iduzytkownik) from ZEZLOMOWANIE group by iduzytkownik order by count(iduzytkownik)

SELECT mycount 
FROM (SELECT COUNT(iduzytkownik) mycount 
FROM ZEZLOMOWANIE where MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017
GROUP BY iduzytkownik) y;

select * from ZEZLOMOWANIE where MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017 and iduzytkownik<>15 order by iduzytkownik 

select * from ZEZLOMOWANIE where 
iduzytkownik not in (select iduzytkownik from ZEZLOMOWANIE  where MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017 
group by iduzytkownik having count(idzezlomowanie)>0) order by iduzytkownik

select iduzytkownik,count(idzezlomowanie) from ZEZLOMOWANIE where MONTH(data_zlomowania)=6 and year(data_zlomowania)=2017 group by iduzytkownik having count(idzezlomowanie)>0 order by iduzytkownik

select * from ZEZLOMOWANIE where iduzytkownik=13 and idczesc=19 order by idzezlomowanie desc 

select max(idzezlomowanie) from ZEZLOMOWANIE where  iduzytkownik=13 and idczesc=19 and idzlecenie=15

SELECT ZEZLOMOWANIE.tydzien,ZLECENIE.idzlecenie, ZLECENIE.numer, SUM(ZEZLOMOWANIE.cena)/(970*5) AS [TOTAL AMOUNT]
FROM ZLECENIE INNER JOIN ZEZLOMOWANIE
ON ZLECENIE.idzlecenie=ZEZLOMOWANIE.idzlecenie
GROUP BY ZLECENIE.idzlecenie, ZLECENIE.numer,ZEZLOMOWANIE.tydzien
order by ZEZLOMOWANIE.tydzien,ZLECENIE.numer

select SUM(cena)/11397 from ZEZLOMOWANIE where data_zlomowania='2017-07-10'

select sum(cena) from ZEZLOMOWANIE where tydzien=1

select sum(cena)/240000 from ZEZLOMOWANIE where tydzien=28

select sum(cena)/346271 from ZEZLOMOWANIE where tydzien=1

select SUM(cena)/49915 from ZEZLOMOWANIE where data_zlomowania='2017-07-10'

select sum(cena)/1.5123 from ZEZLOMOWANIE where tydzien=28

select tydzien from ZEZLOMOWANIE where data_zlomowania between '2017-01-10' and '2017-02-10'

select * from ZEZLOMOWANIE where tydzien=1 and rok=2017

select round(sum(koszt_zlomowania)/sum(liczba_zmywarek),4) from PODSUMOWANIE where tydzien =9
union
select round(sum(koszt_zlomowania)/sum(liczba_zmywarek),4) from PODSUMOWANIE where tydzien between 1 and 9
union
select sum(koszt_zlomowania)/sum(liczba_zmywarek) from PODSUMOWANIE where tydzien =1
union
select sum(koszt_zlomowania)/sum(liczba_zmywarek) from PODSUMOWANIE where tydzien between 1 and 1

select idpodsumowanie from PODSUMOWANIE where tydzien between 20 and 28
union
select idpodsumowanie_skum from PODSUMOWANIE_SKUMULOWANE 
where tydzien between 20 and 28

select * from PODSUMOWANIE where tydzien between (select tydzien from PODSUMOWANIE where tydzien=3 and rok=2017)
and (select tydzien from PODSUMOWANIE where tydzien=6 and rok=2017)

select * from PODSUMOWANIE where tydzien=3 and rok=2017 and idprodukcja in
(select idprodukcja from PRODUKCJA where linia='Wszystkie'
and tydzien=3 and rok=2017);

select * from PODSUMOWANIE where tydzien between (select tydzien from PODSUMOWANIE where tydzien=3 and rok=2017)
and (select tydzien from PODSUMOWANIE where tydzien=6 and rok=2017) and idprodukcja in
(select idprodukcja from PRODUKCJA where linia='Wszystkie'
and tydzien between (select tydzien from PRODUKCJA where tydzien=3 and rok=2017) and (
select tydzien from PRODUKCJA where tydzien=6 and rok=2017))

update PRODUKCJA set linia='Wszystkie'

select * from PODSUMOWANIE where idprodukcja in (select idprodukcja from PRODUKCJA where idtydzien in (select idtydzien from TYDZIEN where tydzien=1)
and idrok in (select idrok from ROK where rok=2017) and idlinia in (select idobszar from OBSZAR where obszar='Ogolne'))    

select * from PODSUMOWANIE where idprodukcja in (select idprodukcja from PRODUKCJA where idtydzien in (select idtydzien from TYDZIEN where tydzien between 1 and 6)
and idrok in (select idrok from ROK where rok between 2017 and 2017) and idlinia in (select idobszar from OBSZAR where obszar='Ogolne'))        
union
select * from PODSUMOWANIE_SKUMULOWANE where idprodukcja in (select idprodukcja from PRODUKCJA where idtydzien in (select idtydzien from TYDZIEN where tydzien between 1 and 6)
and idrok in (select idrok from ROK where rok between 2017 and 2017) and idlinia in (select idobszar from OBSZAR where obszar='Ogolne'))                               