import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|");
            String command = parts[0];

            switch (command) {
                case "ADD_BOOK":
                    library.addItem(new Book(parts[1], parts[2], parts[3]));
                    System.out.println("OK");
                    break;

                case "ADD_MAGAZINE":
                    library.addItem(new Magazine(parts[1], parts[2], parts[3]));
                    System.out.println("OK");
                    break;

                case "ADD_REFERENCE":
                    library.addItem(new ReferenceBook(parts[1], parts[2], parts[3]));
                    System.out.println("OK");
                    break;

                case "ADD_MEMBER":
                    String id = parts[1];
                    String name = parts[2];
                    String type = parts[3];
                    if (type.equals("STUDENT")) {
                        library.addMember(new Student(id, name));
                    } else if (type.equals("STAFF")) {
                        library.addMember(new Staff(id, name));
                    } else if (type.equals("FACULTY")) {
                        library.addMember(new Faculty(id, name));
                    }
                    System.out.println("OK");
                    break;

                case "INVENTORY":
                    List<LibraryItem> inv = library.inventory();
                    if (inv.isEmpty()) {
                        System.out.println("NONE");
                    } else {
                        for (LibraryItem item : inv) {
                            System.out.println(item.getId() + "|" + item.getType() + "|" + library.getItemStatus(item));
                        }
                    }
                    break;
            }
        }
        scanner.close();
    }
}
