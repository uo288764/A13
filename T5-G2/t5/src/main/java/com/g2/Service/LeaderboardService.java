package com.g2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.g2.dto.ExternalUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LeaderboardService {

    private static final Logger logger = LoggerFactory.getLogger(LeaderboardService.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/user";

    public String handleUserScore(String email, ExternalUserDto userDto, boolean win) {
        try {
            logger.info("Handling score for user: {}, win: {}", email, win);
            
            ExternalUserDto existingUser = getUserByEmail(email);
            
            if (existingUser != null) {
                logger.info("Existing user found: {}", existingUser);
                // Update existing user stats
                existingUser.setGamesPlayed(existingUser.getGamesPlayed() + 1);
                if (win) {
                    existingUser.setGamesWon(existingUser.getGamesWon() + 1);
                }
                // Recalculate win rate
                double winRate = (double) existingUser.getGamesWon() / existingUser.getGamesPlayed() * 100;
                existingUser.setGamesRate(winRate);
                
                return updateUserStats(existingUser);
            } else {
                logger.info("Creating new user with data: {}", userDto);
                return postScores(userDto);
            }
        } catch (Exception e) {
            logger.error("Error in handleUserScore: ", e);
            throw new RuntimeException("Failed to handle user score", e);
        }
    }

    public ExternalUserDto getUserByEmail(String email) {
        String url = BASE_URL + "/user?email=" + email;
        try {
            logger.info("Fetching user by email: {}", email);
            ResponseEntity<ExternalUserDto> response = restTemplate.getForEntity(url, ExternalUserDto.class);
            logger.info("Got response status: {}", response.getStatusCode());
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("Error fetching user by email: {}", email, e);
        }
        return null;
    }

    private String updateUserStats(ExternalUserDto userDto) {
        String url = BASE_URL + "/updatestats";
        try {
            logger.info("Updating user stats: {}", userDto);
            HttpEntity<ExternalUserDto> requestEntity = new HttpEntity<>(userDto);
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                String.class
            );
            
            logger.info("Update response status: {}", response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.OK) {
                return "User stats updated successfully: " + response.getBody();
            } else {
                logger.error("Failed to update user stats. Status: {}", response.getStatusCode());
                return "Failed to update user stats: " + response.getBody();
            }
        } catch (Exception e) {
            logger.error("Error updating user stats: ", e);
            throw new RuntimeException("Failed to update user stats", e);
        }
    }

    public String postScores(ExternalUserDto userDto) {
        String url = BASE_URL + "/scores";
        try {
            logger.info("Creating new user: {}", userDto);
            HttpEntity<ExternalUserDto> requestEntity = new HttpEntity<>(userDto);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            
            logger.info("Create user response status: {}", response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.OK) {
                return "User created successfully: " + response.getBody();
            } else {
                logger.error("Failed to create user. Status: {}", response.getStatusCode());
                return "Failed to create user: " + response.getBody();
            }
        } catch (Exception e) {
            logger.error("Error creating user: ", e);
            throw new RuntimeException("Failed to create user", e);
        }
    }
}