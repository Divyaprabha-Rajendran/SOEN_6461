import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.concordia.soen.sdm.dao.CatalogDAO;

public class TestAdminCatalogController {
	CatalogDAO cl= new CatalogDAO();
	@Test public void test() {
	assertNotNull(cl);}
	}

