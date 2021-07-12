package id.acthive.repository;

import id.acthive.dao.PersonDao;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends CrudRepository<PersonDao, UUID> {

  Optional<PersonDao> findByEmail(String email);

  Optional<PersonDao> findByPhone(String phone);
}
