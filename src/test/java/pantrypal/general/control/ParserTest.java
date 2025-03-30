package pantrypal.general.control;

import org.junit.jupiter.api.Test;
import pantrypal.general.commands.Command;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.general.Exit;
import pantrypal.general.commands.general.Help;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;

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
        Command command = parser.parse("addNewIngredient Flour 2.5 cup");

        assertInstanceOf(AddIngredient.class, command);
        AddIngredient addIngredientCommand = (AddIngredient) command;
        assertEquals("FLOUR", addIngredientCommand.getName());
        assertEquals(2.5, addIngredientCommand.getQuantity(), 0.01);
        assertEquals("cup", addIngredientCommand.getUnit());
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
    public void parse_inputInvalidCommand_returnsNullCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("invalidCommand");

        assertInstanceOf(NullCommand.class, command);
    }

}
