package pantrypal.general.commands.recipe;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Category;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.time.LocalDate;
import java.util.Scanner;

public class AddRecipe extends RecipeCommand {
    private String recipeName;
    private String stepContent;
    private String content;
    private LocalDate expiryDate;

    public AddRecipe() {
        super("addRecipe", "Add a recipe");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        boolean isFinished = false;
        boolean isValidRecipe = false;

        Recipe recipe = null;
        while (recipe == null) {
            do {
                System.out.println("Please Input Recipe Name: ");
                recipeName = in.nextLine();
            } while (recipeName.trim().isEmpty());
            recipe = recipes.addRecipe(recipeName.trim().toUpperCase());
        }

        int stepNumber = 1;
        while (!isFinished) {
            System.out.println("Please Input Ingredient Name: <When done, type exit>");
            String ingredientName = in.nextLine().trim().toUpperCase();
            if (ingredientName.isBlank()){
                Ui.printErrorMessage("Ingredient Name cannot be blank. Try again");
                continue;
            }

            if (ingredientName.equals("EXIT")) {
                isFinished = true;
                continue;
            }

            int quantity = 0;
            Unit unit = null;
            Category category = null;

            try {
                System.out.println("Please Input Ingredient Quantity:");
                quantity = Integer.parseInt(in.nextLine());

                if (quantity <= 0) {
                    throw new ArithmeticException("Quantity must be greater than 0.");
                }

                System.out.println("Please Input Quantity Unit:");
                String quantityUnit = in.nextLine();
                unit = Unit.parseUnit(quantityUnit);

                while (category == null) {  // Keep prompting if category is invalid
                    try {
                        System.out.println("Please Input Ingredient Category:");
                        String categoryText = in.nextLine().trim().toUpperCase();
                        category = Category.parseCategory(categoryText);
                    } catch (IllegalArgumentException e) {
                        Ui.printErrorMessage("Invalid category! Try again.");
                    }
                }
            } catch (NumberFormatException e) {
                Ui.printErrorMessage("Invalid ingredient quantity! Try again.");
                continue;
            } catch (ArithmeticException e) {
                Ui.printErrorMessage(e.getMessage() + "Try again.");
                continue;
            } catch (IllegalArgumentException e) {
                Ui.printErrorMessage("Invalid ingredient unit! Try again.");
                continue;
            }

            recipes.addRecipeIngredients(recipe, ingredientName, quantity, unit, category);
        }

        isFinished = false;
        while (!isFinished) {
            System.out.println("Please Input Instruction Step: <When done, type exit>");
            stepContent = in.nextLine();
            if (stepContent.isBlank()) {
                Ui.printErrorMessage("Instruction cannot be blank. Try again.");
                continue;
            }
            if (stepContent.equals("exit")) {
                isFinished = true;
            } else {
                recipes.addRecipeInstruction(recipe, stepNumber++, stepContent);
            }
        }
        System.out.println("Recipe Added Successfully!");
    }
}
