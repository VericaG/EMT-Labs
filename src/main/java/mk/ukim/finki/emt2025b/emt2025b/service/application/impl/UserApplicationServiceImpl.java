package mk.ukim.finki.emt2025b.emt2025b.service.application.impl;

import mk.ukim.finki.emt2025b.emt2025b.dto.CreateUserDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayUserDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.LoginResponseDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.LoginUserDto;
import mk.ukim.finki.emt2025b.emt2025b.helpers.JwtHelper;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.User;
import mk.ukim.finki.emt2025b.emt2025b.service.application.UserApplicationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(loginUserDto.username(), loginUserDto.password());
        String token = jwtHelper.generateToken(user);
        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

}