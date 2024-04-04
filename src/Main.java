import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlantUMLRunner.setJarPath("C:\\Users\\KOMPUTER\\IdeaProjects\\programowanie_obiektowe\\plantuml-1.2024.3.jar");
//        PlantUMLRunner.generate(data, "uml", "test");

        try {
            List<Person> people = Person.fromCsv("family.csv");

//            Optional<String> changedPeople = people
//                    .stream()
//                    .sorted((person1, person2) -> person1.name.compareTo(person2.name))
//                    .map(person -> person.name)
//                    .filter(name -> !name.equals("Anna Dąbrowska"))
//                    .max((name1, name2) -> name1.compareTo(name2));
//                    .collect(Collectors.toList());

//            if(!changedPeople.isEmpty()) {
//                System.out.println(changedPeople.get());
//            }
//            List<Person> changedPeople = Person.substringCheck(people, "Dąb");
            List<Person> changedPeople = Person.sortedList(people);
            for (Person person: changedPeople)
                System.out.println(person);

//            for(String person: changedPeople)
//                System.out.println(person);
//
//            String uml = Person.toUML(people);
//            PlantUMLRunner.generate(uml, "uml", "family");

//            Person person = people.get(2);
//            String uml = person.toUML();
//            PlantUMLRunner.generate(uml, "uml", person.name);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// String test = "dsdsds";
// test.contains("");