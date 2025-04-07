package pantrypal.mealplan;


import static org.junit.jupiter.api.Assertions.assertEquals;
/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
import pantrypal.inventory.IngredientInventory;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
*/
public class MealPlanTest {

    private MealPlanManager plans;

    @BeforeEach
    void setUp(){
        this.plans = new MealPlanManager();
    }

    @Test
    void testAddPlan() {
        plans.addPlan("");
        plans.addPlan("plan1");

        assertEquals("default", plans.getPlanDetails().get(0).getPlanName(),
                "plan name not correctly converted");
        assertEquals("plan1", plans.getPlanDetails().get(1).getPlanName(),
                "plan name not correctly parsed");

    }



}
