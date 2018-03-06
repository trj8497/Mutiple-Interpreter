package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program extends Object implements ActionNode {

    private List<ActionNode> prog;

    public Program(){
        prog = new ArrayList<>();
    }

    public void addAction​(ActionNode newNode){
        prog.add(newNode);
    }

    @Override
    public void execute(Map<String, Integer> symTab) {
        for (ActionNode i : prog){
            i.execute(symTab);
        }
    }

    @Override
    public void infixDisplay() {
        for (ActionNode i : prog){
            i.infixDisplay();
        }
    }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> loadall = new ArrayList<>();
        for (ActionNode i: prog){
            loadall.addAll(i.emit());
        }
        return loadall;
    }
}
