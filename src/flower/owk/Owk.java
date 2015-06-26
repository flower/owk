package flower.owk;

import java.io.*;
import java.util.*;
import javax.script.*;

public class Owk {

    public String file = "console.owk";
    public ScriptEngine engine;
    private PrintWriter writer = new PrintWriter(file, "UTF-8");
    public HashMap<String, String> mathOperators = new HashMap<String, String>(){{
        put("+", "04");
        put("-", "05");
        put("*", "06");
        put("/", "07");
        put("%", "08");
        put("&", "09");
        put("|", "0A");
        put("^", "0B");
        put("}", "0D");
        put("{", "0E");
        put("!", "0C");
        put("-", "17");
        put("~", "18");
    }};

    public static void main(String[] args) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Owk owk = new Owk();
        owk.engine = engine;

        if(args.length > 0) {
            owk.file = args[0];
            owk.writer = new PrintWriter(owk.file, "UTF-8");
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                for(String line; (line = br.readLine()) != null;) {
                    owk.processCommand(line);
                }
            } finally {
                br.close();
            }
        } else {
            Console console = System.console();
            String input = console.readLine(">> ");
            owk.processCommand(input);
        }
    }

    public void processCommand(String input) {
        if(input == null || input.isEmpty()) return;
        input = input.replaceAll("\\s+");
        if(input.contains(";") {
            for(String s : input.split(";"))
                processCommand(s);
            return;
        }
        //Command Checks
        try {
            if(input.startsWith("#") {
                //Comment
                return;
            }
            else if(input.contains("#") {
                input = input.split("#")[0];
            }
            else if(input.matches("[0-9a-fA-F]=\\([0-9()+\-*/.]+\\)")) {
                String reg = input.split("=")[0]
                String ex = input.split("=")[1];
                int ans = (int) Math.floor(engine.eval(ex));
                if(Math.abs(ans) > 255) {
                    throw new IllegalArgumentException();
                    return;
                }
                if(ans > 0) {
                    String num = Integer.toHexString(ans);
                    writeBytecode("01" + reg + num + "0");
                } else {
                    String num = Integer.toHexString(Math.abs(ans));
                    writeBytecode("01" + reg + num + "0");
                    writeBytecode(mathOperators.get("-") + reg + reg + "00");
                }
            }
            else if(input.matches("[0-9a-fA-F]=["']\w["']")) {
                String reg = input.split("=")[0];
                char c = (char) input.split("=")[1].replace("["']", "");
                writeBytecode("01" + reg + ((int)c) + "0");
            }
            else if(input.matches("[0-9a-fA-F]=([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])")) {
                String reg input.split("=")[0];
                String num = Integer.toHexString(Integer.parseInt(input.split("=")[1]));
                writeBytecode("01" + reg + num + "0");
            }
            else if(input.matches("[0-9a-fA-F]=[!|~|-]([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])")) {
                String reg = input.split("=")[0];
                String num = Integer.toHexString(Integer.parseInt(input.split("=")[1].substring(2)));
                writeBytecode("01" + reg + num + "0");
                String func = input.split("=")[1].substring(1,2);
                writeBytecode(mathOperators.get(func) + reg + reg + "00");
            }
            else if(input.matches("[0-9a-fA-F]<[0-9a-fA-F][+-*/%&|^><][0-9a-fA-F]")) {
                String reg1 = input.split("<")[1].split("[+-*/%&|^><]")[0];
                String reg2 = input.split("<")[1].split("[+-*/%&|^><]")[1];
                String reg3 = input.split("<")[0];
                String func = input.split(reg2)[0].split(reg1)[1];
                writeBytecode(mathOperators.get(func) + reg1 + reg2 + reg3 + "0");
            }
            else if(input.mathces("[0-9a-fA-F]<[!|~|-][0-9a-fA-F]") {
                String func = input.split("<")[1].substring(1,2);
                String reg1 = input.split("<")[0];
                String reg2 = input.split("<")[1].substring(2);
                writeBytecode(mathOperators.get(func) + reg1 + reg2 + "00");
            }
            else if(input.mathces("[0-9a-fA-F]<[0-9a-fA-F]") {
                String reg1 = input.split("<")[0];
                String reg2 = input.split("<")[1];
                writeBytecode("14" + reg1 + reg1 + "00");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeBytecode(String output) {
        writer.println(output);
    }
}
