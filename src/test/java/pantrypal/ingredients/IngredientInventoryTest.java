package pantrypal.ingredients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Category;
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
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("kg"),
                Category.parseCategory("CONDIMENT"));

        // Get inventory
        Map<String, Ingredient> stock = inventory.getInventory();

        // Check if sugar was added
        assertTrue(stock.containsKey("Sugar")); // Contains sugar?
        assertEquals(2.5, stock.get("Sugar").getQuantity()); // Quantity correct?
        assertEquals("kg", stock.get("Sugar").getUnit().toString()); // Unit correct?
        assertEquals("Condiment", stock.get("Sugar").getCategory().toString());
    }

    @Test
    void testSetAlert() {
        // Add sugar
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("kg"),
                Category.parseCategory("CONDIMENT"));
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
        inventory.addNewIngredient("Flour", 1.0, Unit.KILOGRAM, Category.parseCategory("GRAIN"));
        inventory.increaseQuantity("Flour", 0.5);
        assertEquals(1.5, inventory.getInventory().get("Flour").getQuantity(), "Flour quantity should"
                + " be 1.5 kg");
    }

    @Test
    void testDecreaseQuantity() {
        inventory.addNewIngredient("Milk", 2.0, Unit.LITER, Category.parseCategory("DAIRY"));
        inventory.decreaseQuantity("Milk", 1.5);
        assertEquals(0.5, inventory.getInventory().get("Milk").getQuantity(), "Milk quantity " +
                "should be" +
                " 0.5 L");
    }

    @Test
    void testDecreaseQuantityInsufficientStock() {
        inventory.addNewIngredient("Butter", 0.2, Unit.KILOGRAM, Category.parseCategory("DAIRY"));
        inventory.decreaseQuantity("Butter", 0.5);
        assertEquals(0.2, inventory.getInventory().get("Butter").getQuantity(), "Butter " +
                "quantity should remain 0.2 kg");
    }

    @Test
    void testDeleteIngredient() {
        inventory.addNewIngredient("Eggs", 12, Unit.POUND, Category.parseCategory("DAIRY"));
        inventory.deleteIngredient("Eggs");
        assertFalse(inventory.getInventory().containsKey("Eggs"), "Eggs should be removed from inventory");
    }

    @Test
    void testFindInventory() {
        inventory.addNewIngredient("Cheese", 0.5, Unit.KILOGRAM, Category.parseCategory("DAIRY"));
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
        // Test for invalid name
        assertThrows(AssertionError.class, () -> inventory.addNewIngredient("", 1.0, Unit.GRAM,
                Category.parseCategory("GRAIN")));

        // Test for invalid quantity
        assertThrows(AssertionError.class, () -> inventory.addNewIngredient("Salt", -1.0, Unit.GRAM,
                Category.parseCategory("GRAIN")));

        // Test for invalid unit
        assertThrows(AssertionError.class, () -> inventory.addNewIngredient("Pepper", 1.0, null,
                Category.parseCategory("SPICE")));
    }

    @Test
    void testNullCategoryThrowsException() {
        // Arrange: Set up the necessary objects
        IngredientInventory inventory = new IngredientInventory();
        String nullCategory = null;  // Null category input

        // Act and Assert: Use assertThrows to expect the IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            // Call the method that should throw the exception
            inventory.validateIngredientCategory(nullCategory);
        });

        // Assert the exception message is what we expect
        assertEquals("Category cannot be null.\nType categoryList for a list of valid categories.",
                exception.getMessage());
    }


    @Test
    void testInvalidCategoryThrowsException() {
        // Arrange: Set up the necessary objects
        IngredientInventory inventory = new IngredientInventory();
        String invalidCategory = "INVALID_CATEGORY";  // Example invalid category

        // Act and Assert: Use assertThrows to expect the IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            // Call the method that should throw the exception
            inventory.validateIngredientCategory(invalidCategory);
        });

        // Assert the exception message is what we expect
        assertEquals("Invalid category: " + invalidCategory + "\nType categoryList for a " +
                "list of valid categories.", exception.getMessage());
    }

    @Test
    public void testConvertIngredient() {
        // Add "Sugar" with 2.5 grams to the inventory
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("g"),
                Category.parseCategory("CONDIMENT"));

        // Convert "Sugar" from GRAM to KILOGRAM
        inventory.convertIngredient("Sugar", Unit.KILOGRAM);

        // Get ingredient after conversion
        Map<String, Ingredient> stock = inventory.getInventory();
        Ingredient sugar = stock.get("Sugar");

        // Check if sugar was converted
        assertNotNull(sugar); // Sugar exists
        assertEquals(0.0025, sugar.getQuantity(), 0.0001); //  2.5g -> 0.0025kg
        assertEquals("kg", sugar.getUnit().toString()); // Unit should be kg
    }

    @Test
    public void testViewIngredientsByCategory() {
        // Add ingredients to inventory
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("g"),
                Category.parseCategory("CONDIMENT"));

        String result = inventory.viewIngredientsByCategory(Category.parseCategory("CONDIMENT"));

        // Check if the output contains "Sugar"
        assertTrue(result.contains("Sugar"));

        String expectedOutput = "Sugar 2.5 g Condiment\n";
        assertEquals(expectedOutput, result);
    }
}
