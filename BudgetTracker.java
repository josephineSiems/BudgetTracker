/*Budget Tracking Program 
 * Lässt Nutzer Einnahmen und Ausgaben eintragen.
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker{

    public static void main (String[] args)throws IOException{

        System.out.println("\nWillkommen beim BudgetTracker!");
        System.out.println("Um eine neue Einzahlung einzutragen tippe 'einzahlung'");
        System.out.println("Um eine neue Ausgabe einzutragen tippe 'ausgabe'");
        System.out.println("Um Kontostand anzusehen tippe 'kontostand'");
        System.out.println("Um Kontoauszug anzusehen tippe 'kontoauszug'");
        System.out.println("Um das Programm zu beenden tippe 'ende' \n");

        Konto konto = new Konto("name", 0.0f);

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        Boolean loop = true;
        while(loop){
            if (input.equals("einzahlung")){
                konto.askEinnahmen(konto,in);
                konto.saveKontostand(konto); 
            }else if (input.equals("ausgabe")){
                konto.askAusgaben(konto,in);
                konto.saveKontostand(konto); 
            }else if (input.equals("kontostand")){
                konto.getKontostand(konto);
            }else if (input.equals("kontoauszug")){
                konto.getKontoauszug(konto);
            }else if (input.equals("ende")){
                //loop = false;
                break;
            }else {
                System.out.println("Ungültige Eingabe");
            }
            input = in.nextLine();
        }
        in.close();
    }


} 