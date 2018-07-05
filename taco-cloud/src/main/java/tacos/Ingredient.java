package tacos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 @Data annotation at the class level is provided
by Lombok and tells Lombok to generate all of those missing methods as well as a
constructor that accepts all final properties as arguments.
 */
@Data
@Getter  // Lombok annotation-- for creating get()
@Setter  // Lombok annotation-- for creating set()
@ToString  // Lombok annotation-- for creating toString()
@EqualsAndHashCode  // Lombok annotation-- for creating equals() and hashCode()
@RequiredArgsConstructor  // it will generate constructor with fields as parameters
public class Ingredient {
	
	private final @NonNull String id;  // @NonNull = to make a field required for constructor
	
	private final @NonNull String name;
	
	private final @NonNull Type type;
	
	public static enum Type{
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
