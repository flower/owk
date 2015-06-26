package flower.owk;

import java.util.*;

public class Lambda {

    public static ArrayList<Lambda> lambdas = new ArrayList<Lambda>();
    public String name;
    public String[] variables;
    public String code;

    //square:λx.x*x
    //add:λx.λy.x+y
    public Lambda(String input) {
        this.name = input.split(":")[0];
        this.code = input.split(":")[1].split(".")[input.split(":")[1].split(".").length-1];
        String vars = input.replace("." + this.code, "").replace("λ", "");
        this.variables = vars.split(".");
        lambdas.add(this);
    }

    public Lambda(String name, String[] variables, String code) {
        this.name = name;
        for(int i = 0; i < variables.length; i++)
            variables[i] = variables[i].replace("λ", "");
        this.variables = variables;
        this.code = code;
        lambdas.add(this);
    }

    public void call(String store, String... regs) {
        if(regs.length != variables.length) {
            throw new IllegalArgumentException();
            return;
        }
        String c = code;
        for(int i = 0; i < variables.length; i++) {
            c = c.replace(variables[i], regs[i]);
        }
        if(c.matches("([0-9a-fA-F]+[+-/*]?)*") {
            //Not PEMDAS
            for(int i = 1; i < c.length(); i+=2) {
                String ex = c.substring(i, i+3);
                if(ex.matches("[0-9a-fA-F][+-*/%&|^><][0-9a-fA-F]") {
                    String r1 = ex.split("[+-*/%&|^><]")[0];
                    String r2 = ex.split("[+-*/%&|^><]")[1];
                    String func = ex.replace(r1, "").replace(r2, "");
                    Owk.getInstance().writeBytecode(Owk.getInstance().mathOperators.get(func) + r1 + r2 + "00");
                }
            }
        }
    }

}
