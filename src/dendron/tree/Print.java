/**
 * Author's Name: Tejaswini Jagtap
 * File Name: Print.java
 */

package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Print extends Object implements ActionNode {

    private ExpressionNode printee;

    /**
     * Setting up a Print node.
     * @param printee
     */
    public Print(ExpressionNode printee){
        this.printee = printee;
    }

    /**
     * Evaluating the expression and display the result on the console.
     * @param symTab the table where variable values are stored
     */
    @Override
    public void execute(Map<String, Integer> symTab) {
        System.out.println("===" + printee.evaluate(symTab));
    }

    /**
     * Showing this statement on standard output as the word "Print"
     * followed by the infix form of the expression.
     */
    @Override
    public void infixDisplay() {
        System.out.print("Print");
        printee.infixDisplay();
    }

    /**
     * This method returns the code emitted by the printee node that
     * pushes the value of the printee expression onto the stack,
     * followed by a PRINT instruction
     * @return a list of instructions ending in the ones that compute
     * the value to be printed, and print it.
     */
    @Override
    public List<Machine.Instruction> emit() {
        Machine.Print mac = new Machine.Print();
        List<Machine.Instruction> load = new ArrayList<>();
        List<Machine.Instruction> push = printee.emit();
        load.addAll(push);
        load.add(mac);

        return load;
    }
}
