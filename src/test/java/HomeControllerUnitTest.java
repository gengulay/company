package test.java;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gengulay.demo.controller.HomeController;


public class HomeControllerUnitTest {

	private MockMvc mockMvc;

	private HomeController controller;

	@Before
	public void setUp() {
		controller = new HomeController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void home_shouldRenderTheHomeView() throws Exception {
		mockMvc.perform(get("/")).andExpect(MockMvcResultMatchers.view().name("home"));
	}

}
