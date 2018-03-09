/**
 * Author's Name: Tejaswini Jagtap
 * File Name: BinaryOperation.java
 */

package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;

import java.util.*;

public class BinaryOperation extends Object implements ExpressionNode {

    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "*";
    public static final String DIV = "/";
    public static final java.util.Collection<String> OPERATORS = new HashSet<>(Arrays.asList(ADD, SUB, MUL, DIV));
    private String operator;
    private ExpressionNode leftChild;
    private ExpressionNode rightChild;

    /**
     * Creating a new BinaryOperation node.
     * @param operator the string rep. of the operation
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Computing the result of evaluating both operands and applying
     * the operator to them.
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the result of the computation
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        int lh = leftChild.evaluate(symTab);
        int rh = rightChild.evaluate(symTab);
        if (operator.equals(ADD)) {
            return lh + rh;
        } else if (operator.equals(SUB)) {
            return lh - rh;
        } else if (operator.equals(MUL)) {
            return lh * rh;
        } else if (operator.equals(DIV)) {
            if (rh == 0) {
                Errors.report(Errors.Type.DIVIDE_BY_ZERO, null);
            } else {
                return lh / rh;
            }
        }
        return -1;
    }

    /**
     * Printing, on standard output, the infixDisplay of the two child
     * nodes separated by the operator and surrounded by parentheses.
     */
    @Override
    public void infixDisplay() {
        System.out.print("(");
        leftChild.infixDisplay();
        System.out.print(operator);
        rightChild.infixDisplay();
        System.out.print(")");
    }

    /**
     * Emitting the Machine instructions necessary to perform the
     * computation of this BinaryOperation.
     * @return a list containing instructions for the left operand,
     * instructions for the right operand, and the instruction to
     * perform the operation
     */
    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> push = new ArrayList<>();
        List<Machine.Instruction> lhload = leftChild.emit();
        List<Machine.Instruction> rhload = rightChild.emit();
        if (operator.equals(ADD)) {
            Machine.Add Add = new Machine.Add();
            push.add(Add);
        } else if (operator.equals(SUB)) {
            Machine.Subtract Sub = new Machine.Subtract();
            push.add(Sub);
        } else if (operator.equals(MUL)) {
            Machine.Multiply Mul = new Machine.Multiply();
            push.add(Mul);
        } else if (operator.equals(DIV)) {
            Machine.Divide Div = new Machine.Divide();
            push.add(Div);
        }
        lhload.addAll(rhload);
        lhload.addAll(push);
        return lhload;
    }
}

