package aa.approvedappetites.Domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientId;

    private String name;

    // private String category; /* oma luokka tälle - luo yhteys myös */

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "ingredient")
    @JsonIgnoreProperties("ingredient")
    private List<RecipeIngredient> recipeIngredients;

    /* Constructors */
    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    /* Getters */
    public Long getIngredientId() {
        return ingredientId;
    }

    public String getName() {
        return name;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    /* Setters */
    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    /* toString */
    @Override
    public String toString() {
        return "Ingredient [ingredientId=" + ingredientId + ", name=" + name + "]";
    }
}