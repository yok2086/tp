package pantrypal.recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    private final ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeManager() {}

    public void addRecipe(String recipeName) {

        String[] parts = recipeName.split(" ", 2);
        if (parts.length > 1){
            System.out.println("Warning: Recipe name should not contain space. Use '_' instead.");
            return;
        }

        List<Recipe> filteredItems = recipes.stream()
                .filter(item -> recipeName.equals(item.getName()))
                .toList();

        if (!filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " already exists");
            return;
        }

        Recipe recipe = new Recipe(recipeName);
        recipes.add(recipe);
        System.out.println("Recipe added sucessfully" + recipe);
    }

    public void editRecipe(String command) {

        // Splitting into parts
        String[] parts = command.split(" ", 5);

        String recipeName = parts[0];

        List<Recipe> filteredItems = recipes.stream()
                .filter(item -> recipeName.equals(item.getName()))
                .toList();

        if (filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " does not exist");
            return;
        }

        Recipe recipe = filteredItems.get(0);

        if (parts[1].equals("instruction")){
            if (parts[2].equals("add")){
                addRecipeInstruction(recipe, parts[3], parts[4]);
            }
            else if (parts[2].equals("remove")){
                removeRecipeInstruction(recipe, parts[3]);
            }
            else if (parts[2].equals("edit")){
                editRecipeInstruction(recipe, parts[3], parts[4]);
            }
            else {
                //throw new Exception();
                System.out.println("Wrong editRecipe format!");
                System.out.println("Expected add/remove/edit after 'instruction'");
            }

        }
        else if (parts[1].equals("ingredient")) {
            if (parts[2].equals("add")){
                //Add function
                recipe.addIngredient(parts[3]);
            }
            else if (parts[2].equals("remove")){
                //Add function
                recipe.removeIngredient(parts[3]);
            }
            else if (parts[2].equals("edit")){
                recipe.editIngredient(parts[3], parts[4]);
            }
            else {
                System.out.println("Wrong ingredient format!");
                System.out.println("Expected add/remove/edit after 'ingredient'");
                //throw new Excpetion()
            }
        }
        else {
            System.out.println("Wrong editRecipe format!");
            System.out.println("Expected instruction/ingredient after 'editRecipe'");
            //throw new Exception();
        }

    }

    public void addRecipeInstruction(Recipe recipe, String stepString, String content) {
        try {
            int stepInt = Integer.parseInt(stepString);
            Instruction instruction = new Instruction(stepInt, content);
            recipe.addInstruction(instruction);
        }
        catch (NumberFormatException e) {
            System.out.println("Warning: Invalid instruction");
            System.out.println("The correct format is: editRecipe <recipe_name> instruction add <step> <content>\n" +
                    "Example: editRecipe fried_egg instruction add 3 'add salt'");
        }
    }

    public void editRecipeInstruction(Recipe recipe, String stepString, String content) {
        try{
            int stepInt = Integer.parseInt(stepString);
            Instruction instruction = recipe.getInstruction(stepInt);
            if (instruction == null) {
                System.out.println("There is no instruction for step " + stepInt);
                return;
            }

            String instructionContent = content;
            instruction.setInstruction(instructionContent);
        }
        catch (NumberFormatException e) {
            System.out.println("Warning: Invalid instruction");
            System.out.println("The correct format is: editRecipe <recipe_name> instruction edit <step> <content>\n" +
                    "Example: editRecipe fried_egg instruction edit 3 'add salt'");
        }
    }

    public void removeRecipeInstruction(Recipe recipe, String stepString) {
        try{
            int stepInt = Integer.parseInt(stepString);
            recipe.removeInstruction(stepInt);
        }
        catch (NumberFormatException e) {
            System.out.println("Warning: Invalid instruction");
            System.out.println("The correct format is: editRecipe <recipe_name> instruction remove <step>\n" +
                    "Example: editRecipe fried_egg instruction remove 3");
        }
    }

    public void listRecipe(){
        for (Recipe recipe : recipes) {
            System.out.println(recipe.toString());
        }
    }

    public void showRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream()
                .filter(item -> recipeName.equals(item.getName()))
                .toList();

        for (Recipe recipe : filteredItems) {
            System.out.println(recipe.getContent());
        }
    }

    public Recipe searchRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().
                filter(recipe -> recipeName.equals(recipe.getName()))
                .toList();

        if (filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " does not exist");
            return null;
        }
        else {
            return filteredItems.get(0);
        }
    }

    public void removeRecipe(String recipeName) {
        List<Recipe> filteredItems = recipes.stream().
                filter(recipe -> recipeName.equals(recipe.getName()))
                .toList();

        if (filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " does not exist");
        }
        else{
            recipes.remove(filteredItems.get(0));
        }
    }

    //For testing
    protected ArrayList<Recipe> getRecipeList() {
        return recipes;
    }

}
