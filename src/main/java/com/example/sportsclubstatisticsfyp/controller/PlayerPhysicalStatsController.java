package com.example.sportsclubstatisticsfyp.controller;

import com.example.sportsclubstatisticsfyp.model.entities.PlayerPhysicalStats;
import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.model.repositories.PlayerPhysicalStatsRepository;
import com.example.sportsclubstatisticsfyp.service.PlayerPhysicalStatsService;
import com.example.sportsclubstatisticsfyp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/playerPhysicalStats")
public class PlayerPhysicalStatsController {

    @Autowired
    private PlayerPhysicalStatsService playerPhysicalStatsService;
    @Autowired
    private UserService userService;

    @GetMapping("/viewAllPlayerPhysicalStats/{userId}/{teamId}")
    public String viewPlayerPhysicalStastByUserId(Model model,
                                                  @PathVariable int userId,
                                                  @PathVariable int teamId) {
        List<PlayerPhysicalStats> listOfPlayerPhysicalStats = playerPhysicalStatsService.getPlayerPhysicalStatsById(userId);
        model.addAttribute("teamId", teamId);
        model.addAttribute("listOfPlayerPhysicalStats", listOfPlayerPhysicalStats);
        User player=userService.getUserById(userId);
        model.addAttribute("player", player);
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
        redirectAttributes.addFlashAttribute("message", "Player physical stats has been created");
        return "redirect:/playerPhysicalStats/viewAllPlayerPhysicalStats/"
                +playerPhysicalStats.getPlayer().getUserId()+"/"+teamId;

    }
}
