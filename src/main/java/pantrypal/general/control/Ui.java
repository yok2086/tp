package pantrypal.general.control;

import pantrypal.general.commands.Command;

public class Ui {
    private static final String LOGO =
            "____             _              ____       _\n" +
                    "|  _ \\ __ _ _ __ | |_ _ __ _   _|  _ \\ __ _| |\n" +
                    "| |_) / _` | '_ \\| __| '__| | | | |_) / _` | |\n" +
                    "|  __/ (_| | | | | |_| |  | |_| |  __/ (_| | |\n" +
                    "|_|   \\__,_|_| |_|\\__|_|   \\__, |_|   \\__,_|_|\n" +
                    "                           |___/";

    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        Ui.printLine();
        System.out.println("Welcome to PantryPal, please enter your command!");
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printExitMessage() {
        Ui.printLine();
        System.out.println("See you next time!");
        Ui.printLine();
    }

    public void printHelpMessage(Command[] commands) {
        Ui.printLine();
        System.out.println("Here are all the available commands:\n");
        for (Command command : commands) {
            System.out.print(command.getCommandDescription() + ": ");
            System.out.println(command.getCommandInstruction());
        }
        Ui.printLine();
    }

    public void printInvalidCommandMessage() {
        Ui.printLine();
        System.out.println("Invalid command!");
        Ui.printLine();
    }

    public void showMessage(String message) {
        Ui.printLine();
        System.out.println(message);
        Ui.printLine();
    }

}
