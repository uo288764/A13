package com.example.demo.dto;

public class ResponseDto {

    private int rank;
    private String firstName;
    private String lastName;
    private String email;
    private int gamesPlayed;
    private int gamesWon;
    private double gamesRate;

    // Default Constructor
    public ResponseDto() {}

    // All-Args Constructor
    public ResponseDto(int rank, String firstName, String lastName, String email, int gamesPlayed, int gamesWon, double gamesRate) {
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesRate = gamesRate;
    }

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
