package HomeWorks;

public class Task4 {


    private String title;
    private String author;
    private String isbn;
    protected int totalCopies;
    protected int availableCopies;


    {
        System.out.println("New book created!");
    }


    public Task4(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = 1;
        this.availableCopies = 1;
    }


    public void borrowTask4() {
        if (availableCopies > 0) {
            availableCopies--;
            System.out.println("Task4 borrowed successfully.");
        } else {
            System.out.println("Sorry, no copies available.");
        }
    }

    public void returnTask4() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            System.out.println("Task4 returned successfully.");
        } else {
            System.out.println("All copies are already in the library.");
        }
    }

    public void printInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Total Copies: " + totalCopies);
        System.out.println("Available Copies: " + availableCopies);
    }


    public static void libraryRules() {
        System.out.println("Max 3 books can be borrowed per person.");
    }


    public final void bookType() {
        System.out.println("This is a regular book.");
    }


    public static void main(String[] args) {
        // Class-dan obyekt yaradılır
        Task4 book = new Task4("Effective Java", "Joshua Bloch", "123456789");

        // Məlumat çap edilir
        book.printInfo();

        // Metodlar yoxlanılır
        book.borrowTask4();
        book.returnTask4();

        // Static və final metodlar
        Task4.libraryRules();
        book.bookType();
    }
}
