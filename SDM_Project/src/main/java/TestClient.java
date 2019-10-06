import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.concordia.soen.sdm.dao.ClientDAO;
import com.concordia.soen.sdm.pojo.Client;
public class TestClient {
@Test public void testgetClientDetails() {
ClientDAO cli =new ClientDAO();
try {
Client client= cli.getClientDetails("A-1234-123456-12");
String name=(String)client.getFirstName();
assertEquals("Swetha",name);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

}
