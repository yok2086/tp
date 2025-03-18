package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class DeleteIngredientCommand extends Command {
    private String name;

    public DeleteIngredientCommand() {
        super("deleteIngredient <name>", "Delete an ingredient from the inventory");
    }

    public DeleteIngredientCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        inventory.deleteIngredient(name);
    }
}
