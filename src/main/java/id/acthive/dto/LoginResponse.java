package id.acthive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "id_ID")
  private LocalDateTime tokenExpiresIn;
}
