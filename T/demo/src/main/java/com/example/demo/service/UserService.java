package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;


public interface UserService {

  String postScores(UserDto userLoginDto) throws Exception;

  List<User> getAllScores() throws Exception;

  User updateUserScore(String email, boolean win) throws Exception;

}
