crime = load 'Crimes.txt' using PigStorage(',') as(id:int,casenum:chararray,date:chararray,block:chararray,iucr:int,primarytype:chararray,description:chararray,locdesc:chararray,arrest:boolean,domestic:boolean,beat:int,district:chararray,ward:int,commarea:int,fbicode:chararray,xcor:int,ycor:int,year:int,update:chararray,latitude:float,longitude:float,location:chararray);


--Question 1

grpbyfbicode = GROUP crime by fbicode;
countbyfbicode = FOREACH grpbyfbicode generate group, COUNT(crime.id);
dump countbyfbicode;



--Question 2

filtercount = filter countbyfbicode by group == '32';
dump filtercount;

--Question 3

grpbydistrict = GROUP crime by district;
filtertheftdistrict = FOREACH grpbydistrict { theftfilter = FILTER crime by primarytype == 'THEFT' and arrest == true; GENERATE FLATTEN(theftfilter);}; 
groupbydis = GROUP filtertheftdistrict by district;
countnumtheft = FOREACH groupbydis generate group,COUNT(filtertheftdistrict.id);
dump countnumtheft;


--Question 4

crimefilter = FILTER crime by ToDate(date,'dd/MM/yyyy hh:mm:ss aa') >= ToDate('01/10/2014 00:00:01 AM','dd/MM/yyyy hh:mm:ss aa') and ToDate(date,'dd/MM/yyyy hh:mm:ss aa') <=ToDate('01/10/2015 23:59:59 PM','dd/MM/yyyy hh:mm:ss aa') and arrest == true;
group_crime = GROUP crimefilter ALL;
countcrimes = FOREACH group_crime GENERATE COUNT(crimefilter);
dump countcrimes;

