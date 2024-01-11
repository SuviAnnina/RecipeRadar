package aa.approvedappetites.Domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String password;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Recipe> recipes;

    /* Constructors */
    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /* Getters */
    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    /* Setters */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    /* toString */
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", password=" + password + "]";
    }
}