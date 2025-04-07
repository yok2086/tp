package pantrypal.general.control;

import pantrypal.inventory.Category;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;
import pantrypal.mealplan.Day;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.mealplan.MealType;
import pantrypal.mealplan.Plan;
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

/**
 * The Storage class handles the loading and saving of data to a file.
 * It provides methods to create a file, load data from it, and save data to it.
 */
public class Storage {
    private static String filePath = "";
    private static File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file used for storage
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Creates a new file if it does not exist and loads data from the file if it exists.
     *
     * @param inventory the ingredient inventory
     * @param shoppingList the shopping list
     * @param plans the meal plan manager
     * @param recipeManager the recipe manager
     */
    public static void createFile(IngredientInventory inventory, ShoppingList shoppingList, MealPlanManager plans,
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
            Storage.loadData(inventory, shoppingList, plans, recipeManager);
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

    /**
     * Loads data from the file into the provided inventory, shopping list, meal plan manager, and recipe manager.
     *
     * @param inventory the ingredient inventory
     * @param shoppingList the shopping list
     * @param plans the meal plan manager
     * @param recipeManager the recipe manager
     */
    public static void loadData(IngredientInventory inventory, ShoppingList shoppingList, MealPlanManager plans,
                                RecipeManager recipeManager) {
        try (Scanner scanner = new Scanner(file)) {
            String currentSection = "";
            Recipe recipe = null;
            Plan plan = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    if (line.startsWith("[Shopping]")) {
                        currentSection = "Shopping";
                        processShoppingLine(line, shoppingList);
                    } else if (line.startsWith("[Recipe]")) {
                        currentSection = "Recipe";
                        recipe = processRecipeLine(line, recipeManager);
                    } else if (line.startsWith("[Stock]")) {
                        currentSection = "Stock";
                        processStockLine(line, inventory);
                    } else if (line.startsWith("[LowStock]")) {
                        currentSection = "LowStock";
                        processLowStockLine(line, inventory);
                    } else if (line.startsWith("[MealPlan]")) {
                        currentSection = "MealPlan";
                        plan = processMealPlanLine(line, plans);
                    } else if (line.startsWith("[WeeklyPlan]")) {
                        currentSection = "WeeklyPlan";
                    } else {
                        processSectionLine(currentSection, line, recipe, plan, plans, recipeManager);
                    }
                } catch (DataCorruptionException e) {
                    Ui.printErrorMessage("Data corruption detected: " + e.getMessage());
                    Ui.printErrorMessage("Skipping corrupted entry and continuing with next line...");
                }
            }
        } catch (IOException e) {
            Ui.printErrorMessage("Error reading save file: " + e.getMessage());
            Ui.printErrorMessage("Starting with empty data...");
        }
    }

    /**
     * Processes a line from the shopping section of the file.
     *
     * @param line the line to process
     * @param shoppingList the shopping list
     */
    private static void processShoppingLine(String line, ShoppingList shoppingList) {
        try {
            String[] shoppingListItem = line.substring("[Shopping]".length()).trim().split(" ");
            if (shoppingListItem.length != 4) {
                throw new DataCorruptionException("Invalid shopping list item format");
            }
            String name = unescapeSpecialCharacters(shoppingListItem[0]);
            double quantity = Double.parseDouble(shoppingListItem[1]);
            Unit unit = Unit.parseUnit(shoppingListItem[2]);
            boolean isPurchased = Boolean.parseBoolean(shoppingListItem[3]);
            shoppingList.addItem(new ShoppingListItem(name, quantity, unit));
            if (isPurchased) {
                shoppingList.markItemAsPurchased(name);
            }
        } catch (NumberFormatException e) {
            throw new DataCorruptionException("Invalid quantity format in shopping list");
        } catch (IllegalArgumentException e) {
            throw new DataCorruptionException("Invalid unit or category in shopping list");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataCorruptionException("Invalid arguments in shopping list item");
        }
    }

    /**
     * Processes a line from the recipe section of the file.
     *
     * @param line the line to process
     * @param recipeManager the recipe manager
     * @return the processed recipe
     */
    private static Recipe processRecipeLine(String line, RecipeManager recipeManager) {
        try {
            String recipeName = unescapeSpecialCharacters(line.substring("[Recipe]".length()).trim());
            if (recipeName.isEmpty()) {
                throw new DataCorruptionException("Empty recipe name");
            }
            return recipeManager.addRecipe(recipeName);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DataCorruptionException("Invalid recipe line format");
        }
    }

    /**
     * Processes a line from the stock section of the file.
     *
     * @param line the line to process
     * @param inventory the ingredient inventory
     */
    private static void processStockLine(String line, IngredientInventory inventory) {
        try {
            line = line.substring("[Stock]".length()).trim();
            String[] stockItem = line.split(" ");
            if (stockItem.length != 4) {
                throw new DataCorruptionException("Invalid stock item format");
            }
            String stockName = unescapeSpecialCharacters(stockItem[0]);
            double stockQuantity = Double.parseDouble(stockItem[1]);
            Unit stockUnit = Unit.parseUnit(stockItem[2]);
            Category stockCategory = Category.parseCategory(stockItem[3]);
            inventory.addNewIngredient(stockName, stockQuantity, stockUnit, stockCategory);
        } catch (NumberFormatException e) {
            throw new DataCorruptionException("Invalid quantity format in stock");
        } catch (IllegalArgumentException e) {
            throw new DataCorruptionException("Invalid unit or category in stock");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataCorruptionException("Invalid arguments in stock item");
        }
    }

    /**
     * Processes a line from the low stock section of the file.
     *
     * @param line the line to process
     * @param inventory the ingredient inventory
     */
    private static void processLowStockLine(String line, IngredientInventory inventory) {
        try {
            line = line.substring("[LowStock]".length()).trim();
            String[] lowStockItem = line.split(" ");
            String lowStockName = unescapeSpecialCharacters(lowStockItem[0]);
            double lowStockQuantity = Double.parseDouble(lowStockItem[1]);
            inventory.setAlert(lowStockName, lowStockQuantity);
        } catch (NumberFormatException e) {
            throw new DataCorruptionException("Invalid quantity format in stock");
        } catch (IllegalArgumentException e) {
            throw new DataCorruptionException("Invalid unit or category in stock");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataCorruptionException("Invalid arguemtns in stock item");
        }
    }

    /**
     * Processes a line from the specified section of the file.
     *
     * @param currentSection the current section being processed
     * @param line the line to process
     * @param recipe the current recipe being processed
     * @param plan the current plan being processed
     * @param plans the meal plan manager
     * @param recipeManager the recipe manager
     */
    private static void processSectionLine(String currentSection, String line, Recipe recipe, Plan plan,
                                           MealPlanManager plans, RecipeManager recipeManager) {
        switch (currentSection) {
        case "Recipe":
            if (line.startsWith("[Ingredients]")) {
                processRecipeIngredients(line, recipe);
            } else if (line.startsWith("[Instructions]")) {
                processRecipeInstructions(line, recipe);
            }
            break;
        case "MealPlan":
            processMealPlanDetails(line, plan, plans, recipeManager);
            break;
        case "WeeklyPlan":
            processWeeklyPlans(line, plans);
            break;
        default:
            System.out.println("Unknown data: " + line);
        }
    }

    /**
     * Processes the ingredients of a recipe from a line in the file.
     *
     * @param line the line to process
     * @param recipe the recipe to add ingredients to
     */
    private static void processRecipeIngredients(String line, Recipe recipe) {
        line = line.substring("[Ingredients]".length()).trim();
        String[] ingredients = line.trim().split(" \\|");
        for (String ingredient : ingredients) {
            String[] parts = ingredient.trim().split(" ");
            if (parts.length > 1) {
                String ingredientName = unescapeSpecialCharacters(parts[0]);
                double ingredientQuantity = Double.parseDouble(parts[1]);
                Unit ingredientUnit = Unit.parseUnit(parts[2]);
                Category ingredientCategory = Category.valueOf(parts[3].toUpperCase());
                recipe.addIngredient(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit,
                        ingredientCategory));
            }
        }
    }

    /**
     * Processes the instructions of a recipe from a line in the file.
     *
     * @param line the line to process
     * @param recipe the recipe to add instructions to
     */
    private static void processRecipeInstructions(String line, Recipe recipe) {
        line = line.substring("[Instructions]".length()).trim();
        String[] instructions = line.trim().split(" \\|");
        int step = 1;
        for (String instruction : instructions) {
            Instruction instructionObject = new Instruction(step++, unescapeSpecialCharacters(instruction.trim()));
            recipe.addInstruction(instructionObject);
        }
    }

    /**
     * Saves the ingredients of a recipe to a StringBuilder.
     *
     * @param ingredients the list of ingredients
     * @param fileInput the StringBuilder to append to
     * @return the updated StringBuilder
     */
    private static StringBuilder saveRecipeIngredients(ArrayList<Ingredient> ingredients, StringBuilder fileInput) {
        fileInput.append("[Ingredients] ");
        for (Ingredient ingredient : ingredients) {
            fileInput.append(escapeSpecialCharacters(ingredient.getName())).append(" ")
                    .append(ingredient.getQuantity()).append(" ")
                    .append(ingredient.getUnit()).append(" ")
                    .append(ingredient.getCategory()).append(" | ");
        }
        fileInput.append("\n");
        return fileInput;
    }

    /**
     * Saves the instructions of a recipe to a StringBuilder.
     *
     * @param instructions the list of instructions
     * @param fileInput the StringBuilder to append to
     * @return the updated StringBuilder
     */
    private static StringBuilder saveRecipeInstructions(ArrayList<Instruction> instructions, StringBuilder fileInput) {
        fileInput.append("[Instructions] ");
        for (Instruction instruction : instructions) {
            fileInput.append(escapeSpecialCharacters(instruction.getInstruction())).append(" | ");
        }
        fileInput.append("\n");
        return fileInput;
    }

    /**
     * Saves the meal plans to a StringBuilder.
     *
     * @param recipeManager the recipe manager
     * @param plans the meal plan manager
     * @param fileInput the StringBuilder to append to
     */
    private static void saveMealPlans(RecipeManager recipeManager, MealPlanManager plans,
                                      StringBuilder fileInput) {
        for (Plan plan : plans.getPlanList()) {
            Recipe[] planRecipes = plan.getPlanRecipes();
            fileInput.append("[MealPlan] ").append(plan.getPlanName()).append("\n")
                    .append("[Breakfast] ")
                    .append(planRecipes[0] != null ? recipeManager.getRecipeIndex(planRecipes[0]) : "null")
                    .append("\n")
                    .append("[Lunch] ")
                    .append(planRecipes[1] != null ? recipeManager.getRecipeIndex(planRecipes[1]) : "null")
                    .append("\n")
                    .append("[Dinner] ")
                    .append(planRecipes[2] != null ? recipeManager.getRecipeIndex(planRecipes[2]) : "null")
                    .append("\n");
        }
    }

    /**
     * Processes the weekly plans from a line in the file.
     *
     * @param line the line to process
     * @param plans the meal plan manager
     */
    private static void processWeeklyPlans(String line, MealPlanManager plans) {
        String[] parts = line.trim().split(" ");
        if (parts.length != 2) {
            throw new DataCorruptionException("Invalid weekly plan format");
        }
        String dayName = parts[0].substring(1, parts[0].length() - 1);
        if (parts[1].equals("null")) {
            return;
        }
        Day day = Day.valueOf(dayName.toUpperCase());
        int planIndex = Integer.parseInt(parts[1]);
        if (planIndex < 0 || planIndex >= plans.getPlanList().length) {
            throw new DataCorruptionException("Invalid plan index in weekly plan");
        }
        plans.addPlanToDay(planIndex, day);
    }

    /**
     * Saves the weekly plans to a StringBuilder.
     *
     * @param plans the meal plan manager
     * @param fileInput the StringBuilder to append to
     */
    private static void saveWeeklyPlans(MealPlanManager plans, StringBuilder fileInput) {
        fileInput.append("[WeeklyPlan] \n");
        for (int i = 0; i < plans.getWeeklyPlans().length; i++) {
            Plan plan = plans.getWeeklyPlans()[i];
            if (plan != null) {
                fileInput.append("[").append(Day.values()[i].name()).append("]").append(" ")
                        .append(plans.getPlanIndex(plan.getPlanName())).append("\n");
            }
        }
    }

    /**
     * Processes a line from the meal plan section of the file.
     *
     * @param line the line to process
     * @param plans the meal plan manager
     * @return the processed plan
     */
    private static Plan processMealPlanLine(String line, MealPlanManager plans) {
        String[] parts = line.substring("[MealPlan]".length()).trim().split(" ");
        if (parts.length != 1) {
            throw new DataCorruptionException("Invalid meal plan format");
        }
        String planName = unescapeSpecialCharacters(parts[0]);
        plans.addPlanToList(planName);
        return new Plan(planName);
    }

    /**
     * Processes the details of a meal plan from a line in the file.
     *
     * @param line the line to process
     * @param plan the current plan being processed
     * @param plans the meal plan manager
     * @param recipeManager the recipe manager
     */
    public static void processMealPlanDetails(String line, Plan plan, MealPlanManager plans,
                                              RecipeManager recipeManager) {
        int recipeIndex = 0;
        String[] parts = line.trim().split(" ");
        if (line.startsWith("[Breakfast]") || line.startsWith("[Lunch]") || line.startsWith("[Dinner]")) {
            if (parts.length != 2) {
                throw new DataCorruptionException("Invalid meal plan details format");
            }
            if (!parts[1].equals("null")) {
                recipeIndex = Integer.parseInt(parts[1]);
            }
            if (recipeIndex < 0) {
                throw new DataCorruptionException("Invalid recipe index in meal plan");
            }
        }

        if (parts[1].equals("null")) {
            return;
        }

        if (line.startsWith("[Breakfast]")) {
            plans.getPlanDetails(plan.getPlanName()).addRecipeToPlan(recipeManager.getRecipeList().get(recipeIndex),
                    MealType.BREAKFAST);
        } else if (line.startsWith("[Lunch]")) {
            plans.getPlanDetails(plan.getPlanName()).addRecipeToPlan(recipeManager.getRecipeList().get(recipeIndex),
                    MealType.LUNCH);
        } else if (line.startsWith("[Dinner]")) {
            plans.getPlanDetails(plan.getPlanName()).addRecipeToPlan(recipeManager.getRecipeList().get(recipeIndex),
                    MealType.DINNER);
        } else {
            throw new DataCorruptionException("Invalid meal plan details format");
        }
    }


    /**
     * Saves the data from the provided inventory, shopping list, meal plan manager, and recipe manager to the file.
     *
     * @param inventory the ingredient inventory
     * @param shoppingList the shopping list
     * @param plans the meal plan manager
     * @param recipeManager the recipe manager
     */
    public static void saveData(IngredientInventory inventory, ShoppingList shoppingList, MealPlanManager plans,
                                RecipeManager recipeManager) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            StringBuilder fileInput = new StringBuilder();

            for (ShoppingListItem item : shoppingList.getItems()) {
                fileInput.append("[Shopping] ").append(escapeSpecialCharacters(item.getIngredientName())).append(" ")
                        .append(item.getQuantity()).append(" ")
                        .append(item.getUnit()).append(" ").append(item.isPurchased()).append("\n");
            }

            for (Map.Entry<String, Ingredient> ingredient : inventory.getInventory().entrySet()) {
                Ingredient item = ingredient.getValue();
                fileInput.append("[Stock] ").append(escapeSpecialCharacters(item.getName())).append(" ")
                        .append(item.getQuantity()).append(" ").append(item.getUnit()).append(" ")
                        .append(item.getCategory().toString().toUpperCase()).append("\n");
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

            saveMealPlans(recipeManager, plans, fileInput);
            saveWeeklyPlans(plans, fileInput);

            fileWriter.write(fileInput.toString());
        } catch (IOException e) {
            Ui.printErrorMessage(e.getMessage());
        }
    }
}
