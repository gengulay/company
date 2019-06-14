package test.java;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnit44Runner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gengulay.demo.controller.EmployeeController;
import com.gengulay.demo.dao.EmployeeDAO;
import com.gengulay.demo.entity.DepartmentEntity;
import com.gengulay.demo.entity.EmployeeEntity;

@RunWith(MockitoJUnit44Runner.class)
public class EmployeeControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private EmployeeController controller;

	@Mock
	private EmployeeDAO employeeDaoMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		controller.setEmployeeDAO(employeeDaoMock);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void findAllEmployees_shouldReturnAllEmployees() throws Exception {
		EmployeeEntity kaido = new EmployeeEntity(1, "kaido", new DepartmentEntity());
		EmployeeEntity shanks = new EmployeeEntity(2, "shanks", new DepartmentEntity());

		when(employeeDaoMock.getAllEmployees()).thenReturn(Arrays.asList(kaido, shanks));

		mockMvc.perform(get("/employees/")).andExpect(status().isOk()).andExpect(view().name("employees"))
				.andExpect(model().attribute("employees", hasSize(2)));

	}

}
