import java.util.Map;
import java.util.Scanner;

public class DepoIslemler implements MainInterface {
    static int urunID = 1000;
    static Scanner scan = new Scanner(System.in);

    public void girisMenusu() {
        banner();
        girisMenusuSecim();
    }

    private void girisMenusuSecim() {
        System.out.println("1- Urun Tanimlama\n2- urunListele\n3- urunGirisi\n4- urunRafaKoy\n5- urunCikis\n6- Sonlandır\nLütfen seçim yapınız");
        int secim = TryCatches.tryCatchesInt();

        switch (secim) {
            case 1:
                urunTanimla();
                girisMenusuSecim();
                break;
            case 2:
                urunListele();
                girisMenusuSecim();
                break;
            case 3:
                urunGirisi();
                girisMenusuSecim();
                break;
            case 4:
                urunuRafaKoy();
                girisMenusuSecim();
                break;
            case 5:
                urunCikisi();
                girisMenusuSecim();
                break;
            case 6 :
                cikis();
                break;
            default:
                System.out.println("lütfen geçerli bir giris yapiniz");
                girisMenusuSecim();
                break;
        }
    }

    private static void banner() {
        System.out.println("Depo projesi");
        System.out.println("============================");
    }

    @Override
    public void urunTanimla() {
        System.out.println("urunun ismini giriniz");
        String urunIsmi = scan.nextLine();
        System.out.println("urunun Ureticisini giriniz");
        String urunUretici = scan.nextLine();
        System.out.println("urunun Birimini giriniz");
        String urunBirim = scan.nextLine();

        Urunler obje = new Urunler(urunIsmi, urunUretici, urunBirim, 0, "Rafı belli değil");
        Urunler.urunListesiMap.put(++urunID, obje);
    }

    @Override
    public void urunListele() {

        // %-10s

        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Urun ID", "Urun Ismi", "Uretici", "Miktar", "Birim", "Raf");
        System.out.println("============================================================================");

        //Set<Map.Entry<Integer, Urunler>> urnEntry=Urunler.urunListesiMap.entrySet();

        for (Map.Entry<Integer, Urunler> w : Urunler.urunListesiMap.entrySet()) {
            System.out.printf("%-10d %-10s %-10s %-10d %-10s %-10s\n", w.getKey(), w.getValue().getIsim(), w.getValue().getUretici(), w.getValue().getMiktar(), w.getValue().getBirim(), w.getValue().getRaf());
        }
    }

    @Override
    public void urunGirisi() {

        System.out.printf("%-10s %-10s\n", "Urun ID", "Urun Ismi");
        System.out.println("======================");

        for (Map.Entry<Integer, Urunler> w : Urunler.urunListesiMap.entrySet()) {
            System.out.printf("%-10d %-10s \n", w.getKey(), w.getValue().getIsim());
        }


        System.out.println("lütfen ürün ID si giriniz");
        int urunID = TryCatches.tryCatchesInt();
        if (Urunler.urunListesiMap.containsKey(urunID)) {
            System.out.println("lütfen eklemek istediğiniz miktarı giriniz");
            int miktar = TryCatches.tryCatchesInt();
            Urunler.urunListesiMap.get(urunID).setMiktar(Urunler.urunListesiMap.get(urunID).getMiktar() + miktar);


        } else {            //Sonradan ana menü değilde içerde dönüşüm
            System.out.println("ID kayıtlı değildir");

        }


    }

    @Override
    public void urunuRafaKoy() {
        System.out.printf("%-10s %-10s\n", "Urun ID", "Urun Ismi");
        System.out.println("======================");

        for (Map.Entry<Integer, Urunler> w : Urunler.urunListesiMap.entrySet()) {
            System.out.printf("%-10d %-10s \n", w.getKey(), w.getValue().getIsim());
        }
        System.out.println("lütfen ürün ID si giriniz");
        int urunID = TryCatches.tryCatchesInt();
        if (Urunler.urunListesiMap.containsKey(urunID)) {
            System.out.println("lütfen raf bilgisi giriniz");
            String urunRaf =scan.next();
            Urunler.urunListesiMap.get(urunID).setRaf(urunRaf);


        } else {                                             //Sonradan ana menü değilde içerde dönüşüm
            System.out.println("ID kayıtlı değildir");
        }


    }

    @Override
    public void urunCikisi() {
        System.out.printf("%-10s %-10s\n", "Urun ID", "Urun Ismi");
        System.out.println("======================");

        for (Map.Entry<Integer, Urunler> w : Urunler.urunListesiMap.entrySet()) {
            System.out.printf("%-10d %-10s \n", w.getKey(), w.getValue().getIsim());
        }
        System.out.println("lütfen ürün ID si giriniz");
        int urunID = TryCatches.tryCatchesInt();
        if (Urunler.urunListesiMap.containsKey(urunID)) {
            System.out.println("lütfen çıkarmak istediğiniz miktarı giriniz");

            int miktar = TryCatches.tryCatchesInt();
            if (miktar > Urunler.urunListesiMap.get(urunID).getMiktar()) {
                System.out.println("depoda yeterli miktarda ürün yok");
                urunCikisi();
            } else {
                Urunler.urunListesiMap.get(urunID).setMiktar(Urunler.urunListesiMap.get(urunID).getMiktar() - miktar);
            }
        } else {                                             //Sonradan ana menü değilde içerde dönüşüm
            System.out.println("ID kayıtlı değildir");
        }
    }

    @Override
    public void cikis() {
        System.out.println("byby");

    }


}
