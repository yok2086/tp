package pantrypal.main;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Parser;
import pantrypal.general.control.Storage;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class PantryPal {
    static final String FILE_PATH = "./data/data.txt";
    /**
     * Main entry-point for the PantryPal application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isFinished = false;
        Parser parser = new Parser();
        Ui ui = new Ui();
        IngredientInventory inventory = new IngredientInventory();
        ShoppingList shoppingList = new ShoppingList();
        PlanPresets planPresets = new PlanPresets();
        RecipeManager recipeManager = new RecipeManager();
        String input;
        Storage storage = new Storage(FILE_PATH);

        Ui.printWelcomeMessage();
        storage.createFile(inventory, shoppingList, planPresets, recipeManager);
        while (!isFinished) {
            input = in.nextLine();

            Command centralCommand = parser.parse(input);
            centralCommand.execute(ui, inventory, shoppingList, planPresets, recipeManager, in);
            isFinished = centralCommand.isExit();
        }
    }
}
