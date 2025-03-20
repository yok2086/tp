package pantrypal.general.commands;

import pantrypal.general.commands.inventory.AddIngredientCommand;
import pantrypal.general.commands.inventory.IncreaseQuantityCommand;
import pantrypal.general.commands.inventory.DecreaseQuantityCommand;
import pantrypal.general.commands.inventory.SetAlertCommand;
import pantrypal.general.commands.inventory.CheckStockCommand;
import pantrypal.general.commands.inventory.ViewLowStockCommand;
import pantrypal.general.commands.mealplan.AddNewPlan;
import pantrypal.general.commands.mealplan.AddRecipeToPlan;
import pantrypal.general.commands.mealplan.RemovePlan;
import pantrypal.general.commands.mealplan.RemoveRecipeFromPlan;
import pantrypal.general.commands.mealplan.ViewPlan;
import pantrypal.general.commands.recipe.AddRecipe;
import pantrypal.general.commands.recipe.ListRecipe;
import pantrypal.general.commands.recipe.RemoveRecipe;
import pantrypal.general.commands.recipe.ViewRecipe;
import pantrypal.general.commands.shoppinglist.AddShoppingItem;
import pantrypal.general.commands.shoppinglist.GenerateShoppingList;
import pantrypal.general.commands.shoppinglist.RemoveShoppingItem;
import pantrypal.general.commands.shoppinglist.ViewShoppingList;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HelpCommand extends Command {
    private static final List<Command> commandList = Arrays.asList(
            new ExitCommand(), new AddIngredientCommand(),
            new IncreaseQuantityCommand(), new DecreaseQuantityCommand(),
            new SetAlertCommand(), new CheckStockCommand(), new ViewLowStockCommand(), new AddShoppingItem(),
            new GenerateShoppingList(), new RemoveShoppingItem(), new ViewShoppingList(), new ViewPlan(), new AddNewPlan(),
            new RemovePlan(), new AddRecipe(), new ViewRecipe(), new RemoveRecipe(), new ListRecipe(),
            new AddRecipeToPlan(), new RemoveRecipeFromPlan()
    );

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets, RecipeManager recipeManager, Scanner in) {
        ui.printHelpMessage(commandList.toArray(new Command[0]));
    }
}
