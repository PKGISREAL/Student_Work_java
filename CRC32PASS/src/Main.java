import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Main {
    // хэш функция - это одностороннее преобразование произвольного числа байт
    // в число фиксированного размера (например, для CRC32 - это 32 бита, для MD5 - 128)
    // невозможно получить исходный набор байт по хэшу кроме как перебрать все
    // возможные комбинации (полный перебор, brute force)

    public static void main(String[] args) throws IOException {
        long hash2 = 0x0BA02B6E1L; // хэш CRC32 пароля, который нужно угадать
        File f = new File("10k-most-common.txt");
        String findPass = null;




        try {
            Scanner scan = new Scanner(f);
            String password;

            while (scan.hasNextLine()) {
                String temppass = scan.nextLine();
                for (int i = 0; i < 9999; i++) {
                    password = temppass + i;
                    long hash = calculateCRC32(password);
                    if (hash == hash2){
                        findPass = password;
                        break;
                    }
                }

            }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        if (findPass != null) {
            System.out.println("Пароль найден: " + findPass);
        } else {
            System.out.println("Пароль не найден.");
        }
    }

    private static long calculateCRC32(String input){
        CRC32 crc32 = new CRC32();
        crc32.update(input.getBytes());
        long hash = crc32.getValue();
        return hash;
    }



}