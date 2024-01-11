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
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long typeId;

    private String name;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "type")
    @JsonIgnoreProperties("type")
    private List<Recipe> recipes;

    /* Constructors */
    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    /* Getters */
    public Long getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    /* Setters */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    /* toString */
    @Override
    public String toString() {
        return "Type [typeId=" + typeId + ", name=" + name + "]";
    }
}