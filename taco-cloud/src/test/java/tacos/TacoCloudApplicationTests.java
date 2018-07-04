package tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
// @RunWith is a JUnit annotation which provides a test runner that guides JUnit in running a test
@RunWith(SpringRunner.class)
@WebMvcTest  //(HomeController.class)  Web test for HomeController

public class TacoCloudApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;   // Inject MockMvc

	@Test
	public void testHomePage() throws Exception {
		
		mockMvc.perform(get("/"))   // Perform GET /
		.andExpect(status().isOk())  // Expect HTTP 200
		.andExpect(view().name("home"))  // Expect "home" view
		.andExpect(content().string(containsString("Welcome to Taco Cloud")));  // Expect "Welcome to…"
		
		
	}

}
