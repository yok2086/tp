package pantrypal.recipe;

import pantrypal.inventory.Ingredient;
import pantrypal.inventory.Unit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class represents individual recipes in the system. Each will have its list of ingredients and
 * list of instructions
 */
public class Recipe {

    private static final String LINE = "____________________________________________________________";

    private String name;
    private ArrayList<Instruction> instructions = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();


    /**
     * Constructor for Recipe class
     * @param name the name of the recipe
     */
    public Recipe(String name){
        this.name = name;
    }

    /**
     *
     * @return the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Add an instruction into the recipe
     * @param instruction the instruction to be added into the recipe
     * @return True if item successfully added, False if not
     */
    public boolean addInstruction(Instruction instruction) {
        if (instructions.contains(instruction)) {
            System.out.println("Error: Instruction at step " + instruction.getStep() + " already exists.");
            return false; // Duplicate item not added
        }
        instructions.add(instruction);
        instructions.sort(Comparator.comparingInt(Instruction::getStep));
        return true; // Successfully added
    }

    /**
     * Get the instruction object at a specific step number
     * @param index the step number the instruction is gotten
     * @return the instruction object which has the specified step number
     */
    public Instruction getInstruction(int index) {
        List<Instruction> filtered = instructions.stream()
                .filter(i -> i.getStep() == index).toList();

        if (filtered.isEmpty()) {
            return null;
        }

        return filtered.get(0);
    }

    /**
     * Remove the instruction of the recipe
     * @param index the step number of the instruction
     */
    public void removeInstruction(int index) {
        List<Instruction> filtered = instructions.stream()
                .filter(i -> i.getStep() == index).toList();
        if (filtered.isEmpty()) {
            System.out.println("Error: Instruction at step " + index + " does not exist.");
            return;
        }
        instructions.remove(filtered.get(0));
    }

    /**
     * Group all instruction content in the recipe into a string
     * @return the string of all instruction content
     */
    public String getAllInstructions(){
        StringBuilder allInstructions = new StringBuilder();
        for (Instruction instruction : instructions) {
            allInstructions.append(instruction.toString()).append("\n");
        }
        return allInstructions.toString();
    }

    /**
     * Add an ingredient into the recipe
     * @param ingredient the Ingredient object
     */
    public void addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            System.out.println("Error: Ingredient " + ingredient + " already exists.");
        }
        ingredients.add(ingredient);
    }

    /**
     * Edits an existing ingredient
     * @param ingredientName the name of the ingredient to be edited
     * @param newQuantity new quantity
     * @param newUnit new unit of measurement
     */
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

    /**
     * Remove an ingredient with the specified name from the recipe
     * @param ingredientName the name of the ingredient to be removed
     */
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

    /**
     * Group all ingredient content into a string
     * @return the string consist of all ingredient content
     */
    public String getAllIngredients(){
        StringBuilder allIngredients = new StringBuilder();
        int index = 1;
        for (Ingredient ingredient : ingredients) {
            allIngredients.append((index++)).append(". ").append(ingredient.toString()).append("\n");
        }
        return allIngredients.toString();
    }

    /**
     * Get the ingredient object with the ingredient name
     * @param ingredientName the name of the ingredient to be returned
     * @return the ingredient object with the specified name
     */
    public Ingredient getIngredient(String ingredientName) {
        List<Ingredient> filtered = ingredients.stream().filter(i -> i.getName().
                equals(ingredientName)).toList();
        if (filtered.isEmpty()) {
            return null;
        }
        return filtered.get(0);
    }

    /**
     * Group all content (instructions and ingredients) into one string
     * @return the string consists of all the content of the recipe
     */
    public String getContent(){
        return name + "\n" + LINE + "\nIngredients:\n" + getAllIngredients() +
                LINE + "\nInstructions:\n" + getAllInstructions() +"\n";
    }

    /**
     * Get the instruction list. Mostly for debugging and testing purposes
     * @return the instruction list consists of all the instruction objects of the recipe
     */
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    /**
     * Get the ingredient list. Mostly for debugging and testing purposes
     * @return the ingredient list consists of all the ingredient objects of the recipe
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * For printing purposes
     * @return the name of the Recipe
     */
    @Override
    public String toString(){
        return name;
    }
}
