package pantrypal.recipe;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class RecipeManagerTest {

    private RecipeManager rm;

    @BeforeEach
    public void SetUp() {
        rm = new RecipeManager();
    }

    @Test
    public void addRecipe() {
        rm.addRecipe("fried_egg");

        assertEquals(1, rm.getRecipeList().size());
        assertEquals("fried_egg", rm.getRecipeList().get(0).toString());

    }


}