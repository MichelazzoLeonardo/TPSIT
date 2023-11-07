// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Scuola.Studente;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Studente s1 = new Studente("Leonardo","Michelazzo", LocalDate.of(2005,8,5), "Informatica");
        System.out.print(s1.toString());

        s1.AggiungiVoto(6);
        s1.AggiungiVoto(8);
        s1.AggiungiVoto(4.2F);
        System.out.print(s1.toString());

        System.out.print("media voti: "+s1.CalcolaMediaVoti()+'\n');
        System.out.print("Eta: "+s1.CalcolaEta()+'\n');


        Studente s2 = new Studente("Helmut","Alzheimer", LocalDate.of(1957,2,20), "Chimica");
        System.out.print(s2.toString());

        s2.AggiungiVoto(7);
        s2.AggiungiVoto(6);
        s2.AggiungiVoto(5.75F);
        System.out.print(s2.toString());
        s2.RimuoviVoti();
        System.out.print(s2.toString());

        System.out.print("Eta: "+s2.CalcolaEta()+'\n');
    }
}