import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class day1 {
    static int parseLine(String number) {
        return Integer.parseInt(number);
    }

    public static void solution_1() {
        String puzzleInputFile = "data/aoc18.1.txt";


//        try (Stream<String> stream = Files.lines(Paths.get(puzzleInputFile))) {
//
//            stream.forEach(System.out::println).sum()   ;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try{
            File f = new File(puzzleInputFile);
            Scanner scanner = new Scanner(f);
            int sum = 0;
            while (scanner.hasNext()){
                sum += scanner.nextInt();
            }
            System.out.println("Sum:"+sum);
        }catch(Exception err){
            err.printStackTrace();
        }

    }
}
