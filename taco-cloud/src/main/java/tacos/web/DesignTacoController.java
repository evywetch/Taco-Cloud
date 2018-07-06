package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;


/*
 * @Slf4j is a Lombok-provided annotation that, at runtime, will
automatically generate an SLF4J5 Logger in the class
 */
@Slf4j  
@Controller
@RequestMapping("/design")  // specifies the kind of requests that this controller handles
/* we need the order the be present across multiple requests so that we can create multiple
tacos and add them to the order. The class-level @SessionAttributes annotation
specifies any model objects that should be kept in session—such as the "order"
attribute—and thus available across multiple requests.
 */
@SessionAttributes("order")  // Dont know for what?
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	private final TacoRepository tacoRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
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

	
	
 // @ModelAttribute ensures that the Order object will be created in the model	
	@ModelAttribute(name = "order")
	public Order order() {
	return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
	return new Taco();
	}
	
	/* -(@Valid Taco design, Errors errors) -- Can't use these parameter for processDesign()
	 * coz i don't validate fields or catch error in design.html. So there will be no "error"
	 * sent from design.html. If u use these parameters, u will get error.
	 * 
	 * - The Order parameter is annotated with @ModelAttribute here to indicate that its value
should come from the model and that Spring MVC should not attempt to bind request parameters to it.

	 */
	@PostMapping
	public String processDesign( Taco design, @ModelAttribute Order order) {
		/*if there are any validation errors , If so, then the method
		concludes without processing the Taco and returns the "design" view
		
		if (errors.hasErrors()) {
			return "design";
			}
		 */	
		
	// Save the taco design...
	// We'll do this in chapter 3
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
	log.info("Processing design: " + design);
	return "redirect:/orders/current";
	}

}
