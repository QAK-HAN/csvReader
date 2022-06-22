import java.io.*; // Methode import um auf die Datai zugreifen zu könnnen.
import java.util.Scanner;  // Methode import um auf um den scanner benutzen zu könnnen.

public class Notenauswertung {

    public static void main(String[] args) throws IOException {
        int arrayGroesse = zeihlenZaehlen(); // Hier habe ich eine Variable initialisiert die aus der Methode zeilenzählen kommt. (genauere infos stehen bei der Methode)
        int[][] hauptArray = new int[arrayGroesse][2]; // hier ist mein Haut Array das eine Abhängige zeilengroesse hat
        hauptArray = csvAuslesung(hauptArray); // hier wird mein Haupt Array von dem csvArray aus der der csvAuslesung Methode überschrieben bzw. inizialisiert.
        int[] notenKatastrophe = notenspiegelRechner(hauptArray); //hier habe ich ein Neues Array erstellt und es mit dem Array aus der Methode noten kontkatastrophe überschrieben.
        for (int i = 0; i < notenKatastrophe.length;i++){ 
            System.out.println(notenKatastrophe[i]);
        }
        int htmlBesteNote = bestNote(hauptArray);
        ausgabeHtml(notenKatastrophe, htmlBesteNote);
        

    }

    public static int zeihlenZaehlen() throws FileNotFoundException { // hier habe. ich eine Methode um die anzahl der zeilen aus der csv datai zu zählen.

        File getCSVFiles = new File("Notenliste.csv"); // hier wird auf die datai zugegriffen. der genaue pfad wurde bei mir nicht benötigt, da meine datai im selben ordner ist.
        Scanner sc = new Scanner(getCSVFiles);// hier wird mithilfe des Scanners die daten der Csv herausgelesen bzw. das herauslesen wird ermöglicht.

        int h = 0; //Dies ist eien Variable die ich als counter menutzten möchte damit ich die Array länge nicht immert geändert werden muss.
        while (sc.hasNext()) { // Hier habe ich eine while schleife die solange diie methode dursc##chführt bis es keine neue zeile mehr gibt.
            sc.nextLine(); // sc.next.Line verlangt dann auch nach der neuen zeile die sc.hasNext mir aussucht. Das ist wichtig damit die Methode auch aufhört.
            h++;// variable wird addiert. (counter)
        }
        sc.close(); // damit es schöner aussieht. (benutzten die Variable sc nicht wirklich)
        return h; // returnt nur den Counter.
    }

    public static int[][] csvAuslesung(int csvArray[][]) throws FileNotFoundException {// liest die csv dataien und packt sie in einem array.

        File getCSVFiles = new File("Notenliste.csv");
        Scanner sc = new Scanner(getCSVFiles);

       
        int h = -1;
        while (sc.hasNext()) {

            String zwischwenVariable = sc.nextLine();
            String[] zwischenArray = zwischwenVariable.split(",");
            if (h == -1){
                h++;
                continue;
            }

            int fugeEins = Integer.parseInt(zwischenArray[0]);
            int fugeZwei = Integer.parseInt(zwischenArray[1]);

            csvArray[h][0] = fugeEins;
            csvArray[h][1] = fugeZwei;

            h++;

        }
        sc.close();
        return csvArray;
    }

    public static int[] notenspiegelRechner(int[][] noten) {
        int[] notenspiegel = new int[5];
        for (int i = 0; i < noten.length; i++) {

            if (noten[i][1] > 87) {

                notenspiegel[0] += 1;
            } else if (noten[i][1] > 72) {

                notenspiegel[1] += 1;
            } else if (noten[i][1] > 57) {

                notenspiegel[2] += 1;
            } else if (noten[i][1] > 49) {

                notenspiegel[3] += 1;
            } else {
                notenspiegel[4] += 1;
            }

        }
        return notenspiegel;
    }
    public static int bestNote(int [][] bestNoteArray) {
        int bestNote1 = 0;
        for (int i = 0; i < bestNoteArray.length; i++) {
            if (bestNote1 <= bestNoteArray[i][1]) {
                bestNote1 = bestNoteArray[i][1];
            }
        }
        return bestNote1;
    }

    public static void ausgabeHtml (int[] htmlPunkte, int bestePunkte) throws IOException
    { 
       File htmlFile = new File("htmlDatai.html");
       BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
       writer.write("<!DOCTYPE html> "+"\n" +
       "<html> "+"\n" +
       "<body>"+"\n" +
       
       "<svg height=\"10000\" width=\"10000\">"+"\n" +
       
       "<rect x=\"90\" y=\"35\" width=" + htmlPunkte[0]*10 + " height=\"20\" rx=\"3\" ry=\"3\" fill=\"#2A7BB4\" />"+"\n" +

       "<text x=\"10\" y=\"50\" font-size=\"12\" font-family=\"Arial\" fill=\"#404040\">Note 1: "+htmlPunkte[0]+" </text>"+"\n" +
       
       
       "<rect x=\"90\" y=\"70\" width=" + htmlPunkte[1]*10 + " height=\"20\" rx=\"3\" ry=\"3\" fill=\"#2A7BB4\" />"+"\n" +
       
       "<text x=\"10\" y=\"85\" font-size=\"12\" font-family=\"Arial\" fill=\"#404040\">Note 2: "+htmlPunkte[1]+" </text>"+"\n" +
       
       
       "<rect x=\"90\" y=\"105\" width=" + htmlPunkte[2]*10 + " height=\"20\" rx=\"3\" ry=\"3\" fill=\"#2A7BB4\" />"+"\n" +
       
       "<text x=\"10\" y=\"120\" font-size=\"12\" font-family=\"Arial\" fill=\"#404040\">Note 3: "+htmlPunkte[2]+" </text>"+"\n" +
       
       
       "<rect x=\"90\" y=\"140\" width=" + htmlPunkte[3]*10 + " height=\"20\" rx=\"3\" ry=\"3\" fill=\"#2A7BB4\" />"+"\n" +
       
       "<text x=\"10\" y=\"155\" font-size=\"12\" font-family=\"Arial\" fill=\"#404040\">Note 4: "+htmlPunkte[3]+" </text>"+"\n" +
       
       
       "<rect x=\"90\" y=\"175\" width=" + htmlPunkte[4]*10 + " height=\"20\" rx=\"3\" ry=\"3\" fill=\"#2A7BB4\" />"+"\n" +
       
       "<text x=\"10\" y=\"190\" font-size=\"12\" font-family=\"Arial\" fill=\"#404040\">Note 5: "+htmlPunkte[4]+" </text>"+"\n" +
       
       
       "<text x=\"10\" y=\"250\" font-size=\"12\" font-family=\"Arial\" fill=\"#404040\">Die beste Punktzahl ist " + bestePunkte +" .</text>"+"\n" +
         
       "</svg> "+"\n" +
        
       "</body>"+"\n" +
       "</html>"+"\n");
       writer.close();

    }
    
}

