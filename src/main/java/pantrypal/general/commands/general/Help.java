package pantrypal.general.commands.general;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.AlertExpiredIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;
import pantrypal.general.commands.inventory.DecreaseQuantity;
import pantrypal.general.commands.inventory.SetAlert;
import pantrypal.general.commands.inventory.CheckStock;
import pantrypal.general.commands.inventory.ViewLowStock;
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

public class Help extends GeneralCommand {
    private static final List<Command> commandList = Arrays.asList(
            new Exit(), new AddIngredient(),
            new IncreaseQuantity(), new DecreaseQuantity(),
            new SetAlert(), new CheckStock(), new ViewLowStock(), new AddShoppingItem(),
            new GenerateShoppingList(), new RemoveShoppingItem(), new ViewShoppingList(), new ViewPlan(),
            new AddNewPlan(), new RemovePlan(), new AddRecipe(), new ViewRecipe(), new RemoveRecipe(), new ListRecipe(),
            new AddRecipeToPlan(), new RemoveRecipeFromPlan(), new Help(), new AlertExpiredIngredient(), new UnitList()
    );

    public Help() {
        super("help","list all commands");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        ui.printHelpMessage(commandList.toArray(new Command[0]));
    }
}
