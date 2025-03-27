package pantrypal.shoppinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;

import java.time.LocalDate;
import java.util.List;

public class ShoppingListGeneratorTest {

    private IngredientInventory inventory;

    @BeforeEach
    public void setUp() {
        // Initialize the inventory and add some ingredients.
        inventory = new IngredientInventory();
        inventory.addNewIngredient("sugar", 100, Unit.parseUnit("g"), LocalDate.parse("2026-12-31"));
        inventory.addNewIngredient("flour", 300,  Unit.parseUnit("g"), LocalDate.parse("2026-01-15"));
        inventory.addNewIngredient("butter", 50, Unit.parseUnit("g"), LocalDate.parse("2026-02-01"));
    }

    @Test
    public void testGenerateShoppingList_whenIngredientsBelowThreshold_thenItemsAdded() {
        // Set low stock alerts:
        inventory.setAlert("sugar", 500);
        inventory.setAlert("flour", 300);
        inventory.setAlert("butter", 200);

        // Generate the shopping list.
        ShoppingListGenerator generator = new ShoppingListGenerator(inventory);
        ShoppingList shoppingList = generator.generateShoppingList();
        List<ShoppingListItem> items = shoppingList.getItems();

        // Expect two items: sugar and butter.
        assertEquals(2, items.size(), "There should be 2 items in the shopping list.");

        // Verify sugar item.
        ShoppingListItem sugarItem = items.stream()
                .filter(item -> item.getIngredientName().equalsIgnoreCase("sugar"))
                .findFirst().orElse(null);
        assertNotNull(sugarItem, "Sugar item should be present.");
        assertEquals(400, sugarItem.getQuantity(), "Required sugar quantity should be 400.");
        assertEquals("g", sugarItem.getUnit().toString(), "Sugar unit should be 'g'.");
        assertEquals(LocalDate.parse("2026-12-31"), sugarItem.getExpiryDate(), "Sugar expiry date should be 2026-12-31.");

        // Verify butter item.
        ShoppingListItem butterItem = items.stream()
                .filter(item -> item.getIngredientName().equalsIgnoreCase("butter"))
                .findFirst().orElse(null);
        assertNotNull(butterItem, "Butter item should be present.");
        assertEquals(150, butterItem.getQuantity(), "Required butter quantity should be 150.");
        assertEquals("g", butterItem.getUnit().toString(), "Butter unit should be 'g'.");

        // Ensure flour is not added.
        boolean flourPresent = items.stream()
                .anyMatch(item -> item.getIngredientName().equalsIgnoreCase("flour"));
        assertFalse(flourPresent, "Flour should not be added since its quantity equals the threshold.");
    }

    @Test
    public void testGenerateShoppingList_whenNoIngredientBelowThreshold_thenEmptyList() {
        // Set alerts so that all ingredient quantities meet or exceed their thresholds.
        inventory.setAlert("sugar", 50);   // sugar: 100 >= 50
        inventory.setAlert("flour", 200);    // flour: 300 >= 200
        inventory.setAlert("butter", 30);     // butter: 50 >= 30

        ShoppingListGenerator generator = new ShoppingListGenerator(inventory);
        ShoppingList shoppingList = generator.generateShoppingList();
        List<ShoppingListItem> items = shoppingList.getItems();

        assertTrue(items.isEmpty(), "Shopping list should be empty when no ingredient is below its threshold.");
    }
}
