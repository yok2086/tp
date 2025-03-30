package pantrypal.general.control;

import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;
import pantrypal.mealplan.PlanPresets;
import pantrypal.recipe.Instruction;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Storage {
    private static String filePath = "";
    private static File file;


    public Storage(String filePath) {
        Storage.filePath = filePath;
        file = new File(filePath);
    }


    public static void createFile(IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets,
                                  RecipeManager recipeManager) {
        if (file.getParentFile().mkdirs()) {
            System.out.println("Save file directory not found");
            System.out.println("Save file directory created");
        }

        Boolean safeFileExists = false;
        try {
            if (file.createNewFile()) {
                System.out.println("Save file created");
            } else {
                System.out.println("Save file already exists. Loading from file...");
                safeFileExists = true;
            }
        } catch (IOException e) {
            Ui.printErrorMessage(e.getMessage());
        }

        if (safeFileExists) {
            Storage.loadData(inventory, shoppingList, planPresets, recipeManager);
        }

        Ui.printLine();
    }

    // Escape and unescape methods to handle "|"
    private static String escapeSpecialCharacters(String input) {
        return input.replace("|", "\\|");
    }

    private static String unescapeSpecialCharacters(String input) {
        return input.replace("\\|", "|");
    }

    public static void loadData(IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets,
                                RecipeManager recipeManager) {
        try (Scanner scanner = new Scanner(file)) {
            String currentSection = "";
            Recipe recipe = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Identify sections
                if (line.startsWith("[Shopping]")) {
                    currentSection = "Shopping";
                    String[] shoppingListItem = line.substring("[Shopping]".length()).trim().split(" ");
                    String name = unescapeSpecialCharacters(shoppingListItem[0]);
                    double quantity = Double.parseDouble(shoppingListItem[1]);
                    Unit unit = Unit.parseUnit(shoppingListItem[2]);
                    shoppingList.addItem(new ShoppingListItem(name, quantity, unit));
                } else if (line.startsWith("[Recipe]")) {
                    currentSection = "Recipe";
                    String recipeName = unescapeSpecialCharacters(line.substring("[Recipe]".length()).trim());
                    recipe = recipeManager.addRecipe(recipeName);
                } else if (line.startsWith("[Stock]")) {
                    currentSection = "Stock";
                    line = line.substring("[Stock]".length()).trim();
                    String[] stockItem = line.split(" ");
                    String stockName = unescapeSpecialCharacters(stockItem[0]);
                    double stockQuantity = Double.parseDouble(stockItem[1]);
                    Unit stockUnit = Unit.parseUnit(stockItem[2]);
                    inventory.addNewIngredient(stockName, stockQuantity, stockUnit);
                } else if (line.startsWith("[LowStock]")) {
                    currentSection = "LowStock";
                    line = line.substring("[LowStock]".length()).trim();
                    String[] lowStockItem = line.split(" ");
                    String lowStockName = unescapeSpecialCharacters(lowStockItem[0]);
                    double lowStockQuantity = Double.parseDouble(lowStockItem[1]);
                    inventory.setAlert(lowStockName, lowStockQuantity);
                } else if (line.startsWith("[MealPlan]")) {
                    currentSection = "MealPlan";
                } else {
                    switch (currentSection) {
                    case "Recipe":
                        if (line.startsWith("[Ingredients]")) {
                            line = line.substring("[Ingredients]".length()).trim();
                            String[] ingredients = line.trim().split(" \\|");
                            for (String ingredient : ingredients) {
                                String[] parts = ingredient.trim().split(" ");
                                String ingredientName = unescapeSpecialCharacters(parts[0]);
                                double ingredientQuantity = Double.parseDouble(parts[1]);
                                Unit ingredientUnit = Unit.parseUnit(parts[2]);
                                recipe.addIngredient(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
                            }
                        } else if (line.startsWith("[Instructions]")) {
                            line = line.substring("[Instructions]".length()).trim();
                            String[] instructions = line.trim().split(" \\|");
                            int step = 1;
                            for (String instruction : instructions) {
                                Instruction instructionObject = new Instruction(step++, unescapeSpecialCharacters(instruction.trim()));
                                recipe.addInstruction(instructionObject);
                            }
                        }
                        break;
                    default:
                        System.out.println("Unknown data: " + line);
                    }
                }
            }
        } catch (IOException e) {
            Ui.printErrorMessage(e.getMessage());
        }
    }

    private static StringBuilder saveRecipeIngredients(ArrayList<Ingredient> ingredients, StringBuilder fileInput) {
        fileInput.append("[Ingredients] ");
        for (Ingredient ingredient : ingredients) {
            fileInput.append(escapeSpecialCharacters(ingredient.getName())).append(" ")
                    .append(ingredient.getQuantity()).append(" ")
                    .append(ingredient.getUnit()).append(" | ");
        }
        fileInput.append("\n");
        return fileInput;
    }

    private static StringBuilder saveRecipeInstructions(ArrayList<Instruction> instructions, StringBuilder fileInput) {
        fileInput.append("[Instructions] ");
        for (Instruction instruction : instructions) {
            fileInput.append(escapeSpecialCharacters(instruction.getInstruction())).append(" | ");
        }
        fileInput.append("\n");
        return fileInput;
    }

    public static void saveData(IngredientInventory inventory, ShoppingList shoppingList, PlanPresets planPresets,
                                RecipeManager recipeManager) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            StringBuilder fileInput = new StringBuilder();

            for (ShoppingListItem item : shoppingList.getItems()) {
                fileInput.append("[Shopping] ").append(escapeSpecialCharacters(item.getIngredientName())).append(" ")
                        .append(item.getQuantity()).append(" ")
                        .append(item.getUnit()).append("\n");
            }

            for (Map.Entry<String, Ingredient> ingredient : inventory.getInventory().entrySet()) {
                Ingredient item = ingredient.getValue();
                fileInput.append("[Stock] ").append(escapeSpecialCharacters(item.getName())).append(" ")
                        .append(item.getQuantity()).append(" ").append(item.getUnit()).append("\n");
            }

            for (Map.Entry<String, Double> lowStockItem : inventory.getLowStockAlerts().entrySet()) {
                String lowStockName = escapeSpecialCharacters(lowStockItem.getKey());
                double lowStockQuantity = lowStockItem.getValue();
                fileInput.append("[LowStock] ").append(lowStockName).append(" ").append(lowStockQuantity).append("\n");
            }

            for (Recipe recipe : recipeManager.getRecipeList()) {
                String recipeName = escapeSpecialCharacters(recipe.getName());
                ArrayList<Ingredient> ingredients = recipe.getIngredients();
                ArrayList<Instruction> instructions = recipe.getInstructions();
                fileInput.append("[Recipe] ").append(recipeName).append("\n");
                fileInput = saveRecipeIngredients(ingredients, fileInput);
                fileInput = saveRecipeInstructions(instructions, fileInput);
            }
            fileWriter.write(fileInput.toString());
        } catch (IOException e) {
            Ui.printErrorMessage(e.getMessage());
        }
    }
}
