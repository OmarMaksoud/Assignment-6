import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[]args){
        try {
            int x= args.length;
            error0(x);
            String name=args[0];
            File file=new File(name);
            error1(name);
            error2(file);
            Scanner input=new Scanner(file);
            ArrayList<String> List=new ArrayList<>();
            while (input.hasNextLine()){
                List.add(input.nextLine());
            }
            input.close();
            String outputName=name.replaceAll(".arxml","_mod.arxml");
            File outputFile=new File(outputName);
            PrintWriter output = new PrintWriter(outputFile);

            ArrayList<Integer> index = new ArrayList<Integer>();
            for (int i = 0; i < List.size(); i++) {
                String z =List.get(i);
                if (z.trim().startsWith("<SHORT-NAME>")) {
                    index.add(i);
                }
            }

            for (int i = 0; i < index.size(); i++) {
                for (int j = i + 1; j < index.size(); j++) {
                    String s1 = List.get(index.get(i));
                    String s2 = List.get(index.get(j));
                    int minLength = Math.min(s1.length(), s2.length());

                    for (int k = 0; k < minLength; k++) {
                        char c1 = s1.charAt(k);
                        char c2 = s2.charAt(k);

                        if (c1 == c2) {
                            continue;
                        } else if (c1 > c2) {
                            Collections.swap(List, index.get(i), index.get(j));
                            int start1=index.get(i);
                            int end1=index.get(i);
                            int start2=index.get(j);
                            int end2=index.get(j);
                            while(!List.get(start1).contains("CONTAINER"))
                            {
                                start1 --;
                                start2--;

                                Collections.swap(List, start1, start2);

                            }
                            while(!List.get(end1).contains("CONTAINER"))
                            {
                                end1 ++;
                                end2 ++;
                                Collections.swap(List, end1, end2);
                            }
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < List.size(); i++) {
                output.println(List.get(i));
            }

            output.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("No files passed");
        }
        catch (NotVaildAutosarFileException ex){
            System.out.println("Passed file has wrong extension");
        }
        catch (EmptyAutosarFileException ex){
            System.out.println("Passed file is empty");
        }


    }
    public static void error0(int x)throws FileNotFoundException{
        if (x==0) {
            throw new FileNotFoundException();
        }
    }
    public static void error1(String name)throws NotVaildAutosarFileException{
        if (!name.endsWith("arxml")){
            throw new NotVaildAutosarFileException();
        }
    }
    public static void error2(File file)throws EmptyAutosarFileException{
        if (file.length()==0){
            throw new EmptyAutosarFileException();
        }
    }
}
class EmptyAutosarFileException extends Exception {
    EmptyAutosarFileException() {
        super("The file is empty");
    }
}
class NotVaildAutosarFileException extends Exception{
    NotVaildAutosarFileException()
    {
        super("invalid extension");
    }
}
