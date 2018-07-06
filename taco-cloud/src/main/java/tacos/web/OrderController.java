package tacos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
	this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		
	model.addAttribute("order", new Order());
	return "orderForm";
	}
	
	
/* we use @Valid (from Validation API) 
 * The @Valid annotation tells Spring MVC to perform validation on the
submitted Order object after it is bound to the submitted form data and before
the processOrder() method is called.
* If there are any validation errors, the details of those errors will be 
* captured in an Errors object
 */
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors,SessionStatus sessionStatus) {
		
		if (errors.hasErrors()) {
			return "orderForm";
			}
		
		orderRepo.save(order);
		sessionStatus.setComplete(); // SessionStatus parameter calls its setComplete() to reset the session.
		
		log.info("Order submitted: " + order);
		return "redirect:/";
		}
	}


