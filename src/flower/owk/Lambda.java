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

    public int call(String store, String... regs) {
        if(regs.length != variables.length) {
            throw new IllegalArgumentException();
            return;
        }
        String c = code;
        for(int i = 0; i < variables.length; i++) {
            c = c.replace(variables[i], regs[i]);
        }
    }

}
