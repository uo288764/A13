package com.example.demo.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.Request;
import com.example.demo.model.User;
import com.example.demo.repo.Userrepo;
import com.example.demo.service.UserService;

@Service
public class Userimp implements UserService {

  @Autowired
  private Userrepo userRepo;

  @Override
  public String postScores(UserDto userDto) throws Exception {
    try {
      // Map UserDto to User
      User user = Request.toUser(userDto);

      // Save the User entity to the database
      userRepo.save(user);

      // Return success message
      return "Success";
    } catch (Exception err) {
      throw new Exception("Error occurred while saving user scores: " + err.getMessage(), err);
    }
  }

  @Override
  public List<User> getAllScores() throws Exception {
    try {
      // Fetch all users from the repository
      List<User> listOfUsers = userRepo.findAll();

      listOfUsers.sort((u1, u2) -> {
            // WeightedScore = GamesRate * log(GamesPlayed + 1)
            double weightedScore1 = u1.getGamesRate() * Math.log(u1.getGamesPlayed() + 1);
            double weightedScore2 = u2.getGamesRate() * Math.log(u2.getGamesPlayed() + 1);

            return Double.compare(weightedScore2, weightedScore1); // Descending order
        });

        // Assign ranks to users based on their sorted order
        for (int i = 0; i < listOfUsers.size(); i++) {
            User user = listOfUsers.get(i);
            user.setRank(i + 1); // Rank starts from 1
        }

        // Save the updated ranks back to the repository
        userRepo.saveAll(listOfUsers);

      return listOfUsers;
    } catch (Exception err) {
      System.out.println("Error caught in getAllScores: " + err);
      throw err; // Rethrow the exception to inform the caller
    }
  }

  @Override
  public User updateUserScore(String email, boolean win) {
    try {
      // Retrieve the user or throw an exception if not found
      User user = userRepo.findByEmail(email)
          .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));

      // Increment games played
      user.setGamesPlayed(user.getGamesPlayed() + 1);

      // Increment games won if the user won
      if (win) {
        user.setGamesWon(user.getGamesWon() + 1);
      }

      user.setGamesRate(Math.round(((double) user.getGamesWon() / user.getGamesPlayed()) * 100 * 100.0) / 100.0);

      // Save the updated user
      userRepo.save(user);

      return user;
    } catch (Exception err) {
      // Log the exception
      System.err.println("Error updating user score: " + err.getMessage());
      // Rethrow the exception to inform the caller
      throw err;
    }
  }

  @Override
  public User getUserByEmail(String email) {
      try {
          return userRepo.findByEmail(email)
              .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
      } catch (Exception err) {
          System.err.println("Error retrieving user by email: " + err.getMessage());
          throw err;
      }
  }

}
