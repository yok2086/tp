package pantrypal.general.commands;

import pantrypal.general.control.Ui;

public abstract class Command {
    protected Boolean exit = false;

    public Boolean isExit() { return exit; }

    public abstract void execute(Ui ui);
    public abstract String getCommandDescription();
    public abstract String getCommandName();
}
