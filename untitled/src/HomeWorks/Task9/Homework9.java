import java.time.LocalDate;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class Homework9 {

        static class Book {
        private String ad;
        private String muellif;
        private int il;
        private double reytinq;
        private boolean movcuddur;

        public Book(String ad, String muellif, int il, double reytinq, boolean movcuddur) {
            this.ad = ad;
            this.muellif = muellif;
            this.il = il;
            this.reytinq = reytinq;
            this.movcuddur = movcuddur;
        }

        public String getAd() { return ad; }
        public String getMuellif() { return muellif; }
        public int getIl() { return il; }
        public double getReytinq() { return reytinq; }
        public boolean isMovcuddur() { return movcuddur; }

        @Override
        public String toString() {
            return ad + " (" + muellif + ", " + il + ") ⭐" + reytinq;
        }
    }

    static class BorrowRecord {
        private Book kitab;
        private LocalDate goturulen;
        private LocalDate qaytarilan;

        public BorrowRecord(Book kitab, LocalDate goturulen, LocalDate qaytarilan) {
            this.kitab = kitab;
            this.goturulen = goturulen;
            this.qaytarilan = qaytarilan;
        }

        public Book getKitab() { return kitab; }
        public LocalDate getGoturulen() { return goturulen; }
        public LocalDate getQaytarilan() { return qaytarilan; }
        public boolean isQaytarilib() { return qaytarilan != null; }
    }

    static class User {
        private String ad;
        private int yas;
        private List<BorrowRecord> tarixce;

        public User(String ad, int yas, List<BorrowRecord> tarixce) {
            this.ad = ad;
            this.yas = yas;
            this.tarixce = tarixce;
        }

        public String getAd() { return ad; }
        public List<BorrowRecord> getTarixce() { return tarixce; }

        @Override
        public String toString() {
            return ad + " (" + yas + ")";
        }
    }


    static class LibraryService {
        private List<Book> kitablar;
        private List<User> istifadeciler;

        public LibraryService(List<Book> kitablar, List<User> istifadeciler) {
            this.kitablar = kitablar;
            this.istifadeciler = istifadeciler;
        }


        public void siralaKitablar() {
            System.out.println("\n📘 Sıralanmış Kitablar:");

            kitablar.stream()
                    .sorted(
                            Comparator.comparing(Book::getReytinq).reversed()
                                    .thenComparing(Book::getIl)
                                    .thenComparing(Book::getAd)
                    )
                    .forEach(System.out::println);
        }

        public void analiz() {
            System.out.println("\n📊 Kitabxana Analizi:");

                 double ortalama = kitablar.stream()
                    .mapToDouble(Book::getReytinq)
                    .average()
                    .orElse(0);

            System.out.println("Ortalama Reytinq: " + ortalama);

            List<Book> movcud2000 = kitablar.stream()
                    .filter(b -> b.getIl() > 2000 && b.isMovcuddur())
                    .collect(Collectors.toList());
            System.out.println("2000-dən sonra yazılıb və mövcud: " + movcud2000);

            String enCox = istifadeciler.stream()
                    .flatMap(u -> u.getTarixce().stream())
                    .collect(Collectors.groupingBy(
                            r -> r.getKitab().getAd(),
                            Collectors.counting()))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("tapılmadı");
            System.out.println("Ən çox götürülən kitab: " + enCox);

            System.out.println("Hazırda oxunan kitablar:");
            Map<String, List<Book>> oxunan = istifadeciler.stream()
                    .collect(Collectors.toMap(
                            User::getAd,
                            u -> u.getTarixce().stream()
                                    .filter(r -> !r.isQaytarilib())
                                    .map(BorrowRecord::getKitab)
                                    .collect(Collectors.toList())
                    ));

            oxunan.forEach((k, v) -> System.out.println(k + " → " + v));
        }

        public Optional<Book> tovsiye(User user) {
            if (user.getTarixce().isEmpty()) {
                return Optional.empty();
            }


            String sevimli = user.getTarixce().stream()
                    .collect(Collectors.groupingBy(
                            r -> r.getKitab().getMuellif(),
                            Collectors.counting()))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);


            return kitablar.stream()
                    .filter(b -> b.getMuellif().equals(sevimli))
                    .max(Comparator.comparing(Book::getReytinq));
        }


        public Optional<User> ayinOxucusu(int ay, int il) {
            return istifadeciler.stream()
                    .max(Comparator.comparingLong(u ->
                            u.getTarixce().stream()
                                    .filter(r -> r.getGoturulen().getMonthValue() == ay &&
                                            r.getGoturulen().getYear() == il)
                                    .count()
                    ));
        }
    }


    public static void main(String[] args) {

        // Kitablar
        Book b1 = new Book("1984", "George Orwell", 1949, 4.9, true);
        Book b2 = new Book("Animal Farm", "George Orwell", 1945, 4.8, false);
        Book b3 = new Book("Clean Code", "Robert Martin", 2008, 4.7, true);
        Book b4 = new Book("Effective Java", "Joshua Bloch", 2018, 4.9, true);
        Book b5 = new Book("The Pragmatic Programmer", "Andy Hunt", 1999, 4.6, true);
        Book b6 = new Book("Java Concurrency in Practice", "Brian Goetz", 2006, 4.5, false);

        List<Book> kitablar = Arrays.asList(b1, b2, b3, b4, b5, b6);


        User u1 = new User("Aydın", 25, Arrays.asList(
                new BorrowRecord(b1, LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9, 10)),
                new BorrowRecord(b3, LocalDate.of(2025, 10, 5), null)
        ));

        User u2 = new User("Leyla", 22, Arrays.asList(
                new BorrowRecord(b4, LocalDate.of(2025, 10, 2), LocalDate.of(2025, 10, 20)),
                new BorrowRecord(b6, LocalDate.of(2025, 10, 12), null)
        ));

        User u3 = new User("Murad", 28, Arrays.asList(
                new BorrowRecord(b5, LocalDate.of(2025, 9, 10), LocalDate.of(2025, 9, 25))
        ));

        List<User> users = Arrays.asList(u1, u2, u3);

        LibraryService service = new LibraryService(kitablar, users);


        service.siralaKitablar();
        service.analiz();

        System.out.println("\n📖 Aydın üçün tövsiyə:");
        service.tovsiye(u1).ifPresentOrElse(
                b -> System.out.println("Tövsiyə olunan kitab: " + b),
                () -> System.out.println("Tövsiyə tapılmadı")
        );

        System.out.println("\n🏆 Oktyabr 2025 üçün ən çox oxuyan:");
        service.ayinOxucusu(10, 2025).ifPresentOrElse(
                u -> System.out.println("Ən çox oxuyan: " + u.getAd()),
                () -> System.out.println("Heç kim tapılmadı")
        );


        System.out.println("\n⏱ Oxuma müddətləri:");
        users.stream()
                .flatMap(u -> u.getTarixce().stream())
                .filter(BorrowRecord::isQaytarilib)
                .forEach(r -> {
                    long gun = Duration.between(
                            r.getGoturulen().atStartOfDay(),
                            r.getQaytarilan().atStartOfDay()
                    ).toDays();
                    System.out.println(r.getKitab().getAd() + " → " + gun + " günə oxunub");
                });
    }
}
