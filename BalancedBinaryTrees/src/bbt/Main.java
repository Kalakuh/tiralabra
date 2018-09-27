package bbt;

import bbt.datastructure.*;
import bbt.testing.*;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        Tester tester = new Tester(new ScapegoatTree());
        tester.runTest(new InsertCheckEraseNTest(10000));
    }
}
