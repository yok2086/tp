package pantrypal.shoppinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Category;
import pantrypal.inventory.Unit;

public class ShoppingListItemTest {

    private ShoppingListItem item;

    @BeforeEach
    public void setUp() {
        // Assuming Unit.G represents grams and is a valid enum constant.
        item = new ShoppingListItem("sugar", 100, Unit.GRAM);
        // Plain Java assert to ensure initialization.
        assert item != null : "ShoppingListItem instance should be initialized.";
    }

    @Test
    public void testConstructorInitializesCorrectly() {
        assertEquals("sugar", item.getIngredientName(), "Ingredient name should be 'sugar'.");
        assertEquals(100, item.getQuantity(), "Quantity should be 100.");
        assertEquals(Unit.GRAM, item.getUnit(), "Unit should be Unit.G.");
        assertFalse(item.isPurchased(), "New item should not be marked as purchased by default.");
    }

    @Test
    public void testSetIngredientName() {
        item.setIngredientName("flour");
        assertEquals("flour", item.getIngredientName(), "Ingredient name should be updated to 'flour'.");
    }

    @Test
    public void testSetQuantity() {
        item.setQuantity(200);
        assertEquals(200, item.getQuantity(), "Quantity should be updated to 200.");
    }

    @Test
    public void testSetUnit() {
        // Assuming Unit.ML exists as a valid constant in Unit.
        item.setUnit(Unit.MILLILITER);
        assertEquals(Unit.MILLILITER, item.getUnit(), "Unit should be updated to Unit.ML.");
    }

    @Test
    public void testSetPurchased() {
        item.setPurchased(true);
        assertTrue(item.isPurchased(), "Item should be marked as purchased.");
    }

    @Test
    public void testToStringWithoutPurchase() {
        String expected = "sugar: 100 " + Unit.GRAM;
        String actual = item.toString();
        assertEquals(expected, actual, "toString() should not indicate purchased when false.");
    }

    @Test
    public void testToStringWithPurchase() {
        item.setPurchased(true);
        String expected = "sugar: 100 " + Unit.GRAM + " (Purchased)";
        String actual = item.toString();
        assertEquals(expected, actual, "toString() should indicate purchased when true.");
    }

    @Test
    public void testCopyConstructor() {
        item.setPurchased(true);
        ShoppingListItem copy = new ShoppingListItem(item);
        assertEquals(item.getIngredientName(), copy.getIngredientName(),
                "Copied item should have the same ingredient name.");
        assertEquals(item.getQuantity(), copy.getQuantity(),
                "Copied item should have the same quantity.");
        assertEquals(item.getUnit(), copy.getUnit(),
                "Copied item should have the same unit.");
        assertEquals(item.isPurchased(), copy.isPurchased(),
                "Copied item should have the same purchased status.");

        // Modify original and ensure copy remains unchanged.
        item.setIngredientName("milk");
        item.setQuantity(50);
        item.setPurchased(false);
        assertEquals("sugar", copy.getIngredientName(),
                "Copy's ingredient name should remain 'sugar'.");
        assertEquals(100, copy.getQuantity(),
                "Copy's quantity should remain 100.");
        assertTrue(copy.isPurchased(),
                "Copy's purchased flag should remain true.");
    }
}
