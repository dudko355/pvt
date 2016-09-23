FOR /F "tokens=1* delims==" %%A IN (company.properties) DO (
    IF "%%A"=="jdbc.username" SET root=%%B 
   )
FOR /F "tokens=1* delims==" %%A IN (company.properties) DO (
    IF "%%A"=="jdbc.password" SET password=%%B
  )
mysql.exe -u%root% -p%password%<MY_BASE.sql
cd ..
cd ..
cd ..
cd ..
mvn tomcat7:redeploy
pause






