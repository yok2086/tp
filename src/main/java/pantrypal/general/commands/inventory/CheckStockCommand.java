package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class CheckStockCommand extends Command {

    public CheckStockCommand() {
        super("checkStock", "Checks the stock of an item in the inventory");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        inventory.checkStock();
    }
}
