import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BitVector {
    private final int[] vector;
    public final int DEFAULT_SIZE = 8;
    private int size = DEFAULT_SIZE;

    BitVector() {
        vector = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    }
    BitVector(int initialSize) {
        vector = new int[initialSize];
        for (int i = 0; i < initialSize; i++) {
            vector[i] = 0;
        }
        size = initialSize;
    }
    public int size() {
        return size;
    }
    public void reset(int index) {
        try {
            vector[index] = 0;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index not met");
        }
    }
    public void set(int index) {
        try {
            vector[index] = 1;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index not met");
        }
    }
    public void start() {
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        boolean isFinished = false;
        while (!isFinished) {
            switch (scan.nextLine()) {
                case "set" -> set(scan1.nextInt());
                case "reset" -> reset(scan1.nextInt());
                case "end" -> {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("yourBitVector.txt"));
                        writer.write(toString());
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
