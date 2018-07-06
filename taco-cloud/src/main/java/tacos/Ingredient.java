package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
/*
 @Data annotation at the class level is provided
by Lombok and tells Lombok to generate all of those missing methods as well as a
constructor that accepts all final properties as arguments.
 */
@Data
//@Getter  // Lombok annotation-- for creating get()
//@Setter  // Lombok annotation-- for creating set()
//@ToString  // Lombok annotation-- for creating toString()
//@EqualsAndHashCode  // Lombok annotation-- for creating equals() and hashCode()

@RequiredArgsConstructor  // it will generate constructor with fields as parameters
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient {
	
	@Id
	private final  String id;  //@NonNull = to make a field required for constructor
	
	private final  String name;
	
	private final  Type type;
	
	public static enum Type{
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
