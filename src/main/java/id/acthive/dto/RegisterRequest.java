package id.acthive.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class RegisterRequest {

  private String firstName;
  private String lastName;
  private String phone;
  private Integer age;
  private String email;
  private String password;
}
