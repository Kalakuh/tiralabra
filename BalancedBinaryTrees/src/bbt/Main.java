package bbt;

import bbt.datastructure.*;
import bbt.testing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
    private static ByteArrayOutputStream baos;
    private static boolean interactive;
    private static boolean fileInput;
    private static String inputPath;
    private static boolean fileOutput;
    private static String outputPath;
    private static boolean gui;
    
    private static void init() {
        baos = null;
        interactive = false;
        fileInput = false;
        inputPath = "";
        fileOutput = false;
        outputPath = "";
        gui = false;
    }
    
    /**
     * The main function, handles argument logic.
     * -f file      read input from file
     * -o file      output data to file
     * -i           enable interactive mode
     * -g           enable graphical user interface
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
        analyzeArgs(args);
        
        if (fileOutput) {
            baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
        }
        
        run();
        
        try {
            if (fileOutput) {
                FileWriter writer = new FileWriter(new File(outputPath));
                writer.write(baos.toString());
                writer.close();
            }
        } catch (IOException e) {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            System.out.println("An error happened while trying to output the results into a file.");
        }
    }
    
    /**
     * Analyze arguments and enable flags.
     * @param args Arguments from command line
     */
    private static void analyzeArgs(String[] args) {
        String previousArg = "";
        for (String arg : args) {
            switch (arg) {
                case "-i":
                    interactive = true;
                    break;
                case "-o":
                    fileOutput = true;
                    break;
                case "-f":
                    fileInput = true;
                    break;
                case "-g":
                    gui = true;
                    break;
                default:
                    if (previousArg.equals("-o")) {
                        outputPath = arg;
                    } else if (previousArg.equals("-f")) {
                        inputPath = arg;
                    } else if (arg.startsWith("-")) {
                        System.out.println("Unknown flag '" + arg + "'.");
                        return;
                    } else {
                        System.out.println("Unknown argument '" + arg + "'.");
                        return;    
                    }
            }
            previousArg = arg;
        }
    }
    
    /**
     * Select the running mode based on the flags and then run.
     */
    private static void run() {
        if (fileInput && interactive && gui) {
            System.out.println("Flags '-f', '-i' and '-g' can not be used at the same time.");
        } else if (fileInput && interactive) {
            System.out.println("Flags '-f' and '-i' can not be used at the same time.");
        } else if (fileInput && gui) {
            System.out.println("Flags '-f' and '-g' can not be used at the same time.");
        } else if (interactive && gui) {
            System.out.println("Flags '-i' and '-g' can not be used at the same time.");
        } else if (interactive) {
            
        } else if (fileInput) {
            
        } else if (gui) {
            System.out.println("Graphical user interface has not been implemented yet.");
        } else {
            System.out.println("No special flags were found - running default tests.");
            Test test = new RandomNCommandsTest(1000000, 1);

            Tester avlTester = new Tester(new AVLTree());
            avlTester.runTest(test);

            Tester treapTester = new Tester(new Treap());
            treapTester.runTest(test);

            Tester scapegoatTester = new Tester(new ScapegoatTree());
            scapegoatTester.runTest(test);
        }
    }
}
