package mk.ukim.finki.emt2025b.emt2025b.service.application;

import mk.ukim.finki.emt2025b.emt2025b.dto.CreateUserDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayUserDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.LoginResponseDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
