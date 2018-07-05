package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import tacos.data.IngredientRepository;


/*
 * @Slf4j is a Lombok-provided annotation that, at runtime, will
automatically generate an SLF4J5 Logger in the class
 */
@Slf4j  
@Controller
@RequestMapping("/design")  // specifies the kind of requests that this controller handles
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
	this.ingredientRepo = ingredientRepo;
	}
	
	/* @GetMapping specifies that when an HTTP GET request is received for "/design",
	 *  the showDesignForm() will be called to handle the request.
	 */
	@GetMapping
	public String showDesignFrom(Model model) {
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		/*
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
		*/
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
		model.addAttribute(type.toString().toLowerCase(),
		filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		
		return "design";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
}

	
	/* (@Valid Taco design, Errors errors) -- Can't use these parameter for processDesign()
	 * coz i don't validate fields or catch error in design.html. So there will be no "error"
	 * sent from design.html. If u use these parameters, u will get error.
	 */
	@PostMapping
	public String processDesign(Taco design) {
		/*if there are any validation errors , If so, then the method
		concludes without processing the Taco and returns the "design" view
		 
		if (errors.hasErrors()) {
			return "design";
			}
			*/
		
	// Save the taco design...
	// We'll do this in chapter 3
	log.info("Processing design: " + design);
	return "redirect:/orders/current";
	}

}
