package pantrypal.general.control;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.ExitCommand;
import pantrypal.general.commands.HelpCommand;
import pantrypal.general.commands.NullCommand;

public class Parser {

    public Command parse(String input) {

        switch (input) {
            case "help": return new HelpCommand();
            case "exit": return new ExitCommand();
            default: return new NullCommand();
        }
    }
}
