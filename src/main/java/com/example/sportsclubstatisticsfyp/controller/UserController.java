package com.example.sportsclubstatisticsfyp.controller;

import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterMemberDTOForm;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.model.entities.Role;
import com.example.sportsclubstatisticsfyp.model.repositories.UserRepository;
import com.example.sportsclubstatisticsfyp.service.RoleService;
import com.example.sportsclubstatisticsfyp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
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
    public ModelAndView addClubMember(@Valid @ModelAttribute("newMemberForm") RegisterMemberDTOForm clubMember,
                                      RedirectAttributes redirectAttributes ) throws ParseException {


        userService.createUser(clubMember);




            redirectAttributes.addFlashAttribute("message", "Club member account has been created");
            return new ModelAndView("redirect:/");

        }


    @GetMapping("/generateClubMemberStats")
    public ModelAndView getClubMemberStats(Model model) throws JsonProcessingException {
    Map<String, Integer> maleClubMembers= userService.getAllMaleClubMembersByUserType();
    Map<String, Integer> femaleClubMembers=userService.getAllFemaleClubMembersByUserType();

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
    model.addAttribute("allMaleUsersCount",allMaleUsers);
    model.addAttribute("allMaleAdminsCount",allMaleAdmin);
    model.addAttribute("allMaleTrainersCount",allMaleTrainers);
    model.addAttribute("allMalePlayersCount",allMalePlayers);
    model.addAttribute("allMaleClubMembersCount",allMaleClubMembers);

    model.addAttribute("allFemaleUsersCount",allFemaleUsers);
    model.addAttribute("allFemaleTrainersCount",allFemaleTrainers);
    model.addAttribute("allFemalePlayersCount",allFemalePlayers);
    model.addAttribute("allFemaleAdminCount",allFemaleAdmin);
    model.addAttribute("allFemaleClubMembersCount",allFemaleClubMembers);

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

    model.addAttribute("femalesBetween0to24",femalesBetween0to24);
    model.addAttribute("femalesBetween25to49",femalesBetween25to49);
    model.addAttribute("femalesBetween50to74",femalesBetween50to74);
    model.addAttribute("femalesBetween75to100",femalesBetween75to100);

    model.addAttribute("malesBetween0to24",malesBetween0to24);
    model.addAttribute("malesBetween25to49",malesBetween25to49);
    model.addAttribute("malesBetween50to74",malesBetween50to74);
    model.addAttribute("malesBetween75to100",malesBetween75to100);
    System.out.println(femalesBetween0to24);
    System.out.println(femalesBetween25to49);
    System.out.println(femalesBetween50to74);
    System.out.println(femalesBetween75to100);


    String listOfNewUsersPast6monthsJson = userService.getSixMonthsUserRegisterationStatus();
    System.out.println(listOfNewUsersPast6monthsJson);
    model.addAttribute("listOfNewUsersPast6monthsJson", listOfNewUsersPast6monthsJson);

    /*
    Integer zeroTo24Years = userService.getUsersAgeCountBetweenMinAgeToMaxYears(0,24);
    Integer twentyFiveTo49Years = userService.getUsersAgeCountBetweenMinAgeToMaxYears(25,49);
    Integer fiftyTo74years = userService.getUsersAgeCountBetweenMinAgeToMaxYears(50,74);
    Integer seventyFiveTo100years = userService.getUsersAgeCountBetweenMinAgeToMaxYears(75,100);

    model.addAttribute("zeroTo24Years",zeroTo24Years);
    model.addAttribute("twentyFiveTo49Years",twentyFiveTo49Years);
    model.addAttribute("fiftyTo74Years",fiftyTo74years);
    model.addAttribute("seventyFiveTo100Years",seventyFiveTo100years);

     */
    return new ModelAndView("clubMemberStats");
    }
}



