import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String path = "zgony.csv";

        try {
            Files.lines(Paths.get(path))
                    .skip(2)
                    .map(v -> Integer.parseInt(v))  //mapowanie wartości z wartości
                    .filter();   //tu są strumienie, tak można posortować itp bardzo prosto
            // test.subList([odkąd], [dokąd])

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}