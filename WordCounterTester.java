import java.io.*;
import java.util.Arrays;
import java.util.List;

class WordCounterTester {
    static boolean runCmd(String cmd, String expected) throws IOException {

        System.out.print("Testing \"" + cmd + "\"...");
        List<String> cmdList = Arrays.asList(cmd.split(" "));

        Process p = new ProcessBuilder(cmdList).start();
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String output = input.readLine();
        if(output.equals(expected)) {
            System.out.println("OK");
            return true;
        } else {
            System.out.println("False!");
            System.out.println("Your output: " + output);
            System.out.println("Expected: " + expected);
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        String cmd, output;
        //if(!runCmd("java WordCounter bible.txt", "30383|766111|4047392|bible.txt")) throw new Exception("Test failed");
        if(!runCmd("java WordCounter -w bible.txt", "766111|bible.txt")) throw new Exception("Test failed");
        if(!runCmd("java WordCounter -lw bible.txt", "30383|766111|bible.txt")) throw new Exception("Test failed");
        if(!runCmd("java WordCounter -wc bible.txt", "766111|4047392|bible.txt")) throw new Exception("Test failed");
        if(!runCmd("java WordCounter -wllcc bible.txt", "30383|766111|4047392|bible.txt")) throw new Exception("Test failed");
        if(!runCmd("java WordCounter", "usage: java WordCounter [options] [input_file]")) throw new Exception("Test failed");
        if(!runCmd("java WordCounter -w", "usage: java WordCounter [options] [input_file]")) throw new Exception("Test failed");
    }
}
