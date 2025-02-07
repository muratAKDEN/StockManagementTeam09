import java.util.Map;
import java.util.Scanner;

public class DepoIslemler implements MainInterface {
    static int urunID = 1000; //Map için
    static Scanner scan = new Scanner(System.in);

    public void girisMenusu() {banner(); // Menünun başına banner eklendi.
        girisMenusuSecim();}

    private void girisMenusuSecim() {
        System.out.println("1- Urun Tanimlama\n2- urunListele\n3- urunGirisi\n4- urunRafaKoy\n5- urunCikis\n6- Sonlandır\nLütfen seçim yapınız");

        int secim = TryCatches.tryCatchesInt();

        // Sürekli olarak tryCatches'lerle uğraşmamak için bir Class ve method oluşturuldu. Class'ta dinamik olarak eklemelere açık bırakıldı.
        // TryCatch sorunları dışında muhtemel dummy problemleri de hesaba katıldı ve çözüldü.
        //Her bir işlem için ayrı method oluşturuldu ve switch ile seçime bağlandı. Kodta kopma olmaması için herbir case ana menuye veya uygun methodlara
        // yönlendirildi.

        switch (secim) {
            case 1:
                urunTanimla();girisMenusuSecim();break;
            case 2:
                urunListele();girisMenusuSecim();break;
            case 3:
                urunGirisi();girisMenusuSecim();break;
            case 4:
                urunuRafaKoy();girisMenusuSecim();break;
            case 5:
                urunCikisi();girisMenusuSecim();break;
            case 6 :
                cikis();break;
            default:
                System.out.println("lütfen geçerli bir giris yapiniz");
                girisMenusuSecim();break;
        }
    }

    private static void banner() {

        System.out.println("Depo projesi");
        System.out.println("============================");
    }

    @Override
    public void urunTanimla() {

        /* Urunler classı oluşturuldu. isim, üretici,birim,miktar,raf parametreli bir cons. create edildi ve Urunler data type'lı objeler bir listeye atandı.
        Urunler Class'ında create edilen  "static Map<Integer,Urunler> urunListesiMap = new HashMap<>()" mapine dinamik olarak her yeni ürün ile değişen
         Integer tipi UrunId ve Urunler data type'ına sahip objeler atandı.

         *Tasklerde bir ürünün tamamen kaldırılması yer almadığı için UrunID için farklı çalışmalar yapılmadı.

         *Daha sonra işleme konu olacak miktar ve raf için default değerler atandı.

         */
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

        // Urun ID 'e bağlı şekilde printf ile düzenlenerek ürünler listesi oluşturuldu.

        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Urun ID", "Urun Ismi", "Uretici", "Miktar", "Birim", "Raf");
        System.out.println("============================================================================");

        for (Map.Entry<Integer, Urunler> w : Urunler.urunListesiMap.entrySet()) {
            System.out.printf("%-10d %-10s %-10s %-10d %-10s %-10s\n", w.getKey(), w.getValue().getIsim(), w.getValue().getUretici(), w.getValue().getMiktar(), w.getValue().getBirim(), w.getValue().getRaf());
        }
    }

    @Override
    public void urunGirisi() {
        //ID üzerinden urun girişi yapılması için method oluşturuldu. ID'nin hatırlanması sorun olmaması için Methodun başına ürünler ve ID'leri hatırlatma
        // olarak eklendi. Scannerdan alınan ID ve miktar değişkenleri try-Catch methodu ile kontrol edildi.
        //* kullanıcının hatırlayamama dışında hatalı girişleri için ana menu, tekrar deneme ve çıkış seçenekleri sunuldu. Tercih'in exception'a yol açmaması
        // için kontrol yapıldı.

        System.out.printf("%-10s %-10s\n", "Urun ID", "Urun Ismi");
        System.out.println("======================");

        for (Map.Entry<Integer, Urunler> w : Urunler.urunListesiMap.entrySet()) {
            System.out.printf("%-10d %-10s \n", w.getKey(), w.getValue().getIsim());}


        System.out.println("lütfen ürün ID si giriniz");
        int urunID = TryCatches.tryCatchesInt();
        if (Urunler.urunListesiMap.containsKey(urunID)) {
            System.out.println("lütfen eklemek istediğiniz miktarı giriniz");
            int miktar = TryCatches.tryCatchesInt();
            Urunler.urunListesiMap.get(urunID).setMiktar(Urunler.urunListesiMap.get(urunID).getMiktar() + miktar);


        } else {
            System.out.println("ID kayıtlı değildir. hatalı işlem yaptınız. Ana menuye donmek için 1'e, ID'i yeniden girmek için 2'e çıkış için 9'a basınız");

            int tercih=TryCatches.tryCatchesInt();

            switch (tercih){

                case 1: girisMenusuSecim();break;
                case 2:urunGirisi();break;
                case 9:cikis();break;

                default:
                    System.out.println("yeniden hatalı giriş yaptınız. Ana menuye yönlendirildiniz.");
                    girisMenusuSecim();break;

            }

        }
    }

    @Override
    public void urunuRafaKoy() {

        /* ID üzerinden urun raf seçimi yapılabilmesi için method oluşturuldu. ID'nin hatırlanması sorun olmaması için Methodun başına ürünler ve ID'leri
         hatırlatma olarak eklendi. Scannerdan alınan ID ve miktar değişkenleri try-Catch methodu ile kontrol edildi. Kullanıcının hatırlayamama dışında
          hatalı girişleri için ana menu, tekrar deneme ve çıkış seçenekleri sunuldu. Tercih'in exception'a yol açmaması için kontrol yapıldı.
         */
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


        } else {   System.out.println("ID kayıtlı değildir. hatalı işlem yaptınız. Ana menuye donmek için 1'e, ID'i yeniden girmek için 2'e çıkış için 9'a basınız");

            int tercih2=TryCatches.tryCatchesInt();

            switch (tercih2){

                case 1: girisMenusuSecim();break;
                case 2:urunGirisi();break;
                case 9:cikis();break;
                default:
                    System.out.println("yeniden hatalı giriş yaptınız. Ana menuye yönlendirildiniz.");
                    girisMenusuSecim();break;
            }
        }
    }

    @Override
    public void urunCikisi() {

        /* ID üzerinden urun çıkışı yapılabilmesi için method oluşturuldu. ID'nin hatırlanması sorun olmaması için Methodun başına ürünler ve ID'leri
         hatırlatma olarak eklendi. Scannerdan alınan ID ve miktar değişkenleri try-Catch methodu ile kontrol edildi. Kullanıcının hatırlayamama dışında
          hatalı girişleri için ana menu, tekrar deneme ve çıkış seçenekleri sunuldu. Tercih'in exception'a yol açmaması için kontrol yapıldı.
           *Depoda olandan fazla ürünün çıkışı istenememesi için kontrol sağlandı.
         */
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
            } else {Urunler.urunListesiMap.get(urunID).setMiktar(Urunler.urunListesiMap.get(urunID).getMiktar() - miktar);}
        } else {  System.out.println("ID kayıtlı değildir. hatalı işlem yaptınız. Ana menuye donmek için 1'e, ID'i yeniden girmek için 2'e çıkış için 9'a basınız");

            int tercih2=TryCatches.tryCatchesInt();

            switch (tercih2){

                case 1: girisMenusuSecim();break;
                case 2:urunGirisi();break;
                case 9:cikis();break;
                default:
                    System.out.println("yeniden hatalı giriş yaptınız. Ana menuye yönlendirildiniz.");
                    girisMenusuSecim();break;
            }
        }
    }

    @Override
    public void cikis() {
        System.out.println("İşlemlerinin tamamlandı.Teşekkür ederiz.");

    }


}
