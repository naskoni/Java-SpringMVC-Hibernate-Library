--------------------------------------------------------
--  File created - Събота-Април-09-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOOK
--------------------------------------------------------

  CREATE TABLE "BOOK" ("ID" NUMBER, "NAME" VARCHAR2(50), "AUTHOR" VARCHAR2(50), "YEAR" NUMBER, "ISBN" VARCHAR2(50))
--------------------------------------------------------
--  DDL for Table CLIENT
--------------------------------------------------------

  CREATE TABLE "CLIENT" ("ID" NUMBER, "NAME" VARCHAR2(50), "PID" NUMBER, "BIRTHDATE" DATE, "CREATED_BY" NUMBER)
--------------------------------------------------------
--  DDL for Table LEND
--------------------------------------------------------

  CREATE TABLE "LEND" ("ID" NUMBER, "BOOK" NUMBER, "CLIENT" NUMBER, "LENDING_DATE" DATE, "RETURN_DATE" DATE)
--------------------------------------------------------
--  DDL for Table LIBRARY_USER
--------------------------------------------------------

  CREATE TABLE "LIBRARY_USER" ("ID" NUMBER, "USERNAME" VARCHAR2(20), "NAME" VARCHAR2(50), "PASSWORD" VARCHAR2(100), "STATUS" VARCHAR2(11), "CREATED_BY" NUMBER, "ROLE" VARCHAR2(20))
REM INSERTING into BOOK
SET DEFINE OFF;
Insert into BOOK (ID,NAME,AUTHOR,YEAR,ISBN) values (1,'Women in Tech','Tarah Wheeler Van Vlack',2015,null);
Insert into BOOK (ID,NAME,AUTHOR,YEAR,ISBN) values (2,'Nation on the Take','Wendell Potter',2016,null);
Insert into BOOK (ID,NAME,AUTHOR,YEAR,ISBN) values (3,'We Should All Be Feminists','Chimamanda Ngozi Adichie',2016,null);
Insert into BOOK (ID,NAME,AUTHOR,YEAR,ISBN) values (4,'Because of Miss Bridgerton','Julia Quinn',2015,null);
REM INSERTING into CLIENT
SET DEFINE OFF;
Insert into CLIENT (ID,NAME,PID,BIRTHDATE,CREATED_BY) values (1,'John Smith',8808080088,to_date('1988-08-08','RRRR-MM-DD'),1);
Insert into CLIENT (ID,NAME,PID,BIRTHDATE,CREATED_BY) values (2,'Jack Frost',7707070077,to_date('1977-07-07','RRRR-MM-DD'),2);
Insert into CLIENT (ID,NAME,PID,BIRTHDATE,CREATED_BY) values (3,'Anna Maria Antoaneta',9909090099,to_date('1999-09-09','RRRR-MM-DD'),1);
REM INSERTING into LEND
SET DEFINE OFF;
Insert into LEND (ID,BOOK,CLIENT,LENDING_DATE,RETURN_DATE) values (1,3,3,to_date('2012-11-11','RRRR-MM-DD'),to_date('2012-11-12','RRRR-MM-DD'));
Insert into LEND (ID,BOOK,CLIENT,LENDING_DATE,RETURN_DATE) values (2,2,2,to_date('2012-11-11','RRRR-MM-DD'),to_date('2012-11-12','RRRR-MM-DD'));
REM INSERTING into LIBRARY_USER
SET DEFINE OFF;
Insert into LIBRARY_USER (ID,USERNAME,NAME,PASSWORD,STATUS,CREATED_BY,ROLE) values (3,'spiro','Spiro Spiriev','e10adc3949ba59abbe56e057f20f883e','deactivated',1,'ROLE_USER');
Insert into LIBRARY_USER (ID,USERNAME,NAME,PASSWORD,STATUS,CREATED_BY,ROLE) values (1,'admin','Atanas Atanasov','e10adc3949ba59abbe56e057f20f883e','active',1,'ROLE_ADMIN');
Insert into LIBRARY_USER (ID,USERNAME,NAME,PASSWORD,STATUS,CREATED_BY,ROLE) values (2,'kiro','Kiril Kirilov','e10adc3949ba59abbe56e057f20f883e','active',1,'ROLE_USER');
--------------------------------------------------------
--  DDL for Index SYS_C007140
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007140" ON "BOOK" ("ID")
--------------------------------------------------------
--  DDL for Index SYS_C007142
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007142" ON "CLIENT" ("ID")
--------------------------------------------------------
--  DDL for Index SYS_C007151
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007151" ON "LEND" ("ID")
--------------------------------------------------------
--  DDL for Index SYS_C007135
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007135" ON "LIBRARY_USER" ("ID")
--------------------------------------------------------
--  Constraints for Table BOOK
--------------------------------------------------------

  ALTER TABLE "BOOK" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table CLIENT
--------------------------------------------------------

  ALTER TABLE "CLIENT" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table LEND
--------------------------------------------------------

  ALTER TABLE "LEND" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table LIBRARY_USER
--------------------------------------------------------

  ALTER TABLE "LIBRARY_USER" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CLIENT
--------------------------------------------------------

  ALTER TABLE "CLIENT" ADD CONSTRAINT "CLIENT_CREATED_BY" FOREIGN KEY ("CREATED_BY") REFERENCES "LIBRARY_USER" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table LEND
--------------------------------------------------------

  ALTER TABLE "LEND" ADD CONSTRAINT "LEND_BOOK" FOREIGN KEY ("BOOK") REFERENCES "BOOK" ("ID") ENABLE
  ALTER TABLE "LEND" ADD CONSTRAINT "LEND_CLIENT" FOREIGN KEY ("CLIENT") REFERENCES "CLIENT" ("ID") ENABLE
