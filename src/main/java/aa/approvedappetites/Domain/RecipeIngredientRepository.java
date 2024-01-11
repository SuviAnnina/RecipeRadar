package aa.approvedappetites.Domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {

    @Query("SELECT ri FROM RecipeIngredient ri WHERE ri.recipe.id = :recipeId")
    List<RecipeIngredient> findByRecipeId(@Param("recipeId") Long recipeId);

}
