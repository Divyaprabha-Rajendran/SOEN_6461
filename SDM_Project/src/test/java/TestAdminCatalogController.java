import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.concordia.soen.sdm.tableDataGateway.AdminCatalogTableDataGateway;


public class TestAdminCatalogController {
ResultSet rs;
AdminCatalogTableDataGateway cl= new AdminCatalogTableDataGateway();
@Test public void test() {
try {
rs = cl.selectMultipleRows();
} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
assertNotNull(rs);}
}



