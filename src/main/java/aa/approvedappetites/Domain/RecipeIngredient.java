package aa.approvedappetites.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeIngredientId;

    private double amount;
    private String measurement;

    @ManyToOne
    @JsonIgnoreProperties("recipeIngredients")
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredientId")
    @JsonIgnoreProperties("recipeIngredients")
    private Ingredient ingredient;

    /* Constructors */
    public RecipeIngredient() {
    }

    public RecipeIngredient(double amount, String measurement) {
        this.amount = amount;
        this.measurement = measurement;
    }

    public RecipeIngredient(double amount, String measurement, Recipe recipe, Ingredient ingredient) {
        this.amount = amount;
        this.measurement = measurement;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public RecipeIngredient(double amount, String measurement, Recipe recipe) {
        this.amount = amount;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    public RecipeIngredient(double amount, String measurement, Ingredient ingredient) {
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
    }

    /* Getters */
    public Long getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMeasurement() {
        return measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    /* Setters */
    public void setRecipeIngredientId(Long recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    /* toString */
    @Override
    public String toString() {
        return "RecipeIngredient [recipeIngredientId=" + recipeIngredientId + ", amount=" + amount
                + ", measurement=" + measurement + "]";
    }
}