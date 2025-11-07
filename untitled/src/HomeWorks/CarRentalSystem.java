package HomeWorks;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class CarRentalSystem {
    private Set<Car> availableCars = new HashSet<>();
    private Map<Customer, Car> customerToCar = new HashMap<>();
    private Map<Car, LocalDateTime> carToRentTime = new HashMap<>();
    private Map<Car, List<Map.Entry<LocalDateTime, LocalDateTime>>> history = new HashMap<>();

    public void addCar(Car car) {
        availableCars.add(car);
    }

    public void rentCar(Customer c, Car car) {
        if (customerToCar.containsKey(c) || carToRentTime.containsKey(car) || !availableCars.contains(car)) {
            System.out.println("avtomobil doludur!");
            return;
        }
        customerToCar.put(c, car);
        LocalDateTime now = LocalDateTime.now();
        carToRentTime.put(car, now);
        availableCars.remove(car);
        System.out.println(c.getName() + " icarədədir " + car.getModel() + " saat " + now);
    }

    public void returnCar(Customer c) {
        if (!customerToCar.containsKey(c)) {
            System.out.println("icarəçi tapılmadı!");
            return;
        }
        Car car = customerToCar.get(c);
        LocalDateTime rentTime = carToRentTime.get(car);
        LocalDateTime returnTime = LocalDateTime.now();
        Duration duration = Duration.between(rentTime, returnTime);
        long totalHours = duration.toHours();
        long days = duration.toDays();
        String durationStr;
        if (days > 0) {
            durationStr = days + " gün (" + totalHours + " icarə saatı)";
        } else {
            durationStr = totalHours + " Saat";
        }
        System.out.println( c.getName() + " qaytarıldı " + car.getModel() + " sonra " + durationStr);

        history.computeIfAbsent(car, k -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(rentTime, returnTime));

        customerToCar.remove(c);
        carToRentTime.remove(car);
        availableCars.add(car);
    }

    public void printActiveRentals() {
        System.out.println("İcarədə olan:");
        for (Map.Entry<Customer, Car> entry : customerToCar.entrySet()) {
            Customer cust = entry.getKey();
            Car car = entry.getValue();
            LocalDateTime time = carToRentTime.get(car);
            System.out.println(cust.getName() + " -> " + car.getModel() + " (rented at " + time + ")");
        }
    }

    public void printAvailableCars() {
        System.out.println("Boş maşınlar:");
        for (Car car : availableCars) {
            System.out.println(car.toString());
        }
    }

    public void printHistory() {
        System.out.println("Icarə Tarixçəsi:");
        for (Map.Entry<Car, List<Map.Entry<LocalDateTime, LocalDateTime>>> entry : history.entrySet()) {
            Car car = entry.getKey();
            List<Map.Entry<LocalDateTime, LocalDateTime>> rents = entry.getValue();
            // For simplicity, show the last return as in the example; if you want all, loop over rents
            LocalDateTime lastReturn = rents.get(rents.size() - 1).getValue();
            System.out.println(car.getModel() + " Geri qaytarılma vaxtı " + lastReturn);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CarRentalSystem system = new CarRentalSystem();
        Car c1 = new Car(1, "Toyota", "Camry", 2022);
        Car c2 = new Car(2, "BMW", "X5", 2023);
        Car c3 = new Car(3, "Hyundai", "Elantra", 2021);
        Car c4 = new Car(4, "Audi", "A6", 2024);
        system.addCar(c1);
        system.addCar(c2);
        system.addCar(c3);
        Customer u1 = new Customer(101, "Aydin", "AZ12345");
        Customer u2 = new Customer(102, "Nigar", "AZ67890");
        Customer u3 = new Customer(103, "Huseyn", "AZ69580");
        system.rentCar(u1, c1);
        system.rentCar(u3, c2);
        system.printActiveRentals();
        system.printAvailableCars();
        system.returnCar(u1);
        system.printHistory();
        system.printAvailableCars();
    }
}

class Car {
    private int id;
    private String brand;
    private String model;
    private int year;

    public Car(int id, String brand, String model, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ")";
    }
}

class Customer {
    private int id;
    private String name;
    private String licenseNumber;

    public Customer(int id, String name, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " (" + licenseNumber + ")";
    }
}