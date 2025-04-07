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

    void setUp() {
        inventory = new IngredientInventory();
    }

    /**
     * Tests adding a new ingredient to the inventory and checks if it is stored correctly.
     */
    @Test
    void testAddNewIngredient() {
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("kg"),
                Category.parseCategory("CONDIMENT"));

        Map<String, Ingredient> stock = inventory.getInventory();

        assertTrue(stock.containsKey("Sugar"));
        assertEquals(2.5, stock.get("Sugar").getQuantity());
        assertEquals("kg", stock.get("Sugar").getUnit().toString());
        assertEquals("Condiment", stock.get("Sugar").getCategory().toString());
    }

    /**
     * Tests setting a low stock alert and decreasing quantity below the threshold.
     */
    @Test
    void testSetAlert() {
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("kg"),
                Category.parseCategory("CONDIMENT"));
        inventory.setAlert("Sugar", 2.0);
        inventory.decreaseQuantity("Sugar", 1);

        Map<String, Ingredient> stock = inventory.getInventory();
        Map<String, Double> lowStockAlerts = inventory.getLowStockAlerts();

        assertTrue(stock.containsKey("Sugar"), "Sugar should exist in inventory");
        assertEquals(1.5, stock.get("Sugar").getQuantity(), 0.01, "Sugar quantity " +
                "should be 1.5 kg");
        assertTrue(lowStockAlerts.containsKey("Sugar"), "Low stock alert should exist for Sugar");
        assertEquals(2.0, lowStockAlerts.get("Sugar"), 0.01, "Low stock threshold " +
                "should be 2.0" +
                " kg");

    }

    /**
     * Tests increasing the quantity of an existing ingredient.
     */
    @Test
    void testIncreaseQuantity() {
        inventory.addNewIngredient("Flour", 1.0, Unit.KILOGRAM, Category.parseCategory("GRAIN"));
        inventory.increaseQuantity("Flour", 0.5);
        assertEquals(1.5, inventory.getInventory().get("Flour").getQuantity(), "Flour quantity should"
                + " be 1.5 kg");
    }

    /**
     * Tests decreasing the quantity of an existing ingredient.
     */
    @Test
    void testDecreaseQuantity() {
        inventory.addNewIngredient("Milk", 2.0, Unit.LITER, Category.parseCategory("DAIRY"));
        inventory.decreaseQuantity("Milk", 1.5);
        assertEquals(0.5, inventory.getInventory().get("Milk").getQuantity(), "Milk quantity " +
                "should be" +
                " 0.5 L");
    }

    /**
     * Tests decreasing the quantity of an existing ingredient.
     */
    @Test
    void testDecreaseQuantityInsufficientStock() {
        inventory.addNewIngredient("Butter", 0.2, Unit.KILOGRAM, Category.parseCategory("DAIRY"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventory.decreaseQuantity("Butter", 0.5);
        });

        assertEquals("Not enough BUTTER in stock.", exception.getMessage());
    }

    /**
     * Tests that deleting an ingredient removes it from the inventory.
     */
    @Test
    void testDeleteIngredient() {
        inventory.addNewIngredient("Eggs", 12, Unit.POUND, Category.parseCategory("DAIRY"));
        inventory.deleteIngredient("Eggs");
        assertFalse(inventory.getInventory().containsKey("Eggs"), "Eggs should be removed from inventory");
    }

    /**
     * Tests checking if an ingredient with sufficient quantity exists in the inventory.
     */
    @Test
    void testIsInInventory() {
        inventory.addNewIngredient("Cheese", 0.5, Unit.KILOGRAM, Category.parseCategory("DAIRY"));
        assertTrue(inventory.isInInventory("Cheese", 0.3, Unit.KILOGRAM), "Cheese should " +
                "be in inventory with enough quantity");
        assertFalse(inventory.isInInventory("Cheese", 0.6, Unit.KILOGRAM), "Cheese should " +
                "not be sufficient in inventory");
    }

    /**
     * Tests that the inventory is initially empty.
     */
    @Test
    void testCheckStockEmpty() {
        assertTrue(inventory.getInventory().isEmpty(), "Inventory should be empty initially");
    }

    /**
     * Tests that providing a null category
     */
    @Test
    void testNullCategoryThrowsException() {
        IngredientInventory inventory = new IngredientInventory();
        String nullCategory = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventory.validateIngredientCategory(nullCategory);
        });
        assertEquals("Category cannot be null.\nType categoryList for a list of valid categories.",
                exception.getMessage());
    }

    /**
     * Tests that providing an invalid category
     */
    @Test
    void testInvalidCategoryThrowsException() {
        IngredientInventory inventory = new IngredientInventory();
        String invalidCategory = "INVALID_CATEGORY";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventory.validateIngredientCategory(invalidCategory);
        });
        assertEquals("Invalid category: " + invalidCategory + "\nType categoryList for a " +
                "list of valid categories.", exception.getMessage());
    }

    /**
     * Tests conversion of an ingredient’s unit (e.g., from grams to kilograms).
     */
    @Test
    public void testConvertIngredient() {
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("g"),
                Category.parseCategory("CONDIMENT"));
        inventory.convertIngredient("Sugar", Unit.KILOGRAM);

        Map<String, Ingredient> stock = inventory.getInventory();
        Ingredient sugar = stock.get("Sugar");

        assertNotNull(sugar);
        assertEquals(0.0025, sugar.getQuantity(), 0.0001);
        assertEquals("kg", sugar.getUnit().toString());
    }

    /**
     * Tests filtering and viewing ingredients by a specific category.
     */
    @Test
    public void testViewIngredientsByCategory() {
        inventory.addNewIngredient("Sugar", 2.5, Unit.parseUnit("g"),
                Category.parseCategory("CONDIMENT"));

        String result = inventory.viewIngredientsByCategory(Category.parseCategory("CONDIMENT"));

        assertTrue(result.contains("Sugar"));

        String expectedOutput = "Sugar 2.5 g Condiment\n";
        assertEquals(expectedOutput, result);
    }

    /**
     * Tests that adding an ingredient with a null name
     */
    @Test
    void testNullName() {
        assertThrows(AssertionError.class, () ->
                inventory.addNewIngredient(null, 1.0, Unit.GRAM, Category.CONDIMENT)
        );
    }

    /**
     * Tests that adding an ingredient with an empty name
     */
    @Test
    void testEmptyName() {
        assertThrows(AssertionError.class, () ->
                inventory.addNewIngredient("", 1.0, Unit.GRAM, Category.CONDIMENT)
        );
    }

    /**
     * Tests that adding an ingredient with a negative quantity
     */
    @Test
    void testNegativeQuantity() {
        assertThrows(AssertionError.class, () ->
                inventory.addNewIngredient("Sugar", -5.0, Unit.GRAM, Category.CONDIMENT)
        );
    }

    /**
     * Tests that adding an ingredient with zero quantity
     */
    @Test
    void testZeroQuantity() {
        assertThrows(AssertionError.class, () ->
                inventory.addNewIngredient("Sugar", 0.0, Unit.GRAM, Category.CONDIMENT)
        );
    }

    /**
     * Tests that adding an ingredient with a null unit
     */
    @Test
    void testNullUnit() {
        assertThrows(AssertionError.class, () ->
                inventory.addNewIngredient("Sugar", 1.0, null, Category.CONDIMENT)
        );
    }

    /**
     * Tests that adding an ingredient with a null category
     */
    @Test
    void testNullCategory() {
        assertThrows(AssertionError.class, () ->
                inventory.addNewIngredient("Sugar", 1.0, Unit.GRAM, null)
        );
    }

}
