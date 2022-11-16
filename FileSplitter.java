import java.io.*;
import java.util.*;

public class FileSplitter {
    private static int w =0, l = 0 , c =0; // amount of elements
    private static int count = 0;
    private static boolean [] flag = new boolean[]{true, true, true};//flags for option used
    private static ArrayList<String> commandQ = new java.util.ArrayList<String>();//save command the queue
    private static File Folder;
    private static String path;//path of input file
    private static String outputPath;
    private static int numOfLines = 0;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//BufferReader Instance
    private static StringTokenizer Tokens;//Tokens for recognize the commands
    private static ArrayList<String> argsCommandQ = new ArrayList<String>();//for main args to ArrayList



    public static void main(String[] args) throws IOException {
        for(int i=0;i<args.length;i++){
            argsCommandQ.add(args[i]);//args to args command Queue
        }

        //Arraylist to list (including replacement of '[' ']'
        Tokens = new StringTokenizer(argsCommandQ.toString().replace('[',' ').replace(']',' '));

//        Tokens = new StringTokenizer(br.readLine());//test read buffer line

        //input command to command queue one by one until Tokens have no more tokens
        while (Tokens.hasMoreTokens()){
            commandQ.add(Tokens.nextToken());
        }
        if(commandQ.size()!=3||!commandQ.get(0).contains(".txt")){//if command queue doesn't have any arguments or the final argument is not txt filetype
            System.out.println("usage: java FileSplitter [input_file] [output_directory] [number_of_lines]");
            return;
        }else{

            path = commandQ.get(0).replace(',',' ').trim();//path for text file
            outputPath = commandQ.get(1).replace(',',' ').trim();//output path
            numOfLines = Integer.parseInt(commandQ.get(2));//number of lines

            Folder = new File(outputPath.replace(',',' ').trim());//File instance for output path
            if(!Folder.exists()){//if directory does not exist
                Folder.mkdir();//make directory
            }

        }
        br = new BufferedReader(new FileReader(new File("").getAbsolutePath()+"/"+path.trim()));//read bible.txt
        try{
            String filepath; //for fragment file path
            int fragNum=1;//fragment index
            String readLine;// save line
            StringBuffer fragbuf = new StringBuffer();//String buffer for a fragment file
            boolean flag = true;//check buffer EOF flag


            while(flag==true){//if EOF flag is true
                for(int i = 1;i<=numOfLines;i++){
                    if((readLine=br.readLine())==null){//EOF
                        flag = false;
                        break;
                    }
                    fragbuf.append(readLine+"\n");//change line
                }
                filepath ="./"+ outputPath+ "/frag" + fragNum+".txt";//set fragment file name

                File file = new File(filepath);
                if(!file.exists()){
                    try{
                        file.createNewFile();
                    }catch (Exception e) {
                        e.getStackTrace();
                    }
                }

                FileOutputStream txtOutputStream = new FileOutputStream(file);//output stream
                txtOutputStream.write(fragbuf.toString().getBytes());//write file
                fragbuf = new StringBuffer();//init fragbuf
                fragNum++;//change fragment number index

            }

        }catch (Exception e){
            e.getStackTrace();
        }

    }
}
