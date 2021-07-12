package id.acthive.service;

import id.acthive.auth.JwtClient;
import id.acthive.dao.PersonDao;
import id.acthive.dao.PersonStatus;
import id.acthive.dto.LoginRequest;
import id.acthive.dto.LoginResponse;
import id.acthive.dto.RegisterRequest;
import id.acthive.dto.RegisterResponse;
import id.acthive.repository.PersonRepository;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import java.time.LocalDateTime;
import javax.inject.Singleton;

@Singleton
public class AuthServiceImpl implements AuthService {

  @Client
  protected final JwtClient client;
  protected final PersonRepository personRepository;

  public AuthServiceImpl(JwtClient client, PersonRepository personRepository) {
    this.client = client;
    this.personRepository = personRepository;
  }

  @Override
  public LoginResponse login(LoginRequest request) {

    var personDaoOptional = personRepository.findByEmail(request.getUsername());
    if (personDaoOptional.isPresent() && personDaoOptional.get().getPassword()
        .equals(request.getPassword())) {
      PersonDao personDao = personDaoOptional.get();
      var credentials = new UsernamePasswordCredentials(personDao.getEmail(),
          personDao.getPassword());
      var token = client.login(credentials);
      personDao.setToken(token.getAccessToken());
      personDao.setTokenExpiresIn(LocalDateTime.now().plusDays(7));
      PersonDao update = personRepository.update(personDao);

      return LoginResponse.builder()
          .username(update.getEmail())
          .token(update.getToken())
          .tokenExpiresIn(update.getTokenExpiresIn())
          .build();
    }
    return null;
  }

  @Override
  public RegisterResponse register(RegisterRequest request) {
    var personDao = new PersonDao();
    personDao.setFirstName(request.getFirstName());
    personDao.setLastName(request.getLastName());
    personDao.setPhone(request.getPhone());
    personDao.setAge(request.getAge());
    personDao.setEmail(request.getEmail());
    personDao.setStatus(PersonStatus.NOT_ACTIVE);
    personDao.setPassword(request.getPassword());
    PersonDao save = personRepository.save(personDao);

    return RegisterResponse.builder()
        .age(save.getAge())
        .status(save.getStatus())
        .email(save.getEmail())
        .firstName(save.getFirstName())
        .lastName(save.getLastName())
        .id(save.getId())
        .phone(save.getPhone())
        .build();
  }
}
