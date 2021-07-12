package id.acthive.auth;

import id.acthive.dao.PersonDao;
import id.acthive.repository.PersonRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.Optional;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

  protected final PersonRepository personRepository;

  public AuthenticationProviderUserPassword(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
      AuthenticationRequest<?, ?> authenticationRequest) {
    return Flowable.create(emitter -> {

      var email = (String) authenticationRequest.getIdentity();
      var password = (String) authenticationRequest.getSecret();

      Optional<PersonDao> personDaoOptional = personRepository.findByEmail(email);
      if (personDaoOptional.isPresent() && personDaoOptional.get().getPassword().equals(password)) {
        emitter.onNext(
            new UserDetails(email, new ArrayList<>()));
        emitter.onComplete();
      } else {
        emitter.onError(new AuthenticationException(new AuthenticationFailed()));
      }
    }, BackpressureStrategy.ERROR);
  }
}