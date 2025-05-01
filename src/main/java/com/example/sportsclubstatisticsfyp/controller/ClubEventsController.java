package com.example.sportsclubstatisticsfyp.controller;


import com.example.sportsclubstatisticsfyp.model.entities.ClubEvents;

import com.example.sportsclubstatisticsfyp.model.entities.User;
import com.example.sportsclubstatisticsfyp.service.ClubEventsService;
import com.example.sportsclubstatisticsfyp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clubEvents")
public class ClubEventsController {
    @Autowired
    private ClubEventsService clubEventsService;
    @Autowired
    private UserService userService;

    @GetMapping("/addClubEventForm")
    public ModelAndView displayaddClubEventForm(Model model) {

        ClubEvents newClubEvent = new ClubEvents();
        return new ModelAndView("addClubEventForm", "newClubEvent", newClubEvent );

    }

    @PostMapping("/addClubEvent")
    public ModelAndView addClubEvent(@ModelAttribute("newClubEvent") ClubEvents newClubEvent, RedirectAttributes redirectAttributes ) throws ParseException {

        clubEventsService.createClubEvent(newClubEvent);
        redirectAttributes.addFlashAttribute("successMessage", "Club Event has been created");
        return new ModelAndView("redirect:/clubEvents/viewClubEvents");

    }

    @GetMapping("/viewClubEvents")
    public ModelAndView displayClubEventList() {

        List<ClubEvents> listOfClubEvents = clubEventsService.getAllClubEvents();


        return new ModelAndView("viewClubEvents", "clubEventList", listOfClubEvents );

    }

    @GetMapping("/clubEventDrillDown/{id}")
    public ModelAndView clubEventDrillDown(@PathVariable("id") Integer id, Model model) throws JsonProcessingException {

        ClubEvents clubEvent = clubEventsService.getClubEventById(id);
        Integer attendingCount = clubEventsService.getAttendingRecordCount(clubEvent,true);
        Integer notAttendingCount = clubEventsService.getAttendingRecordCount(clubEvent, false);
        Integer attendanceNotRecordedCount = clubEventsService.getNotRecordedAttendanceCount(clubEvent);

        model.addAttribute("attendingCount", attendingCount);
        model.addAttribute("notAttendingCount", notAttendingCount);
        model.addAttribute("attendanceNotRecordedCount", attendanceNotRecordedCount);
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> eventAttendanceMap = new ArrayList<>();
        eventAttendanceMap.add(Map.of("category","Attending","value", attendingCount));
        eventAttendanceMap.add(Map.of("category","Not Attending","value", notAttendingCount));
        eventAttendanceMap.add(Map.of("category","Attendance Not Recorded","value", attendanceNotRecordedCount));

        String jsonClubEventAttendanceString = objectMapper.writeValueAsString(eventAttendanceMap);
        model.addAttribute("clubEventAttendanceStats", jsonClubEventAttendanceString);
        model.addAttribute("notAttendingCount",notAttendingCount);
        model.addAttribute("attendingCount",attendingCount);
        return new ModelAndView("clubEventDrillDown", "clubEvent", clubEvent );

    }

    @GetMapping("/removeClubEvent/{clubEventId}")
    public ModelAndView removeclubEvent (RedirectAttributes redirectAttributes,
                                      @PathVariable("clubEventId") int clubEventId,
                                      Model model ) {

        ClubEvents clubEvent = clubEventsService.getClubEventById(clubEventId);
        clubEventsService.removeClubEvent(clubEvent);
        List<ClubEvents> listOfClubEvents = clubEventsService.getAllClubEvents();

        redirectAttributes.addFlashAttribute("successMessage", "Club Event has been removed");

        return new ModelAndView("redirect:/clubEvents/viewClubEvents", "clubEventList", listOfClubEvents );
    }


    @GetMapping("/goingToClubEvent/{id}/{attending}")
    public ModelAndView goingToClubEvent(@PathVariable("id") Integer id,
                                         @PathVariable("attending") boolean attending,
                                         RedirectAttributes redirectAttributes) {

        ClubEvents clubEvent = clubEventsService.getClubEventById(id);

        User user = userService.getUserByEmail(User.getLoggedInEmail());
        clubEventsService.goingToClubEvent(clubEvent,user, attending);
        List<ClubEvents> listOfClubEvents = clubEventsService.getAllClubEvents();

        redirectAttributes.addFlashAttribute("successMessage", "Club event attendance recorded");

        return new ModelAndView("redirect:/clubEvents/viewClubEvents", "clubEventList", listOfClubEvents );

    }

    @GetMapping("/editClubEventForm/{id}")
    public ModelAndView editClubEventForm(@PathVariable("id") Integer id) {

        ClubEvents clubEvent = clubEventsService.getClubEventById(id);
        return new ModelAndView("editClubEventForm", "clubEvent", clubEvent );

    }

    @PostMapping("/editClubEvent")
    public ModelAndView editClubEvent(@ModelAttribute("clubEvent") ClubEvents clubEvent, RedirectAttributes redirectAttributes) {

        clubEventsService.updateClubEvent(clubEvent);

        redirectAttributes.addFlashAttribute("successMessage", "Club Event has been updated");
        return new ModelAndView("redirect:/clubEvents/viewClubEvents");

    }




}
