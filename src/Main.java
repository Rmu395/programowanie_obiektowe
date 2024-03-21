import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("Test1");
//        list.add("Test2");
//        list.add("Test3");
//        for (String str : list) {
//            System.out.println(str);
//        }
//        System.out.println("\nuga " + list.get(1) + " booga");
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        String dateString = "10.10.2010";
//        LocalDate date = LocalDate.parse(dateString, formatter);

//        try {
//            FileReader fileReader = new FileReader("");
//            BufferedReader reader = new BufferedReader(fileReader);
//            String line = reader.readLine();
//
//            while(line != null) {
//                // ... line
//                line = reader.readLine();
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        List<Person> list;
        try {
            list = Person.fromCsv("family.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Person person : list) {
            System.out.println(person.name);
        }

    }
}