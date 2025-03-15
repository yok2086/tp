package pantrypal.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeManager {

    private final ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeManager() {}

    public void addRecipe(String recipeName) {

        List<Recipe> filteredItems = recipes.stream()
                .filter(item -> recipeName.equals(item.getName()))
                .toList();

        if (!filteredItems.isEmpty()) {
            System.out.println("Warning: Recipe " + recipeName + " already exists");
            return;
        }

        Recipe recipe = new Recipe(recipeName);
        recipes.add(recipe);
        System.out.println("Recipe added sucessfully" + recipe.toString());
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
                //Add function
            }
            else if (parts[2].equals("remove")){
                //Add function
            }
            else {
                //throw new Exception();
            }

        }
        else if (parts[1].equals("ingredient")) {
            if (parts[2].equals("add")){
                //Add function
            }
            else if (parts[2].equals("remove")){
                //Add function
            }
            else {
                //throw new Excpetion()
            }
        }
        else {
            //throw new Exception();
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
