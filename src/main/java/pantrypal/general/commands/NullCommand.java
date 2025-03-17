package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class NullCommand extends Command {
    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        ui.printInvalidCommandMessage();
    }

}
