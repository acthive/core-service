package id.acthive.service;

import id.acthive.dto.LoginRequest;
import id.acthive.dto.LoginResponse;
import id.acthive.dto.RegisterRequest;
import id.acthive.dto.RegisterResponse;

public interface AuthService {

  LoginResponse login(LoginRequest request);

  RegisterResponse register(RegisterRequest request);
}
