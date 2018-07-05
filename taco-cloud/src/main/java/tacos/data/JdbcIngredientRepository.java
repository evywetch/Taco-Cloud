package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
/*
 * @Repository, we’re declaring that it should be automatically discovered by Spring 
 * component-scanning and instantiated as a bean in the Spring application context.
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
	this.jdbc = jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient",
				this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject(
				"select id, name, type from Ingredient where id=?",
				this::mapRowToIngredient, id);
	}
/*
 * the save() method. Use JdbcTemplate 's update() method to insert data into the database.
 */
	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update(
				"insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString());
				return ingredient;
	}
	/*
	 Spring’s RowMapper  for the purpose of mapping each row in the result set to an object
	 */
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
			throws SQLException {
			return new Ingredient(
			rs.getString("id"),
			rs.getString("name"),
			Ingredient.Type.valueOf(rs.getString("type")));
			}

}
