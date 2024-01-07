package com.tugasbesarpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // Tes Koneksi Ke Database
        String url = "jdbc:mysql://localhost:3306/tbpbo";
        String dbuser = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, dbuser, password);
            System.out.println("Connection Success");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Connection Failed, Please Try Again");
            e.printStackTrace();
        }

        // Method Untuk Membuat Format Date and Time
        DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
        Date tanggal = new Date();

        // Output Date and Time
        System.out.println("\nCurrent Date: " + formatTanggal.format(tanggal));
        System.out.println("Current Time: " + formatJam.format(tanggal));

        // String Manipulation
        String greetingmessage = "good morning, how are you today";
        String opening = greetingmessage.replace("good morning", "hello");
        String introduction = "WELCOME TO NAEVIZ CAR DEALER";
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(opening.toUpperCase());
        System.out.println(introduction.toLowerCase());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Scanner scanner = new Scanner(System.in);

        boolean continueMenu = true;

        // Menampilkan Menu Dengan Perulangan While 
        while (continueMenu) {
            System.out.println("\nMain Menu:");
            System.out.println("----------------");
            System.out.println("1. Buy a Car");
            System.out.println("2. Check Data in Database");
            System.out.println("3. Exit");

            try {
                System.out.print("Please choose what you want to do: ");
                int mainChoice = scanner.nextInt();

                // Percabangan Dengan Switch Case
                switch (mainChoice) {
                    case 1:
                        buyCarMenu();
                        break;
                    case 2:
                        checkDatabaseMenu();
                        break;
                    case 3:
                        continueMenu = false;
                        break;
                    default:
                        System.out.println("Invalid Choice, Please Input A Valid Choice");
                }
            
             // Exeption Handling Dimana Harus Menginput Numerik   
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }

        System.out.println("Thank you for using this program.");
        System.out.println("-------------------------------------");
        System.out.println("Created By : Naufal Adli Dhiaurrahman");
        System.out.println("NIM        : 2211521008");
        System.out.println("Tugas Besar PBO 2023");

        // Array List Collection Framework
        List<String> stringList = new ArrayList<>();

        // Menambah Ke Array
        stringList.add("1. Electric Car");
        stringList.add("2. Gasoline Car");
        stringList.add("3. Hybrid Car");

        // Menampilkan Data Dalam Array
        System.out.println("\nNAEVIZ CAR DEALER");
        System.out.println("List of Products In NAEVIZ Car Dealer:");
        for (String ProductsList : stringList) {
            System.out.println(ProductsList);
        }
        scanner.close();
        
    }

    // Membuat ID Transaksi 
    private static int transactionIdCounter = 1;

    // Menu Pembelian
    private static void buyCarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continueBuyCar = true;

        while (continueBuyCar) {
            System.out.println("\nHere Are Some Of The Products We Offer:");
            System.out.println("---------------------------------------");
            System.out.println("1. Electric Car");
            System.out.println("2. Gasoline Car");
            System.out.println("3. Hybrid Car");
            System.out.println("4. Back to Main Menu");

            try {
                System.out.print("Please Pick Your Preferred Type: ");
                int choice = scanner.nextInt();
                // Pemanggilan Dari Masing-Masing Sub Class
                switch (choice) {
                    case 1:
                        ElectricCar electricCar = new ElectricCar("NAEVIZ Electric", "S-Car Model", 2021, 75);
                        System.out.println("\nElectric Car Information:");
                        electricCar.displayInfo();

                        processTransaction(electricCar, scanner);
                        break;
                    case 2:
                        GasolineCar gasolineCar = new GasolineCar("NAEVIZ", "Standard", 2019, 15);
                        System.out.println("\nGasoline Car Information:");
                        gasolineCar.displayInfo();

                        processTransaction(gasolineCar, scanner);
                        break;
                    case 3:
                        HybridCar hybridCar = new HybridCar("NAEVIZ Hybrid", "Hybrid Model-V", 2023, 40, 10);
                        System.out.println("\nHybrid Car Information:");
                        hybridCar.displayInfo();

                        processTransaction(hybridCar, scanner);
                        break;
                    case 4:
                        continueBuyCar = false;
                        break;
                    // Input Harus Sesuai Dengan Pilihan Yang Ada
                    default:
                        System.out.println("Invalid Choice, Please Input A Valid Choice");
                        continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Exeption Input Harus Numerik
            }
        }
    }

    // Proses Transaksi
    private static void processTransaction(ElectricCar electricCar, Scanner scanner) {
        // Input Jumlah Mobil Yang Ingin Dibeli
        System.out.print("How many of this car would you like to buy? ");
        int quantity = scanner.nextInt();
        int totalPrice = electricCar.getPrice() * quantity;

        int transactionId = generateTransactionId();

        // Input Nama Pembeli
        scanner.nextLine();

        System.out.print("Enter the buyer's name: ");
        String buyerName = scanner.nextLine();

        // Display Detail Transaksi
        System.out.println("\nTransaction Details:");
        System.out.println("------------------------");
        System.out.println("1. Transaction ID : " + transactionId);
        System.out.println("2. Buyer Name     : " + buyerName);
        System.out.println("3. Car Type       : " + electricCar.getClass().getSimpleName());
        System.out.println("4. Quantity       : " + quantity);
        System.out.println("5. Final Price    : Rp." + totalPrice);
        // Memasukan Ke Database Atau Create Dalam Metode CRUD
        addToDatabase(transactionId, buyerName, electricCar.getClass().getSimpleName(), quantity, totalPrice);

    }

    private static void processTransaction(GasolineCar gasolineCar, Scanner scanner) {
        System.out.print("How many of this car would you like to buy? ");
        int quantity = scanner.nextInt();
        int totalPrice = gasolineCar.getPrice() * quantity;

        int transactionId = generateTransactionId();

        scanner.nextLine();

        System.out.print("Enter the buyer's name: ");
        String buyerName = scanner.nextLine();

        System.out.println("\nTransaction Details:");
        System.out.println("------------------------");
        System.out.println("1. Transaction ID : " + transactionId);
        System.out.println("2. Buyer Name     : " + buyerName);
        System.out.println("3. Car Type       : " + gasolineCar.getClass().getSimpleName());
        System.out.println("4. Quantity       : " + quantity);
        System.out.println("5. Final Price    : Rp." + totalPrice);

        addToDatabase(transactionId, buyerName, gasolineCar.getClass().getSimpleName(), quantity, totalPrice);

    }
    
    private static void processTransaction(HybridCar hybridCar, Scanner scanner) {
        System.out.print("How many of this car would you like to buy? ");
        int quantity = scanner.nextInt();
        int totalPrice = hybridCar.getPrice() * quantity;

        int transactionId = generateTransactionId();

        scanner.nextLine();

        System.out.print("Enter the buyer's name: ");
        String buyerName = scanner.nextLine();

        System.out.println("\nTransaction Details:");
        System.out.println("------------------------");
        System.out.println("1. Transaction ID : " + transactionId);
        System.out.println("2. Buyer Name     : " + buyerName);
        System.out.println("3. Car Type       : " + hybridCar.getClass().getSimpleName());
        System.out.println("4. Quantity       : " + quantity);
        System.out.println("5. Final Price    : Rp." + totalPrice);

        addToDatabase(transactionId, buyerName, hybridCar.getClass().getSimpleName(), quantity, totalPrice);

    }

     
     private static final String INSERT_QUERY = "INSERT INTO pembelian (id_transaksi, nama_pembeli, tipe_mobil, jumlah, harga) VALUES (?, ?, ?, ?, ?)";

private static void addToDatabase(int transactionId, String buyerName, String carType, int quantity, int totalPrice) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tbpbo", "root", "");
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

        preparedStatement.setInt(1, transactionId);
        preparedStatement.setString(2, buyerName);
        preparedStatement.setString(3, carType);
        preparedStatement.setInt(4, quantity);
        preparedStatement.setInt(5, totalPrice);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("\nYour order has been added to the database.");
        } else {
            System.out.println("\nFailed to add the order to the database.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Membuat ID Transaksi Baru Untuk Setiap Pembelian
    private static int generateTransactionId() {
        return transactionIdCounter++;
    }

    // Menu Manipulasi Database Dengan CRUD
    private static void checkDatabaseMenu() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("\nDatabase Menu:");
        System.out.println("--------------------");
        System.out.println("1. Read Data");
        System.out.println("2. Update Data");
        System.out.println("3. Delete Data");
        System.out.println("4. Back to Main Menu");
    
        try {
            System.out.print("Please choose what you want to do: ");
            int databaseChoice = scanner.nextInt();
    
            switch (databaseChoice) {
                case 1:
                    readAllDataFromDatabase();
                    break;
                case 2:
                    updateDataInDatabase();
                    break;
                case 3:
                    deleteDataFromDatabase();
                    break;
                case 4:
                
                    break;
                default:
                    System.out.println("Invalid Choice, Please Input A Valid Choice");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); 
        }
    }
    
private static final String SELECT_QUERY = "SELECT * FROM pembelian";
private static final String UPDATE_QUERY = "UPDATE pembelian SET nama_pembeli = ? WHERE id_transaksi = ?";
private static final String DELETE_QUERY = "DELETE FROM pembelian WHERE id_transaksi = ?";

// Menampilkan Data Dengan Fungsi READ
private static void readAllDataFromDatabase() {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tbpbo", "root", "");
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
         ResultSet resultSet = preparedStatement.executeQuery()) {

         // Jika Database Kosong Maka Akan Muncul Pesan Ini
        if (!resultSet.isBeforeFirst()) {
            System.out.println("The database is empty.");
            return;
        }

        // Menampilkan Semua Transaksi Yang Ada Di Database
        System.out.println("Data dalam tabel pembelian:");
        while (resultSet.next()) {
            int id_transaksi = resultSet.getInt("id_transaksi");
            String nama_pembeli = resultSet.getString("nama_pembeli");
            String tipe_mobil = resultSet.getString("tipe_mobil");
            int jumlah = resultSet.getInt("jumlah");
            int harga = resultSet.getInt("harga");

            System.out.println("\nTransaction ID  : " + id_transaksi);
            System.out.println("Buyer Name      : " + nama_pembeli);
            System.out.println("Car Type        : " + tipe_mobil);
            System.out.println("Quantity        : " + jumlah);
            System.out.println("Price           : " + harga);
            System.out.println("------------------------------------");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}



// Mengupdate Data Dengan Fungsi UPDATE
private static void updateDataInDatabase() {
    Scanner scanner = new Scanner(System.in);

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tbpbo", "root", "");
         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

        System.out.print("Enter the Transaction ID you want to update: ");
        int transactionId = scanner.nextInt();
        scanner.nextLine();  
        // Menggunakan ID Transaksi Untuk Menapilkan Data Yang Ingin Diubah

        // Melihat Apakah Ada Atau Tidak
        if (!isTransactionIdExists(transactionId)) {
            System.out.println("Transaction ID not found.");
            return;
        }

        // Update Yang Dapat Dilakukan Adalah Update Nama Pelanggan
        System.out.print("Enter the new Buyer Name: ");
        String newBuyerName = scanner.nextLine();

        preparedStatement.setString(1, newBuyerName);
        preparedStatement.setInt(2, transactionId);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("\nYour order has been updated in the database.");
        } else {
            System.out.println("\nFailed to update the order in the database.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private static boolean isTransactionIdExists(int transactionId) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tbpbo", "root", "");
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_transaksi FROM pembelian WHERE id_transaksi = ?")) {

        preparedStatement.setInt(1, transactionId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next(); 
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
// Menghapus Data Dengan Fungsi DELETE
private static void deleteDataFromDatabase() {
    Scanner scanner = new Scanner(System.in);

    // Masukan ID Transaksi Untuk Transaksi Yang Ingin Dihapus
    System.out.print("Enter the Transaction ID you want to delete: ");
    int transactionIdToDelete = scanner.nextInt();

    String DELETE_QUERY = "DELETE FROM pembelian WHERE id_transaksi = ?";

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tbpbo", "root", "");
         PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

        preparedStatement.setInt(1, transactionIdToDelete);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("\nTransaction ID " + transactionIdToDelete + " Has Been Deleted From The Database.");
        } else {
            System.out.println("\nNo Data Found For Transaction ID " + transactionIdToDelete + " In The Database.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        scanner.nextLine(); 
    }
}


    
}
