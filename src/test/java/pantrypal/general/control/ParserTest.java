package pantrypal.general.control;

import org.junit.jupiter.api.Test;
import pantrypal.general.commands.Command;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.general.Exit;
import pantrypal.general.commands.general.Help;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.CheckStock;
import pantrypal.general.commands.inventory.ConvertIngredient;
import pantrypal.general.commands.inventory.DecreaseQuantity;
import pantrypal.general.commands.inventory.DeleteIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;
import pantrypal.general.commands.inventory.SetAlert;
import pantrypal.general.commands.inventory.ViewIngredientsByCategory;
import pantrypal.general.commands.inventory.ViewLowStock;
import pantrypal.general.commands.mealplan.AddPlan;
import pantrypal.general.commands.mealplan.AddPlanToDay;
import pantrypal.general.commands.mealplan.AddRecipeToPlan;
import pantrypal.general.commands.mealplan.ExecutePlan;
import pantrypal.general.commands.mealplan.FindPlan;
import pantrypal.general.commands.mealplan.RemovePlan;
import pantrypal.general.commands.mealplan.RemovePlanFromDay;
import pantrypal.general.commands.mealplan.RemoveRecipeFromPlan;
import pantrypal.general.commands.mealplan.ViewPlan;
import pantrypal.general.commands.mealplan.ViewDayPlan;
import pantrypal.general.commands.mealplan.ViewWeekPlans;
import pantrypal.general.commands.mealplan.ViewPlanList;
import pantrypal.general.commands.recipe.AddRecipe;
import pantrypal.general.commands.recipe.ListRecipe;
import pantrypal.general.commands.recipe.RemoveRecipe;
import pantrypal.general.commands.recipe.ViewRecipe;
import pantrypal.general.commands.shoppinglist.AddShoppingItem;
import pantrypal.inventory.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class ParserTest {

    @Test
    public void parse_helpCommand_returnsHelpCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("help");

        assertInstanceOf(Help.class, command);
    }

    @Test
    public void parse_exitCommand_returnsExitCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("exit");

        assertInstanceOf(Exit.class, command);
    }

    @Test
    public void parse_addNewIngredientWithValidInputs_returnsAddIngredientCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addNewIngredient Flour 2.5 cup GRAIN");

        assertInstanceOf(AddIngredient.class, command);
        AddIngredient addIngredientCommand = (AddIngredient) command;
        assertEquals("FLOUR", addIngredientCommand.getName());
        assertEquals(2.5, addIngredientCommand.getQuantity(), 0.01);
        assertEquals(Unit.CUP, addIngredientCommand.getUnit());
    }

    @Test
    public void parse_increaseQuantityWithValidInputs_returnsIncreaseQuantityCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("increaseQuantity Sugar 1.5");

        assertInstanceOf(IncreaseQuantity.class, command);
        IncreaseQuantity increaseQuantityCommand = (IncreaseQuantity) command;
        assertEquals("SUGAR", increaseQuantityCommand.getName());
        assertEquals(1.5, increaseQuantityCommand.getQuantity(), 0.01);
    }

    @Test
    public void parse_decreaseQuantityWithValidInputs_returnsDecreaseQuantityCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("decreaseQuantity Sugar 1.5");

        assertInstanceOf(DecreaseQuantity.class, command);
        DecreaseQuantity decreaseQuantityCommand = (DecreaseQuantity) command;
        assertEquals("SUGAR", decreaseQuantityCommand.getName());
        assertEquals(1.5, decreaseQuantityCommand.getQuantity(), 0.01);
    }

    @Test
    public void parse_inputInvalidCommand_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("invalidCommand");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_deleteIngredientWithValidInputs_returnsDeleteIngredientCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("deleteIngredient Sugar");

        assertInstanceOf(DeleteIngredient.class, command);
        DeleteIngredient deleteIngredientCommand = (DeleteIngredient) command;
        assertEquals("SUGAR", deleteIngredientCommand.getName());
    }

    @Test
    public void parse_convertIngredientWithValidInputs_returnsConvertIngredientCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("convertIngredient Sugar kg");

        assertInstanceOf(ConvertIngredient.class, command);
        ConvertIngredient convertIngredientCommand = (ConvertIngredient) command;
        assertEquals("SUGAR", convertIngredientCommand.getName());
        assertEquals("kg", convertIngredientCommand.getTargetUnit().toString());
    }

    @Test
    public void parse_viewIngredientsByCategoryWithValidInputs_returnsViewIngredientsByCategoryCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewIngredientsByCategory grain");

        assertInstanceOf(ViewIngredientsByCategory.class, command);
        ViewIngredientsByCategory viewIngredientsByCategoryCommand = (ViewIngredientsByCategory) command;
        assertEquals("Grain", viewIngredientsByCategoryCommand.getCategory().toString());
    }

    @Test
    public void parse_viewRecipeWithValidInputs_returnsViewRecipeCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewRecipe Pancakes");

        assertInstanceOf(ViewRecipe.class, command);
        ViewRecipe viewRecipeCommand = (ViewRecipe) command;
        assertEquals("PANCAKES", viewRecipeCommand.getRecipeName());
    }

    @Test
    public void parse_removeRecipeWithValidInputs_returnsRemoveRecipeCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("removeRecipe Pancakes");

        assertInstanceOf(RemoveRecipe.class, command);
        RemoveRecipe removeRecipeCommand = (RemoveRecipe) command;
        assertEquals("PANCAKES", removeRecipeCommand.getRecipeName());
    }

    @Test
    public void parse_addShoppingItemWithValidInputs_returnsAddShoppingItemCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addShoppingItem Sugar 2.0 kg");

        assertInstanceOf(AddShoppingItem.class, command);
        AddShoppingItem addShoppingItemCommand = (AddShoppingItem) command;
        assertEquals("SUGAR", addShoppingItemCommand.getName());
        assertEquals(2.0, addShoppingItemCommand.getQuantity(), 0.01);
        assertEquals("kg", addShoppingItemCommand.getUnit().toString());
    }

    @Test
    public void parse_setAlertWithValidInputs_returnsSetAlertCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("setAlert Sugar 5.0");

        assertInstanceOf(SetAlert.class, command);
        SetAlert setAlertCommand = (SetAlert) command;
        assertEquals("SUGAR", setAlertCommand.getName());
        assertEquals(5.0, setAlertCommand.getThreshold(), 0.01);
    }

    @Test
    public void parse_setAlertWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("setAlert Sugar -5.0");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_viewLowStock_returnsViewLowStockCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewLowStock");

        assertInstanceOf(ViewLowStock.class, command);
    }

    @Test
    public void parse_checkStock_returnsCheckStockCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewStock");

        assertInstanceOf(CheckStock.class, command);
    }

    @Test
    public void parse_addRecipeWithValidInputs_returnsAddRecipeCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addRecipe");

        assertInstanceOf(AddRecipe.class, command);
    }

    @Test
    public void parse_listRecipe_returnsListRecipeCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewRecipeList");

        assertInstanceOf(ListRecipe.class, command);
    }

    @Test
    public void parse_addPlanWithValidInputs_returnsAddPlanCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addPlan WeeklyPlan");

        assertInstanceOf(AddPlan.class, command);
        AddPlan addPlanCommand = (AddPlan) command;
        assertEquals("WeeklyPlan", addPlanCommand.getPlanName());
    }

    @Test
    public void parse_addPlanWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addPlan");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_addPlanToDayWithValidInputs_returnsAddPlanToDayCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addPlanToDay 1 Monday");

        assertInstanceOf(AddPlanToDay.class, command);
        AddPlanToDay addPlanToDayCommand = (AddPlanToDay) command;
        assertEquals(0, addPlanToDayCommand.getPlanIndex());
        assertEquals("Monday", addPlanToDayCommand.getDay());
    }

    @Test
    public void parse_addPlanToDayWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addPlanToDay 1");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_addRecipeToPlanWithValidInputs_returnsAddRecipeToPlanCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addRecipeToPlan 1 1 Breakfast");

        assertInstanceOf(AddRecipeToPlan.class, command);
        AddRecipeToPlan addRecipeToPlanCommand = (AddRecipeToPlan) command;
        assertEquals(0, addRecipeToPlanCommand.getPlanIndex());
        assertEquals(0, addRecipeToPlanCommand.getRecipeIndex());
        assertEquals("Breakfast", addRecipeToPlanCommand.getMealType());
    }

    @Test
    public void parse_addRecipeToPlanWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addRecipeToPlan 1 1");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_removeRecipeFromPlanWithValidInputs_returnsRemoveRecipeFromPlanCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("removeRecipeFromPlan 1 Breakfast");

        assertInstanceOf(RemoveRecipeFromPlan.class, command);
        RemoveRecipeFromPlan removeRecipeFromPlanCommand = (RemoveRecipeFromPlan) command;
        assertEquals(0, removeRecipeFromPlanCommand.getPlanIndex());
        assertEquals("Breakfast", removeRecipeFromPlanCommand.getMealType());
    }

    @Test
    public void parse_removeRecipeFromPlanWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("removeRecipeFromPlan 1");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_removePlanFromDayWithValidInputs_returnsRemovePlanFromDayCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("removePlanFromDay Monday");

        assertInstanceOf(RemovePlanFromDay.class, command);
        RemovePlanFromDay removePlanFromDayCommand = (RemovePlanFromDay) command;
        assertEquals("Monday", removePlanFromDayCommand.getDay());
    }

    @Test
    public void parse_removePlanFromDayWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("removePlanFromDay");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_viewPlanForDayWithValidInputs_returnsViewPlanForDayCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewPlanForDay Monday");

        assertInstanceOf(ViewDayPlan.class, command);
        ViewDayPlan viewDayPlanCommand = (ViewDayPlan) command;
        assertEquals("Monday", viewDayPlanCommand.getDay());
    }

    @Test
    public void parse_viewPlanForDayWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewPlanForDay");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_viewPlanForWeek_returnsViewPlanForWeekCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewPlanForWeek");

        assertInstanceOf(ViewWeekPlans.class, command);
    }

    @Test
    public void parse_executePlanForDayWithValidInputs_returnsExecutePlanForDayCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("execute Monday");

        assertInstanceOf(ExecutePlan.class, command);
        ExecutePlan executePlanCommand = (ExecutePlan) command;
        assertEquals("Monday", executePlanCommand.getDay());
    }

    @Test
    public void parse_executePlanForDayWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("execute");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_findPlanWithValidInputs_returnsFindPlanCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("findPlan Weekly");

        assertInstanceOf(FindPlan.class, command);
        FindPlan findPlanCommand = (FindPlan) command;
        assertEquals("Weekly", findPlanCommand.getSearchKey());
    }

    @Test
    public void parse_findPlanWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("findPlan");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_viewPlanList_returnsViewPlanListCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewPlanList");

        assertInstanceOf(ViewPlanList.class, command);
    }

    @Test
    public void parse_viewPlanWithValidInputs_returnsViewPlanCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewPlan 1");

        assertInstanceOf(ViewPlan.class, command);
        ViewPlan viewPlanCommand = (ViewPlan) command;
        assertEquals(0, viewPlanCommand.getPlanIndex());
    }

    @Test
    public void parse_viewPlanWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("viewPlan");

        assertInstanceOf(NullCommand.class, command);
    }

    @Test
    public void parse_removePlanWithValidInputs_returnsRemovePlanCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("removePlan 1");

        assertInstanceOf(RemovePlan.class, command);
        RemovePlan removePlanCommand = (RemovePlan) command;
        assertEquals(0, removePlanCommand.getPlanIndex());
    }

    @Test
    public void parse_removePlanWithInvalidInputs_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("removePlan");

        assertInstanceOf(NullCommand.class, command);
    }



}
