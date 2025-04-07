package pantrypal.general.control;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.general.GeneralCommand;
import pantrypal.general.commands.inventory.InventoryCommand;
import pantrypal.general.commands.mealplan.MealPlanCommand;
import pantrypal.general.commands.recipe.RecipeCommand;
import pantrypal.general.commands.shoppinglist.ShoppingListCommand;


/**
 * The Ui class handles the user interface for the PantryPal application.
 * It provides methods to print various messages to the console, including
 * welcome messages, help messages, and error messages.
 */
public class Ui {
    private static final String LOGO =
            " ____             _              ____       _\n" +
            "|  _ \\ __ _ _ __ | |_ _ __ _   _|  _ \\ __ _| |\n" +
            "| |_) / _` | '_ \\| __| '__| | | | |_) / _` | |\n" +
            "|  __/ (_| | | | | |_| |  | |_| |  __/ (_| | |\n" +
            "|_|   \\__,_|_| |_|\\__|_|   \\__, |_|   \\__,_|_|\n" +
            "                           |___/";


    /**
     * Prints the welcome message to the console.
     */
    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        Ui.printLine();
        System.out.println("Welcome to PantryPal, please enter your command!");
        System.out.println("Type \"help\" for a list of commands.");
    }

    /**
     * Prints a line separator to the console.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }


    /**
     * Prints a message indicating that a low stock alert has been set for an ingredient.
     *
     * @param name      the name of the ingredient
     * @param threshold the threshold for the low stock alert
     */
    public static void printSetAlertMessage(String name, double threshold) {
        Ui.showMessage("Set low stock alert for " + name + " at " + threshold);
    }

    /**
     * Prints a message indicating that an ingredient was not found.
     */
    public static void printIngredientNotFoundMessage() {
        Ui.showMessage("Ingredient not found.");
    }

    /**
     * Prints the exit message to the console.
     */
    public void printExitMessage() {
        Ui.showMessage("See you next time!");
    }


    /**
     * Prints the help message to the console, listing all available commands.
     *
     * @param commands an array of Command objects representing the available commands
     */
    public void printHelpMessage(Command[] commands) {
        Ui.printLine();
        System.out.println("Here are all the available commands:\n");

        Ui.printLine();

        System.out.println("Shopping list commands:\n");
        for (Command command : commands) {
            if (command instanceof ShoppingListCommand) {
                System.out.print(command.getCommandDescription() + ": ");
                System.out.println("\"" + command.getCommandInstruction() + "\"");
            }
        }

        Ui.printLine();

        System.out.println("Recipe commands:\n");
        for (Command command : commands) {
            if (command instanceof RecipeCommand) {
                System.out.print(command.getCommandDescription() + ": ");
                System.out.println("\"" + command.getCommandInstruction() + "\"");
            }
        }

        Ui.printLine();

        System.out.println("Inventory Commands:\n");

        for (Command command : commands) {
            if (command instanceof InventoryCommand) {
                System.out.print(command.getCommandDescription() + ": ");
                System.out.println("\"" + command.getCommandInstruction() + "\"");
            }
        }

        Ui.printLine();

        System.out.println("Meal Plan Commands:\n");

        for (Command command : commands) {
            if (command instanceof MealPlanCommand) {
                System.out.print(command.getCommandDescription() + ": ");
                System.out.println("\"" + command.getCommandInstruction() + "\"");
            }
        }

        Ui.printLine();

        System.out.println("General commands:\n");
        for (Command command : commands) {
            if (command instanceof GeneralCommand) {
                System.out.print(command.getCommandDescription() + ": ");
                System.out.println("\"" + command.getCommandInstruction() + "\"");
            }
        }
        Ui.printLine();
    }

    /**
     * Prints an invalid command message to the console.
     *
     * @param errorMessage the error message to be printed
     */
    public void printInvalidCommandMessage(String errorMessage) {
        Ui.showMessage(errorMessage + "Please try again.");
    }

    /**
     * Prints a message to the console.
     *
     * @param message the message to be printed
     */
    public static void showMessage(String message) {
        System.out.println(message);
        Ui.printLine();
    }

    /**
     * Prints an error message to the console.
     *
     * @param message the error message to be printed
     */
    public static void printErrorMessage(String message) {
        Ui.showMessage(message);
    }

}
