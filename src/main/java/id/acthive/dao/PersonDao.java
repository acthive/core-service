package id.acthive.dao;

import io.micronaut.core.annotation.Introspected;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "acthive", name = "person")
public class PersonDao {

  @Id
  private UUID id = UUID.randomUUID();

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "phone", nullable = false, unique = true)
  private String phone;

  @Column(name = "age", nullable = false)
  private Integer age;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private PersonStatus status = PersonStatus.NOT_ACTIVE;

  @Column(name = "token")
  private String token;

  @Column(name = "token_expires_in", columnDefinition = "TIMESTAMP")
  private LocalDateTime tokenExpiresIn;

  @Column(name = "created_timestamp", nullable = false, columnDefinition = "TIMESTAMP")
  private LocalDateTime createdTimestamp = LocalDateTime.now();

  @Column(name = "modified_timestamp", nullable = false, columnDefinition = "TIMESTAMP")
  private LocalDateTime modifiedTimestamp = LocalDateTime.now();

  @Column(name = "deleted_timestamp", columnDefinition = "TIMESTAMP")
  private LocalDateTime deletedTimestamp;
}
