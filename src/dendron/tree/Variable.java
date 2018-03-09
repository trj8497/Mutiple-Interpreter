/**
 * Author's Name: Tejaswini Jagtap
 * File Name: Variable.java
 */

package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Variable extends Object implements ExpressionNode {

    /**
     * Setting the name of this new Variable.
     */
    private String name;
    public Variable(String name){
        this.name = name;
    }

    /**
     * Evaluating a variable by fetching its value
     * @param symTab symbol table, if needed, to fetch variable values
     * @return this variable's current value in the symbol table
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        if (symTab.containsKey(this.name)){
        return symTab.get(this.name);
        }
        else{
            Errors.report(Errors.Type.UNINITIALIZED, symTab.get(this.name));
            return Integer.parseInt(null);
        }
    }

    /**
     * Printing on standard output the Variable's name.
     */
    @Override
    public void infixDisplay() {
        System.out.print(this.name);
    }

    /**
     *Emitting a LOAD instruction that pushes the Variable's value onto
     * the stack.
     * @return a list containing a single LOAD instruction
     */
    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> load = new ArrayList<>();
        Machine.Load Load = new Machine.Load(this.name);
        load.add(Load);
        return load;
    }
}
