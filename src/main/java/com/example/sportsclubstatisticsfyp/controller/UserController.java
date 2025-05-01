package com.example.sportsclubstatisticsfyp.controller;

import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterMemberDTOForm;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.model.entities.Role;
import com.example.sportsclubstatisticsfyp.service.RoleService;
import com.example.sportsclubstatisticsfyp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/registerMemberForm")
    public ModelAndView displayRegisterMemberForm(Model model) {
        RegisterMemberDTOForm newMemberForm = new RegisterMemberDTOForm();
        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("roles", roles);

        return new ModelAndView("registerMemberForm", "newMemberForm", newMemberForm );

    }

    @PostMapping("/registerMember")
    public ModelAndView addClubMember(@ModelAttribute("newMemberForm") RegisterMemberDTOForm clubMember,
                                      RedirectAttributes redirectAttributes ) throws ParseException {

        userService.createUser(clubMember);

        redirectAttributes.addFlashAttribute("successMessage", "New Member has been successfully registered");
            return new ModelAndView("redirect:/");

        }


    @GetMapping("/generateClubMemberStats")
    public ModelAndView getClubMemberStats(Model model) throws JsonProcessingException {
    Map<String, Integer> maleClubMembers= userService.getAllMaleClubMembersByUserType();
    Map<String, Integer> femaleClubMembers=userService.getAllFemaleClubMembersByUserType();

    ObjectMapper objectMapper = new ObjectMapper();

    Integer allMaleUsers = maleClubMembers.get("allUsersCount");
    Integer allMaleAdmin = maleClubMembers.get("adminsCount");
    Integer allMaleTrainers = maleClubMembers.get("trainersCount");
    Integer allMalePlayers = maleClubMembers.get("playersCount");
    Integer allMaleClubMembers = maleClubMembers.get("clubMemberCount");

    Integer allFemaleUsers = femaleClubMembers.get("allUsersCount");
    Integer allFemaleAdmin = femaleClubMembers.get("adminsCount");
    Integer allFemaleTrainers = femaleClubMembers.get("trainersCount");
    Integer allFemalePlayers = femaleClubMembers.get("playersCount");
    Integer allFemaleClubMembers = femaleClubMembers.get("clubMemberCount");
    List<Map<String, Object>> allClubUsersGenderCount = new ArrayList<>();
    List<Map<String, Object>> clubMemberGenderCount = new ArrayList<>();
    List<Map<String, Object>> clubPlayerGenderCount = new ArrayList<>();
    List<Map<String, Object>> clubTrainerGenderCount = new ArrayList<>();
    List<Map<String, Object>> clubAdminGenderCount = new ArrayList<>();
    allClubUsersGenderCount.add(Map.of("gender","Male","value", allMaleUsers));
    allClubUsersGenderCount.add(Map.of("gender","Female","value", allFemaleUsers));

        clubMemberGenderCount.add(Map.of("gender","Male","value", allMaleClubMembers));
        clubMemberGenderCount.add(Map.of("gender","Female","value", allFemaleClubMembers));
        clubPlayerGenderCount.add(Map.of("gender","Male","value", allMalePlayers));
        clubPlayerGenderCount.add(Map.of("gender","Female","value", allFemalePlayers));
        clubTrainerGenderCount.add(Map.of("gender","Male","value", allMaleTrainers));
        clubTrainerGenderCount.add(Map.of("gender","Female","value", allFemaleTrainers));

        clubAdminGenderCount.add(Map.of("gender","Male","value", allMaleAdmin));
        clubAdminGenderCount.add(Map.of("gender","Female","value", allFemaleAdmin));
        String totalClubAdmins = objectMapper.writeValueAsString(clubAdminGenderCount);

        String totalClubUsers = objectMapper.writeValueAsString(allClubUsersGenderCount);
        String totalClubMembers = objectMapper.writeValueAsString(clubMemberGenderCount);
        String totalClubPlayers = objectMapper.writeValueAsString(clubPlayerGenderCount);
        String totalClubTrainer = objectMapper.writeValueAsString(clubTrainerGenderCount);

        model.addAttribute("totalClubUsers", totalClubUsers);
        model.addAttribute("totalClubMembers",totalClubMembers);
        model.addAttribute("totalClubPlayers", totalClubPlayers);
        model.addAttribute("totalClubTrainer",totalClubTrainer);
        model.addAttribute("totalClubAdmins",totalClubAdmins);

    List<User> listOfFemaleUsers=userService.getAllFemaleUsers();
    List<User> listOfMaleUsers=userService.getAllMaleUsers();




    Integer femalesBetween0to24 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfFemaleUsers,0,24);
    Integer femalesBetween25to49 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfFemaleUsers,25,49);
    Integer femalesBetween50to74 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfFemaleUsers,50,74);
    Integer femalesBetween75to100 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfFemaleUsers,75,100);

    Integer malesBetween0to24 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfMaleUsers,0,24);
    Integer malesBetween25to49 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfMaleUsers,25,49);
    Integer malesBetween50to74 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfMaleUsers,50,74);
    Integer malesBetween75to100 = userService.getUsersAgeCountBetweenMinAgeToMaxYears(listOfMaleUsers,75,100);



        List<Map<String,Object>> allUsersAgeGroup = new ArrayList<>();


        allUsersAgeGroup.add(Map.of("group","0-24", "Female",femalesBetween0to24,"Male", malesBetween0to24));
        allUsersAgeGroup.add(Map.of("group","25-49", "Female",femalesBetween25to49,"Male", malesBetween25to49));
        allUsersAgeGroup.add(Map.of("group","50-74", "Female",femalesBetween50to74,"Male", malesBetween50to74));
        allUsersAgeGroup.add(Map.of("group","75-100", "Female",femalesBetween75to100,"Male", malesBetween75to100));



        String totalClubUsersAgeGroup = objectMapper.writeValueAsString(allUsersAgeGroup);


        model.addAttribute("totalClubUsersAgeGroup", totalClubUsersAgeGroup);



    String listOfNewUsersPast6monthsJson = userService.getSixMonthsUserRegisterationStatus();

    model.addAttribute("listOfNewUsersPast6monthsJson", listOfNewUsersPast6monthsJson);


    return new ModelAndView("clubMemberStats");
    }
}



