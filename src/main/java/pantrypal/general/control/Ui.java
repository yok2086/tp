package pantrypal.general.control;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.general.GeneralCommand;
import pantrypal.general.commands.inventory.InventoryCommand;
import pantrypal.general.commands.mealplan.MealPlanCommand;
import pantrypal.general.commands.recipe.RecipeCommand;
import pantrypal.general.commands.shoppinglist.ShoppingListCommand;

public class Ui {
    private static final String LOGO =
            " ____             _              ____       _\n" +
            "|  _ \\ __ _ _ __ | |_ _ __ _   _|  _ \\ __ _| |\n" +
            "| |_) / _` | '_ \\| __| '__| | | | |_) / _` | |\n" +
            "|  __/ (_| | | | | |_| |  | |_| |  __/ (_| | |\n" +
            "|_|   \\__,_|_| |_|\\__|_|   \\__, |_|   \\__,_|_|\n" +
            "                           |___/";

    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        Ui.printLine();
        System.out.println("Welcome to PantryPal, please enter your command!");
        System.out.println("Type \"help\" for a list of commands.");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printAddIngredientMessage(String name, double quantity, String unit, String category) {
        Ui.showMessage("Adding " + name + " " + quantity + " " + unit);
    }

    public static void printIngredientExists(String name) {
        Ui.showMessage("Ingredient " + name + " already exists.");
    }

    public static void printSetAlertMessage(String name, double threshold) {
        Ui.showMessage("Set low stock alert for " + name + " at " + threshold);
    }

    public static void printIngredientNotFoundMessage() {
        Ui.showMessage("Ingredient not found.");
    }

    public void printExitMessage() {
        Ui.showMessage("See you next time!");
    }

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

    public void printInvalidCommandMessage(String errorMessage) {
        Ui.showMessage(errorMessage + "Please try again.");
        Ui.printLine();
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        Ui.showMessage(message);
    }

}
