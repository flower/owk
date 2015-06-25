package flower.owk;

import java.io.*;

public class Owk {

    public String file = "console.owk";

    public static void main(String[] args) {
        Owk owk = new Owk();
        if(args.length > 0) {
            owk.file = args[0];
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
            else if(input.matches("[0-9a-fA-F]=([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])")) {
                //Load  a=7 loads 7 to register a (up to 255)
                String reg = input.split("=")[0]
                String num = Integer.toHexString(Integer.parseInt(input.split("=")[1]));
                writeBytecode("01" + reg + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeBytecode(String output) {
        //TODO: Write bytecode to file
    }
}
