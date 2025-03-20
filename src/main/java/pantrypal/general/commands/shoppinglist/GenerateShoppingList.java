package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListGenerator;

public class GenerateShoppingList extends Command {

    public GenerateShoppingList() {
        super("generateShoppingList", "Auto-generate shopping list");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList) {
        ShoppingListGenerator shoppingListGenerator = new ShoppingListGenerator(inventory);
        ShoppingList newShoppingList = shoppingListGenerator.generateShoppingList();
        shoppingList.copyFrom(newShoppingList);
        ui.showMessage("Shopping list has been auto-generated.");
    }
}
