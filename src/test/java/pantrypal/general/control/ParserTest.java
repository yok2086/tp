package pantrypal.general.control;

import org.junit.jupiter.api.Test;
import pantrypal.general.commands.Command;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.general.ExitCommand;
import pantrypal.general.commands.general.HelpCommand;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class ParserTest {

    @Test
    public void parse_helpCommand_returnsHelpCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("help");

        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    public void parse_exitCommand_returnsExitCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("exit");

        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void parse_addNewIngredientWithValidInputs_returnsAddIngredientCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("addNewIngredient Flour 2.5 cups");

        assertInstanceOf(AddIngredient.class, command);
        AddIngredient addIngredientCommand = (AddIngredient) command;
        assertEquals("Flour", addIngredientCommand.getName());
        assertEquals(2.5, addIngredientCommand.getQuantity(), 0.01);
        assertEquals("cups", addIngredientCommand.getUnit());
    }

    @Test
    public void parse_increaseQuantityWithValidInputs_returnsIncreaseQuantityCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("increaseQuantity Sugar 1.5");

        assertInstanceOf(IncreaseQuantity.class, command);
        IncreaseQuantity increaseQuantityCommand = (IncreaseQuantity) command;
        assertEquals("Sugar", increaseQuantityCommand.getName());
        assertEquals(1.5, increaseQuantityCommand.getQuantity(), 0.01);
    }

    @Test
    public void testParseUnknownCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("invalidCommand");

        assertInstanceOf(NullCommand.class, command);
    }

}
