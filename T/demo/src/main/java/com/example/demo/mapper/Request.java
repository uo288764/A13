package com.example.demo.mapper;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;



public class Request {

  public static User toUser(UserDto userDto) {
    User user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    return user;
  }

  public static ResponseDto userToResponseDto(User user) {
    ResponseDto responseDto = new ResponseDto();
    responseDto.setFirstName(user.getFirstName());
    responseDto.setLastName(user.getLastName());
    responseDto.setEmail(user.getEmail());
    responseDto.setGamesPlayed(user.getGamesPlayed());
    responseDto.setGamesRate(user.getGamesRate());
    responseDto.setGamesWon(user.getGamesWon());
    responseDto.setRank(user.getRank());
    return responseDto;
  }
}
