package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListGenerator;

public class generateShoppingList extends Command {
    private ShoppingList shoppingList = new ShoppingList();

    public generateShoppingList() {
        super("generateShoppingList", "Auto-generate shopping list");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        ShoppingListGenerator shoppingListGenerator = new ShoppingListGenerator(inventory);
        shoppingList = shoppingListGenerator.generateShoppingList();
        ui.showMessage("Shopping list has been auto-generated.");
    }
}
