package pantrypal.shoppinglist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Unit;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ShoppingListTest {

    private ShoppingList shoppingList;

    @BeforeEach
    public void setUp() {
        shoppingList = new ShoppingList();
        assert shoppingList != null : "ShoppingList should be initialized.";
    }

    @Test
    public void testAddAndGetItems() {
        ShoppingListItem item1 = new ShoppingListItem("sugar", 100, Unit.GRAM);
        ShoppingListItem item2 = new ShoppingListItem("flour", 200, Unit.GRAM);
        shoppingList.addItem(item1);
        shoppingList.addItem(item2);
        List<ShoppingListItem> items = shoppingList.getItems();
        assertEquals(2, items.size(), "There should be 2 items in the shopping list.");
        assertTrue(items.contains(item1), "The shopping list should contain 'sugar'.");
        assertTrue(items.contains(item2), "The shopping list should contain 'flour'.");
    }

    @Test
    public void testRemoveItem() {
        ShoppingListItem item1 = new ShoppingListItem("sugar", 100, Unit.GRAM);
        ShoppingListItem item2 = new ShoppingListItem("flour", 200, Unit.GRAM);
        shoppingList.addItem(item1);
        shoppingList.addItem(item2);
        boolean removed = shoppingList.removeItem("sugar");
        assertTrue(removed, "Removing 'sugar' should return true.");
        assertEquals(1, shoppingList.getItems().size(), "There should be 1 item left in the list.");
        boolean removedNonExistent = shoppingList.removeItem("milk");
        assertFalse(removedNonExistent, "Removing a non-existent item should return false.");
    }

    @Test
    public void testUpdateItem() {
        ShoppingListItem item1 = new ShoppingListItem("sugar", 100, Unit.GRAM);
        shoppingList.addItem(item1);
        boolean updated = shoppingList.updateItem("sugar", 150, Unit.GRAM);
        assertTrue(updated, "Update should return true when the item exists.");
        ShoppingListItem updatedItem = shoppingList.getItems().get(0);
        assertEquals(150, updatedItem.getQuantity(), "Quantity should be updated to 150.");
        // Attempt to update a non-existent item.
        boolean updateNonExistent = shoppingList.updateItem("flour", 200, Unit.GRAM);
        assertFalse(updateNonExistent, "Updating non-existent item should return false.");
    }

    @Test
    public void testEditItem() {
        ShoppingListItem item1 = new ShoppingListItem("sugar", 100, Unit.GRAM);
        ShoppingListItem item2 = new ShoppingListItem("flour", 200, Unit.GRAM);
        shoppingList.addItem(item1);
        shoppingList.addItem(item2);
        // Valid edit: edit item at index 1 (flour).
        shoppingList.editItem(1, "flour", 250, Unit.GRAM);
        ShoppingListItem editedItem = shoppingList.getItems().get(1);
        assertEquals("flour", editedItem.getIngredientName(),
                "Ingredient name should remain 'flour'.");
        assertEquals(250, editedItem.getQuantity(), "Quantity should be updated to 250.");
    }

    @Test
    public void testMarkItemAsPurchased() {
        ShoppingListItem item1 = new ShoppingListItem("sugar", 100, Unit.GRAM);
        shoppingList.addItem(item1);
        boolean marked = shoppingList.markItemAsPurchased("sugar");
        assertTrue(marked, "Marking an existing item as purchased should return true.");
        assertTrue(item1.isPurchased(), "The item 'sugar' should be marked as purchased.");
        // Attempt to mark a non-existent item.
        boolean markNonExistent = shoppingList.markItemAsPurchased("flour");
        assertFalse(markNonExistent, "Marking a non-existent item should return false.");
    }

    @Test
    public void testCopyFromAndCopyList() {
        ShoppingListItem item1 = new ShoppingListItem("sugar", 100, Unit.GRAM);
        ShoppingListItem item2 = new ShoppingListItem("flour", 200, Unit.GRAM);
        shoppingList.addItem(item1);
        shoppingList.addItem(item2);

        ShoppingList newList = new ShoppingList();
        newList.copyFrom(shoppingList);
        List<ShoppingListItem> newItems = newList.getItems();
        assertEquals(2, newItems.size(), "Copied list should have 2 items.");
        // Verify deep copy: newList items should not be the same instance as original.
        assertNotSame(item1, newItems.get(0), "Item should be a deep copy.");
        assertEquals("sugar", newItems.get(0).getIngredientName(),
                "Copied item should have correct name.");

        // Test copyList similarly.
        ShoppingList anotherList = new ShoppingList();
        anotherList.copyList(shoppingList);
        List<ShoppingListItem> anotherItems = anotherList.getItems();
        assertEquals(2, anotherItems.size(), "Copied list should have 2 items.");
    }

    @Test
    public void testDisplayList() {
        // Prepare to capture the console output.
        ShoppingListItem item1 = new ShoppingListItem("sugar", 100, Unit.GRAM);
        shoppingList.addItem(item1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        shoppingList.displayList();

        // Reset the System.out.
        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Shopping List:"), "Display output should contain header.");
        assertTrue(output.contains("sugar: 100 " + Unit.GRAM), "Display output should contain the " +
                "item details.");
    }
}
