
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.concordia.soen.sdm.controller.CancelReturnController;
import com.concordia.soen.sdm.controller.ClientManagementController;
import com.concordia.soen.sdm.controller.TransactionController;
import com.concordia.soen.sdm.dao.CatalogDAO;
import com.concordia.soen.sdm.dao.ClientDAO;
import com.concordia.soen.sdm.dao.TransactionDAO;
import com.concordia.soen.sdm.service.TransactionAvailabilityCheckService;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.ReflectionUtils;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.concordia.soen.sdm.pojo.Transaction;
public class TestCancelReturnController {
 
	@Mock
	
	private CatalogDAO catalogDao;
	
       @Mock
	
	private TransactionDAO transactionDAO;;

    @InjectMocks
    private CancelReturnController cancelReturnController;
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cancelReturnController).build();
    }
 
    @Test
    public void testViewTransactionHistory() throws Exception {
      
      
    	 this.mockMvc.perform(get("/cancelReturn/transactionSearch"))
    	            .andExpect(status().isOk())
    	            .andExpect(view().name("CancelDetail"))
    	            ;
    	            
    }
    
    
    
    

}
