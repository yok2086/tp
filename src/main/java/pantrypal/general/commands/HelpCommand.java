package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class HelpCommand extends Command {
    private Command[] commandList = new Command[]{
            new ExitCommand(),
            new AddIngredientCommand(),
            new IncreaseQuantityCommand(),
            new DecreaseQuantityCommand()
    };

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        ui.printHelpMessage(commandList);
    }
}
