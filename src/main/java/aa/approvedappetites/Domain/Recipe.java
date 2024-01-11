package aa.approvedappetites.Domain;

import java.time.Duration;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeId;

    private String name;
    private Duration cookTime;
    private Duration prepTime;
    private String instructions;
    private String note;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties("recipes")
    private User user;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "recipe")
    @JsonIgnoreProperties("recipe")
    private List<RecipeIngredient> recipeIngredients;

    @ManyToOne
    @JoinColumn(name = "typeId")
    @JsonIgnoreProperties("recipes")
    private Type type;

    /* Constructors */
    public Recipe() {
    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, Duration cookTime, Duration prepTime, String instructions, Type type) {
        this.name = name;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.instructions = instructions;
        this.type = type;
    }

    public Recipe(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public Recipe(String name, String instructions, String note) {
        this.name = name;
        this.instructions = instructions;
        this.note = note;
    }

    public Recipe(String name, Duration cookTime, Duration prepTime, String instructions) {
        this.name = name;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.instructions = instructions;
    }

    public Recipe(String name, String instructions, Type type) {
        this.name = name;
        this.instructions = instructions;
        this.type = type;
    }

    public Recipe(String name, Duration cookTime, Duration prepTime, String instructions, String note, User user,
            Type type) {
        this.name = name;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.instructions = instructions;
        this.note = note;
        this.user = user;
        this.type = type;
    }

    /* Getters */
    public Long getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public Duration getCookTime() {
        return cookTime;
    }

    public Duration getPrepTime() {
        return prepTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getNote() {
        return note;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public User getUser() {
        return user;
    }

    public Type getType() {
        return type;
    }

    /* Setters */
    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCookTime(Duration cookTime) {
        this.cookTime = cookTime;
    }

    public void setPrepTime(Duration prepTime) {
        this.prepTime = prepTime;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /* toString */
    @Override
    public String toString() {
        return "Recipe [recipeId=" + recipeId + ", name=" + name + ", cookTime=" + cookTime + ", prepTime=" + prepTime
                + ", instructions=" + instructions + ", note=" + note + "]";
    }
}