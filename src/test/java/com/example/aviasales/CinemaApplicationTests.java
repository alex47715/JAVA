package com.example.aviasales;

import com.example.aviasales.Domain.Flight;
import com.example.aviasales.Domain.Order;
import com.example.aviasales.Domain.User;
import com.example.aviasales.repositories.FlightRepository;
import com.example.aviasales.repositories.IOrderRepository;
import com.example.aviasales.repositories.IUserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CinemaApplicationTests {
	@MockBean
	private FlightRepository flightRepository;
	@MockBean
	private IUserRepository userRepository;
	@MockBean
	private IOrderRepository orderRepository;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void	findFlightAll() throws Exception {
		setUp();

		List<Flight> expected = Arrays.asList(
				new Flight(),
				new Flight()
		);
		when(flightRepository.findAll()).thenReturn(expected);
				mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flight/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	@Test
	public void	findAllUsers() throws Exception {
		setUp();

		List<User> expected = Arrays.asList(
				new User(),
				new User()
		);
		when(userRepository.findAll()).thenReturn(expected);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	@Test
	public void	getAllOrders() throws Exception {
		setUp();

		List<Order> expected = Arrays.asList(
				new Order(),
				new Order()
		);
		when(orderRepository.findAll()).thenReturn(expected);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}

}
