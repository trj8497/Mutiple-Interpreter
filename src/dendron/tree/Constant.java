/**
 * Author's Name: Tejaswini Jagtap
 * File's Name: Constant.java
 */

package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Constant extends Object implements ExpressionNode {

    private int value;

    /**
     * Storing the integer value in this new Constant.
     * @param value the integer it will hold
     */
    public Constant(int value){
        this.value = value;
    }

    /**
     * Evaluating the constant
     * @param symTab symbol table, if needed, to fetch variable values
     * @return this Constant's value
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return this.value;
    }

    /**
     * Printing this Constant's value on standard output.
     */
    @Override
    public void infixDisplay() {
        System.out.print(this.value);
    }

    /**
     * Emitting an instruction to push the value onto the stack.
     * @return a list containing that one instruction
     */
    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> push = new ArrayList<>();
        Machine.PushConst Push = new Machine.PushConst(this.value);
        push.add(Push);
        return push;
    }
}
