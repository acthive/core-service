package id.acthive.dto;

import id.acthive.dao.PersonStatus;
import io.micronaut.core.annotation.Introspected;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Introspected
public class RegisterResponse {

  private UUID id;
  private String firstName;
  private String lastName;
  private String phone;
  private Integer age;
  private String email;
  private PersonStatus status;
}
