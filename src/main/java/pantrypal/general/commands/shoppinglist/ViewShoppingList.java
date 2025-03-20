package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

public class ViewShoppingList extends Command {

    public ViewShoppingList() {
        super("view", "View Shopping List");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList) {
        shoppingList.displayList(); // Error handling is inside the function
    }
}
