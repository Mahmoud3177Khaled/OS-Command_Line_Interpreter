package os.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {

            CLI cli = new CLI("C:\\");
            boolean run = true;
            String arg;
            String command;

            while (run) {
                System.out.print(cli.getCurrentDir() + ">");

                command = input.next();
                arg = input.nextLine().trim();

                switch (command) {
                    case "pwd" -> cli.pwd(arg);
                    case "cd" -> cli.cd(arg);
                    case "mkdir" -> cli.mkdir(arg);
                    case "touch" -> cli.touch(arg);
                    case "mv" -> cli.mv(arg);
                    case "rm" -> cli.rm(arg);
                    case "rmdir" -> cli.rmdir(arg);
                    case "cat" -> cli.cat(arg);
                    case "ls" -> cli.ls(arg);
                    case "uname" -> cli.uname(arg);
                    case "cp" -> cli.cp(arg, input);
                    case "<" -> cli.inputOp(arg);
                    default ->  cli.UndefinedInput(command);
                }
            }
        }
    }
}

class CLI {

    private String currentDir;

    public CLI(String currentDir) {
        this.currentDir = currentDir;
    }

    public String getCurrentDir() {
        return this.currentDir;
    }

    public void UndefinedInput(String com) {
        System.out.print(com + " is not a recognized command,");
        System.out.println(" please make sure you typed a legal command or try again.");
    }

    private String[] proccess_args(String com) {
        String[] proccessed_args = com.split(" ");
        return proccessed_args;
    }

    // 

    public void pwd(String com) {  //20220027 
        System.out.println("pwd called");
        System.out.println("args in comm: " + com);

        String[] MyArgs = proccess_args(com);

        for(int i = 1; i < MyArgs.length; i++) {
            System.out.println(MyArgs[i]);
        }
    }

    public void ls(String com) { //20220028
        System.out.println("ls called");
        System.out.println("args in comm: " + com);

        String[] MyArgs = proccess_args(com);

        for(int i = 1; i < MyArgs.length; i++) {
            System.out.println(MyArgs[i]);
        }
    }

    public void mkdir(String com) {// 20220246
        System.out.println("mkdir called");
        System.out.println("args in comm: " + com);

        String[] MyArgs = proccess_args(com);

        for(int i = 1; i < MyArgs.length; i++) {
            System.out.println(i+MyArgs[i]);
        }
        


    }

    public void touch(String com) { // 20220027
        System.out.println("touch called");
        System.out.println("args in comm: " + com);

        String[] MyArgs = proccess_args(com);

        for(int i = 1; i < MyArgs.length; i++) {
            System.out.println(MyArgs[i]);
        }
    }

    public void mv(String com) { //20220028
        System.out.println("mv called");
        System.out.println("args in comm: " + com);

        String[] MyArgs = proccess_args(com);

        for(int i = 1; i < MyArgs.length; i++) {
            System.out.println(MyArgs[i]);
        }
    }

    public void rm(String com) { //20220246
        System.out.println("rm called");
        System.out.println("args in comm: " + com);

        String[] MyArgs = proccess_args(com);

        for(int i = 1; i < MyArgs.length; i++) {
            System.out.println(MyArgs[i]);
        }
    }

    // --------------------------- # Mahmoud Khaled 20220317 # --------------------------- //

    public void cd(String com) {                           
        // System.out.println("cd called");
        // System.out.println("args in comm: " + com);

        // String[] MyArgs = proccess_args(com);

        // for(int i = 1; i < MyArgs.length; i++) {
        //     System.out.println(MyArgs[i]);
        // }

        if("..".equals(com)) {
            File newdir = new File(this.currentDir).getParentFile();
            if (newdir != null) {
                this.currentDir = newdir.getAbsolutePath();
            }
        } else {
            File newdir = new File(this.currentDir, com);
            if (newdir.isDirectory() && newdir.exists()) {
                this.currentDir = newdir.getAbsolutePath();
    
            } else {
                System.out.println("Directory " + com + " does not exists in " + this.currentDir);
                // System.out.println(newdir.exists());
                // System.out.println(newdir.isDirectory());
            }

        }

    }
    
    public void rmdir(String com) {                          //20220317

        // System.out.println("rmdir called");
        // System.out.println("args in comm: " + com);

        // String[] MyArgs = proccess_args(com);

        // for(int i = 1; i < MyArgs.length; i++) {
        //     System.out.println(MyArgs[i]);
        // }

       File folderToDelete = new File(this.currentDir, com);

       if(!folderToDelete.exists()) {
            System.out.println("Error: Folder does not exists.");
       } else if (!folderToDelete.isDirectory()) {
            System.out.println("Error: Please provide a folder.");
       } else if (folderToDelete.listFiles().length != 0) {
            System.out.println("Error: Please provide an empty folder.");
       } else {
            if(folderToDelete.delete()) {
                // System.out.println("Folder deleted.");
            } else {
                System.out.println("Error: Failed to delete folder");
            }
       }

    }

    public void cat(String com) {                           //20220317
        // System.out.println("cat called");
        // System.out.println("args in comm: " + com);

        // String[] MyArgs = proccess_args(com);

        // for(int i = 1; i < MyArgs.length; i++) {
        //     System.out.println(MyArgs[i]);
        // }

        File FileToPrint = new File(this.currentDir, com);

        if(!FileToPrint.exists()) {
            System.out.println("Error: File does not exists.");
        } else if (!FileToPrint.isFile()) {
            System.out.println("Error: Please provide a normal folder.");
        } else {
            try {
                try (Scanner scanner = new Scanner(FileToPrint)) {
                    while (scanner.hasNextLine()) {
                        System.err.println(scanner.nextLine());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: Unable to read from file.");
            }
        }


    }


    public void uname(String com) {                           //20220317
        // System.out.println("uname called");
        // System.out.println("args in comm: " + com);

        // String[] MyArgs = proccess_args(com);
        
        // for(int i = 1; i < MyArgs.length; i++) {
            //     System.out.println(MyArgs[i]);
        // }

        String[] MyArgs = proccess_args(com);

        for (String MyArg : MyArgs) {
            if (MyArg.equals("-s")) {
                System.out.println(System.getProperty("os.name"));
            }
            if (MyArg.equals("-r")) {
                System.out.println(System.getProperty("os.version"));
            }
            if (MyArg.equals("-m")) {
                System.out.println(System.getProperty("os.arch"));
            }
            if (MyArg.equals("-n")) {
                try {
                    System.out.println(java.net.InetAddress.getLocalHost().getHostName());
                } catch (UnknownHostException ex) {
                    System.out.println("Failed to retrive info");
                }
            }
        }
        
        
    }

    public void cp(String com, Scanner inputChoice) { //20220317
        // System.out.println("cp called");
        // System.out.println("args in comm: " + com);

        // String[] MyArgs = proccess_args(com);

        // for(int i = 1; i < MyArgs.length; i++) {
        //     System.out.println(MyArgs[i]);
        // }

        String[] parameters = proccess_args(com);

        File OgfileToCopy = new File(this.currentDir, parameters[0]);
        File fileToCopy = new File(this.currentDir, parameters[1]);

        int destType = 0; // 0 --> file 1 --> folder
        int pathType = 0; // 0 --> relative 1 --> absolute

        for (int i = 0; i < parameters[1].length(); i++) {
            if (parameters[1].charAt(i) == '\\') {
                destType = 1;
            } 
            if (parameters[1].charAt(i) == ':') {
                pathType = 1;
            }
        }
        
        if (OgfileToCopy.isFile() && destType == 0) {
            
            if (!OgfileToCopy.exists()) {
                System.out.println("Error: File does not exists.");
                return;
            } 

            if (fileToCopy.exists()) {
                System.out.print("File with this name already exists. Overide? [y/n] ");
                String choice = inputChoice.next();
                if (choice.equals("n") || choice.equals("N")) {
                    System.out.println("cp cancelled");
                    return;
                }
            }

            try {
                fileToCopy.createNewFile();

                FileWriter outputFile;
                try (Scanner inputFile = new Scanner(OgfileToCopy)) {
                    outputFile = new FileWriter(fileToCopy);

                    String line;
                    while (inputFile.hasNextLine()) {
                        line = inputFile.nextLine();
                        outputFile.write(line + "\n");
                    }
                }
                outputFile.close();
            } catch (IOException ex) {
                System.out.println("Error: Failed to copy file.");
            }
            
        } else if (OgfileToCopy.isFile() && destType == 1) {

            File FileToCopyFar = new File(this.currentDir + parameters[1], parameters[0]);
            System.out.println(this.currentDir + parameters[1]);
            
            if (pathType == 1) {
                FileToCopyFar = new File(parameters[1], parameters[0]);
            }

            if (!OgfileToCopy.exists()) {
                System.out.println("Error: File does not exists.");
                return;
            } 

            if (FileToCopyFar.exists()) {
                System.out.print("File with this name already exists. Overide? [y/n] ");
                String choice = inputChoice.next();
                if (choice.equals("n") || choice.equals("N")) {
                    System.out.println("cp cancelled");
                    return;
                }
            }

            try {
                FileToCopyFar.createNewFile();

                FileWriter outputFile;
                try (Scanner inputFile = new Scanner(OgfileToCopy)) {
                    outputFile = new FileWriter(FileToCopyFar);

                    String line;
                    while (inputFile.hasNextLine()) {
                        line = inputFile.nextLine();
                        outputFile.write(line + "\n");
                    }
                }
                outputFile.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }

        } else if (!OgfileToCopy.isFile()) {
            // implement copying folders 
        }

    }

    public void inputOp(String com) {                           //20220317
        System.out.println("inputOp called");
        System.out.println("args in comm: " + com);

        String[] MyArgs = proccess_args(com);

        for(int i = 1; i < MyArgs.length; i++) {
            System.out.println(MyArgs[i]);
        }
    }


}
