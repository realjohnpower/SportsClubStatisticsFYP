package com.example.sportsclubstatisticsfyp.controller;

import com.example.sportsclubstatisticsfyp.model.entities.PlayerPhysicalStats;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.service.PlayerPhysicalStatsService;
import com.example.sportsclubstatisticsfyp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/playerPhysicalStats")
public class PlayerPhysicalStatsController {

    @Autowired
    private PlayerPhysicalStatsService playerPhysicalStatsService;
    @Autowired
    private UserService userService;

    @GetMapping("/viewAllPlayerPhysicalStats/{userId}/{teamId}")
    public String viewPlayerPhysicalStatsByUserId(Model model,
                                                  @PathVariable int userId,
                                                  @PathVariable int teamId) throws JsonProcessingException {


        List<PlayerPhysicalStats> listOfPlayerPhysicalStats = playerPhysicalStatsService.getPlayerPhysicalStatsById(userId);

        model.addAttribute("teamId", teamId);

        model.addAttribute("listOfPlayerPhysicalStats", listOfPlayerPhysicalStats);

        User player=userService.getUserById(userId);
        model.addAttribute("player", player);

       Map<String, List<Map<String,Object>>> playerPhysicalStats = playerPhysicalStatsService.returnPlayerPhysicalStatsinJson(listOfPlayerPhysicalStats,player);
       List<Map<String, Object>> heightStats= playerPhysicalStats.get("heightStats");
       List<Map<String, Object>> weightStats= playerPhysicalStats.get("weightStats");
       List<Map<String,Object>> bmiStats= playerPhysicalStats.get("bmiStats");
       List<Map<String, Object>> bodyFatPercentageStats= playerPhysicalStats.get("bodyFatPercentageStats");

       ObjectMapper objectMapper = new ObjectMapper();

       String heightStatsJson=objectMapper.writeValueAsString(heightStats);
       String weightStatsJson=objectMapper.writeValueAsString(weightStats);
       String bmiStatsJson=objectMapper.writeValueAsString(bmiStats);
       String bodyFatPercentageStatsJson=objectMapper.writeValueAsString(bodyFatPercentageStats);

       model.addAttribute("heightStats", heightStatsJson);
       model.addAttribute("weightStats", weightStatsJson);
       model.addAttribute("bmiStats", bmiStatsJson);
        model.addAttribute("bodyFatPercentageStats", bodyFatPercentageStatsJson);


        return "viewPlayerPhysicalStats";

    }

    @GetMapping("/playerPhysicalStatsForm/{userId}/{teamId}")
    public String getPlayerPhysicalStatsForm(Model model,
                                             @PathVariable int userId,
                                             @PathVariable int teamId) {
        PlayerPhysicalStats playerPhysicalStats = new PlayerPhysicalStats();
        User player = userService.getUserById(userId);
        playerPhysicalStats.setPlayer(player);

        model.addAttribute("teamId", teamId);
        model.addAttribute("playerPhysicalStats", playerPhysicalStats);
        return "addPlayerPhysicalStatsForm";
    }

    @PostMapping("/createPlayerPhysicalStats")
    public String createPlayerPhysicalStats(Model model,
                                            @ModelAttribute PlayerPhysicalStats playerPhysicalStats,
                                            @RequestParam("teamId") int teamId,
                                            RedirectAttributes redirectAttributes) {
        User player = userService.getUserById(playerPhysicalStats.getPlayer().getUserId());
        playerPhysicalStats.setPlayer(player);
        playerPhysicalStatsService.createPlayerPhysicalStats(playerPhysicalStats);
        redirectAttributes.addFlashAttribute("successMessage", "Player physical stats has been created");
        return "redirect:/playerPhysicalStats/viewAllPlayerPhysicalStats/"
                +playerPhysicalStats.getPlayer().getUserId()+"/"+teamId;

    }
}
