package pl.pomoku.schoolabsencecounter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pomoku.schoolabsencecounter.dto.UserDto;
import pl.pomoku.schoolabsencecounter.entity.Role;
import pl.pomoku.schoolabsencecounter.entity.User;
import pl.pomoku.schoolabsencecounter.repositoriy.RoleRepository;
import pl.pomoku.schoolabsencecounter.repositoriy.UserRepository;
import pl.pomoku.schoolabsencecounter.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        userRepository.save(mapToUser(userDto));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUser() {
        return userRepository.findAll().stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private User mapToUser(UserDto userDto){
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) role = checkRoleExist();

        return User.builder()
                .name(userDto.getFirstName() + " " + userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(Collections.singletonList(role)).build();
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
