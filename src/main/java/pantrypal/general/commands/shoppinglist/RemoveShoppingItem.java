package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

public class RemoveShoppingItem extends Command {
    private String name;
    private ShoppingList shoppingList;

    public RemoveShoppingItem() {
        super("RemoveShoppingItem <name>", "Delete an item from the ShoppingList");
    }

    public RemoveShoppingItem(String name, ShoppingList shoppingList) {
        super("RemoveShoppingItem <name>", "Delete an item from the ShoppingList");
        this.name = name;
        this.shoppingList = shoppingList;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        if (shoppingList == null) {
            ui.showMessage("Error: ShoppingList is not initialized.");
            return;
        }
        boolean removed = shoppingList.removeItem(name);
        if (removed) {
            ui.showMessage("Removed '" + name + "' from the shopping list.");
        } else {
            ui.showMessage("Item '" + name + "' not found in the shopping list.");
        }
    }
}
