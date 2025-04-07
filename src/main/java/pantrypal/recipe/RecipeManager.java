package pantrypal.recipe;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Category;
import pantrypal.inventory.Ingredient;
import pantrypal.inventory.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * The central manager that manages all methods related to Recipe
 */
public class RecipeManager{

    private final ArrayList<Recipe> recipes = new ArrayList<>();

    /**
     * Constructor for the RecipeManager class
     */
    public RecipeManager() {
    }

    /**
     * Add a recipe into the recipe repository
     * @param recipeName the name of the recipe to be added
     * @return the newly created recipe object
     */
    public Recipe addRecipe(String recipeName) {

        String[] parts = recipeName.split(" ", 2);
        if (parts.length > 1) {
            Ui.showMessage("Warning: Recipe name should not contain space. Use '_' instead.");
            return null;
        }

        List<Recipe> filteredItems = recipes.stream().filter(
                item -> recipeName.equals(item.getName())).toList();

        if (!filteredItems.isEmpty()) {
            Ui.showMessage("Warning: Recipe " + recipeName + " already exists");
            return null;
        }

        Recipe recipe = new Recipe(recipeName);
        recipes.add(recipe);
        return recipe;
    }

    /**
     * Edit recipe by custom command. Unused up to this version
     * @param command the custom string command
     */
    public void editRecipe(String command) {

        // Splitting into parts
        String[] parts = command.split(" ", 7);

        String recipeName = parts[0];

        List<Recipe> filteredItems = recipes.stream().filter(
                item -> recipeName.equals(item.getName())).toList();

        if (filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " does not exist");
            return;
        }

        Recipe recipe = filteredItems.get(0);

        if (parts[1].equals("instruction")) {
            switch (parts[2]) {
            case "add" -> addRecipeInstruction(recipe, Integer.parseInt(parts[3]), parts[4] + parts[5]);
            case "remove" -> removeRecipeInstruction(recipe, parts[3]);
            case "edit" -> editRecipeInstruction(recipe, parts[3], parts[4] + parts[5]);
            default -> {
                //throw new Exception();
                System.out.println("Wrong editRecipe format!");
                System.out.println("Expected add/remove/edit after 'instruction'");
            }
            }

        } else if (parts[1].equals("ingredient")) {
            //Command structure: edit ingredient add <name> <quantity> <unit>
            switch (parts[2]) {
            case "add" -> addRecipeIngredients(recipe, parts[3], Integer.parseInt(parts[4]), Unit.parseUnit(parts[5]),
                    Category.parseCategory(parts[6]));
            case "remove" -> recipe.removeIngredient(parts[3]);
            //case "edit" -> recipe.editIngredient(parts[3], parts[4]);

            default -> {
                System.out.println("Wrong ingredient format!");
                System.out.println("Expected add/remove/edit after 'ingredient'");
            }
            //throw new Exception()
            }
        } else {
            System.out.println("Wrong editRecipe format!");
            System.out.println("Expected instruction/ingredient after 'editRecipe'");
            //throw new Exception();
        }

    }

    /**
     * Add an ingredient to a recipe
     * @param recipe the recipe object to have an ingredient added
     * @param ingredientName the name of the ingredient to be added
     * @param quantity the quanitity of the ingredient to be added
     * @param unit the unit of measurement for the ingredient
     * @param category the category that the ingredient falls in
     */
    public void addRecipeIngredients(Recipe recipe, String ingredientName, int quantity, Unit unit, Category category) {

        if (recipe == null) {
            return;
        }

        assert quantity > 0 : "Quantity must be positive";

        List<Ingredient> ingredientFilteredList = recipe.getIngredients().stream()
                .filter(i -> i.getName().equals(ingredientName)).toList();
        if (!ingredientFilteredList.isEmpty()) {
            System.out.println("Warning: Recipe " + recipe.getName() + " already exists");
            return;
        }

        try{
            Ingredient ingredient = new Ingredient(ingredientName, quantity, unit, category);
            recipe.addIngredient(ingredient);
        } catch (Exception e){
            System.out.println("Warning: Invalid ingredient " + ingredientName);
        }
    }

    /**
     * Edit an ingredient of a recipe
     * @param recipe the recipe object that has an ingredient being edited
     * @param ingredientName the name of the ingredient to be edited
     * @param newName new name of the ingredient
     * @param newQuantity new quanitity for the ingredient
     * @param newUnit new unit of measurement for the ingredient
     */
    public void editRecipeIngredients(Recipe recipe, String ingredientName,
                                      String newName, int newQuantity, Unit newUnit) {
        try {
            Ingredient ingredient = recipe.getIngredient(ingredientName);
            if (ingredient == null) {
                System.out.println("There is no ingredient of name " + ingredientName);
                return;
            }

            assert newQuantity > 0 : "Quantity must be positive";

            ingredient.setName(newName);
            ingredient.setQuantity(newQuantity);
            ingredient.setUnit(newUnit);

        } catch (Exception e) {
            System.out.println("Warning: Invalid instruction");
        }
    }

    /**
     * Add an instruction to a specified recipe
     * @param recipe the recipe object that needs their instruction edited
     * @param step new instruction step number
     * @param content new instruction content
     */
    public void addRecipeInstruction(Recipe recipe, int step, String content) {
        assert step > 0 : "Step must be positive";

        try {
            Instruction instruction = new Instruction(step, content);
            recipe.addInstruction(instruction);
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid instruction");
            System.out.println("The correct format is: <content>\n");
        }
    }

    /**
     * Edit the recipe instruction. Unused in this version
     * @param recipe the recipe object that needs their instruction modified
     * @param stepString new step number
     * @param content new instruction content
     */
    public void editRecipeInstruction(Recipe recipe, String stepString, String content) {
        try {
            int stepInt = Integer.parseInt(stepString);
            Instruction instruction = recipe.getInstruction(stepInt);
            if (instruction == null) {
                System.out.println("There is no instruction for step " + stepInt);
                return;
            }

            instruction.setInstruction(content);
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid instruction");
            System.out.println("The correct format is: editRecipe <recipe_name> instruction edit <step> <content>\n" +
                    "Example: editRecipe fried_egg instruction edit 3 'add salt'");
        }
    }

    /**
     * Remove an instruction from a recipe
     * @param recipe the recipe object that has their instruction removed
     * @param stepString the step number at which the instruction needs to be removed
     */
    public void removeRecipeInstruction(Recipe recipe, String stepString) {
        try {
            int stepInt = Integer.parseInt(stepString);
            recipe.removeInstruction(stepInt);
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid instruction");
            System.out.println("The correct format is: editRecipe <recipe_name> instruction remove <step>\n" +
                    "Example: editRecipe fried_egg instruction remove 3");
        }
    }

    /**
     * Remove an ingredient from the recipe
     * @param recipe the recipe object to have their ingredient removed
     * @param ingredientName the name of the ingredient to be removed
     */
    public void removeRecipeIngredient(Recipe recipe, String ingredientName) {
        recipe.removeIngredient(ingredientName);
    }

    /**
     * List all recipe names available in the list
     */
    public void listRecipe() {
        if (recipes.isEmpty()) {
            System.out.println("There are no recipes at the moment. You can add via addRecipe");
        }
        int index = 1;
        for (Recipe recipe : recipes) {
            System.out.print(index++ + ". ");
            System.out.println(recipe.toString());
        }
    }

    /**
     * Show all information of a specified recipe
     * @param recipeName the name of the recipe to have their information shown
     */
    public void showRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().filter(item -> recipeName.equals(item.getName())).toList();

        if (filteredItems.isEmpty()) {
            System.out.println("There is no recipe with name " + recipeName);
            return;
        }

        for (Recipe recipe : filteredItems) {
            Ui.printLine();
            System.out.println(recipe.getContent());
            Ui.printLine();
        }
    }

    /**
     * Search a recipe by their name
     * @param recipeName the name of the recip to be searched
     * @return the recipe object that has a specified name
     */
    public Recipe searchRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().
                filter(recipe -> recipeName.equals(recipe.getName())).toList();

        if (filteredItems.isEmpty()) {
            Ui.showMessage("Warning: Recipe " + recipeName + " does not exist");
            return null;
        } else {
            return filteredItems.get(0);
        }
    }

    /**
     * Remove a recipe from the list
     * @param recipeName the name of the recipe to be removed
     */
    public void removeRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().filter(recipe -> recipeName.
                equals(recipe.getName())).toList();

        if (filteredItems.isEmpty()) {
            Ui.showMessage("Warning: Recipe " + recipeName + " does not exist");
        } else {
            recipes.remove(filteredItems.get(0));
            Ui.showMessage("Recipe " + recipeName + " has been removed");
        }
    }

    /**
     * Copy the recipes from a list to this manager's list. Used to load in data from the external dataset.
     * @param recipes the list of recipe to be copied into the manager's list
     */
    public void copyList(ArrayList<Recipe> recipes) {
        this.recipes.addAll(recipes);
    }

    /**
     * Used mainly for debugging and testing
     * @return the whole recipe list
     */
    public ArrayList<Recipe> getRecipeList() {
        return recipes;
    }

    /**
     * For use in MealPlan
     * @return the corresponding index in recipe list
     */
    public Recipe getRecipe(int recipeIndex) {
        return recipes.get(recipeIndex);
    }

    /**
     * Get the index of a specified recipe object
     * @param recipe the recipe object to be found
     * @return the index of the recipe in the list
     */
    public int getRecipeIndex(Recipe recipe) {
        int index = recipes.indexOf(recipe);
        if (index == -1) {
            Ui.showMessage("Recipe not found");
            return -1;
        }
        return index;
    }

}


