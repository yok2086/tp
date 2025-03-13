package pantrypal.general.commands;

import pantrypal.general.control.Ui;

public class ExitCommand extends Command {
    private static final String COMMAND_DESCRIPTION = "Exit the program";
    private static final String COMMAND_NAME = "exit";

    @Override
    public void execute(Ui ui) {
        exit = true;
        ui.printExitMessage();
    }

    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
