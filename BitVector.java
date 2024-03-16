import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BitVector {
    public static void main(String[] args) {
        new BitVector().start();
    }
    private static final int DEFAULT_INITIAL_SIZE = 8;
    private int[] vector = null;
    private int size = 0;
    private static final String COMMANDS = """
    -s _ sets the bit at given index.
    -r _ resets the bit at given index.
    -e _ ends the work with bit vector and saves as "yourBitVector.txt"
    """;
    private BitVector() {

    }
    private void reset(int index) {
        try {
            vector[index] = 0;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index not met");
        }
    }
    private void set(int index) {
        try {
            vector[index] = 1;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index not met");
        }
    }
    public void start() {
        Scanner scan = new Scanner(System.in);
        Scanner index = new Scanner(System.in);
        boolean isFinished = false;
        System.out.println("please enter the initial size of your bit vector.");
        int size = scan.nextInt();
        if (size > 0) {
            this.size = size;
        } else {
            this.size = DEFAULT_INITIAL_SIZE;
        }
        vector = new int[size];
        System.out.println("array size is set to " + size);
        scan.nextLine();

        while (!isFinished) {
            System.out.println(COMMANDS);
            switch (scan.nextLine()) {
                case "-s" -> {
                    System.out.println("please enter the index to set");
                    int ind = index.nextInt();
                    if (ind < size && ind > -1) {
                        set(ind);
                        System.out.println("bit at index " + ind + " is set");
                    } else {
                        System.out.println("index is less than 0 or more than " + this.size);
                    }
                }
                case "-r" -> {
                    System.out.println("please enter the index to reset");
                    int ind = index.nextInt();
                    if (ind < size && ind > -1) {
                        reset(ind);
                        System.out.println("bit at index " + ind + " is reset");
                    } else {
                        System.out.println("index is less than 0 or more than " + this.size);
                    }
                }
                case "-e" -> {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("yourBitVector.txt"));
                        writer.write(toString());
                        writer.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    isFinished = true;
                }
                default -> System.out.println("command not met");
            }
        }
    }
    @Override
    public String toString() {
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
