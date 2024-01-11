package aa.approvedappetites;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import aa.approvedappetites.Domain.Ingredient;
import aa.approvedappetites.Domain.IngredientRepository;
import aa.approvedappetites.Domain.Recipe;
import aa.approvedappetites.Domain.RecipeIngredient;
import aa.approvedappetites.Domain.RecipeIngredientRepository;
import aa.approvedappetites.Domain.RecipeRepository;
import aa.approvedappetites.Domain.Type;
import aa.approvedappetites.Domain.TypeRepository;
import aa.approvedappetites.Domain.User;
import aa.approvedappetites.Domain.UserRepository;

@SpringBootApplication
public class ApprovedappetitesApplication {

	private static final Logger log = LoggerFactory.getLogger(ApprovedappetitesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApprovedappetitesApplication.class, args);
	}

	@Bean
	public CommandLineRunner approvedAppetitesDemo(IngredientRepository ingredientRepository,
			RecipeIngredientRepository recipeIngredientRepository, RecipeRepository recipeRepository,
			TypeRepository typeRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("Save some types");
			Type type1 = new Type("Dessert");
			Type type2 = new Type("Main course");
			Type type3 = new Type("Baked goods");

			typeRepository.save(type1);
			typeRepository.save(type2);
			typeRepository.save(type3);

			log.info("Save some ingredients");
			Ingredient ingredient1 = new Ingredient("Chicken");
			Ingredient ingredient2 = new Ingredient("Salt");
			Ingredient ingredient3 = new Ingredient("Vanilla essence");
			Ingredient ingredient4 = new Ingredient("Egg");
			Ingredient ingredient5 = new Ingredient("Sugar");

			ingredientRepository.save(ingredient1);
			ingredientRepository.save(ingredient2);
			ingredientRepository.save(ingredient3);
			ingredientRepository.save(ingredient4);
			ingredientRepository.save(ingredient5);

			log.info("Create some preptimes");
			Duration prepTime1 = Duration.ofMinutes(15);
			Duration prepTime2 = Duration.ofMinutes(30);
			// Duration prepTime3 = Duration.ofMinutes(45);
			Duration prepTime4 = Duration.ofMinutes(60);

			log.info("Create some cooktimes");
			// Duration cooktime1 = Duration.ofMinutes(20);
			// Duration cooktime2 = Duration.ofMinutes(30);
			Duration cooktime3 = Duration.ofMinutes(40);
			Duration cooktime4 = Duration.ofMinutes(50);

			log.info("Save some recipes");
			Recipe recipe1 = new Recipe("Chicken wowzers", cooktime3, prepTime2,
					"chop the chicky and fry the chicky. Wow!", type2);
			Recipe recipe2 = new Recipe("Vanilla cups", cooktime4, prepTime1, "heat the milk and add vanilla. Enjoy!",
					type1);
			Recipe recipe3 = new Recipe("Cinnamon chomps", cooktime3, prepTime4, "beat the eggs and sugar, yummy!",
					type3);

			recipeRepository.save(recipe1);
			recipeRepository.save(recipe2);
			recipeRepository.save(recipe3);

			log.info("Save some recipe ingredients");
			RecipeIngredient recipeIngredient1 = new RecipeIngredient(2.5, "dl", recipe3, ingredient5);
			RecipeIngredient recipeIngredient2 = new RecipeIngredient(1, "item", recipe1, ingredient1);
			RecipeIngredient recipeIngredient3 = new RecipeIngredient(2, "tsp", recipe2, ingredient3);
			RecipeIngredient recipeIngredient4 = new RecipeIngredient(1, "tbls", recipe1, ingredient2);
			RecipeIngredient recipeIngredient5 = new RecipeIngredient(4, "items", recipe3, ingredient4);
			RecipeIngredient recipeIngredient6 = new RecipeIngredient(3, "dl", recipe2, ingredient5);

			recipeIngredientRepository.save(recipeIngredient1);
			recipeIngredientRepository.save(recipeIngredient2);
			recipeIngredientRepository.save(recipeIngredient3);
			recipeIngredientRepository.save(recipeIngredient4);
			recipeIngredientRepository.save(recipeIngredient5);
			recipeIngredientRepository.save(recipeIngredient6);

			log.info("Save some users");
			User user1 = new User("testy", "testy123");
			User user2 = new User("tasty", "tasty123");

			userRepository.save(user1);
			userRepository.save(user2);

		};
	}

}
