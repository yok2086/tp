package pantrypal.general.commands;

import pantrypal.general.commands.inventory.AddIngredientCommand;
import pantrypal.general.commands.inventory.IncreaseQuantityCommand;
import pantrypal.general.commands.inventory.DecreaseQuantityCommand;
import pantrypal.general.commands.inventory.SetAlertCommand;
import pantrypal.general.commands.inventory.CheckStockCommand;
import pantrypal.general.commands.inventory.ViewLowStockCommand;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

import java.util.Arrays;
import java.util.List;

public class HelpCommand extends Command {
    private static final List<Command> commandList = Arrays.asList(
            new ExitCommand(), new AddIngredientCommand(),
            new IncreaseQuantityCommand(), new DecreaseQuantityCommand(),
            new SetAlertCommand(), new CheckStockCommand(), new ViewLowStockCommand()
    );

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        ui.printHelpMessage(commandList.toArray(new Command[0]));
    }
}
