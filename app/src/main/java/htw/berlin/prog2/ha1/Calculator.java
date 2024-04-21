package htw.berlin.prog2.ha1;


import java.util.ArrayList;


/**
 * Eine Klasse, die das Verhalten des Online Taschenrechners imitiert, welcher auf
 * https://www.online-calculator.com/ aufgerufen werden kann (ohne die Memory-Funktionen)
 * und dessen Bildschirm bis zu zehn Ziffern plus einem Dezimaltrennzeichen darstellen kann.
 * Enthält mit Absicht noch diverse Bugs oder unvollständige Funktionen.
 */
public class Calculator {

    private String screen = "0";

    private double latestValue;

    private String latestOperation = "";
    private int counterClearKey = 0;
    private ArrayList<String> term = new ArrayList<>();
    private ArrayList<String> termFinal = new ArrayList<>();
    boolean keineFehler = true;

    public String readScreen() {
        return screen;
    }

    /**
     * Empfängt den Wert einer gedrückten Zifferntaste. Da man nur eine Taste auf einmal
     * drücken kann muss der Wert positiv und einstellig sein und zwischen 0 und 9 liegen.
     * Führt in jedem Fall dazu, dass die gerade gedrückte Ziffer auf dem Bildschirm angezeigt
     * oder rechts an die zuvor gedrückte Ziffer angehängt angezeigt wird.
     * @param digit Die Ziffer, deren Taste gedrückt wurde
     */
    public void pressDigitKey(int digit) {
        String neu;
        String vorherigerWert;
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();

        if(screen.equals("0") || latestValue == Double.parseDouble(screen)) screen = "";

        screen = screen + digit;
        if (digit == 0 && !term.isEmpty()){
            vorherigerWert = term.get(term.size()-1);
            if (!vorherigerWert.equals("+") && !vorherigerWert.equals("-") && !vorherigerWert.equals("x") && !vorherigerWert.equals("/")) {
                neu = vorherigerWert + "0";
                term.set(term.size() - 1, neu);
            }
            else{
                term.add(Integer.toString(digit));
            }
        }else {
            term.add(Integer.toString(digit));
        }
    }

    /**
     * Empfängt den Befehl der C- bzw. CE-Taste (Clear bzw. Clear Entry).
     * Einmaliges Drücken der Taste löscht die zuvor eingegebenen Ziffern auf dem Bildschirm
     * so dass "0" angezeigt wird, jedoch ohne zuvor zwischengespeicherte Werte zu löschen.
     * Wird daraufhin noch einmal die Taste gedrückt, dann werden auch zwischengespeicherte
     * Werte sowie der aktuelle Operationsmodus zurückgesetzt, so dass der Rechner wieder
     * im Ursprungszustand ist.
     */
    public void pressClearKey() {
        counterClearKey += 1;
        screen = "0";
        String lastIndex = term.get(term.size()-1);
        if (counterClearKey == 2) {
            latestOperation = "";
            latestValue = 0.0;
            counterClearKey = 0;
            term.clear();
        }else if (!lastIndex.equals("+") && !lastIndex.equals("-") && !lastIndex.equals("x") && !lastIndex.equals("/")){
            term.remove(term.size()-1);
        }

    }

    /**
     * Empfängt den Wert einer gedrückten binären Operationstaste, also eine der vier Operationen
     * Addition, Substraktion, Division, oder Multiplikation, welche zwei Operanden benötigen.
     * Beim ersten Drücken der Taste wird der Bildschirminhalt nicht verändert, sondern nur der
     * Rechner in den passenden Operationsmodus versetzt.
     * Beim zweiten Drücken nach Eingabe einer weiteren Zahl wird direkt des aktuelle Zwischenergebnis
     * auf dem Bildschirm angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * @param operation "+" für Addition, "-" für Substraktion, "x" für Multiplikation, "/" für Division
     */
    public void pressBinaryOperationKey(String operation)  {
        String vorherigerWert;
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
        vorherigerWert = term.isEmpty() ? "0" : term.get(term.size()-1);
        if (!vorherigerWert.equals("+") && !vorherigerWert.equals("-") && !vorherigerWert.equals("x") && !vorherigerWert.equals("/")) {
            term.add(operation);
        }

    }

    /**
     * Filtert aus der ArrayList term Multiplikation- und Divisionsoperationen heraus und berechnet das Ergebnis zweier
     * Zahlen, zwischen denen diese Operationen stehen. Die Ergebnisse, andere Rechenzeichen sowie Zahlen, mit denen
     * bisher keine Berechnungen durchgeführt wurden, werden der ArrayList termFinal hinzugefügt.
     * Auf diese Weise wird sichergestellt, dass die Regel Punkt- vor Strichrechnung bei weiteren Berechnungen eingehalten wird.
     * Bei einer Division durch null wird auf dem Bildschirm 'Error' angezeigt.
     */
    public void analysiereTerm(){
        String op;
        for(int i = 0; i < term.size(); i++){
            if (i == 0){
                termFinal.add(term.get(i));
            }
            else if (term.get(i).equals("x") || term.get(i).equals("/")){
                op = term.get(i);
                double partResult;
                if (op.equals("x")){
                    partResult = Double.parseDouble(term.get(i-1)) * Double.parseDouble(term.get(i+1));
                }
                else{
                    try{
                       partResult = Double.parseDouble(term.get(i-1)) / Double.parseDouble(term.get(i+1));
                    }
                    catch (ArithmeticException err){
                        keineFehler = false;
                        break;
                    }
                }

                termFinal.remove(termFinal.size()-1);
                termFinal.add(Double.toString(partResult));
            }
            else if (!(term.get(i-1).equals("x")) && !(term.get(i-1).equals("/"))){
                termFinal.add(term.get(i));
            }
        }
    }

    /**
     * Empfängt den Wert einer gedrückten unären Operationstaste, also eine der drei Operationen
     * Quadratwurzel, Prozent, Inversion, welche nur einen Operanden benötigen.
     * Beim Drücken der Taste wird direkt die Operation auf den aktuellen Zahlenwert angewendet und
     * der Bildschirminhalt mit dem Ergebnis aktualisiert.
     * @param operation "√" für Quadratwurzel, "%" für Prozent, "1/x" für Inversion
     */
    public void pressUnaryOperationKey(String operation) {
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
        var result = switch(operation) {
            case "√" -> Math.sqrt(Double.parseDouble(screen));
            case "%" -> Double.parseDouble(screen) / 100;
            case "1/x" -> 1 / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if(screen.equals("NaN")) screen = "Error";
        if(screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);

    }

    /**
     * Empfängt den Befehl der gedrückten Dezimaltrennzeichentaste, im Englischen üblicherweise "."
     * Fügt beim ersten Mal Drücken dem aktuellen Bildschirminhalt das Trennzeichen auf der rechten
     * Seite hinzu und aktualisiert den Bildschirm. Daraufhin eingegebene Zahlen werden rechts vom
     * Trennzeichen angegeben und daher als Dezimalziffern interpretiert.
     * Beim zweimaligem Drücken, oder wenn bereits ein Trennzeichen angezeigt wird, passiert nichts.
     */
    public void pressDotKey() {
        String neu;
        if(!screen.contains(".")) {
            screen = screen + ".";
            neu = term.get(term.size() - 1) + ".";
            term.set(term.size() - 1, neu);
        }
    }

    /**
     * Empfängt den Befehl der gedrückten Vorzeichenumkehrstaste ("+/-").
     * Zeigt der Bildschirm einen positiven Wert an, so wird ein "-" links angehängt, der Bildschirm
     * aktualisiert und die Inhalt fortan als negativ interpretiert.
     * Zeigt der Bildschirm bereits einen negativen Wert mit führendem Minus an, dann wird dieses
     * entfernt und der Inhalt fortan als positiv interpretiert.
     */
    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
        term.set(term.size()-1, screen);

    }

    /**
     * Empfängt den Befehl der gedrückten "="-Taste.
     * Wurde zuvor keine Operationstaste gedrückt, passiert nichts.
     * Wurde zuvor eine binäre Operationstaste gedrückt und zwei Operanden eingegeben, wird das
     * Ergebnis der Operation angezeigt. Es ist auch möglich einen längeren Term mit mehreren Operationen und Operanden
     * auszuwerten. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * Wird die Taste weitere Male gedrückt (ohne andere Tasten dazwischen), so wird die letzte
     * Operation (ggf. inklusive letztem Operand) erneut auf den aktuellen Bildschirminhalt angewandt
     * und das Ergebnis direkt angezeigt.
     */
    public void pressEqualsKey() {
        analysiereTerm();
        if (keineFehler == false){
            screen = "Error";
        }
        else {
            double result = 0;
            String op;
            for (int i = 0; i < termFinal.size(); i++) {
                if (result == 0) {
                    result = Double.parseDouble(termFinal.get(i));
                } else if (termFinal.get(i).equals("+")) {
                    result += Double.parseDouble(termFinal.get(i + 1));
                } else if (termFinal.get(i).equals("-")) {
                    result -= Double.parseDouble(termFinal.get(i + 1));
                }
            }
            screen = Double.toString(result);
            if (screen.equals("Infinity")) screen = "Error";
            if (screen.endsWith(".0")) screen = screen.substring(0, screen.length() - 2);
            if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);
        }
    }
}
