package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    CartDto cartDto;

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPassword(),
                userDto.getEmailAddress(),
                userDto.getAddress(),
                userDto.getPhoneNumber(),
                cartDto.getListOfProducts()
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmailAddress(),
                user.getAddress(),
                user.getPhoneNumber(),
                cartDto.getUserDto().getCart()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
