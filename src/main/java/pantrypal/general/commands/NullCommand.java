package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

public class NullCommand extends Command {
    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList) {
        ui.printInvalidCommandMessage();
    }

}
