
    function addMember(teamID, userID) {

        //URL for add team member controller method
        var url = "/teams/addTeamMember/"+ teamID +"/"+userID;

        //Loads fragment table with updated table of team members
        $('#teamMembersTable_div').load(url);
    }

    function removeMember(teamID, userID) {

        //URL for remove team member controller method
        var url = "/teams/removeTeamMember/"+ teamID +"/"+userID;

        //Loads fragment table with updated table of team members
        $('#teamMembersTable_div').load(url);
    }












