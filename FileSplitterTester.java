import java.io.*;
import java.util.Arrays;
import java.util.List;

class FileSplitterTester {
    static int getLineNum(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        int lineCnt = 0;
        String line = null;

        do { 
            line = br.readLine();
            if (line != null) 
                lineCnt++;

        } while(line != null);

        br.close();
        return lineCnt;
    }

    static boolean runCmd(String cmd, String expected) throws IOException, InterruptedException {
        System.out.print("Testing \"" + cmd + "\"...");
        List<String> cmdList = Arrays.asList(cmd.split(" "));
        ProcessBuilder pb = new ProcessBuilder(cmdList);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String output = reader.readLine();
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

    static boolean runCmd(String cmd) throws IOException, InterruptedException {
        System.out.print("Testing \"" + cmd + "\"...");
        List<String> cmdList = Arrays.asList(cmd.split(" "));

        //run the given command
        ProcessBuilder pb = new ProcessBuilder(cmdList);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null);
        p.waitFor();

        //inspect if the command splits the target file correctly.
        String fileName = cmdList.get(2);
        String outputDir = cmdList.get(3);
        int unitLine = Integer.parseInt(cmdList.get(4));
        int totalLine = getLineNum(fileName);
        int fileNum = totalLine / unitLine;
        int lastFile = totalLine % unitLine;

        for(int i=1; i<=fileNum+1; i++) {
            String fragName = outputDir + "/frag" + i + ".txt";
            if (i > fileNum) unitLine = lastFile;
            int fragNum = getLineNum(fragName);
            if(fragNum != unitLine) {
                System.out.println("False!");
                System.out.println("Your output: " + fragName + ": " + fragNum);
                System.out.println("Expected: " + fragName + ": " + unitLine);
                return false;
            }
        }

        System.out.println("OK");
        return true;
    }

    public static void main(String[] args) throws Exception {
        String cmd, output;
        if(!runCmd("java FileSplitter bible.txt", "usage: java FileSplitter [input_file] [output_directory] [number_of_lines]")) throw new Exception("Test failed");
        if(!runCmd("java FileSplitter bible.txt output", "usage: java FileSplitter [input_file] [output_directory] [number_of_lines]")) throw new Exception("Test failed");
        if(!runCmd("java FileSplitter 10000", "usage: java FileSplitter [input_file] [output_directory] [number_of_lines]")) throw new Exception("Test failed");
        if(!runCmd("java FileSplitter bible.txt output 10000")) throw new Exception("Test failed");
        if(!runCmd("java FileSplitter bible.txt output 1000")) throw new Exception("Test failed");
        if(!runCmd("java FileSplitter bible.txt output 100")) throw new Exception("Test failed");
    }
}
