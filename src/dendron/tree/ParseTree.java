package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;
import dendron.tree.ActionNode;
import dendron.tree.ExpressionNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 * THIS CLASS IS UNIMPLEMENTED. All methods are stubbed out.
 *
 * @author Tejaswini Jagtap
 */
public class ParseTree {

    private List<String> prog;
    private Program pr;
    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     */
    public ParseTree( List< String > program ) {
        pr = new Program();
        prog = program;
        while(prog.size()!=0){
            ActionNode action = parseAction(prog);
            pr.addAction​(action);
        }
    }

    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {

        if (program.isEmpty()) {
            Errors.report(Errors.Type.PREMATURE_END, null);
            return null;
        } else {
            String remove1 = program.remove(0);
            if (remove1.equals("@")) {
                ActionNode action = new Print(parseExpr(program));
                return action;
            } else if (remove1.equals(":=")) {
                if(program.isEmpty()){
                    Errors.report(Errors.Type.PREMATURE_END, null);
                    return null;
                }
                else{
                ActionNode action = new Assignment(program.remove(0), parseExpr(program));
                return action;
                }
            } else {
                Errors.report(Errors.Type.ILLEGAL_VALUE, remove1);
                return null;
            }
        }
    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {
        if (program.isEmpty()){
            Errors.report(Errors.Type.PREMATURE_END, null);
            return null;
        }
        else{
        String remove1 = program.remove(0);
        if (BinaryOperation.OPERATORS.contains(remove1)){
            ExpressionNode binary = new BinaryOperation(remove1, parseExpr(program), parseExpr(program));
            return binary;
        }
        else if (UnaryOperation.OPERATORS.contains(remove1)){
            ExpressionNode unary = new UnaryOperation(remove1, parseExpr(program));
            return unary;
        }
        else if (remove1.matches("^[a-zA-Z].*")){
            ExpressionNode var = new Variable(remove1);
            return var;
        }
        else if (remove1.matches("\\d+")){
            ExpressionNode con = new Constant(Integer.parseInt(remove1));
            return con;
        }
        else{
            Errors.report(Errors.Type.ILLEGAL_VALUE, remove1);
            return null;
        }
    }
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see dendron.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
        pr.infixDisplay();
    }

    /**
     * Run the program represented by the tree directly
     * @see dendron.tree.ActionNode#execute(Map)
     */
    public void interpret() {
        System.out.println("");
        System.out.println("Interpreting the parse tree...");
        Map<String, Integer> symTab = new HashMap<>();
        pr.execute(symTab);
        System.out.println("Interpretation complete.");
        Errors.dump(symTab);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
        return pr.emit();
    }

}
