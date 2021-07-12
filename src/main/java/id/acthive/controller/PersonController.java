package id.acthive.controller;

import id.acthive.dao.PersonDao;
import id.acthive.dto.RegisterRequest;
import id.acthive.repository.PersonRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import java.util.Optional;
import java.util.UUID;


@Controller("/persons")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class PersonController {

  protected final PersonRepository personRepository;

  public PersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Get("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<PersonDao> findPersonById(@PathVariable("id") String id) {
    Optional<PersonDao> personDaoOptional = personRepository.findById(UUID.fromString(id));
    if (personDaoOptional.isPresent()) {
      return HttpResponse.ok(personDaoOptional.get());
    }
    return HttpResponse.noContent();
  }

  @Get("/{email}")
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<PersonDao> findPersonByEmail(@PathVariable("email") String email) {
    Optional<PersonDao> personDaoOptional = personRepository.findByEmail(email);
    if (personDaoOptional.isPresent()) {
      return HttpResponse.ok(personDaoOptional.get());
    }
    return HttpResponse.noContent();
  }

  @Get("/{phone}")
  public HttpResponse<PersonDao> findPersonByPhone(@PathVariable("phone") String phone) {
    Optional<PersonDao> personDaoOptional = personRepository.findByPhone(phone);
    if (personDaoOptional.isPresent()) {
      return HttpResponse.ok(personDaoOptional.get());
    }
    return HttpResponse.noContent();
  }

  @Post
  public HttpResponse<PersonDao> savePerson(@Body RegisterRequest request) {
    var personDao = new PersonDao();
    personDao.setFirstName(request.getFirstName());
    personDao.setLastName(request.getLastName());
    personDao.setPhone(request.getPhone());
    personDao.setAge(request.getAge());
    personDao.setEmail(request.getEmail());
    personDao.setPassword(request.getPassword());
    PersonDao save = personRepository.save(personDao);
    return HttpResponse.ok(save);
  }
}
