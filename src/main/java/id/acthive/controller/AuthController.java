package id.acthive.controller;

import id.acthive.dto.LoginRequest;
import id.acthive.dto.LoginResponse;
import id.acthive.dto.RegisterRequest;
import id.acthive.dto.RegisterResponse;
import id.acthive.service.AuthService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AuthController {

  protected final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Post("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<LoginResponse> login(@Body LoginRequest request) {
    return HttpResponse.ok(authService.login(request));
  }

  @Post("/register")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<RegisterResponse> register(@Body RegisterRequest request) {
    return HttpResponse.ok(authService.register(request));
  }

}
