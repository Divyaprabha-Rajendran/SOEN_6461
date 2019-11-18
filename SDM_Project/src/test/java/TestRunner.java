import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({TestCatalogController.class,
TestClient.class,TestTransactionController.class,TestAdminCatalogController.class,TestCancelReturnController.class,TestVehicleRecordController.class,TestClientManagementController.class,
TestVehicleReservationController.class
})
 
public class TestRunner {

}