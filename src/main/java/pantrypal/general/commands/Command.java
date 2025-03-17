package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public abstract class Command {
    protected Boolean exit = false;
    protected  String commandDescription;
    protected  String commandName;

    public Command(String commandDescription, String commandName) {
        this.commandDescription = commandDescription;
        this.commandName = commandName;
    }

    public Command() {}

    public Boolean isExit() {
        return exit;
    }

    public abstract void execute(Ui ui, IngredientInventory inventory);

    public String getCommandDescription() {
        return commandDescription;
    }

    public String getCommandName() {
        return commandName;
    }
}
