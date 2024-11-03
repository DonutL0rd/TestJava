import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file name: ");
        String fileName = sc.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }

    }
}