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

        System.out.println("\nWelcome to BudgetTracker!");
        System.out.println("To add a new earning, type '1'");
        System.out.println("To add a new spending, type '2'");
        System.out.println("To see your account balance '3'");
        System.out.println("To see all past earnings and spendings, type '4'");
        System.out.println("To close the program, type '5' \n");

        Konto konto = new Konto("name", 0.0f);

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        Boolean loop = true;
        while(loop){
            if (input.equals("1")){
                konto.askEinnahmen(konto,in);
                konto.saveKontostand(konto); 
            }else if (input.equals("2")){
                konto.askAusgaben(konto,in);
                konto.saveKontostand(konto); 
            }else if (input.equals("3")){
                konto.getKontostand(konto);
            }else if (input.equals("4")){
                konto.getKontoauszug(konto);
            }else if (input.equals("5")){
                //loop = false;
                break;
            }else {
                System.out.println("Invalid Command");
            }
            input = in.nextLine();
        }
        in.close();
    }


} 