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

import aa.approvedappetites.Domain.RecipeIngredient;
import aa.approvedappetites.Domain.RecipeIngredientRepository;

@CrossOrigin
@Controller
public class RecipeIngredientRestController {

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    /* Listaa kaikki RecipeIngredient-luokan javaoliot JSON-listaksi */
    @GetMapping("/recipeingredients")
    public @ResponseBody List<RecipeIngredient> recipeIngredientListRest() {
        return (List<RecipeIngredient>) recipeIngredientRepository.findAll();
    }

    /* Listaa kaikki tietyn reseptin ainesosat */
    @GetMapping("/recipeingredients/byRecipe/{recipeId}")
    public @ResponseBody List<RecipeIngredient> getRecipeIngredientsByRecipeIdRest(
            @PathVariable("recipeId") Long recipeId) {
        return recipeIngredientRepository.findByRecipeId(recipeId);
    }

    /* Etsii reseptin ainesosan id:n perusteella ja palauttaa sen JSON muodossa */
    @GetMapping("/recipeingredient/{id}")
    public @ResponseBody Optional<RecipeIngredient> findRecipeIngredientRest(
            @PathVariable("id") Long recipeIngredientId) {
        return recipeIngredientRepository.findById(recipeIngredientId);
    }

    /* Tallentaa uuden reseptin ainesosan tietokantaan */
    @PostMapping("/recipeingredients")
    public @ResponseBody RecipeIngredient savRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    /* Editoi reseptin ainesosan id:n perusteella */
    @PatchMapping("/recipeingredients/{id}")
    public @ResponseBody RecipeIngredient patchRecipeIngredientRest(@PathVariable Long recipeIngredientId,
            @RequestBody RecipeIngredient updatedRecipeIngredient) {
        Optional<RecipeIngredient> existingRecipeIngredientOptional = recipeIngredientRepository
                .findById(recipeIngredientId);

        if (existingRecipeIngredientOptional.isPresent()) {
            RecipeIngredient existingRecipeIngredient = existingRecipeIngredientOptional.get();

            if (updatedRecipeIngredient.getAmount() != 0.0) {
                existingRecipeIngredient.setAmount(updatedRecipeIngredient.getAmount());
            }
            if (updatedRecipeIngredient.getMeasurement() != null) {
                existingRecipeIngredient.setMeasurement(updatedRecipeIngredient.getMeasurement());
            }
            if (updatedRecipeIngredient.getIngredient() != null) {
                existingRecipeIngredient.setIngredient(updatedRecipeIngredient.getIngredient());
            }

            return recipeIngredientRepository.save(existingRecipeIngredient);

        } else {
            throw new ResourceNotFoundException("Recipe not found with id " + recipeIngredientId);
        }
    }

    /* Poistaa reseptin ainesosan id:n perusteella */
    @DeleteMapping("recipeingredients/{id}")
    public ResponseEntity<String> deleteRecipeIngredientByIdRest(@PathVariable("id") Long recipeIngredientId) {
        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findById(recipeIngredientId);

        if (recipeIngredientOptional.isPresent()) {
            recipeIngredientRepository.deleteById(recipeIngredientId);
            return ResponseEntity.ok("Recipe's ingredient deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recipe's ingredient not found with id " + recipeIngredientId);
        }

    }
}
