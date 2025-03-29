package pantrypal.recipe;

import pantrypal.inventory.Ingredient;
import pantrypal.inventory.Unit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecipeManager{

    private final ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeManager() {
    }


    public Recipe addRecipe(String recipeName) {

        String[] parts = recipeName.split(" ", 2);
        if (parts.length > 1) {
            System.out.println("Warning: Recipe name should not contain space. Use '_' instead.");
            return null;
        }

        List<Recipe> filteredItems = recipes.stream().filter(
                item -> recipeName.equals(item.getName())).toList();

        if (!filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " already exists");
            return null;
        }

        Recipe recipe = new Recipe(recipeName);
        recipes.add(recipe);
        return recipe;
    }

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
            case "add" -> addRecipeIngredients(recipe, parts[3], Integer.parseInt(parts[4]), Unit.parseUnit(parts[5]));
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

    public void addRecipeIngredients(Recipe recipe, String ingredientName, int quantity, Unit unit) {
        try{
            Ingredient ingredient = new Ingredient(ingredientName, quantity, unit);
            recipe.addIngredient(ingredient);
        } catch (Exception e){
            System.out.println("Warning: Invalid ingredient " + ingredientName);
            System.out.println("The correct format is: ");
        }
    }

    public void addRecipeIngredients(Recipe recipe, String ingredientName, int quantity, Unit unit,
                                     LocalDate expiryDate) {
        try{
            Ingredient ingredient = new Ingredient(ingredientName, quantity, unit, expiryDate);
            recipe.addIngredient(ingredient);
        } catch (Exception e){
            System.out.println("Warning: Invalid ingredient " + ingredientName);
            System.out.println("The correct format is: ");
        }
    }


    public void editRecipeIngredients(Recipe recipe, String ingredientName,
                                      String newName, int newQuantity, Unit newUnit) {
        try {
            Ingredient ingredient = recipe.getIngredient(ingredientName);
            if (ingredient == null) {
                System.out.println("There is no ingredient of name " + ingredientName);
                return;
            }

            ingredient.setName(newName);
            ingredient.setQuantity(newQuantity);
            ingredient.setUnit(newUnit);

        } catch (Exception e) {
            System.out.println("Warning: Invalid instruction");
        }
    }

    public void addRecipeInstruction(Recipe recipe, int step, String content) {
        try {
            Instruction instruction = new Instruction(step, content);
            recipe.addInstruction(instruction);
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid instruction");
            System.out.println("The correct format is: <content>\n");
        }
    }

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

    public void showRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().filter(item -> recipeName.equals(item.getName())).toList();

        for (Recipe recipe : filteredItems) {
            System.out.println(recipe.getContent());
        }
    }

    public Recipe searchRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().
                filter(recipe -> recipeName.equals(recipe.getName())).toList();

        if (filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " does not exist");
            return null;
        } else {
            return filteredItems.get(0);
        }
    }

    public void removeRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().filter(recipe -> recipeName.
                equals(recipe.getName())).toList();

        if (filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " does not exist");
        } else {
            recipes.remove(filteredItems.get(0));
        }
    }

    public void copyList(ArrayList<Recipe> recipes) {
        this.recipes.addAll(recipes);
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipes;
    }

}
