package com.airofbengal.todo.service;

import com.airofbengal.todo.dto.LoginDto;
import com.airofbengal.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
