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
}
