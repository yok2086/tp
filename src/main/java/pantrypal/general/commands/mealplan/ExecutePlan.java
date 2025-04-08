package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.mealplan.Plan;
import pantrypal.mealplan.Day;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListItem;

import java.util.ArrayList;
import java.util.Scanner;

public class ExecutePlan extends MealPlanCommand {
    private String day;

    public ExecutePlan() {
        super("execute <day>", "Execute a plan for a specific day");
    }

    public ExecutePlan(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    private boolean inventoryIsSufficient(Recipe recipe, IngredientInventory inventory, ShoppingList list, Scanner in) {
        // Check if the ingredients required in the recipe are available in the pantry
        ArrayList<Ingredient> requiredIngredients = recipe.getIngredients(); //point of cohesion with Recipe
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
        for (Ingredient ingredient : requiredIngredients) {
            if (!inventory.isInInventory(ingredient.getName(), ingredient.getQuantity(), ingredient.getUnit())) {
                Ui.showMessage("Ingredient " + ingredient.getName() + " is not available.");
                Ui.showMessage("There is currently " + ingredient.getQuantity() + ingredient.getUnit() +
                        " in the inventory.");
                missingIngredients.add(ingredient);
            }
        }
        // If there are missing ingredients, prompt to add them to the shopping list
        if (!missingIngredients.isEmpty()) {
            Ui.showMessage("These are the missing ingredients for " + recipe.getName() + ": " + missingIngredients);
            System.out.println("Would you like to add missing requiredIngredients into shopping list? (yes/no)");
            String response = in.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                for (Ingredient missingIngredient : missingIngredients) {
                    Ui.showMessage("Adding " + missingIngredient.getName() + " to shopping list.");
                    // Add the missing ingredient to the shopping list
                    ShoppingListItem shoppingListItem = new ShoppingListItem(missingIngredient.getName(),
                            missingIngredient.getQuantity(), missingIngredient.getUnit());
                    list.addItem(shoppingListItem);
                }
            } else {
                Ui.showMessage("Missing ingredients will not be added to the shopping list." +
                        " You will not be able to execute the plan.");
            }
            return false;
        } else {
            Ui.showMessage("All ingredients for " + recipe.getName() + " are available!");
            // Decrease the quantity of the requiredIngredients in the pantry
            for (Ingredient ingredient : requiredIngredients) {
                inventory.decreaseQuantity(ingredient.getName(), ingredient.getQuantity());
            }
        }
        return true;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {

        Day dayEnum = getDay(day);

        Plan plan = plans.getPlanForDay(dayEnum);
        if (plan == null) {
            Ui.showMessage("No plan found for the specified day.");
            return;
        }

        Recipe[] recipesInPlan = plan.getPlanRecipes();
        boolean noPlanInRecipe = true;
        for (Recipe recipe : recipesInPlan) {
            if (recipe != null) {
                noPlanInRecipe = false;
                break;
            }
        }
        if (noPlanInRecipe) {
            Ui.showMessage("No recipes found for the specified plan. Aborting execution operation.");
            return;
        }

        boolean ingredientsAreAvailable = true;

        for (Recipe recipe : recipesInPlan) {
            if (recipe == null) {
                continue;
            }
            if (!inventoryIsSufficient(recipe, inventory, list, in)) {
                Ui.showMessage("Please try again once the ingredients are available.");
                ingredientsAreAvailable = false;
                break;
            }
        }

        if (ingredientsAreAvailable) {
            Ui.showMessage("Executing plan for " + day + ".\n Used ingredients have been decreased from inventory." +
                    "\n Here are all your recipes:");
            for (Recipe recipe : recipesInPlan) {
                if (recipe == null) {
                    continue;
                }
                recipes.showRecipe(recipe.getName());
            }
        }
    }
}
