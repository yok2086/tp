package pantrypal.recipe;

import pantrypal.inventory.Ingredient;
import pantrypal.inventory.Unit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Recipe {

    private static final String LINE = "_".repeat(50);

    private String name;
    private ArrayList<Instruction> instructions = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();


    public Recipe(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public boolean addInstruction(Instruction instruction) {
        if (instructions.contains(instruction)) {
            System.out.println("Error: Instruction at step " + instruction.getStep() + " already exists.");
            return false; // Duplicate item not added
        }
        instructions.add(instruction);
        instructions.sort(Comparator.comparingInt(Instruction::getStep));
        return true; // Successfully added
    }

    public Instruction getInstruction(int index) {
        List<Instruction> filtered = instructions.stream()
                .filter(i -> i.getStep() == index).toList();

        if (filtered.isEmpty()) {
            return null;
        }

        return filtered.get(0);
    }

    public void removeInstruction(int index) {
        List<Instruction> filtered = instructions.stream()
                .filter(i -> i.getStep() == index).toList();
        if (filtered.isEmpty()) {
            System.out.println("Error: Instruction at step " + index + " does not exist.");
            return;
        }
        instructions.remove(filtered.get(0));
    }

    public String getAllInstructions(){
        StringBuilder allInstructions = new StringBuilder();
        for (Instruction instruction : instructions) {
            allInstructions.append(instruction.toString()).append("\n");
        }
        return allInstructions.toString();
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            System.out.println("Error: Ingredient " + ingredient + " already exists.");
        }
        ingredients.add(ingredient);
    }


    public void editIngredient(String ingredientName, int newQuantity, String newUnit) {
        List<Ingredient> filtered = ingredients.stream()
                .filter(i -> i.getName().equals(ingredientName)).toList();

        if (filtered.isEmpty()) {
            System.out.println("Error: Ingredient " + ingredientName + " does not exist.");
        }

        Ingredient ingredient = filtered.get(0);
        ingredient.setQuantity(newQuantity);
        ingredient.setUnit(Unit.parseUnit(newUnit));
    }


    public void removeIngredient(String ingredientName) {
        List<Ingredient> filtered = ingredients.stream()
                .filter(i -> i.getName().equals(ingredientName)).toList();

        if (filtered.isEmpty()) {
            System.out.println("Error: Ingredient " + ingredientName + " does not exist.");
            return;
        }

        Ingredient ingredient = filtered.get(0);
        ingredients.remove(ingredient);
    }

    public String getAllIngredients(){
        StringBuilder allIngredients = new StringBuilder();
        int index = 1;
        for (Ingredient ingredient : ingredients) {
            allIngredients.append((index++)).append(". ").append(ingredient.toString()).append("\n");
        }
        return allIngredients.toString();
    }

    public Ingredient getIngredient(String ingredientName) {
        List<Ingredient> filtered = ingredients.stream().filter(i -> i.getName().
                equals(ingredientName)).toList();
        if (filtered.isEmpty()) {
            return null;
        }
        return filtered.get(0);
    }

    public String getContent(){
        return name + "\n" + LINE + "\nIngredients:\n" + getAllIngredients() +
                LINE + "\nInstructions:\n" + getAllInstructions() +"\n";
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString(){
        return name;
    }
}
