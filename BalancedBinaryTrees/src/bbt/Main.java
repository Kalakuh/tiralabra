package bbt;

import bbt.datastructure.*;
import bbt.gui.GUI;
import bbt.testing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    private static final String INSERT = "insert";
    private static final String CONTAINS = "contains";
    private static final String ERASE = "erase";
    private static final String EXIT = "exit";
    
    private static ByteArrayOutputStream baos;
    private static boolean interactive;
    private static boolean fileInput;
    private static String inputPath;
    private static boolean fileOutput;
    private static String outputPath;
    private static boolean gui;
    
    private static Test[] tests;
    
    private static void init() {
        baos = null;
        interactive = false;
        fileInput = false;
        inputPath = "";
        fileOutput = false;
        outputPath = "";
        gui = false;
        
        tests = new Test[]{
            new InsertAndThenRandomTest(10, 10, 1),
            new InsertAndThenRandomTest(100, 100, 1),
            new InsertAndThenRandomTest(1000, 1000, 1),
            new InsertAndThenRandomTest(10000, 10000, 1),
            new InsertAndThenRandomTest(100000, 100000, 1),
            new InsertAndThenRandomTest(1000000, 1000000, 1),
            new InsertAndThenRandomTest(10, 10, 100),
            new InsertAndThenRandomTest(100, 100, 100),
            new InsertAndThenRandomTest(1000, 1000, 100),
            new InsertAndThenRandomTest(10000, 10000, 100),
            new InsertAndThenRandomTest(100000, 100000, 100),
            new InsertAndThenRandomTest(1000000, 1000000, 100),
            new InsertAndThenRandomTest(10, 10, 1000000),
            new InsertAndThenRandomTest(100, 100, 1000000),
            new InsertAndThenRandomTest(1000, 1000, 1000000),
            new InsertAndThenRandomTest(10000, 10000, 1000000),
            new InsertAndThenRandomTest(100000, 100000, 1000000),
            new InsertAndThenRandomTest(1000000, 1000000, 1000000),
        };
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
            error("An error happened while trying to output the results into a file.");
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
                        error("Unknown flag '" + arg + "'.");
                        return;
                    } else {
                        error("Unknown argument '" + arg + "'.");
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
            error("Flags '-f', '-i' and '-g' can not be used at the same time.");
        } else if (fileInput && interactive) {
            error("Flags '-f' and '-i' can not be used at the same time.");
        } else if (fileInput && gui) {
            error("Flags '-f' and '-g' can not be used at the same time.");
        } else if (interactive && gui) {
            error("Flags '-i' and '-g' can not be used at the same time.");
        } else if (fileOutput && gui) {
            error("Flags '-o' and '-g' can not be used at the same time.");
        } else if (interactive || fileInput) {
            runWithInput();
        } else if (gui) {
            GUI gui = new GUI();
            gui.open();
        } else {
            System.out.println("No special flags were found - running default tests.");
            for (Test test : tests) {
                Tester avlTester = new Tester(new AVLTree());
                avlTester.runTest(test);
            
                Tester treapTester = new Tester(new Treap());
                treapTester.runTest(test);
            
                Tester scapegoatTester = new Tester(new ScapegoatTree());
                scapegoatTester.runTest(test);
            }
        }
    }
    
    /**
     * Use either interactive input or file input
     */
    private static void runWithInput() {
        Scanner scanner;
        if (interactive) {
            scanner = new Scanner(System.in);
        } else {
            try {
                scanner = new Scanner(new File(inputPath));
            } catch (IOException e) {
                error("An error happened with input file.");
                error(e.toString());
                return;
            }
        }
        BinaryTree<Integer> tree = new Treap<>();
        while (scanner.hasNext()) {
            if (interactive) {
                System.err.print(">>> ");
            }
            String cmd = scanner.next();
            if (cmd.equals(EXIT)) {
                break;
            }
            if (cmd.matches(INSERT + "|" + CONTAINS + "|" + ERASE)) {
                try {
                    int n = Integer.parseInt(scanner.next());
                    switch (cmd) {
                        case INSERT:
                            tree.insert(n);
                            break;
                        case CONTAINS:
                            System.out.println(tree.contains(n));
                            break;
                        case ERASE:
                            tree.erase(n);
                            break;
                    }
                } catch (NumberFormatException e) {
                    error("The value after the command was not an integer.");
                    if (fileInput) {
                        break;
                    }
                }
            } else {
                error("Unknown command '" + cmd + "'." + scanner);
                if (fileInput) {
                   break;
                }
            }
        }
        scanner.close();
    }
    
    /**
     * Output an error message to stdout
     * @param s error message
     */
    private static void error(String s) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(s);
    }
}
