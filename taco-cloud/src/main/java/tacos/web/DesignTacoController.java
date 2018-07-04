package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;


/*
 * @Slf4j is a Lombok-provided annotation that, at runtime, will
automatically generate an SLF4J5 Logger in the class
 */
@Slf4j  
@Controller
@RequestMapping("/design")  // specifies the kind of requests that this controller handles
public class DesignTacoController {
	
	/* @GetMapping specifies that when an HTTP GET request is received for "/design",
	 *  the showDesignForm() will be called to handle the request.
	 */
	@GetMapping
	public String showDesignFrom(Model model) {
		
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
				);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
		model.addAttribute(type.toString().toLowerCase(),
		filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		
		return "design-a-taco";
	}
	
	public List<Ingredient>  filterByType(List<Ingredient> ingredients, Type type) {
		
		List<Ingredient> ingredientByType = new ArrayList<>();
		
		for(Ingredient ingredient: ingredients) {
			
			if(ingredient.getType().equals(type.toString())) {
				ingredientByType.add(ingredient);
			}
			
		}
			
		return ingredientByType;
	}
	
/* we use @Valid (from Validation API) 
 * The @Valid annotation tells Spring MVC to perform validation on the
submitted Taco object after it is bound to the submitted form data and before
the processDesign() method is called.
* If there are any validation errors, the details of those errors will be 
* captured in an Errors object
 */
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors) {
		/*if there are any validation errors , If so, then the method
		concludes without processing the Taco and returns the "design" view
		 */
		if (errors.hasErrors()) {
			return "design-a-taco";
			}
		
	// Save the taco design...
	// We'll do this in chapter 3
	log.info("Processing design: " + design);
	return "redirect:/orders/current";
	}

}
