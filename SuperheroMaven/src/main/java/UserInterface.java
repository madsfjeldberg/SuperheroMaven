import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// attributes
public class UserInterface {
    private final Database db;
    Scanner input;

    // konstruktør
    public UserInterface() {
        db = new Database();
        input = new Scanner(System.in);
    }

    public UserInterface(Database db) {
        this.db = db;
        input = new Scanner(System.in);
    }

    // tjekker om der er tal eller symboler i et string request
    public boolean stringTester(String string) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    // tilføj superhelt til database
    public void addSuperhero() {
        if (db.size < db.maxSize) {
            System.out.println("Indtast data:\n");
            System.out.print("Superheltenavn (skriv 'n' hvis de ikke har et): ");
            String name = input.nextLine();
            if  (name.equals("n")) {
                name = "";
            }
            while (!stringTester(name)) {
                System.out.println("Du skal indtaste et gyldigt navn.");
                name = input.nextLine();
            }

            System.out.print("\nRigtige navn: ");
            String realName = input.nextLine();
            while (!stringTester(realName)) {
                System.out.println("Du skal indtaste et gyldigt navn.");
                input.nextLine();
            }

            System.out.print("\nSuperkræft: ");
            String superPower = input.nextLine();
            while (!stringTester(superPower)) {
                System.out.println("Du skal indtaste en gyldig superkræft.");
                input.nextLine();
            }

            System.out.print("\nÅrstal for skabelse: ");
            while (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.next();
            }
            int yearCreated = input.nextInt();

            System.out.print("\nEr superhelten et menneske? [y/n]: ");
            String svar = input.next();
            String isHuman;
            while (!svar.equals("y") && !svar.equals("n")) {
                System.out.println("Ugyldigt svar.");
                svar = input.next();
            }
            if (svar.equals("y")) {
                isHuman = "JA";
            }
            else {
                isHuman = "NEJ";
            }

            System.out.print("\nStyrke: ");
            while (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.next();
            }
            int strength = input.nextInt();
            System.out.println();

            Superhero superhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
            db.heroList.add(superhero);
            db.size++;
            System.out.println("Superhelt tilføjet til databasen.\n");
        } else System.out.println("Database er fuld.\n");
    }

    // søgemetode
    public void search() {
        System.out.print("Søg efter superhelt: ");
        String search = input.nextLine();
        System.out.println();
        boolean found = false;
        for (Superhero i : db.heroList) {
            if (i.getName().toLowerCase().contains(search.toLowerCase()) ||
                    i.getRealName().toLowerCase().contains(search.toLowerCase())) {
                showInfo(i);
                found = true;
            }
        }
        if (!found) System.out.println("Superhelt ikke fundet.");
    }

    // redigerer en superhelt
    public void edit() {
        ArrayList<Superhero> foundSuperheroes = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Superhelte i database:");
        for (Superhero i: db.heroList) System.out.println(i.getName());

        System.out.print("Hvilken superhelt vil du redigere?: ");
        String search = input.nextLine();
        System.out.println();
        Superhero chosenSuperhero = null;
        for (Superhero i : db.heroList) {
            if (i.getName().toLowerCase().contains(search.toLowerCase()) ||
                    i.getRealName().toLowerCase().contains(search.toLowerCase())) {
                foundSuperheroes.add(i);
            }
        }

        int index = 1;
        for (Superhero i : foundSuperheroes) {
            System.out.printf("%d. %s\n", index, i.getName());
            index++;
        }
        System.out.print("Vælg en superhelt: ");
        System.out.println();

        while (!input.hasNextInt()) {
            System.out.println("Du skal indtaste et tal.");
            input.next();
        }
        int chosenIndex = input.nextInt();
        System.out.println();
        if (chosenIndex > 0 && chosenIndex <= foundSuperheroes.size()) {
            chosenSuperhero = foundSuperheroes.get(chosenIndex - 1);
            showInfo(chosenSuperhero);
        }

        if (chosenSuperhero != null) {
            String changeValueMessage = "Indtast ny værdi: ";
            System.out.print("Hvad vil du ændre?: ");
            System.out.println();
            switch (input.nextInt()) {
                case 1 -> {
                    System.out.print(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setName(input.nextLine());
                }
                case 2 -> {
                    System.out.print(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setRealName(input.nextLine());
                }
                case 3 -> {
                    System.out.print(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setSuperPower(input.nextLine());
                }
                case 4 -> {
                    System.out.println(changeValueMessage);
                    chosenSuperhero.setYearCreated(input.nextInt());
                }
                case 5 -> {
                    System.out.println(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setHuman(input.nextLine());
                }
                case 6 -> {
                    System.out.println(changeValueMessage);
                    chosenSuperhero.setStrength(input.nextInt());
                }
                default -> System.out.println("Ugyldigt svar.");
            }
            showInfo(chosenSuperhero);
        } else System.out.println("Superhelt ikke fundet.");
    }

    // sletter en superhelt fra databasen
    public void delete() {
        int index = 1;
        System.out.println();
        for (Superhero i : db.heroList) {
            System.out.println(index++ + ". " + i.getName());
        }
        System.out.print("Hvem skal slettes fra databasen?: ");
        while (true) {
            if (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.next();
            } else {
                int choice = input.nextInt();
                if (choice >= 1 && choice <= db.heroList.size()) {
                    db.heroList.remove(choice - 1);
                    System.out.println("\nSletter fra database...");
                    System.out.println("Superhelt slettet.\n");
                    break;
                } else {
                    System.out.println("Du skal indtaste gyldigt tal mellem 1 og " + db.heroList.size() + ".");
                }
            }
        }
    }

    // viser database menu
    public void databaseMenu() {
        System.out.print("─".repeat(25) + "\n");
        System.out.println("MENU");
        System.out.print("─".repeat(25) + "\n");
        System.out.println("1. Opret superhelt");
        System.out.println("2. Vis liste");
        System.out.println("3. Søg efter superhelt");
        System.out.println("4. Rediger superhelt");
        System.out.println("5. Slet en superhelt");
        System.out.println("9. Afslut");
        System.out.print("> ");
    }

    // viser info for en given superhelt
    public void showInfo(Superhero superhero) {
        System.out.printf("1. Superheltenavn: %s\n", superhero.getName());
        System.out.printf("2. Virkeligt navn: %s\n", superhero.getRealName());
        System.out.printf("3. Superkræft: %s\n", superhero.getSuperPower());
        System.out.printf("4. Oprindelsesår: %s\n", superhero.getYearCreated());
        System.out.printf("5. Er menneske: %s\n", superhero.isHuman());
        System.out.printf("6. Styrke: %d\n", superhero.getStrength());
        System.out.println();
    }

    // viser liste af alle superhelte i databasen
    public void showList() {
        System.out.println("Liste af superhelte:");
        System.out.println();
        for (Superhero i: db.heroList) showInfo(i);
    }

    // viser menu, og kører alle metoder for databasen
    public void runDatabase() {
        boolean run = true;
        int choice;
        System.out.println("Velkommen til SUPERHERO UNIVERSET.");
        do {
            databaseMenu();
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> addSuperhero();
                case 2 -> showList();
                case 3 -> search();
                case 4 -> edit();
                case 5 -> delete();
                case 9 -> run = false;
                default -> System.out.println("\nUgyldigt input.\n");
            }
        } while (run);
    }
}
