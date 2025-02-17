package com.example.sportsclubstatisticsfyp.service;

import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamDTOForm;
import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamEventDTOForm;
import com.example.sportsclubstatisticsfyp.model.DTOForms.TeamSessionStatsDTO;
import com.example.sportsclubstatisticsfyp.model.entities.*;
import com.example.sportsclubstatisticsfyp.model.repositories.TeamEventAttendanceRepository;
import com.example.sportsclubstatisticsfyp.model.repositories.TeamEventRepository;
import com.example.sportsclubstatisticsfyp.model.repositories.TeamRepository;
import com.example.sportsclubstatisticsfyp.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamEventService {
    @Autowired
    private TeamEventRepository teamEventRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamEventAttendanceRepository teamEventAttendanceRepository;
    @Autowired
    private UserRepository userRepository;


    public TeamEvent getTeamEventById(int id){
        Optional<TeamEvent> teamEvent = teamEventRepository.findById(id);
        if(teamEvent.isPresent())
        {
            return teamEvent.get();
        }else {
            return null;
        }
    }

    public TeamEvent createTeamEvent(RegisterTeamEventDTOForm newTeamEvent){

        TeamEvent teamEvent = new TeamEvent();
        teamEvent.setEventName(newTeamEvent.getEventName());
        teamEvent.setEventDescription(newTeamEvent.getEventDescription());
        teamEvent.setLocation(newTeamEvent.getLocation());
        teamEvent.setStartDate(newTeamEvent.getEventStartDate());

        Team teamForEvent =teamRepository.findById(newTeamEvent.getTeamId())
                                         .stream()
                                         .findFirst()
                                         .orElse(null);
        teamEvent.setTeam(teamForEvent);


        return teamEventRepository.save(teamEvent);
    }

    public List<TeamEvent> getUsersTeamEvents(Set<Team> userTeams){
        List<TeamEvent> teamEvents = new ArrayList<>();
        for(Team team : userTeams){
            for(TeamEvent teamEvent:team.getListOfTeamEvents()){
                teamEvents.add(teamEvent);
            }
        }
        return teamEvents;
    }

    public TeamEvent goingToTeamEvent(TeamEvent teamEvent, User user, Boolean attending){

        List<TeamEventAttendance> listOfTeamEventAttendances= teamEventAttendanceRepository.findAll();
        if(!listOfTeamEventAttendances.isEmpty()) {
            for (TeamEventAttendance teamEventAttendance : listOfTeamEventAttendances) {
                Integer teamAttendanceClubMemberId = teamEventAttendance.getTeamMember().getUserId();
                Integer teamAttendanceEventId = teamEventAttendance.getTeamEvent().getTeamEventId();
                if (user.getUserId() == teamAttendanceClubMemberId && teamEvent.getTeamEventId() == teamAttendanceEventId) {
                    teamEvent.getAttendanceList().remove(teamEventAttendance);
                    Optional<TeamEventAttendance> optionalAttendance = teamEventAttendanceRepository.findById(teamEventAttendance.getTeamEventAttendanceId());
                    TeamEventAttendance recordedAttendance = optionalAttendance.stream().findFirst().orElse(null);
                    recordedAttendance.setAttendance(attending);
                    teamEvent.getAttendanceList().add(recordedAttendance);

                    return teamEventRepository.save(teamEvent);
                }
            }
        }
        TeamEventAttendance teamEventAttendance = new TeamEventAttendance();
        teamEventAttendance.setTeamEvent(teamEvent);
        teamEventAttendance.setAttendance(attending);
        teamEventAttendance.setTeamMember(user);
        List<TeamEventAttendance> attendanceList=new ArrayList<>();
        attendanceList.add(teamEventAttendance);
        teamEvent.setAttendanceList(attendanceList);

        return teamEventRepository.save(teamEvent);

    }

    public RegisterTeamEventDTOForm editTeamEventForm(TeamEvent teamEvent){
        RegisterTeamEventDTOForm editTeamEventDTOForm = new RegisterTeamEventDTOForm();
        editTeamEventDTOForm.setTeamEventId(teamEvent.getTeamEventId());
        editTeamEventDTOForm.setEventName(teamEvent.getEventName());
        editTeamEventDTOForm.setEventDescription(teamEvent.getEventDescription());
        editTeamEventDTOForm.setLocation(teamEvent.getLocation());
        editTeamEventDTOForm.setEventStartDate(teamEvent.getStartDate());
        editTeamEventDTOForm.setTeamId(teamEvent.getTeam().getTeam_Id());

        return editTeamEventDTOForm;

    }

    public TeamEvent editTeamEvent (RegisterTeamEventDTOForm editTeamEventDTOForm){
        TeamEvent teamEvent = teamEventRepository.findById(editTeamEventDTOForm.getTeamEventId()).orElse(null);

        teamEvent.setTeamEventId(editTeamEventDTOForm.getTeamEventId());
        teamEvent.setEventName(editTeamEventDTOForm.getEventName());
        teamEvent.setEventDescription(editTeamEventDTOForm.getEventDescription());
        teamEvent.setLocation(editTeamEventDTOForm.getLocation());
        teamEvent.setStartDate(editTeamEventDTOForm.getEventStartDate());

        for(Team sportTeam:teamRepository.findAll()){
            if(editTeamEventDTOForm.getTeamId().equals(sportTeam.getTeam_Id())){
                teamEvent.setTeam(sportTeam);
            }
        }

        return teamEventRepository.save(teamEvent);

    }

    public void removeTeamEvent(TeamEvent teamEvent){
        teamEventRepository.delete(teamEvent);
    }

    public Integer getAttendingRecordCount(TeamEvent teamEvent, boolean attending){
        Integer attendingCount = 0;

        for(TeamEventAttendance attendance: teamEvent.getAttendanceList()){
            if(attendance.getAttendance()==attending){
                attendingCount++;
            }
        }
        return attendingCount;
    }

    public Integer getNotRecordedAttendanceCount(TeamEvent teamEvent){
        Integer attendanceRecordedCount = teamEvent.getAttendanceList().size();
        Integer totalTeamMembers = teamEvent.getTeam().getTeamMembers().size();
        Integer notRecordedCount = totalTeamMembers-attendanceRecordedCount;

        return notRecordedCount;
    }

    public TeamSessionStatsDTO getNewTeamSessionStatsDTO(TeamEvent teamEvent){

        TeamSessionStatsDTO teamSessionStatsDTO = new TeamSessionStatsDTO();

        Team team = teamEvent.getTeam();

        for(User teamMember: team.getTeamMembers()){
            TeamSessionStats teamSessionStats = new TeamSessionStats();
            teamSessionStats.setPlayer(teamMember);
            teamSessionStats.setTeamSession(teamEvent);
            teamSessionStatsDTO.addTeamSessionStats(teamSessionStats);
        }

        return teamSessionStatsDTO;
    }

    public TeamEvent createTeamSessionStatsForTeamEvent (List <TeamSessionStats> teamSessionStatsList){


        TeamEvent teamEvent=new TeamEvent();
        for(TeamSessionStats teamSessionStats: teamSessionStatsList){
            User player = userRepository.findById(teamSessionStats.getPlayer().getUserId()).orElse(null);
            teamSessionStats.setPlayer(player);
            teamEvent = teamEventRepository.findById(teamSessionStats.getTeamSession().getTeamEventId()).orElse(null);
            teamSessionStats.setTeamSession(teamEvent);
            System.out.println(teamSessionStats);
        }

        teamEvent.setListOfPlayerSessionsStats(teamSessionStatsList);

        return teamEventRepository.save(teamEvent);


    }





}
