package testDAO;

import com.mycompany.btl_cnpm.dao.SupplierDAO;
import com.mycompany.btl_cnpm.model.Supplier;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class SupplierDAOTest {
    SupplierDAO supplierDAO = new SupplierDAO();

    @Test
    public void testSearchSupplierByNameException1() {
        String key = "xxxxxxxxxxxx";
        ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName(key);
        assertNotNull(suppliers);
        assertEquals(0, suppliers.size());
    }
    
    @Test
    public void testSearchSupplierByNameException2() {
        String key = "sd";
        ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName(key);
        assertNotNull(suppliers);
        assertEquals(0, suppliers.size());
    }

    @Test
    public void testSearchSupplierByNameStandard1() {
        String key = "An";
        ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName(key);
        assertNotNull(suppliers);
        assertEquals(2, suppliers.size());
        for(int i = 0; i < suppliers.size(); i++){
			assertTrue(suppliers.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
    }

    @Test
    public void testSearchSupplierByNameStandard2() {
        String key = "B";
        ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName(key);
        assertNotNull(suppliers);
        assertEquals(4, suppliers.size());
        for(int i = 0; i < suppliers.size(); i++){
			assertTrue(suppliers.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
    }
} 