Motor depot version 1.0 22/09/2016
=======================================================================
Voice: +375(29) 6501403
E-mail: Dudko355@Gmail.com
=======================================================================
On this website you can register a your trip and specify the
parameters of cargo (weight, volume) , number of seats in right ,
direction. Also you need specify start date and finish date.
Website'll determine a car.You can delete the trip if it does't start.

If you are an administrator, you can show all information about 
motor depot (broken machines, a list of drivers, all trips, etc.).
Also you can change the status of the machine and the trip of any of 
the clients.
========================================================================
Your computer should be set to deploy this application:
1)Apache Tomcat Version 8.0.36 http://tomcat.apache.org/
2)Apache Maven Version 3.3.9   http://maven.apache.org/
3)JDK 8                        https://docs.oracle.com/
4)MySql 5.6                    http://dev.mysql.com/
=========================================================================
You need do before deploy:

1)Open the file settings.xml in your maven(...\maven3.3.9\conf\settings.xml) 
and write inside the tag <servers>
     <server>
  	<id>tomcat</id>
      	<username>adm</username>
      	<password>111</password>
     </server>
2)Open the file tomcat-users.xml in your tomcat(...\apache-tomcat-8.0.36\conf\tomcat-users.xml)
 and write inside the tag <tomcat-users> 
     <role rolename="manager-gui"/>
     <role rolename="manager-script"/>
     <role rolename="admin"/>
     <user username="adm" password="111" roles="manager-gui,manager-script"/>
     <user username="admin" password="2110984" roles="admin"/>
3)Register environment variables
-CATALINA_HOME(for tomcat)
-JAVA_HOME(for jdk)
-M2_HOME(for maven)
-PATH(for tomcat,maven,mysql,jdk)
4)Open company.properties
(you can find ...\company\dao\src\main\resources\company.properties)
5)Ñhange the settings of access to the database and port of apache tomcat(if need be)
6)Save and close company.properties
7)run apache tomcat(...\apache-tomcat-8.0.36\bin\startup.bat)
8)run this application(...\company\dao\src\main\resources\company.bat)
(remark:mysql server don't have base with name company)
9)open web browser
10)text http://localhost:8080/company/(if you don't change port)
=====================================================================================
Initial application data
1)administrator(password:2110984 name:admin)
2)user one(password:111111 name:alex)
  user two(password:222222 name:root)
======================================================================================
                 =
Good luck          - )
                 =    
======================================================================================