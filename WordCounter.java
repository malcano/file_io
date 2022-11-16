import java.io.*;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class WordCounter {
    private static int w =0, l = 0 , c =0; // amount of elements
    private static boolean [] flag = new boolean[]{true, true, true};//flags for option used
    private static ArrayList<String> commandQ = new ArrayList<String>();//save command the queue
    private static FileInputStream txtStream; //FileInputStream for bible.txt
    private static String path;//path of input file
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//BufferReader Instance
    private static StringTokenizer Tokens;//Tokens for recognize the commands

    private static ArrayList<String> argsCommandQ = new ArrayList<String>();//for main args to ArrayList

    public static void main(String[] args) throws Exception {
        for(int i=0;i<args.length;i++){
            argsCommandQ.add(args[i]);//args to args command Queue
        }

        //Arraylist to list (including replacement of '[' ']'
        Tokens = new StringTokenizer(argsCommandQ.toString().replace('[',' ').replace(']',' '));

        //input command to command queue one by one until Tokens have no more tokens
        while (Tokens.hasMoreTokens()){
            commandQ.add(Tokens.nextToken());
        }
        if(commandQ.size()==0||!commandQ.get(commandQ.size()-1).contains(".txt")){//if command quue doesn't have any arguments or the final argument is not txt filetype
            System.out.println("usage: java WordCounter [options] [input_file]");
            return;
        }else{
            path = commandQ.get(commandQ.size()-1);
            txtStream = new FileInputStream(path);
        }


        if(commandQ.size()==1&&commandQ.get(0).contains(".txt")==true){//there's not any options
            flag[0] = false;
            flag[1] = false;
            flag[2] = false;

            getOption('l');
            getOption('w');
            getOption('c');
            System.out.println(path);
        }else if (commandQ.get(0).charAt(0)=='-'){//if there's -
            if(commandQ.get(0).contains("w")==true){
                flag[0]=false;
            }
            if(commandQ.get(0).contains("l")==true){
                flag[1]=false;
            }
            if(commandQ.get(0).contains("c")==true){
                flag[2]=false;
            }

            getOption('l');
            getOption('w');
            getOption('c');

            System.out.println(path);
        }
    }
    public static void getOption(char option) throws IOException{//function for get options
        switch (option){
            case 'w':
                if(flag[0]==false){
                    String tmp;
                    br = new BufferedReader(new FileReader(path));
                    while ((tmp = br.readLine())!=null){
                        Tokens = new StringTokenizer(tmp);
                        w+=Tokens.countTokens();
                    }
                    System.out.print(w+"|");
                    flag[0]=true;
                }
                break;
            case 'l':
                if(flag[1]==false){
                    br = new BufferedReader(new FileReader(path));
                    while (br.readLine()!=null){
                        l++;
                    }
                    System.out.print(l+"|");

                    flag[1]=true;
                }
                break;
            case 'c':
                if(flag[2]==false){
                    while (txtStream.read()!=-1){
                        c++;
                    }
                    System.out.print(c+"|");
                    flag[2]=true;
                }

                break;
        }
    }
}
