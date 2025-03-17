package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("exit", "Exit the program");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        exit = true;
        ui.printExitMessage();
    }

}
