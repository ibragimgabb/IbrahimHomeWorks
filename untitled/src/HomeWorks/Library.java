import java.util.ArrayList;
import java.util.List;
public class Library<T extends Library.Section> {
    private List<T> sections = new ArrayList<>();
    public void addSection(T section) {
        sections.add(section);
    }

    public void displayAllSections() {
        for (T section : sections) {
            System.out.println("Section: " + section.getName());
            section.displayBooks();
        }
    }

    public class Book {
        private String title;
        private String author;
        private int year;

        public Book(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public int getYear() { return year; }

        @Override
        public String toString() {
            return "Book{title='" + title + "', author='" + author + "', year=" + year + "}";
        }
    }
    public static class Section {
        private String name;
        private List<Library<?>.Book> books = new ArrayList<>();
        public Section(String name) { this.name = name; }
        public String getName() { return name; }
        public void addBook(Library<?>.Book book) {
            books.add(book);
        }
        public List<Library<?>.Book> getBooks() { return books; }
        public void displayBooks() {
            for (Library<?>.Book book : books) {
                System.out.println("  - " + book);
            }
        }
    }

    interface BookFilter {
        boolean filter(Library<?>.Book book);
    }
    public void filterBooks(BookFilter filter) {
        System.out.println("Filtered books (based on filter condition):");
        for (T section : sections) {
            for (Library<?>.Book book : section.getBooks()) {
                if (filter.filter(book)) {
                    System.out.println("  - " + book);
                }
            }
        }
    }

    public static void main(String[] args) {
        Library<Section> library = new Library<>();

        Section fiction = new Section("Fiction");
        Section science = new Section("Science");

        Library<Section>.Book b1 = library.new Book("The Martian", "Andy Weir", 2011);
        Library<Section>.Book b2 = library.new Book("Project Hail Mary", "Andy Weir", 2021);
        Library<Section>.Book b3 = library.new Book("A Brief History of Time", "Stephen Hawking", 1988);

        fiction.addBook(b1);
        fiction.addBook(b2);
        science.addBook(b3);

        library.addSection(fiction);
        library.addSection(science);

        library.displayAllSections();

        library.filterBooks(new BookFilter() {
            @Override
            public boolean filter(Library<?>.Book book) {
                return book.getYear() > 2015;
            }
        });
    }
}

