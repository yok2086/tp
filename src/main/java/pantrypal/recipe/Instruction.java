package pantrypal.recipe;

import java.util.Objects;

/**
 * This class is used to store instructions in recipes.
 */
public class Instruction {
    private int step;
    private String instruction;

    /**
     * Constructor for Instruction class
     * @param step the step number at which this instruction should be followed
     * @param instruction the content of the instruction
     */
    public Instruction(int step, String instruction) {
        this.step = step;
        this.instruction = instruction;
    }

    /**
     *
     * @return the step number of the instruction
     */
    public int getStep() {
        return step;
    }

    /**
     *
     * @return the content of the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Change the content of the instruction
     * @param instruction the new content to be set to this instruction
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * For printing purposes
     * @return step number. content (for example, 1. serve eggs)
     */
    public String toString() {
        return step + ". " + instruction;
    }

    /**
     * For comparing purposes in lists. This method aids in checking if the instruction at
     * a specified step number already exists
     * @param obj The other instruction to be compared
     * @return True if 2 instruction object has the same step number, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        //Return True if 2 object has the same step number
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Instruction instruction1 = (Instruction) obj;
        return Objects.equals(step, instruction1.step); // Compare only by step number
    }

    /**
     * For hashing purposes in list
     * @return Object hashed by its step number
     */
    @Override
    public int hashCode() {
        return Objects.hash(step);
    }
}
