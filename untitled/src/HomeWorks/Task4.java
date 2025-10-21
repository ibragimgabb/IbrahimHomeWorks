package HomeWorks;

public class Task4 {


    String title;
    String author;
    String isbn;
    int totalCopies;
    int availableCopies;


    public Task4(String title, String author, String isbn, int totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }


    public void borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            System.out.println("Kitab uğurla götürüldü!");
        } else {
            System.out.println("Bağışlayın, kitab qalmayıb.");
        }
    }


    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            System.out.println("Kitab geri qaytarıldı!");
        } else {
            System.out.println("Bütün kitablar artıq mövcuddur!");
        }
    }

    public void printInfo() {
        System.out.println("Kitabın adı: " + title);
        System.out.println("Müəllif: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Ümumi nüsxə sayı: " + totalCopies);
        System.out.println("Mövcud nüsxə sayı: " + availableCopies);
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {

        Task4 book1 = new Task4("Java Programming", "Ibrahim", "02588520", 3);
        Task4 book2 = new Task4("Clean Code", "Ismayil", "58202825", 2);


        book1.printInfo();
        book2.printInfo();

        book1.borrowBook(); // 1 kitab azaldı
        book1.printInfo();  // Yenilənmiş məlumat göstərilir

        book1.returnBook(); // Geri qaytarıldı
        book1.printInfo();  // Yenilənmiş məlumat göstərilir
    }
}