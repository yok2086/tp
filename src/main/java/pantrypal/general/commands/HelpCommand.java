package pantrypal.general.commands;


import pantrypal.general.control.Ui;

public class HelpCommand extends Command {
    private Command[] commandList = new Command[]{new ExitCommand()};


    @Override
    public void execute(Ui ui) {
        ui.printHelpMessage(commandList);
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
