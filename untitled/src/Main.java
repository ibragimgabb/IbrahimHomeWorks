package Lesson5;

import java.util.Scanner;


enum TransportType {
    AVTOBUS,
    TAKSI,
    VELOSEPED
}


abstract class Transport {
    public abstract String getTransportInfo();
    public abstract double calculateFare(double distance, int passengers);
    public abstract double calculateTime(double distance);
}


class Avtobus extends Transport {
    private final double ratePerKm = 0.2; // km başına qiymət
    private final double speed = 40.0;    // km/saat

    @Override
    public String getTransportInfo() {
        return "Bu avtobusdur. Ucuzdur, amma bir az yavaşdır.";
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return distance * ratePerKm * passengers;
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }
}


class Taksi extends Transport {
    private final double ratePerKm = 0.7;
    private final double speed = 60.0;

    @Override
    public String getTransportInfo() {
        return "Bu taksidir. Rahatdır, amma qiymət bir az bahadır.";
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return (distance * ratePerKm) + (passengers * 0.5);
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }
}


class Veloseped extends Transport {
    private final double ratePerKm = 0.0;
    private final double speed = 15.0;

    @Override
    public String getTransportInfo() {
        return "Bu velosipeddir. Ekoloji təmizdir, amma bir az yavaşdır.";
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return ratePerKm; // pulsuz
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }
}


public class Main {


    public static Transport getTransport(TransportType type) {
        switch (type) {
            case AVTOBUS:
                return new Avtobus();
            case TAKSI:
                return new Taksi();
            case VELOSEPED:
                return new Veloseped();
            default:
                throw new IllegalArgumentException("Belə nəqliyyat növü yoxdur!");
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("==== SMART TRANSPORT SYSTEM ====");
        System.out.println("Nəqliyyat növünü seç:");
        System.out.println("1 - Avtobus");
        System.out.println("2 - Taksi");
        System.out.println("3 - Velosiped");
        System.out.print("Seçimin: ");

        int secim = input.nextInt();

        TransportType userChoice;
        switch (secim) {
            case 1:
                userChoice = TransportType.AVTOBUS;
                break;
            case 2:
                userChoice = TransportType.TAKSI;
                break;
            case 3:
                userChoice = TransportType.VELOSEPED;
                break;
            default:
                System.out.println("Yanlış seçim etdiniz!");
                return;
        }

        System.out.print("Məsafəni daxil et (km): ");
        double distance = input.nextDouble();

        System.out.print("Sərnişin sayını daxil et: ");
        int passengers = input.nextInt();


        Transport transport = getTransport(userChoice);


        System.out.println("\n===============================");
        System.out.println("NƏQLİYYAT NÖVÜ: " + userChoice);
        System.out.println("MƏSAFƏ: " + distance + " km");
        System.out.println("SƏRNİŞİN SAYI: " + passengers);
        System.out.println("-------------------------------");
        System.out.println("MƏLUMAT: " + transport.getTransportInfo());
        System.out.println("QİYMƏT: " + transport.calculateFare(distance, passengers) + " AZN");
        System.out.println("TƏXMİNİ VAXT: " + transport.calculateTime(distance) + " saat");
        System.out.println("===============================");
    }
}
