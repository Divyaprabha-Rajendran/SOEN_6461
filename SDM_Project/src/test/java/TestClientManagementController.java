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



import com.concordia.soen.sdm.controller.ClientManagementController;


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





import org.junit.Test;
public class TestClientManagementController {
	

	

	@InjectMocks

    private ClientManagementController clientManagement;

 

    private MockMvc mockMvc;



	@Test

	public void testCreate() {

		try {

			String Check="Successfully Stored data";

			this.mockMvc.perform(get("/client/create").param("firstName", "Swetha" )

					 .param("lastName", "Chenna")

					 .param("licenseNumber", "A-1234-123456-12")

					 .param("expDate", "2019-10-10")

					 .param("phone" ,"1234567890"))

				        .andExpect(status().isOk())

				        .andExpect(view().name("add_client"))

				        .andExpect(model().attribute("message", Check))

				        .andExpect(model().hasNoErrors());

			}

			catch(Exception e)

			{

				//e.printStackTrace();

			}

	}

	

	@Test

	public void testCreateNegative() {

		try {

			String Check=null;

			this.mockMvc.perform(get("/client/create").param("firstName", null )

					 .param("lastName", "Chenna")

					 .param("licenseNumber", "A-1234-123456-12")

					 .param("expDate", "2019-10-10")

					 .param("phone" ,"1234567890"))

				        .andExpect(status().isOk())

				        .andExpect(view().name("add_client"))

				        .andExpect(model().attribute("message", Check))

				        .andExpect(model().hasErrors());

			}

			catch(Exception e)

			{

				//e.printStackTrace();

			}

	}
}
