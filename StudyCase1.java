import java.util.Scanner;

public class StudyCase1 {
    
    static String[] namaMahasiswa = new String[100];
    static String[] nimMahasiswa = new String[100];
    static int[][] SKSMatkul = new int[100][5];
    static String[][] kodeMatkulMahasiswa = new String[100][5];
    static String[][] namaMatkulMahasiswa = new String[100][5];
    static int[] totalSKSMahasiswa = new int[100]; 
    static int jumlahMahasiswa = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu = 0;

        while (menu != 4) {
            System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            menu = scanner.nextInt();
            scanner.nextLine(); 

            if (menu == 1) {
                tambahDataKRS(scanner);
            } else if (menu == 2) {
                tampilkanDaftarKRS(scanner);
            } else if (menu == 3) {
                analisisDataKRS();
            } else if (menu == 4) {
                System.out.println("Terima kasih!");
            } else {
                System.out.println("Menu tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    static void tambahDataKRS(Scanner scanner) {
        System.out.println("\nTambah Data KRS");
        System.out.print("Nama Mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.print("NIM: ");
        String nim = scanner.nextLine();

        namaMahasiswa[jumlahMahasiswa] = nama;
        nimMahasiswa[jumlahMahasiswa] = nim;

        int totalSKS = 0;
        boolean tambahLagi = true;
        int matkulIndex = 0;

        while (tambahLagi && matkulIndex < 5) {
            System.out.print("Kode Mata Kuliah: ");
            String kodeMK = scanner.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String namaMK = scanner.nextLine();
            System.out.print("Jumlah SKS (1-3): ");
            int sks = scanner.nextInt();
            scanner.nextLine(); 

            if (sks < 1 || sks > 3) {
                System.out.println("Jumlah SKS tidak valid! SKS harus antara 1 dan 3.");
                
            } else if (totalSKS + sks > 24) {
                System.out.println("Total SKS melebihi batas maksimum 24.");
            } else {
                kodeMatkulMahasiswa[jumlahMahasiswa][matkulIndex] = kodeMK;
                namaMatkulMahasiswa[jumlahMahasiswa][matkulIndex] = namaMK;
                SKSMatkul[jumlahMahasiswa][matkulIndex] = sks;
                totalSKS += sks;
                matkulIndex++;
                System.out.println("Data mata kuliah berhasil ditambahkan.");
            }

            System.out.print("Tambah mata kuliah lain? (y/t): ");
            tambahLagi = scanner.nextLine().equalsIgnoreCase("y");
        }

        totalSKSMahasiswa[jumlahMahasiswa] = totalSKS;
        jumlahMahasiswa++;
        System.out.println("Total SKS yang diambil: " + totalSKS);
    }

    static void tampilkanDaftarKRS(Scanner scanner) {
        System.out.println("\nTampilkan Daftar KRS Mahasiswa");
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();

        boolean dataDitemukan = false;
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (nimMahasiswa[i] != null && nimMahasiswa[i].equals(nim)) {
                dataDitemukan = true;
                System.out.println("\nDaftar KRS:");
                System.out.printf("%-10s %-15s %-10s %-25s %-5s\n", 
                    "NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS");

                int totalSKS = 0;
                for (int j = 0; j < 5; j++) {
                    if (kodeMatkulMahasiswa[i][j] != null) {
                        System.out.printf("%-10s %-15s %-10s %-25s %-5d\n",
                            nimMahasiswa[i], namaMahasiswa[i], kodeMatkulMahasiswa[i][j], 
                            namaMatkulMahasiswa[i][j], SKSMatkul[i][j]);
                        totalSKS += SKSMatkul[i][j];
                    }
                }
                System.out.println("Total SKS: " + totalSKS);
                break;
            }
        }

        if (!dataDitemukan) {
            System.out.println("Data tidak ditemukan untuk NIM: " + nim);
        }
    }
    
    static void analisisDataKRS() {
        System.out.println("\nAnalisis Data KRS");
        int jumlahMahasiswaKurang20 = 0;

        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (totalSKSMahasiswa[i] > 0 && totalSKSMahasiswa[i] < 20) {
                jumlahMahasiswaKurang20++;
            }
        }

        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + jumlahMahasiswaKurang20);
    }
}
