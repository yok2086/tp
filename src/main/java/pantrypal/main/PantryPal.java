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

/**
 * The PantryPal class is the main entry point for the PantryPal application.
 * It initializes the necessary components and handles the main application loop.
 */
public class PantryPal {
    private static final String FILE_PATH = "./data/data.txt";
    private Storage storage;
    private IngredientInventory inventory;
    private ShoppingList shoppingList;
    private MealPlanManager mealPlanManager;
    private RecipeManager recipes;
    private Ui ui;
    private Parser parser;
    private boolean isFinished;

    /**
     * Constructs a new PantryPal instance with the specified file path.
     *
     * @param filePath the path to the data file
     */
    public PantryPal(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        inventory = new IngredientInventory();
        shoppingList = new ShoppingList();
        mealPlanManager = new MealPlanManager();
        recipes = new RecipeManager();
        parser = new Parser();
        isFinished = false;

        ui.printWelcomeMessage();
        storage.createFile(inventory, shoppingList, mealPlanManager, recipes);
    }

    /**
     * Runs the main application loop, processing user input and executing commands.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        while (!isFinished) {
            String input = in.nextLine();
            Command centralCommand = parser.parse(input);
            centralCommand.execute(ui, inventory, shoppingList, recipes, mealPlanManager, in);
            isFinished = centralCommand.isExit();
            storage.saveData(inventory, shoppingList, mealPlanManager, recipes);
        }
    }

    /**
     * The main method, entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new PantryPal(FILE_PATH).run();
    }
}
