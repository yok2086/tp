package pantrypal.main;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Parser;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

import java.util.Scanner;

public class PantryPal {
    /**
     * Main entry-point for the PantryPal application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isFinished = false;
        Parser parser = new Parser();
        IngredientInventory inventory = new IngredientInventory();
        Ui ui = new Ui();
        String input;

        Ui.printWelcomeMessage();
        while (!isFinished) {
            input = in.nextLine();

            Command centralCommand = parser.parse(input);
            centralCommand.execute(ui,inventory);
            isFinished = centralCommand.isExit();
        }
    }
}
