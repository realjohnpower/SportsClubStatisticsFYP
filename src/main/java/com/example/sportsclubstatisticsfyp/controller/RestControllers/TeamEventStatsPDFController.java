package com.example.sportsclubstatisticsfyp.controller.RestControllers;

import com.example.sportsclubstatisticsfyp.model.entities.TeamEvent;
import com.example.sportsclubstatisticsfyp.service.TeamEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/teamSessionStats")
public class TeamEventStatsPDFController {
    @Autowired
    private TeamEventService teamEventService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getTeamEventStatsPDF(@PathVariable int id) {
        TeamEvent teamEvent = teamEventService.getTeamEventById(id);
        try {
            byte[] pdfBytes = teamEventService.generateTeamSessionStatsPDF(teamEvent);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename(teamEvent.getTeamEventId() + ".pdf").build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
