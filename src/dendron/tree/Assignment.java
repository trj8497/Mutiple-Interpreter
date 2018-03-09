/**
 * Author's Name: Tejaswini Jgatap
 * File Name: Assignment.java
 */

package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Assignment extends Object implements ActionNode {

    private String ident;
    private ExpressionNode rhs;

    /**
     * Seting up an Assignment node.
     * @param ident the name of the variable that is getting a new value
     * @param rhs  the expression on the "right-hand side" (RHS) of the assignment statement
     */
    public Assignment(String ident, ExpressionNode rhs){
        this.ident = ident;
        this.rhs = rhs;
    }

    /**
     * Evaluating the RHS expression and assign the result value to the variable.
     * @param symTab the table where variable values are stored
     */
    @Override
    public void execute(Map<String, Integer> symTab) {
        int rh = rhs.evaluate(symTab);
        symTab.put(ident, rh);
    }

    /**
     * Showing this assignment on standard output as a variable followed
     * by an assignment arrow (":=") followed by the infix form of the RHS
     * expression.
     */
    @Override
    public void infixDisplay() {
        System.out.print(ident + ":=");
        rhs.infixDisplay();
    }

    /**
     * This method returns a STORE instruction for the variable in
     * question preceded by the code emitted by the RHS node that eventually
     * pushes the value of the expression onto the stack.
     * @return
     */
    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> load = new ArrayList<>();
        Machine.Store store = new Machine.Store(ident);
        List<Machine.Instruction> rhload = rhs.emit();
        load.addAll(rhload);
        load.add(store);
        return load;
    }
}
