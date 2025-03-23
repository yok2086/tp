package pantrypal.general.commands.recipe;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddRecipe extends Command {
    private String recipeName;
    private String stepContent;
    private String content;


    public AddRecipe() {
        super("addRecipe", "Add a recipe");
    }


    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, Scanner in) {
        Boolean isFinished = false;
        do {
            System.out.println("Please Input Recipe Name: ");
            recipeName = in.nextLine();
        } while (recipeName.trim().isEmpty());
        Recipe recipe = recipes.addRecipe(recipeName);

        int stepNumber = 1;
        while (!isFinished) {
            System.out.println("Please Input Ingredient Name: <When done, type exit>");
            String ingredientName = in.nextLine();
            if (ingredientName.equals("exit")) {
                isFinished = true;
            } else {
                System.out.println("Please Input Ingredient Quantity:");
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(in.nextLine());
                    if (quantity <= 0) {
                        throw new ArithmeticException("Quantity must be greater than 0");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ingredient quantity, please try again");
                    continue;
                } catch (ArithmeticException e) {
                    System.out.println("Quantity must be greater than 0");
                    continue;
                } finally {
                    System.out.println("Please Input Quantity Unit:");
                    String quantityUnit = in.nextLine();
                    recipes.addRecipeIngredients(recipe, ingredientName, quantity, quantityUnit);
                }
            }
        }

        isFinished = false;
        while (!isFinished) {
            System.out.println("Please Input Instruction Step: <When done, type exit>");
            stepContent = in.nextLine();
            if (stepContent.equals("exit")) {
                isFinished = true;
            } else {
                recipes.addRecipeInstruction(recipe, stepNumber++, stepContent);
            }
        }
        System.out.println("Recipe Added Successfully!");
    }
}
