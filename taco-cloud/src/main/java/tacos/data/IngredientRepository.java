package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

/* CrudRepository<Ingredient, String> - Ingredient = the entity type the repository going to persisit
 * String = type of the entity's id.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	
		Iterable<Ingredient> findAll();
		Ingredient findOne(String id);
		Ingredient save(Ingredient ingredient);
}
