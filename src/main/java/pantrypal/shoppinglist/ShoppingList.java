package pantrypal.shoppinglist;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<ShoppingListItem> items;

    public ShoppingList() {
        items = new ArrayList<>();
    }

    // Add a new item to the shopping list.
    public void addItem(ShoppingListItem item) {
        items.add(item);
    }

    // Get the list of shopping items.
    public List<ShoppingListItem> getItems() {
        return items;
    }

    // Remove an item by ingredient name.
    public boolean removeItem(String ingredientName) {
        return items.removeIf(item -> item.getIngredientName().equalsIgnoreCase(ingredientName));
    }

    // Display the shopping list.
    public void displayList() {
        if (items.isEmpty()) {
            System.out.println("Shopping list is empty.");
        } else {
            System.out.println("Shopping List:");
            for (ShoppingListItem item : items) {
                System.out.println("- " + item);
            }
        }
    }
}
