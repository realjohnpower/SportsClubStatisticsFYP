package com.example.sportsclubstatisticsfyp.controller;

import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamDTOForm;
import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamEventDTOForm;
import com.example.sportsclubstatisticsfyp.model.entities.ClubEvents;
import com.example.sportsclubstatisticsfyp.model.entities.Team;
import com.example.sportsclubstatisticsfyp.model.entities.TeamEvent;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.model.repositories.TeamRepository;
import com.example.sportsclubstatisticsfyp.model.repositories.UserRepository;
import com.example.sportsclubstatisticsfyp.service.ClubEventsService;
import com.example.sportsclubstatisticsfyp.service.TeamService;
import com.example.sportsclubstatisticsfyp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;


    @GetMapping("/addTeamForm")
    public ModelAndView displayAddTeamForm(Model model) {


        RegisterTeamDTOForm newTeamForm = new RegisterTeamDTOForm();
        List<User> listOfPlayers=userService.getAllPlayers();
        List<User> listOfTrainers = userService.getAllTrainers();
        model.addAttribute("listOfPlayers", listOfPlayers);
        model.addAttribute("listOfTrainers", listOfTrainers);

        return new ModelAndView("addTeamForm", "newTeamForm", newTeamForm );

    }

    @GetMapping("/viewTeams")
    public ModelAndView displayTeamsList() {

        List<Team> listOfTeams = teamService.getAllTeams();


        return new ModelAndView("viewTeams", "teamList", listOfTeams );

    }

    @GetMapping("/viewTrainerTeams")
    public ModelAndView displayTrainerTeamsList() {

        User trainer = userService.getUserByEmail(User.getLoggedInEmail());
        Set<Team> listOfTeams = trainer.getTrainersListOfTeams();


        return new ModelAndView("viewTeams", "teamList", listOfTeams );

    }

    @GetMapping("/viewPlayerTeams")
    public ModelAndView displayPlayerTeamsList() {

        User player = userService.getUserByEmail(User.getLoggedInEmail());
        Set<Team> listOfTeams = player.getListOfTeams();


        return new ModelAndView("viewTeams", "teamList", listOfTeams );

    }

    @GetMapping("/teamDrillDown/{id}")
    public ModelAndView teamDrillDown(@PathVariable("id") Integer id) {

        Team team = teamService.getTeamById(id);


        return new ModelAndView("TeamDrillDown", "team", team );

    }

    @GetMapping("/editTeamForm/{id}")
    public ModelAndView editTeamForm(@PathVariable("id") Integer id, Model model) {

        Team team = teamService.getTeamById(id);

        RegisterTeamDTOForm editTeamForm = teamService.editTeamEventForm(team);
        List<User> listOfAvailablePlayers=userService.getAvailablePlayers(team.getTeamMembers());
        List<User> listOfAvailableTrainers=userService.getAllTrainers();

        User Trainer = userService.getUserById(editTeamForm.getTrainerID());

        model.addAttribute("team", team);
        model.addAttribute("availablePlayers", listOfAvailablePlayers);
        model.addAttribute("currentTrainer", Trainer);
        model.addAttribute("listOfAvailableTrainers", listOfAvailableTrainers);
        return new ModelAndView("editTeamForm", "editTeamForm", editTeamForm );

    }

    @PostMapping("/editTeam")
    public ModelAndView editTeam(@ModelAttribute("editTeamForm") RegisterTeamDTOForm editTeam,
                                      RedirectAttributes redirectAttributes) {

        teamService.editTeam(editTeam);
        redirectAttributes.addFlashAttribute("successMessage", "Team has been updated");
        List<Team> listOfTeams=teamService.getAllTeams();

        return new ModelAndView("redirect:/teams/viewTeams","userTeamEvents", listOfTeams );

    }

    @PostMapping("/addTeam")
    public ModelAndView addTeam(@ModelAttribute("newTeamForm") RegisterTeamDTOForm newTeam, RedirectAttributes redirectAttributes ) throws ParseException {


        teamService.createTeam(newTeam);
        redirectAttributes.addFlashAttribute("successMessage", "Team has been created");
        return new ModelAndView("redirect:/");

    }

    @RequestMapping("/addTeamMember/{teamId}/{userId}")
    public String addTeamMember(@PathVariable("teamId") Integer teamId, @PathVariable("userId") Integer userId, Model model) {

         teamService.addTeamMember(teamId, userId);

         Team team = teamService.getTeamById(teamId);
         List<User> availablePlayers=userService.getAvailablePlayers(team.getTeamMembers());

         model.addAttribute("availablePlayers", availablePlayers);
         model.addAttribute("team", team);
         return "fragments/teamMembersTable.html :: Table";
    }

    @RequestMapping("/removeTeamMember/{teamId}/{userId}")
    public String removeTeamMember(@PathVariable("teamId") Integer teamId, @PathVariable("userId") Integer userId, Model model) {

        teamService.removeTeamMember(teamId, userId);
        Team team = teamService.getTeamById(teamId);
        List<User> availablePlayers=userService.getAvailablePlayers(team.getTeamMembers());

        model.addAttribute("availablePlayers", availablePlayers);
        model.addAttribute("team", team);
        return "fragments/teamMembersTable.html :: Table";
    }


}
