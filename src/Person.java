import javax.xml.transform.Result;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Person implements Serializable {
    public final String name;
    public final LocalDate birth, death;
    private List<Person> parents = new ArrayList<>();
    public Person(String name, LocalDate birth, LocalDate death) {
        this.name = name;
        this.birth = birth;
        this.death = death;
    }
    public static Person fromCsvLine(String line) {
        String[] fields = line.split(",");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String birthString = fields[1];
        String deathString = fields[2];
        LocalDate birth = null, death = null;
        if(!birthString.isEmpty())
            birth = LocalDate.parse(birthString, formatter);
        if(!deathString.isEmpty())
            death = LocalDate.parse(deathString, formatter);

        return new Person(fields[0], birth, death);
    }

    public static List<Person> fromCsv(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        List<Person> result = new ArrayList<>();
        List<PersonWithParentsNames> resultWithParents =  new ArrayList<>();
        String line;
        br.readLine();
        try {
            while ((line = br.readLine()) != null) {
                PersonWithParentsNames personWithNames = PersonWithParentsNames.fromCsvLine(line);
                personWithNames.person.validateLifespan();
                personWithNames.person.validateAmbiguity(result);
                resultWithParents.add(personWithNames);
                result.add(personWithNames.person);
            }
            PersonWithParentsNames.linkRelatives(resultWithParents);
            try {
                for(Person person: result)
                    person.validateParentingAge();
            }
            catch(ParentingAgeException exception) {
                Scanner scanner = new Scanner(System.in);
                System.out.println(exception.getMessage());
                System.out.println("Please confirm [Y/N]:");
                String response = scanner.nextLine();
                if(!response.equals("Y") && !response.equals("y"))
                    result.remove(exception.person);
            }
        } catch (NegativeLifespanException | AmbiguousPersonException | UndefinedParentException exception) {
            System.err.println(exception.getMessage());
        }
        return result;
    }
    private void validateLifespan() throws NegativeLifespanException {
        if(this.death != null && this.birth.isAfter(this.death))
            throw new NegativeLifespanException(this);
    }

    private void validateAmbiguity(List<Person> peopleSoFar) throws AmbiguousPersonException {
        for(Person person: peopleSoFar)
            if(person.name.equals(this.name))
                throw new AmbiguousPersonException(name);
    }

    private void validateParentingAge() throws ParentingAgeException {
        for(Person parent: parents)
            if (birth.isBefore(parent.birth.minusYears(15)) || ( parent.death != null && birth.isAfter(parent.death)))
                throw new ParentingAgeException(this, parent);
    }

    public void addParent(Person person) {
        parents.add(person);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", death=" + death +
                ", parents=" + parents +
                '}';
    }

    public static void toBinaryFile(List<Person> people, String filename) throws IOException {
        try (
                FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(people);
        }
    }

    public static List<Person> fromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        try (
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (List<Person>) ois.readObject();
        }
    }

    public String toUML(Function<String, String> postProcess) {
        StringBuilder objects = new StringBuilder();
        StringBuilder relations = new StringBuilder();

        Function<String, String> replaceSpaces = str -> str.replaceAll(" ", "");


        objects.append("object " + replaceSpaces.apply(name) + "\n");   // tutaj postProcess powinien to przekształcać

        for(Person parent : parents) {
            objects.append("object " + replaceSpaces.apply(parent.name) + "\n");
            relations.append(replaceSpaces.apply(name) + " <-- " + replaceSpaces.apply(parent.name) + "\n");
        }

        return String.format(
                "@startuml\n%s\n%s\n@enduml",
                objects,
                relations
        );
    }

    public static String toUML(List<Person> people) {
        Set<String> objects = new HashSet<>();
        Set<String> relations = new HashSet<>();

        Function<String, String> replaceSpaces = str -> str.replaceAll(" ", "");

        for(Person person : people) {
            objects.add("object " + replaceSpaces.apply(person.name));

            for(Person parent : person.parents) {
                objects.add("object " + replaceSpaces.apply(parent.name));
                relations.add(replaceSpaces.apply(person.name) + " <-- " + replaceSpaces.apply(parent.name) + "\n");
            }
        }

        return String.format(
                "@startuml\n%s\n%s\n@enduml",
                String.join("\n", objects),
                String.join("", relations)
        );
    }

    public static List<Person> substringCheck(List<Person> list, String subString) {
        List<Person> result = list
                .stream()
                .filter(person -> person.name.contains(subString))
                .collect(Collectors.toList());
        return result;
    }

    public static List<Person> sortedList(List<Person> list) {
        List<Person> result = list
                .stream()
                .sorted((person1, person2) -> person1.birth.compareTo(person2.birth))
                .collect(Collectors.toList());
        return result;
    }
// zad 6/7
    public static List<Person> deadPeople(List<Person> list) {
        List<Person> result = list
                .stream()
                .filter(person -> person.death != null)
                .sorted((person1, person2) -> Long.compare(person2.age(person2), person1.age(person1)))
                .collect(Collectors.toList());
        return result;
    }

    public long age(Person person) {
        if (person.death != null) { //life in days
            return person.death.toEpochDay() - person.birth.toEpochDay();
        }
        else {
            return LocalDate.now().toEpochDay() - person.birth.toEpochDay();
        }
    }
}