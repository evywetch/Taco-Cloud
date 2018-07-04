package tacos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	
	/* @NotBlank is from hibernate validator
	means this field can't be blank.
	 */
	@NotBlank(message="Name is required")
	private String name;
	
	@NotBlank(message="Street is required")
	private String street;
	
	@NotBlank(message="City is required")
	private String city;
	
	@NotBlank(message="State is required")
	private String state;
	
	@NotBlank(message="Zip code is required")
	private String zip;
	
	
	// from Hibernate Validator's collection of annotations
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	// from Hibernate Validator's collection of annotations
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message="Must be formatted MM/YY")
	private String ccExpiration;
	
	// from Hibernate Validator's collection of annotations
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
}
