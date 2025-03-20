package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

public class ViewShoppingList extends Command {
    private ShoppingList shoppingList;

    public ViewShoppingList() {
        super("GenerateShoppingList", "Auto-generate shopping list");
    }

    public ViewShoppingList(ShoppingList shoppingList) {
        super("GenerateShoppingList", "Auto-generate shopping list");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        shoppingList.displayList(); // Error handling is inside the function
    }
}
