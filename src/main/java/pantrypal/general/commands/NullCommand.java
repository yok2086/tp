package pantrypal.general.commands;

import pantrypal.general.control.Ui;

public class NullCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.printInvalidCommandMessage();
    }

    @Override
    public String getCommandDescription() {
        return "";
    }

    @Override
    public String getCommandName() {
        return "";
    }
}
