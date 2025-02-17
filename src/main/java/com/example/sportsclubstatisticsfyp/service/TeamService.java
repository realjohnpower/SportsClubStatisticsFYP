package com.example.sportsclubstatisticsfyp.service;

import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamDTOForm;
import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamEventDTOForm;
import com.example.sportsclubstatisticsfyp.model.entities.ClubEvents;

import com.example.sportsclubstatisticsfyp.model.entities.Team;
import com.example.sportsclubstatisticsfyp.model.entities.TeamEvent;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.model.repositories.TeamRepository;
import com.example.sportsclubstatisticsfyp.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    public Team createTeam(RegisterTeamDTOForm newTeam){

        Team team = new Team();
        team.setTeamName(newTeam.getTeamName());
        User trainer = userRepository.getById(newTeam.getTrainerID());
        team.setTrainer(trainer);

        for(Integer teamMemberId:newTeam.getListOfTeamMembers())
        {
            User teamMember = userRepository.getById(teamMemberId);
            team.getTeamMembers().add(teamMember);
        }

        return teamRepository.save(team);
    }


        public RegisterTeamDTOForm editTeamEventForm(Team team){
            RegisterTeamDTOForm editTeamDTOForm = new RegisterTeamDTOForm();
            editTeamDTOForm.setTeamId(team.getTeam_Id());
            editTeamDTOForm.setTeamName(team.getTeamName());
            editTeamDTOForm.setTrainerID(team.getTrainer().getUserId());


            return editTeamDTOForm;

        }

        public Team editTeam(RegisterTeamDTOForm editTeamDTOForm){
        Team team = teamRepository.findById(editTeamDTOForm.getTeamId()).stream().findFirst().orElse(null);
        team.setTeamName(editTeamDTOForm.getTeamName());
        User trainer = userRepository.findById(editTeamDTOForm.getTrainerID()).stream().findFirst().orElse(null);
        team.setTrainer(trainer);

        return teamRepository.save(team);
        }


    public void addTeamMember(int teamID, int userID){
        User teamMember= userRepository.findById(userID).stream().findFirst().orElse(null);
        Team team= teamRepository.findById(teamID).stream().findFirst().orElse(null);
        team.getTeamMembers().add(teamMember);
         teamRepository.save(team);

    }

    public void removeTeamMember(int teamID, int userID){
        User teamMember= userRepository.findById(userID).stream().findFirst().orElse(null);
        Team team= teamRepository.findById(teamID).stream().findFirst().orElse(null);
        team.getTeamMembers().remove(teamMember);
         teamRepository.save(team);

    }



    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }




    public Team getTeamById(int id){
        Optional<Team> team = teamRepository.findById(id);
        if(team.isPresent())
        {
            return team.get();
        }else {
            return null;
        }
    }

}
