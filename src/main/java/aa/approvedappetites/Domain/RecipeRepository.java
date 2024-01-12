package aa.approvedappetites.Domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    /* Search by Type */
    @Query("SELECT r FROM Recipe r WHERE r.type.name = :typeName")
    List<Recipe> findByTypeName(@Param("typeName") String typeName);

    /* Search by name/partial name */
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE %:name%")
    List<Recipe> findByName(@Param("name") String name);

}
