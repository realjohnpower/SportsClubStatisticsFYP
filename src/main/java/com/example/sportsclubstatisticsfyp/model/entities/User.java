package com.example.sportsclubstatisticsfyp.model.entities;

import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;

    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;


    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;


    @Basic(optional = false)
    @Column(name = "email")
    private String email;


    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;


    @Basic(optional = false)
    @Column(name = "password")
    private String password;


    @Basic(optional = false)
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;


    @Basic(optional = false)
    @Column(name = "date_registered")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateRegistered;


    /*
     Defined a many-to-many relationship between the User
     entity and the Role entity, specifying that each club member could have
     multiple roles and each role can be assigned to multiple club members.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"), //The foreign key column in the join table (user_roles) that refers to the Customer entity.
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    //The foreign key column in the join table that refers to the Role entity.
//I'm using a Set here so that roles are unique
//This prevents duplicate entries for the same role on a user.
    private Set<Role> roles = new HashSet();

    //list of teams for a club member with a 'PLAYER' role
    @ManyToMany(mappedBy = "teamMembers")
    private Set<Team> listOfTeams = new HashSet();
    //list of teams for club member with a 'TRAINER' role
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainer")
    @ToString.Exclude
    private Set<Team> trainersListOfTeams;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    @ToString.Exclude
    private Set<TeamSessionStats> listOfPlayerSessionsStats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    @ToString.Exclude
    private List<PlayerPhysicalStats> listOfPlayerPhysicalStats;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Team> getListOfTeams() {
        return listOfTeams;
    }

    public void setListOfTeams(Set<Team> listOfTeams) {
        this.listOfTeams = listOfTeams;
    }

    public Set<Team> getTrainersListOfTeams() {
        return trainersListOfTeams;
    }

    public void setTrainersListOfTeams(Set<Team> trainersListOfTeams) {
        this.trainersListOfTeams = trainersListOfTeams;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Set<TeamSessionStats> getListOfPlayerSessionsStats() {
        return listOfPlayerSessionsStats;
    }

    public void setListOfPlayerSessionsStats(Set<TeamSessionStats> listOfPlayerSessionsStats) {
        this.listOfPlayerSessionsStats = listOfPlayerSessionsStats;
    }

    public List<PlayerPhysicalStats> getListOfPlayerPhysicalStats() {
        return listOfPlayerPhysicalStats;
    }

    public void setListOfPlayerPhysicalStats(List<PlayerPhysicalStats> listOfPlayerPhysicalStats) {
        this.listOfPlayerPhysicalStats = listOfPlayerPhysicalStats;
    }

    public static String getLoggedInEmail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String usersEmail= ((UserDetails)principal).getUsername();

        return usersEmail;
    }
}
