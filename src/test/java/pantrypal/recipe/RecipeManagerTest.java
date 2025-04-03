package pantrypal.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Unit;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import static org.junit.jupiter.api.Assertions.*;

class RecipeManagerTest {

    private RecipeManager recipeManager;

    @BeforeEach
    void setUp() {
        recipeManager = new RecipeManager();
        assertNotNull(recipeManager, "Manager should not be null");
    }

    @Test
    void addRecipe() {
        recipeManager.addRecipe("fried_egg");
        recipeManager.addRecipe("cheesy_pizza");
        ArrayList<Recipe> recipes = recipeManager.getRecipeList();
        assertEquals(2, recipes.size(), "Expected 2 recipes in recipe list");
        assertEquals("fried_egg", recipes.get(0).getName(),
                "Expected first recipe to be fried_egg");
        assertEquals("cheesy_pizza", recipes.get(1).getName(),
                "Expected second recipe to be cheesy_pizza");
    }

    @Test
    void addRecipeIngredients() {
        recipeManager.addRecipe("fried_egg");
        Recipe fried_egg = recipeManager.searchRecipe("fried_egg");

        recipeManager.addRecipeIngredients(fried_egg, "eggs", 50, Unit.parseUnit("g"));
        assertEquals("eggs", fried_egg.getIngredients().get(0).getName(),
                "Expected fried egg to be eggs");

        fried_egg.removeIngredient("eggs");
        try {
            recipeManager.addRecipeIngredients(fried_egg, "eggs",
                    50, Unit.parseUnit("eggs"));
            fail("Method should throw an unsupported unit exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e, "Method should throw an unsupported unit exception");
        } catch (Exception e){
            fail("Unexpected exception thrown");
        }

        fried_egg.removeIngredient("eggs");
        try {
            Method method = RecipeManager.class.getMethod("addRecipeIngredients",
                    Recipe.class, String.class, int.class, Unit.class);
            method.invoke(recipeManager, fried_egg, "milk", "eggs", Unit.parseUnit("g"));
            fail("Method should throw a number format exception");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof NumberFormatException) {
                assertNotNull(cause, "Method should throw a number format exception");
            } else {
                fail("Unexpected exception thrown: " + cause);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e, "Method should throw an illegal argument exception");
        } catch (Exception e) {
            System.out.println("Unexpected exception thrown outside Invocation Target: " + e);
            fail("Unexpected exception thrown");
        }

        fried_egg.removeIngredient("eggs");
        try {
            recipeManager.addRecipeIngredients(fried_egg, "eggs", 0, Unit.parseUnit("g"));
            fail("Method should throw an arithmetic exception");
        } catch (ArithmeticException e){
            assertNotNull(e, "Method should throw an arithmetic exception");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        recipeManager.addRecipe("new_recipe");
        Recipe newRecipe = recipeManager.searchRecipe("new_recipe");
        recipeManager.addRecipeIngredients(newRecipe, "eggs", 50, Unit.parseUnit("g"));
        recipeManager.addRecipeIngredients(newRecipe, "eggs", 50, Unit.parseUnit("g"));
        assertEquals(1, newRecipe.getIngredients().size(),
                "Method should not accept duplicate ingredients");
    }

    @Test
    void addRecipeInstruction() {
    }

    @Test
    void removeRecipeInstruction() {
    }

    @Test
    void removeRecipeIngredient() {
    }

    @Test
    void listRecipe() {
    }

    @Test
    void showRecipe() {
    }

    @Test
    void searchRecipe() {
    }

    @Test
    void removeRecipe() {
    }

    @Test
    void copyList() {
    }

    @Test
    void getRecipeList() {
    }
}