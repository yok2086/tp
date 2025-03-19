package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

public class viewShoppingList extends Command {
    private ShoppingList shoppingList;

    public viewShoppingList() {
        super("generateShoppingList", "Auto-generate shopping list");
    }

    public viewShoppingList(ShoppingList shoppingList) {
        super("generateShoppingList", "Auto-generate shopping list");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        shoppingList.displayList(); // Error handling is inside the function
    }
}
