package pantrypal.recipe;

import java.util.Objects;

public class Instruction {
    private int step;
    private String instruction;

    public Instruction(int step, String instruction) {
        this.step = step;
        this.instruction = instruction;
    }

    public int getStep() {
        return step;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String toString() {
        return step + ". " + instruction;
    }

    @Override
    public boolean equals(Object obj) {
        //Return True if 2 object has the same step number
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Instruction instruction1 = (Instruction) obj;
        return Objects.equals(step, instruction1.step); // Compare only by step number
    }

    @Override
    public int hashCode() {
        return Objects.hash(step);
    }
}
