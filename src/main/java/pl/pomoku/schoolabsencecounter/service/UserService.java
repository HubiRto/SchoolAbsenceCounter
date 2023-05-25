package pl.pomoku.schoolabsencecounter.service;

import pl.pomoku.schoolabsencecounter.dto.UserDto;
import pl.pomoku.schoolabsencecounter.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUser();
}
