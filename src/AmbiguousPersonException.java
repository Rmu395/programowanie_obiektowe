public class AmbiguousPersonException extends Exception {
    public AmbiguousPersonException(Person person) {
        super("Osoba " + person.name + " już istnieje!");
    }
}
