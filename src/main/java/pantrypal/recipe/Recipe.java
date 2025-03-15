package pantrypal.recipe;

import pantrypal.inventory.Ingredient;
import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<String> instructions;
    private ArrayList<String> ingredients;

    public Recipe(){

    }

    public Recipe(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
