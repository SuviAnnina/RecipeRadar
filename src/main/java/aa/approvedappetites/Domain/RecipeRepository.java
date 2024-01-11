package aa.approvedappetites.Domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE r.type.name = :typeName")
    List<Recipe> findByTypeName(@Param("typeName") String typeName);

}
