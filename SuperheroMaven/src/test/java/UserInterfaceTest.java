import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class UserInterfaceTest {

    private Database db;
    private UserInterface ui;

    @BeforeEach
    void setUp() {
       db = mock(Database.class);
       ui = new UserInterface(db);
       Superhero superhero = new Superhero("Superman", "Clark Kent", "Flight", 1990, "NEJ", 2000);
       Superhero superhero1 = new Superhero("Batman", "Bruce Wayne", "Money", 1980, "JA", 200);
       Superhero superhero2 = new Superhero("Martian", "Jon", "Strength", 2000, "NEJ", 500);
       db.heroList.add(superhero);
       db.heroList.add(superhero1);
       db.heroList.add(superhero2);
    }

    @Test
    void addSuperhero() {
        when(ui.input.nextLine()).thenReturn("Superman", "Clark Kent", "Flight", "1990", "n", "200");
        // db.heroList.size();
        verify(ui).addSuperhero();
    }

    @Test
    void search() {
        when(ui.input.nextLine()).thenReturn("Superman");

    }

    @Test
    void edit() {
    }

    @Disabled
    @Test
    void delete() {
    }

    @Test
    void databaseMenu() {
    }

    @Test
    void showList() {
    }
}