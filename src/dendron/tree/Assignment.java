package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Assignment extends Object implements ActionNode {

    private String ident;
    private ExpressionNode rhs;

    public Assignment(String ident, ExpressionNode rhs){
        this.ident = ident;
        this.rhs = rhs;
    }

    @Override
    public void execute(Map<String, Integer> symTab) {
        int rh = rhs.evaluate(symTab);
        symTab.put(ident, rh);
    }

    @Override
    public void infixDisplay() {
        System.out.print(ident + ":=");
        rhs.infixDisplay();
    }

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
