@echo off
set DERBY_INSTALL=%CD%\db-derby-10.9.1.0-bin
set CLASSPATH=%DERBY_INSTALL%\lib\derby.jar;%DERBY_INSTALL%\lib\derbytools.jar;.
@echo on
SET DERBY_INSTALL
SET CLASSPATH

@echo Creating database

java -Dij.database=jdbc:derby:ShoppingCartDB;create=true org.apache.derby.tools.ij CREATE_DATABASE.sql


@echo ..... SUCCESS ......
@echo to run the tool enter: java org.apache.derby.tools.ij