package pantrypal.ingredients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;

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
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("kg"));

        // Get inventory
        Map<String, Ingredient> stock = inventory.getInventory();

        // Check if sugar was added
        assertTrue(stock.containsKey("Sugar")); // Contains sugar?
        assertEquals(2.5, stock.get("Sugar").getQuantity()); // Quantity correct?
        assertEquals("kg", stock.get("Sugar").getUnit().toString()); // Unit correct?
    }

    @Test
    void testSetAlert() {
        // Add sugar
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("kg"));
        inventory.setAlert("Sugar", 2.0);
        inventory.decreaseQuantity("Sugar", 1);

        // Get inventory
        Map<String, Ingredient> stock = inventory.getInventory();
        Map<String, Double> lowStockAlerts = inventory.getLowStockAlerts();


        // Check for alert and stock
        assertTrue(stock.containsKey("Sugar"), "Sugar should exist in inventory");
        assertEquals(1.5, stock.get("Sugar").getQuantity(), 0.01, "Sugar quantity " +
                "should be 1.5 kg");
        assertTrue(lowStockAlerts.containsKey("Sugar"), "Low stock alert should exist for Sugar");
        assertEquals(2.0, lowStockAlerts.get("Sugar"), 0.01, "Low stock threshold " +
                "should be 2.0" +
                " kg");

    }

    @Test
    void testIncreaseQuantity() {
        inventory.addNewIngredient("Flour", 1.0, Unit.KILOGRAM);
        inventory.increaseQuantity("Flour", 0.5);
        assertEquals(1.5, inventory.getInventory().get("Flour").getQuantity(), "Flour quantity should"
                + " be 1.5 kg");
    }

    @Test
    void testDecreaseQuantity() {
        inventory.addNewIngredient("Milk", 2.0, Unit.LITER);
        inventory.decreaseQuantity("Milk", 1.5);
        assertEquals(0.5, inventory.getInventory().get("Milk").getQuantity(), "Milk quantity " +
                "should be" +
                " 0.5 L");
    }

    @Test
    void testDecreaseQuantityInsufficientStock() {
        inventory.addNewIngredient("Butter", 0.2, Unit.KILOGRAM);
        inventory.decreaseQuantity("Butter", 0.5);
        assertEquals(0.2, inventory.getInventory().get("Butter").getQuantity(), "Butter " +
                "quantity should remain 0.2 kg");
    }

    @Test
    void testDeleteIngredient() {
        inventory.addNewIngredient("Eggs", 12, Unit.POUND);
        inventory.deleteIngredient("Eggs");
        assertFalse(inventory.getInventory().containsKey("Eggs"), "Eggs should be removed from inventory");
    }

    @Test
    void testFindInventory() {
        inventory.addNewIngredient("Cheese", 0.5, Unit.KILOGRAM);
        assertTrue(inventory.findInventory("Cheese", 0.3, Unit.KILOGRAM), "Cheese should " +
                "be in inventory with enough quantity");
        assertFalse(inventory.findInventory("Cheese", 0.6, Unit.KILOGRAM), "Cheese should " +
                "not be sufficient in inventory");
    }

    @Test
    void testCheckStockEmpty() {
        assertTrue(inventory.getInventory().isEmpty(), "Inventory should be empty initially");
    }

    @Test
    void testValidateIngredientThrowsException() {
        assertThrows(AssertionError.class, () -> inventory.addNewIngredient("", 1.0, Unit.GRAM));
        assertThrows(AssertionError.class, () -> inventory.addNewIngredient("Salt", -1.0, Unit.GRAM));
        assertThrows(AssertionError.class, () -> inventory.addNewIngredient("Pepper", 1.0, null));
    }

}
