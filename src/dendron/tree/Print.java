package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Print extends Object implements ActionNode {

    private ExpressionNode printee;

    public Print(ExpressionNode printee){
        this.printee = printee;
    }

    @Override
    public void execute(Map<String, Integer> symTab) {
        System.out.println("===" + printee.evaluate(symTab));
    }

    @Override
    public void infixDisplay() {
        System.out.println("Print");
        printee.infixDisplay();
    }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> load = new ArrayList<>();
        List<Machine.Instruction> push = printee.emit();
        load.addAll(push);
        return load;
    }
}
