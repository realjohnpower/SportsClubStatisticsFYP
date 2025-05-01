package com.example.sportsclubstatisticsfyp.RestControllers;

import com.example.sportsclubstatisticsfyp.model.entities.TeamEvent;
import com.example.sportsclubstatisticsfyp.service.TeamEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/teamSessionStats")
public class TeamSessionStatsPDFRestController {
    @Autowired
    private TeamEventService teamEventService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamEventStatsPDF(@PathVariable int id) {
        TeamEvent teamEvent = teamEventService.getTeamEventById(id);
        try {
            ByteArrayOutputStream file= teamEventService.generateTeamSessionStatsPDF(teamEvent);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(file.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}

