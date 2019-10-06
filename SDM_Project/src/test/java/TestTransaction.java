import static org.junit.Assert.*;

import org.junit.Test;

import com.concordia.soen.sdm.dao.ClientDAO;
import com.concordia.soen.sdm.dao.TransactionDAO;
import com.concordia.soen.sdm.pojo.Client;
import com.concordia.soen.sdm.pojo.Transaction;

public class TestTransaction {

	@Test
	public void testgetClientDetails() {
	TransactionDAO tli =new TransactionDAO();
	try {
	Transaction trans= tli.getTransactionID(14);
	int cost = trans.getCost();
	assertEquals(280,cost);

	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
}
