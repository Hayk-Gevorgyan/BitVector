import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BitVector {
    private static int[] vector = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    private static int size;
    private BitVector(int initialSize) {
        vector = new int[initialSize];
        for (int i = 0; i < initialSize; i++) {
            vector[i] = 0;
        }
        size = initialSize;
    }
    private static void reset(int index) {
        try {
            vector[index] = 0;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index not met");
        }
    }
    private static void set(int index) {
        try {
            vector[index] = 1;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index not met");
        }
    }
    public static void start() {
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        boolean isFinished = false;
        System.out.println("please enter the initial size of your bit vector.");
        new BitVector(scan1.nextInt());
        System.out.println("-s _ sets the bit at given index.\n-r _ resets the bit at given index.\n-e _ ends the work with bit vector and saves as \"yourBitVector.txt\"");
        while (!isFinished) {
            switch (scan.nextLine()) {
                case "-s" -> {
                    System.out.println("please enter the index to set");
                    set(scan1.nextInt());
                }
                case "-r" -> {
                    System.out.println("please enter the index to reset");
                    reset(scan1.nextInt());
                }
                case "-e" -> {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("yourBitVector.txt"));
                        writer.write(toStr());
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    isFinished = true;
                }
                default -> System.out.println("command not met");
            }
        }
    }
    private static String toStr() {
        StringBuilder sb = new StringBuilder();
        sb.append(vector[size - 1]);
        for (int i = size - 2; i >= 0; i--) {
            if((i + 1) % 8 == 0) {
                sb.append("_");
            }
            sb.append(vector[i]);
        }
        return sb.toString();
    }
}
