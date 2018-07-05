package tacos;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


/*
 * the Taco class is annotated with @Data to automatically generate essential 
 * JavaBean methods for us at runtime.
 */
@Data
public class Taco {
	
	private Long id;
	private Date createdAt;
	
	/* @NotNull = from validation API-- mean this field can't be null
	 * min=5 means should have a value that is at least 5 characters in length
	 */
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<String> ingredients;
}
