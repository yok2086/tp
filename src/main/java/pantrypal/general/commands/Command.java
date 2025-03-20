package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public abstract class Command {
    protected Boolean exit = false;
    protected  String commandInstruction;
    protected  String commandDescription;

    public Command(String commandInstruction, String commandDescription) {
        this.commandInstruction = commandInstruction;
        this.commandDescription = commandDescription;
    }

    public Command() {}

    public Boolean isExit() {
        return exit;
    }

    public abstract void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets, RecipeManager recipeManager, Scanner in);

    public String getCommandInstruction() {
        return commandInstruction;
    }

    public String getCommandDescription() {
        return commandDescription;
    }
}
