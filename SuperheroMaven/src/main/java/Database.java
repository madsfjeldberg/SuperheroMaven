import java.util.ArrayList;


public class Database {

    // attributes
    final ArrayList<Superhero> heroList;
    public int size;
    public final int maxSize;

    // konstruktør
    public Database() {
        heroList = new ArrayList<>();
        size = 0;
        maxSize = 10;

        // temp superhelte for testing
        Superhero superhero = new Superhero("Batman", "Bruce Wayne", "Money", 1980, "JA", 200);
        Superhero superhero1 = new Superhero("Superman", "Clark Kent", "Flight", 1990, "NEJ", 2000);
        Superhero superhero2 = new Superhero("Martian", "Jon", "Flight, Strength", 2000, "NEJ", 500);
        heroList.add(superhero);
        heroList.add(superhero1);
        heroList.add(superhero2);
    }
}
