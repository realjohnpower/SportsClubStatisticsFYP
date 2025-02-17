
    function addMember(teamID, userID) {

        //create url to request fragment
        var url = "/teams/addTeamMember/"+ teamID +"/"+userID;


        //load fragment and replace content
        $('#teamMembersTable_div').load(url);
    }

    function removeMember(teamID, userID) {

        //create url to request fragment
        var url = "/teams/removeTeamMember/"+ teamID +"/"+userID;


        //load fragment and replace content
        $('#teamMembersTable_div').load(url);
    }


    function drawAgeChart(zeroTo24Years,twentyFiveTo49Years,fiftyTo74Years,seventyFiveTo100Years) {
        console.log(zeroTo24Years)
        console.log(twentyFiveTo49Years)
        console.log(fiftyTo74Years)
        console.log(seventyFiveTo100Years)




        var data = google.visualization.arrayToDataTable([
            ['Age Group', 'Ratio'],
            ['0 - 24 years',    zeroTo24Years ],
            ['25 - 49 years ',   twentyFiveTo49Years],
            ['50 - 74 years ',  fiftyTo74Years],
            ['75 - 100 years', seventyFiveTo100Years]

        ]);

        var options = {
            title: 'Club Members Age Group Ratio'
        };

        var chart = new google.visualization.PieChart(document.getElementById('age-piechart'));

        chart.draw(data, options);
    }




// Initialize the plot with the first dataset





    function drawClubEventAttendanceChart(attendingCount, notAttendingCount, attendanceNotRecordedCount) {


        var data = google.visualization.arrayToDataTable([
            ['Club Event Attendance', 'Ratio'],
            ['Attending', attendingCount],
            ['Not Attending', notAttendingCount],
            ['Attendance Not Recorded', attendanceNotRecordedCount]

        ]);

        var options = {
            title: 'Club Event Attendance Ratio'
        };

        var chart = new google.visualization.PieChart(document.getElementById('clubEventAttendance-piechart'));

        chart.draw(data, options);
    }

    function drawTeamEventAttendanceChart(attendingCount, notAttendingCount, attendanceNotRecordedCount) {



        var data = google.visualization.arrayToDataTable([
            ['Team Event Attendance', 'Ratio'],
            ['Attending', attendingCount],
            ['Not Attending', notAttendingCount],
            ['Attendance Not Recorded', attendanceNotRecordedCount]

        ]);

        var options = {
            title: 'Team Event Attendance Ratio'
        };

        var chart = new google.visualization.PieChart(document.getElementById('teamEventAttendance-piechart'));

        chart.draw(data, options);
    }
