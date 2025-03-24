package pantrypal.ingredients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;

public class IngredientInventoryTest {
    private IngredientInventory inventory;

    @BeforeEach
    // Runs before every test case
    void setUp() {
        inventory = new IngredientInventory();
    }

    @Test
    void testAddNewIngredient() {
        // Add sugar
        inventory.addNewIngredient("Sugar", 2.5, "kg");

        // Get inventory
        Map<String, Ingredient> stock = inventory.getInventory();

        // Check if sugar was added
        assertTrue(stock.containsKey("Sugar")); // Contains sugar?
        assertEquals(2.5, stock.get("Sugar").getQuantity()); // Quantity correct?
        assertEquals("kg", stock.get("Sugar").getUnit()); // Unit correct?
    }

    @Test
    void testSetAlert() {
        // Add sugar
        inventory.addNewIngredient("Sugar", 2.5, "kg");
        inventory.setAlert("Sugar", 2.0, "kg");
        inventory.decreaseQuantity("Sugar", 1, "kg");

        // Get inventory
        Map<String, Ingredient> stock = inventory.getInventory();
        Map<String, Double> lowStockAlerts = inventory.getLowStockAlerts();


        // Check for alert and stock
        assertTrue(stock.containsKey("Sugar"), "Sugar should exist in inventory");
        assertEquals(1.5, stock.get("Sugar").getQuantity(), 0.01, "Sugar quantity should be 1.5 kg");
        assertTrue(lowStockAlerts.containsKey("Sugar"), "Low stock alert should exist for Sugar");
        assertEquals(2.0, lowStockAlerts.get("Sugar"), 0.01, "Low stock threshold should be 2.0 kg");


    }


}
