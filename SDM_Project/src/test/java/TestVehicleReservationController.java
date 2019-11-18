

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import org.junit.Before;

import org.junit.Test;

import org.mockito.InjectMocks;

import org.mockito.Matchers;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import com.concordia.soen.sdm.controller.VehicleReservationController;




import java.lang.reflect.Method;

import javax.servlet.ServletContext;

import org.springframework.http.HttpMethod;

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.util.ReflectionUtils;



import static org.junit.Assert.fail;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



public class TestVehicleReservationController {



	
	

	
	

    @InjectMocks

    private VehicleReservationController vehicleReservationController;

 

    private MockMvc mockMvc;



	@Test

	public void testReserve() {

		try {

		String Check="Successfully Stored";

		this.mockMvc.perform(get("/reservation/reserve").param("licensePlate", "CDE 234" )

				 .param("licenseNumber", "A-1234-123456-12")

				 .param("endDate", "2019-11-02 14:00:44")

				 .param("cost", "490")

				 .param("startDate" ,"2019-11-01 01:00:44"))

			        .andExpect(status().isOk())

			        .andExpect(view().name("vehicle_reservation"))

			        .andExpect(model().attribute("message", Check))

			        .andExpect(model().hasNoErrors());

		}

		catch(Exception e)

		{

			//e.printStackTrace();

		}

	}

	

	@Test

	public void testReserveNegative() {

		try {

		String Check= null;

		this.mockMvc.perform(get("/reservation/reserve").param("licensePlate", "CDE 234" )

				 .param("licenseNumber", null)

				 .param("endDate", "2019-11-02 14:00:44")

				 .param("cost", "490")

				 .param("startDate" ,"2019-11-01 01:00:44"))

			        .andExpect(status().isOk())

			        .andExpect(view().name("vehicle_reservation"))

			        .andExpect(model().attribute("message", Check))

			        .andExpect(model().hasErrors());

		}

		catch(Exception e)

		{

			//e.printStackTrace();

		}

	}



}



