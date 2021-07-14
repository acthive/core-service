package id.acthive.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class LoginRequest {

  private String email;
  private String password;
}
