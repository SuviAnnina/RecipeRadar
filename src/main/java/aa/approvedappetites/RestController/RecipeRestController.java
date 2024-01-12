package aa.approvedappetites.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import aa.approvedappetites.Domain.Recipe;
import aa.approvedappetites.Domain.RecipeRepository;
import aa.approvedappetites.Domain.TypeRepository;

@CrossOrigin
@Controller
public class RecipeRestController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private TypeRepository typeRepository;

    /* Listaa kaikki Recipe-luokan javaoliot JSON-listaksi */
    @GetMapping("/recipes")
    public @ResponseBody List<Recipe> getAllRecipesRest() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    /* Etsii reseptin id:n perusteella ja palauttaa sen JSON-muodossa */
    @GetMapping("/recipe/{id}")
    public @ResponseBody Optional<Recipe> getRecipeByIdRest(@PathVariable("id") Long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    /* Tallentaa uuden reseptin tietokantaan */
    @PostMapping("/recipes")
    public @ResponseBody Recipe saveRecipeRest(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    /* Editoi tarvittavat tiedot reseptiin id:n perusteella */
    @PatchMapping("/recipes/{id}")
    public @ResponseBody Recipe patchRecipeRest(@PathVariable Long recipeId, @RequestBody Recipe updatedRecipe) {
        Optional<Recipe> existingRecipeOptional = recipeRepository.findById(recipeId);

        if (existingRecipeOptional.isPresent()) {
            Recipe existingRecipe = existingRecipeOptional.get();

            if (updatedRecipe.getName() != null) {
                existingRecipe.setName(updatedRecipe.getName());
            }
            if (updatedRecipe.getCookTime() != null) {
                existingRecipe.setCookTime(updatedRecipe.getCookTime());
            }
            if (updatedRecipe.getPrepTime() != null) {
                existingRecipe.setPrepTime(updatedRecipe.getPrepTime());
            }
            if (updatedRecipe.getInstructions() != null) {
                existingRecipe.setInstructions(updatedRecipe.getInstructions());
            }
            if (updatedRecipe.getNote() != null) {
                existingRecipe.setNote(updatedRecipe.getNote());
            }
            if (updatedRecipe.getRecipeIngredients() != null) { /* tässä voi jatkossa tulla ongelmia? */
                existingRecipe.setRecipeIngredients(updatedRecipe.getRecipeIngredients());
            }
            if (updatedRecipe.getType() != null) {
                existingRecipe.setType(updatedRecipe.getType());
            }

            return recipeRepository.save(existingRecipe);
        } else {
            throw new ResourceNotFoundException("Recipe not found with id " + recipeId);
        }

    }

    /* Poistaa reseptin tietokannasta id:n perusteella */
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<String> deleteRecipeByIdRest(@PathVariable("id") Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isPresent()) {
            recipeRepository.deleteById(recipeId);
            return ResponseEntity.ok("Recipe deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found with id " + recipeId);
        }
    }

    /* Etsii reseptit tyypin mukaan */
    @GetMapping("/recipes/byType/{type}")
    public @ResponseBody List<Recipe> getRecipesByTypeRest(@PathVariable("type") String typeName) {

        List<Recipe> recipes = recipeRepository.findByTypeName(typeName);

        if (!recipes.isEmpty()) {
            return recipes;
        } else {
            throw new ResourceNotFoundException("Recipes not found with type " + typeName);
        }
    }

    /* Etsii reseptin nimen mukaan */
    @GetMapping("/recipes/searchByName/{name}")
    public @ResponseBody List<Recipe> getRecipesByName(@PathVariable("name") String searchedName) {
        List<Recipe> recipes = recipeRepository.findByName(searchedName);

        if (recipes.isEmpty()) {
            throw new ResourceNotFoundException("No recipes found with " + searchedName);
        }

        return recipes;
    }

}
