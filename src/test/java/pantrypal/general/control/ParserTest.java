package pantrypal.general.control;

import org.junit.jupiter.api.Test;
import pantrypal.general.commands.Command;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.general.Exit;
import pantrypal.general.commands.general.Help;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.ConvertIngredient;
import pantrypal.general.commands.inventory.DecreaseQuantity;
import pantrypal.general.commands.inventory.DeleteIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;
import pantrypal.general.commands.inventory.ViewIngredientsByCategory;
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







}
