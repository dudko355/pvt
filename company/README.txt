Motor depot version 1.0 27/10/2016))))))))))))))))))))))))))))))
=======================================================================
Voice: +375(29) 6501403
E-mail: Dudko355@Gmail.com
=======================================================================
The main idea of the project is to automate managing of clients demand 
and choosing car. You can login in application.Also you can book trip for 
moving your cargo. You have to give the information about cargo and time. 
The system will find a suitable car for you. You can cancel the trip if you  
change you mind. During entering the data into form be attentive. Date of   
the beginning should be after today date and before date of the finish.  
Also you can watch the list your orders,sort and filter it. You can delete 
your account if you have no planned trips. 

If you are administrator, you can watch information about this motor deport. 
You can see list of the broken car, list of trips, list of the drivers  and 
list of the car which in trip now. If a car is broken you can change the
status car "on broken".And this car will be exclude from the list of the 
working. Also administrator can change status of the trips. 
========================================================================
Your computer should have next tools to deploy this application:
1)Apache Tomcat Version 8.0.36 http://tomcat.apache.org/
2)Apache Maven Version 3.3.9   http://maven.apache.org/
3)JDK 8                        https://docs.oracle.com/
4)MySql 5.6                    http://dev.mysql.com/
=========================================================================
You need to do next steps before deploy:
1)turn on the Internet
2)Open the file settings.xml in your maven(...\maven3.3.9\conf\settings.xml) 
and write inside the tag <servers>
     <server>
  	<id>tomcat</id>
      	<username>adm</username>
      	<password>111</password>
     </server>
3)Open the file tomcat-users.xml in your tomcat(...\apache-tomcat-8.0.36\conf\tomcat-users.xml)
 and write inside the tag <tomcat-users> 
     <role rolename="manager-gui"/>
     <role rolename="manager-script"/>
     <role rolename="admin"/>
     <user username="adm" password="111" roles="manager-gui,manager-script"/>
     <user username="admin" password="2110984" roles="admin"/>
4)Register next environment variables
-CATALINA_HOME(for tomcat)
-JAVA_HOME(for jdk)
-M2_HOME(for maven)
-PATH(for tomcat,maven,mysql,jdk)
5)Open company.properties
(you can find ...\company\dao\src\main\resources\company.properties)
6)Change the settings of access to the database and port of apache tomcat(if need be)
7)Save and close company.properties
8)Run apache tomcat(...\apache-tomcat-8.0.36\bin\startup.bat)
9)Run this application(...\company\dao\src\main\resources\company.bat)
(remark:your mysql server shouldn't have database with name "company")
10)open web browser
11)input there http://localhost:8080/logistics/(if you don't change port)
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