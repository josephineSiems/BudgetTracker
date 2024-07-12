import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Konto{
    public float kontostand;
    public String kontoName;

    public Konto(String name, float kontoStand ){
        kontostand = kontoStand;
        kontoName = name;
    }
    
    
    //fragt Kontostand ab und zeigt ihn dem Butzer an
    public void getKontostand(Konto konto)throws IOException{
        File file = new File("Kontostand.txt");

        //wenn Datei existiert wird Kontostand ausgelesen
        if(file.exists() && file != null){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            try{
            konto.kontostand = Float.parseFloat(line);
            }catch(Exception e){
                System.out.println(":(");
            }
            reader.close();
        //wenn Datei noch nicht existiert, wird sie erstellt
        }else{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.close();
        }

        //Kontostand wird dem Benutzer angezeigt
        System.out.println("Account balance: " + konto.kontostand);
    }

    //Zeigt alle bisherigen Einnahmen und Ausgaben an
    public void getKontoauszug(Konto konto)throws IOException{
        File file = new File("Kontoauszug.txt");
        if(file.exists()){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while(line != null){
            System.out.print((line + "\n"));
            line = reader.readLine();
            }
            System.out.print(("\n"));
            reader.close();
        }
    }

    //Fragt Nutzer ob er Ausgaben eintragen möchte
    public void askAusgaben(Konto konto, Scanner in)throws IOException{
        System.out.println("New spending: ");

        while(!(in.hasNextFloat())){
            System.out.println("Please type in a number!");
            in.next();
        }

        if(in.hasNextFloat()){
            float ausgabe = in.nextFloat();

            if (ausgabe != 0.0f){
                System.out.println("Purpose: ");
                String zweck = in.next();
                konto.ausgeben(ausgabe, zweck);
            }
        }
    }

    //Fragt Benutzer ob neue Einnahmen eingetragen werden sollen
    public void askEinnahmen(Konto konto, Scanner in)throws IOException{
        System.out.println("New earning: ");
        while(!(in.hasNextFloat())){
            System.out.println("Please type in a number!");
            in.next();
        }
        if(in.hasNextFloat()){
            float einnahme = in.nextFloat();

            if (einnahme != 0.0f){
                System.out.println("Purpose: ");
                String zweck = in.next();
                konto.einzahlen(einnahme, zweck);
            }
        }
    }

    //Erhöht Kontostand um Einzahlung und trägt Einzahlung ein
    public void einzahlen(float einzahlung, String kontext)throws IOException{
        //add einzahlung zu Kontostand
        kontostand+= einzahlung;
        //kontext und einnahmen in Liste der Einzahlungen
        File file = new File("Kontoauszug.txt");
        BufferedWriter writer;
        if(file.exists()){
            writer = new BufferedWriter(new FileWriter(file, true));
        }else{
            writer = new BufferedWriter(new FileWriter(file));
            
        }
        writer.append("+" + einzahlung + " " + kontext + "\n");
        writer.close(); 
    }

    //Verringert Kontostand um Ausgabe und trägt Ausgabe ein
    public void ausgeben(float ausgabe, String kontext)throws IOException{
        //subtract ausgaben von Kontostand
        kontostand-= ausgabe;
        //kontext und ausgaben in Liste der Ausgaben
        BufferedWriter writer = new BufferedWriter(new FileWriter("Kontoauszug.txt", true));
        writer.append("-" + ausgabe + " " + kontext + "\n");
        writer.close();

    }

    //speichert Kontostand
    public void saveKontostand(Konto konto)throws IOException{
        System.out.println("New balance: " + konto.kontostand);
        BufferedWriter writer = new BufferedWriter(new FileWriter("Kontostand.txt", false));
        writer.write(String.valueOf(konto.kontostand));
        writer.close();
    }
}
