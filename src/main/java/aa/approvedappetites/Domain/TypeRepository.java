package aa.approvedappetites.Domain;

import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {

    Type findByName(String name);

}
