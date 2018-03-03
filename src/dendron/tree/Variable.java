package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Variable extends Object implements ExpressionNode {

    private String name;
    public Variable(String name){
        this.name = name;
    }

    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return symTab.get(this.name);
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.name);
    }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> load = new ArrayList<>();
        Machine.Load Load = new Machine.Load(this.name);
        load.add(Load);
        return load;
    }
}
