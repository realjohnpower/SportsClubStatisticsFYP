# SportsClubStatisticsFYP

This was my final year project which was developed in Spring Boot. It is a sports club management web application with descriptive analytics to identify trends or patterns in club members, club events, team session statistics and player physical attributes. 

# Club Administrators: 
* should be able to login / logout.
* view club member statistics
* create and manage club events
* view club events.
* view more information on a selected club event
* log attendance for a club event.
* create and manage teams.
* view teams.
* view more information on a selected team. 
* register a new club member(club member, player, trainer or admin).
# Club Trainers:
* should be able to log in/log out. view their teams. 
* view more information on a selected team.
* record and view player physical statistics.
* view club events.
* view more information on a selected club event.
* log attendance for a club event.
* create and manage team sessions.
* view team sessions. 
* view more information on a selected team session.
* view team session statistics. 
* export statistics recorded in a team session to a PDF file.
* add team session statistics and upload team session statistics on a CSV file.
# Club Players
* should be able to log in/log out.
* view club events.
* view more information on a club event.
* log attendance for a club event.
* view team sessions.
* view more information on a team session.
* log attendance for a team session and view their teams.
# Club Members
* should be able to log in/log out.
* view club events. 
* view more information on a club event and log attendance for club events. 
 
# Instructions To Run Application On Your Machine (Localhost)
1.	Install XAMPP on your machine (https://www.apachefriends.org/download.html).
   
2.	Open XAMPP control panel app and select start on Apache and then select start on MySQL module.
 
3.	When Apache and MySQL is running (module will become green), select Admin for MySQL which will direct you to PHPMyAdmin.
   
4.	Go to ‘SportsClubStatisticsFYP/src/main/resources’ directory and open the ‘sport_club_db.sql’ file and copy all of the content. The “HurlingSessionCSVExample.csv” CSV file is in the directory as well if you want to test it for the “Gym Session” team session(login as Mary Smith).
  
5.	Select the SQL tab on PHPMyAdmin and paste the script into the query box.
 
6.	Open IntelliJ Ultimate Edition IDE. Open the hamburger icon on the top left corner, select File and chose Open. Go to wherever you saved the project and select “SportsStatisticsFYP”. Press OK.
 
7.	To run the application, Press the green play button on the navigation bar. Make sure that ‘SportsClubStatisticsFypApplication’ is selected in the drop down menu beside the green play button.
 
8.	The application should be running on http://localhost:8888/
   
9.	My database run on port 3306, if you need to change the port to connect the database. Go to the application.properties page and replace the “3306” in the spring.datasource.url to whatever port your database is on.
 
USERS: 
Club Admin: 
Email: john@gmail.com
Password: password123

Trainer:
Email: MSmith@gmail.com
Password: password123

Player:
Email: TK@gmail.com
Password: password123

Club Member:
Email: JHarold@gmail.com
Password: password123

When you open the application on Intellij and press the green play button, the application could fail to build. Just close Intellij and open the project again and press the green button again. The project should now run. There might also be an issue with the java version in which you will be prompted to configure Java version, the application uses version 17. Download the suitable Java version and then close Intellij and open it again.

 
 

	
 
 

 
 

