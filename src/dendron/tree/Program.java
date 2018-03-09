/**
 * Author's Name: Tejaswini Jagtap
 * File Name: Program.java
 */

package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program extends Object implements ActionNode {

    private List<ActionNode> prog;

    /**
     * Initialize this instance as an empty sequence of ActionNode
     * children.
     */
    public Program(){
        prog = new ArrayList<>();
    }

    /**
     * Adding a child of this Program node.
     * @param newNode the node representing the action that will execute
     *               last
     */
    public void addAction​(ActionNode newNode){
        prog.add(newNode);
    }

    /**
     *Executing each ActionNode in this object, from first-added to
     *  last-added.
     * @param symTab the table where variable values are stored
     */
    @Override
    public void execute(Map<String, Integer> symTab) {
        for (ActionNode i : prog){
            i.execute(symTab);
        }
    }

    /**
     * Showing the infix displays of all children on standard output.
     * The order is first-added to last-added.
     */
    @Override
    public void infixDisplay() {
        System.out.println("The Program, with expressions in infix notation:");
        for (ActionNode i : prog){
            System.out.println("");
            i.infixDisplay();
        }
    }

    /**
     * Creating a list of instructions emitted by each child, from the
     * first-added child to the last-added.
     * @return the concatenated instructions lists from all children
     */
    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> loadall = new ArrayList<>();
        for (ActionNode i: prog){
            loadall.addAll(i.emit());
        }
        return loadall;
    }
}
