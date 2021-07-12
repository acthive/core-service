package id.acthive.dto;

import io.micronaut.core.annotation.Introspected;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Introspected
public class LoginResponse {

  private String username;
  private String token;
  private LocalDateTime tokenExpiresIn;
}
