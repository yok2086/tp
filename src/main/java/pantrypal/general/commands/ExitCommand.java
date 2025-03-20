package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("exit", "Exit the program");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList) {
        exit = true;
        ui.printExitMessage();
    }

}
