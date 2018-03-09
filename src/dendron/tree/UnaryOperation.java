/**
 * Author's Name: Tejaswini Jagtap
 * File Name: UnaryOperation.java
 */

package dendron.tree;

import dendron.machine.Machine;

import java.util.*;

public class UnaryOperation extends Object implements ExpressionNode{

    private String operator;
    private ExpressionNode expr;
    public static final String NEG = "_";
    public static final String SQRT = "#";
    public static final java.util.Collection<String> OPERATORS = new HashSet<>(Arrays.asList(NEG,SQRT));

    /**
     * Creating a new UnaryOperation node.
     * @param operator the string rep. of the operation
     * @param expr  the operand
     */
    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator = operator;
        this.expr = expr;
    }

    /**
     * Computing the result of evaluating the expression and applying the
     * operator to it.
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the result of the computation
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        int ex = expr.evaluate(symTab);
        if (operator.equals(NEG)){
            return -ex;
        }
        else if(operator.equals(SQRT)){
            return (int)Math.sqrt(ex);
        }
        return -1;
    }

    /**
     * Printing, on standard output, the infixDisplay of the child
     * nodes preceded by the operator and without an intervening blank.
     */
    @Override
    public void infixDisplay() {
        System.out.print(operator);
        expr.infixDisplay();
    }

    /**
     * Emitting the Machine instructions necessary to perform the
     * computation of this UnaryOperation.
     * @return a list containing instructions for the expression and
     * the instruction to perform the operation
     */
    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> push = new ArrayList<>();
        List<Machine.Instruction> load = expr.emit();
        if (operator.equals(NEG)){
            Machine.Negate Neg = new Machine.Negate();
            push.add(Neg);
        }
        else if(operator.equals(SQRT)){
            Machine.SquareRoot Sqrt = new Machine.SquareRoot();
            push.add(Sqrt);
        }
        load.addAll(push);
        return load;
    }
}
