package dendron.tree;

import dendron.machine.Machine;

import java.util.*;

public class UnaryOperation extends Object implements ExpressionNode{

    private String operator;
    private ExpressionNode expr;
    public static final String NEG = "_";
    public static final String SQRT = "#";
    public static final java.util.Collection<String> OPERATORS = new HashSet<>(Arrays.asList(NEG,SQRT));

    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator = operator;
        this.expr = expr;
    }

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

    @Override
    public void infixDisplay() {
        System.out.print(operator);
        expr.infixDisplay();
    }

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
        push.addAll(load);
        return push;
    }
}
