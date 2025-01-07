package com.g2.dto;

public class ExternalUserDto {
    
    private Long userId;
    private String email;
    private int rank;
    private String firstName;
    private String lastName;
    private int gamesPlayed;
    private int gamesWon;
    private double gamesRate;

    // Default Constructor
    public ExternalUserDto() {
    }

    // Parameterized Constructor
    public ExternalUserDto(Long userId, String email, int rank, String firstName, String lastName, 
                          int gamesPlayed, int gamesWon, double gamesRate) {
        this.userId = userId;
        this.email = email;
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesRate = gamesRate;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public double getGamesRate() {
        return gamesRate;
    }

    public void setGamesRate(double gamesRate) {
        this.gamesRate = gamesRate;
    }

    @Override
    public String toString() {
        return "ExternalUserDto{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", rank=" + rank +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", gamesRate=" + gamesRate +
                '}';
    }
}