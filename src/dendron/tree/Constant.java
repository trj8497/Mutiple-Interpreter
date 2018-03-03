package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Constant extends Object implements ExpressionNode {

    private int value;

    public Constant(int value){
        this.value = value;
    }

    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return this.value;
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.value);
    }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> push = new ArrayList<>();
        Machine.PushConst Push = new Machine.PushConst(this.value);
        push.add(Push);
        return push;
    }
}
