package pantrypal.general.commands.general;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.ConvertIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;
import pantrypal.general.commands.inventory.DecreaseQuantity;
import pantrypal.general.commands.inventory.SetAlert;
import pantrypal.general.commands.inventory.CheckStock;
import pantrypal.general.commands.inventory.ViewIngredientsByCategory;
import pantrypal.general.commands.inventory.ViewLowStock;
import pantrypal.general.commands.recipe.AddRecipe;
import pantrypal.general.commands.recipe.ListRecipe;
import pantrypal.general.commands.recipe.RemoveRecipe;
import pantrypal.general.commands.recipe.ViewRecipe;
import pantrypal.general.commands.shoppinglist.AddShoppingItem;
import pantrypal.general.commands.shoppinglist.RemoveShoppingItem;
import pantrypal.general.commands.shoppinglist.ViewShoppingList;
import pantrypal.general.commands.shoppinglist.EditShoppingItem;
import pantrypal.general.commands.shoppinglist.GenerateShoppingList;
import pantrypal.general.commands.shoppinglist.MarkShoppingItemAsPurchased;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Help extends GeneralCommand {
    private static final List<Command> commandList = Arrays.asList(
            new Exit(), new Help(),
            new AddIngredient(), new IncreaseQuantity(), new DecreaseQuantity(), new SetAlert(), new CheckStock(),
            new ViewLowStock(),  new ConvertIngredient(), new ViewIngredientsByCategory(), new UnitList(),
            new CategoryList(),
            new AddShoppingItem(), new GenerateShoppingList(), new RemoveShoppingItem(), new EditShoppingItem(),
            new MarkShoppingItemAsPurchased(), new ViewShoppingList(),
            new AddRecipe(), new ViewRecipe(), new RemoveRecipe(), new ListRecipe()
    );

    public Help() {
        super("help","List all commands");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        ui.printHelpMessage(commandList.toArray(new Command[0]));
    }
}
