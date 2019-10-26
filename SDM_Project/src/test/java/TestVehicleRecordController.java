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

import com.concordia.soen.sdm.controller.VehicleRecordController;
import com.concordia.soen.sdm.dao.CatalogDAO;
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

public class TestVehicleRecordController {

	@Mock
	CatalogDAO catalogDao;
	
    @InjectMocks
    private VehicleRecordController vehicleRecordController;
 
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(vehicleRecordController).build();
    }
	
	@Test
	public void testCreateNewVehicle() {
		try {
			String Check="vehicle created successful";
			this.mockMvc.perform(get("/vehicle/createNewVehicle").param("type", "sedan" )
				 .param("make", "2016")
				 .param("model", "2019")
				 .param("year", "2019")
				 .param("color" ,"black")
				 .param("licenseNumber" , "ADS 123")
				 .param("cost","30"))
			        .andExpect(status().isOk())
			        .andExpect(view().name("CreateNewVehicle"))
			        .andExpect(model().attribute("message", Check))
			        .andExpect(model().hasNoErrors());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateNewVehicleNegativeCost() {
		try {
			String Check="Invalid cost. Please try again.";
			this.mockMvc.perform(get("/vehicle/createNewVehicle").param("type", "sedan" )
				 .param("make", "2016")
				 .param("model", "2019")
				 .param("year", "2019")
				 .param("color" ,"black")
				 .param("licenseNumber" , "ADS 123")
				 .param("cost","0"))
			        .andExpect(status().isOk())
			        .andExpect(view().name("CreateNewVehicle"))
			        .andExpect(model().attribute("message", Check))
			        .andExpect(model().hasNoErrors());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
