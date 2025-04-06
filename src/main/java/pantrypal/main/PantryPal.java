package pantrypal.main;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Parser;
import pantrypal.general.control.Storage;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
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
        MealPlanManager mealPlanManager = new MealPlanManager();
        RecipeManager recipes = new RecipeManager();
        String input;
        Storage storage = new Storage(FILE_PATH);

        Ui.printWelcomeMessage();
        storage.createFile(inventory, shoppingList, mealPlanManager, recipes);
        while (!isFinished) {
            input = in.nextLine();

            Command centralCommand = parser.parse(input);
            centralCommand.execute(ui, inventory, shoppingList, recipes, mealPlanManager, in);
            isFinished = centralCommand.isExit();
            Storage.saveData(inventory, shoppingList, mealPlanManager, recipes);
        }
    }
}
