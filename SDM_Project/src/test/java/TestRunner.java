import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({TestCatalogController.class,
TestClient.class,TestTransactionController.class,TestAdminCatalogController.class
})
 
public class TestRunner {

}