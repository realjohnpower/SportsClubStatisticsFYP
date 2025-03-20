package com.example.sportsclubstatisticsfyp.service;

import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamDTOForm;
import com.example.sportsclubstatisticsfyp.model.DTOForms.RegisterTeamEventDTOForm;
import com.example.sportsclubstatisticsfyp.model.DTOForms.TeamSessionStatsDTO;
import com.example.sportsclubstatisticsfyp.model.entities.*;
import com.example.sportsclubstatisticsfyp.model.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private TeamSessionStatsRepository teamSessionStatsRepository;
    @Autowired
    private UserService userService;


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
    public void createTeamSessionStatsForTeamSessionFromCsvFile (TeamEvent teamEvent, MultipartFile csvFile){

       try{
           BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));

           CSVParser  csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

           List<TeamSessionStats> sessionStats=new ArrayList<>();

           for(CSVRecord record: csvParser){
               TeamSessionStats teamSessionStats=new TeamSessionStats();
               int playerID=Integer.parseInt(record.get("PlayerID"));
               double maxBPM = Double.parseDouble(record.get("MaxBPM"));
               double restingBPM = Double.parseDouble(record.get("RestingBPM"));
               double averageBPM= Double.parseDouble(record.get("AverageBPM"));
               double caloriesBurned= Double.parseDouble(record.get("CaloriesBurned"));
               User player = userRepository.findById(playerID).orElse(null);

               teamSessionStats.setPlayer(player);
               teamSessionStats.setTeamSession(teamEvent);
               teamSessionStats.setCaloriesBurned(caloriesBurned);
               teamSessionStats.setMaxBpm(maxBPM);
               teamSessionStats.setRestingBpm(restingBPM);
               teamSessionStats.setAverageBpm(averageBPM);
               sessionStats.add(teamSessionStats);
           }
           teamEvent.setListOfPlayerSessionsStats(sessionStats);
           teamEventRepository.save(teamEvent);
       }catch (Exception e){
           throw new RuntimeException("Error in Processing file");
        }

    }

    public List<Map<String,Object>> getTenHighestStatsForSession(TeamEvent teamEvent,String statType) {
        List<TeamSessionStats> teamSessionStats = teamEvent.getListOfPlayerSessionsStats();
        List<Map<String, Object>> statListMap = new ArrayList<>();
        switch (statType) {
            case "highestBPM":
                List<TeamSessionStats> top10HighestBMIlist = teamSessionStats.stream()
                        .sorted(Comparator.comparing(TeamSessionStats::getMaxBpm).reversed())
                        .limit(2)
                        .collect(Collectors.toList());

                for (TeamSessionStats stats : top10HighestBMIlist) {
                    Map<String, Object> map = new HashMap<>();
                    String playerName = stats.getPlayer().getFirstName() + " " + stats.getPlayer().getLastName();
                    map.put("name", playerName);
                    map.put("value", stats.getMaxBpm());
                    statListMap.add(map);
                }
                break;

            case "averageBPM":
                List<TeamSessionStats> top10HighestAverageBPMlist = teamSessionStats.stream()
                        .sorted(Comparator.comparing(TeamSessionStats::getAverageBpm).reversed())
                        .limit(2)
                        .collect(Collectors.toList());

                for (TeamSessionStats stats : top10HighestAverageBPMlist) {
                    Map<String, Object> map = new HashMap<>();
                    String playerName = stats.getPlayer().getFirstName() + " " + stats.getPlayer().getLastName();
                    map.put("name", playerName);
                    map.put("value", stats.getAverageBpm());
                    statListMap.add(map);


                }
                break;
            case "restingBPM":
                List<TeamSessionStats> top10HighestRestingBPMlist = teamSessionStats.stream()
                        .sorted(Comparator.comparing(TeamSessionStats::getRestingBpm).reversed())
                        .limit(2)
                        .collect(Collectors.toList());

                for (TeamSessionStats stats : top10HighestRestingBPMlist) {
                    Map<String, Object> map = new HashMap<>();
                    String playerName = stats.getPlayer().getFirstName() + " " + stats.getPlayer().getLastName();
                    map.put("name", playerName);
                    map.put("value", stats.getRestingBpm());
                    statListMap.add(map);


                }
                break;
            case "caloriesBurned":
                List<TeamSessionStats> top10HighestCaloriesBurnedlist = teamSessionStats.stream()
                        .sorted(Comparator.comparing(TeamSessionStats::getCaloriesBurned).reversed())
                        .limit(2)
                        .collect(Collectors.toList());

                for (TeamSessionStats stats : top10HighestCaloriesBurnedlist) {
                    Map<String, Object> map = new HashMap<>();
                    String playerName = stats.getPlayer().getFirstName() + " " + stats.getPlayer().getLastName();
                    map.put("name", playerName);
                    map.put("value", stats.getCaloriesBurned());
                    statListMap.add(map);


                }
                break;


        }
        return statListMap;

    }
public byte[] generateTeamSessionStatsPDF(TeamEvent teamEvent) throws FileNotFoundException {
        String fileName = teamEvent.getTeamEventId()+".pdf";

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PdfWriter pdfWriter= new PdfWriter(outputStream);
    PdfDocument pdf = new PdfDocument(pdfWriter);
    Document document = new Document(pdf);

    document.add(new Paragraph(teamEvent.getEventName()));

    float [] pointColumnWidths = {150F, 150F, 150F,150F,150F,150F};
    Table table = new Table(pointColumnWidths);
    table.addCell(new Cell().add(new Paragraph("Player ID")));
    table.addCell(new Cell().add(new Paragraph("Player Name")));
    table.addCell(new Cell().add(new Paragraph("Resting BPM")));
    table.addCell(new Cell().add(new Paragraph("Average BPM")));
    table.addCell(new Cell().add(new Paragraph("Max BPM")));
    table.addCell(new Cell().add(new Paragraph("Calories Burned")));
    for(TeamSessionStats sessionStats : teamEvent.getListOfPlayerSessionsStats()){
        String fullName = sessionStats.getPlayer().getFirstName() + " " + sessionStats.getPlayer().getLastName();
        table.addCell(new Cell().add(new Paragraph(sessionStats.getPlayer().getUserId().toString())));
        table.addCell(new Cell().add(new Paragraph(fullName)));
        table.addCell(new Cell().add(new Paragraph(sessionStats.getRestingBpm().toString())));
        table.addCell(new Cell().add(new Paragraph(sessionStats.getAverageBpm().toString())));
        table.addCell(new Cell().add(new Paragraph(sessionStats.getMaxBpm().toString())));
        table.addCell(new Cell().add(new Paragraph(sessionStats.getCaloriesBurned().toString())));
    }
    document.add(table);
    document.close();

    // Save the PDF to a file
    try (FileOutputStream fos = new FileOutputStream(fileName)) {
        fos.write(outputStream.toByteArray());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    return outputStream.toByteArray();
}






}
