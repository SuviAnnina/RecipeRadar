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

import aa.approvedappetites.Domain.Ingredient;
import aa.approvedappetites.Domain.IngredientRepository;

@CrossOrigin
@Controller
public class IngredientRestController {

    @Autowired
    private IngredientRepository ingredientRepository;

    /* Lists all Ingredient java objects as JSON list */
    @GetMapping("/ingredients")
    public @ResponseBody List<Ingredient> getAllIngredientsRest() {
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    /* Searches an ingredient by id */
    @GetMapping("/ingredient/{id}")
    public @ResponseBody Optional<Ingredient> getIngredientByIdRest(@PathVariable("id") Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    /* Save an ingredient to database */
    @PostMapping("/ingredients")
    public @ResponseBody Ingredient saveIngredientRest(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    /* Patches an ingredient by id */
    @PatchMapping("/ingredients/{id}")
    public @ResponseBody Ingredient patchIngredientRest(@PathVariable Long ingredientId,
            @RequestBody Ingredient updatedIngredient) {
        Optional<Ingredient> existingIngredientOptional = ingredientRepository.findById(ingredientId);

        if (existingIngredientOptional.isPresent()) {
            Ingredient existinIngredient = existingIngredientOptional.get();

            if (updatedIngredient.getName() != null) {
                existinIngredient.setName(updatedIngredient.getName());
            }
            return ingredientRepository.save(existinIngredient);
        } else {
            throw new ResourceNotFoundException("Ingredient not found with id " + ingredientId);
        }

    }

    /* Deletes an ingredient by id */
    @DeleteMapping("/ingredient/{id}")
    public ResponseEntity<String> deleteIngredientByIdRest(@PathVariable("id") Long ingredientId) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);

        if (ingredientOptional.isPresent()) {
            ingredientRepository.deleteById(ingredientId);
            return ResponseEntity.ok("Ingredient deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingredient not found with id " + ingredientId);
        }
    }

}
