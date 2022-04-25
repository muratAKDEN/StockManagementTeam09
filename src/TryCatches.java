import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatches {

    static Scanner scan = new Scanner(System.in);


    public static int tryCatchesInt() {
        int giris = 0;
        boolean flag1=false;


        do {


            try {

                if (flag1==true){
                    scan.nextLine();        //Dummy1
                    flag1=false;
                }
                giris = scan.nextInt();

                if (giris < 0) {
                    throw new IllegalArgumentException();
                }
                scan.nextLine();        //Dummy1

            } catch (IllegalArgumentException e) {
                flag1=true;
                System.out.println("lütfen pozitif bir sayi giriniz");
            } catch (InputMismatchException e) {
                flag1=true;
                System.out.println("lütfen bir rakam giriniz");
            } catch (Exception e) {
                flag1=true;
                System.out.println("lütfen geçerli bir giriş yapınız");
            }


        } while (flag1);


        return giris;

    }
}