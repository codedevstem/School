import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Kristian Os on 11.11.2016.
 */
public class Bibliotek {
    static ArrayList<Bok> books = new ArrayList<>();
    static ArrayList<Utlaan> loanedBooks = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int valg = -1;
        System.out.println("Welcome to the library!");
        while (valg != 0){
            System.out.println("Write 0 to quit.\n" +
                    "Write 1 to register a book.\n" +
                    "Write 2 to remove a book.\n" +
                    "Write 3 to print out a list of all books.\n" +
                    "Write 4 to register a book-loan.\n" +
                    "Write 5 to print out list of loaned books.");
            valg = Integer.parseInt(input.nextLine());
            switch (valg){
                case 0 :
                    System.out.println("Exit. Bye!");
                     break;
                case 1 : AddBook(); break;
                case 2 : RemoveBook(); break;
                case 3 : PrintAllBooks(); break;
                case 4 : LoanBook(); break;
                case 5 : PrintLoanes(); break;
                default :
                    System.out.println("Invalid choice. Try again");
            }
        }
    }

    private static void PrintLoanes() {
        loanedBooks.forEach(loanBook -> System.out.println(loanedBooks.toString()));
    }

    private static void LoanBook() {
        System.out.println("Give ISBN:");
        String ISBN = input.nextLine();
        Bok loanBook = null;
        for (Bok book : books) if (book.getISBN().equals(ISBN)) loanBook = book;
        if (!loanBook.equals(null)){
            System.out.println("Give authors name:");
            String loanerName = input.nextLine();
            loanBook.setAntall(1);
            loanedBooks.add(new Utlaan(loanBook, loanerName));
            books.get(books.indexOf(loanBook)).setAntall(books.get(books.indexOf(loanBook)).getAntall()-1);
        } else {
            System.out.println("No book with " + ISBN + " exists in the database");
        }

    }

    private static void PrintAllBooks() {
        // Simple lambda expressions introduced in java 8. Just a fancy quick loop function
        Collections.sort(books, (o1, o2) -> o1.compareTo(o2));
        books.forEach(bok -> System.out.println(bok.toString()));
    }
    private static void RemoveBook() {
        System.out.println("Give ISBN");
        String removeISBN = input.nextLine();
        Bok toRemove = null;
        for (Bok book :
                books) {
            if (book.getISBN().equals(removeISBN))
                toRemove = book;
                break;
        }
        books.remove(toRemove);
    }
    private static void AddBook() {
        System.out.println("Give ISBN:");
        String ISBN = input.nextLine();
        System.out.println("Give authors name:");
        String authorName = input.nextLine();
        System.out.println("Give titel of the book:");
        String bookTitle = input.nextLine();
        System.out.println("Give numer of examples: [Higher than zero]");
        int numOfExamples = Integer.parseInt(input.nextLine());
        books.add(new Bok(ISBN,authorName,bookTitle,numOfExamples));
    }
}

