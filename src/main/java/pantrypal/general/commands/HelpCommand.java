package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

import java.util.Arrays;
import java.util.List;

public class HelpCommand extends Command {
    private static final List<Command> commandList = Arrays.asList(
            new ExitCommand(), new AddIngredientCommand(),
            new IncreaseQuantityCommand(), new DecreaseQuantityCommand()
    );

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        ui.printHelpMessage(commandList.toArray(new Command[0]));
    }
}
