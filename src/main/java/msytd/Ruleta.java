package msytd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

public class Ruleta {

    PrintWriter canalSalida = new PrintWriter(System.out, true);
    BufferedReader canalEntrada = new BufferedReader(new InputStreamReader(System.in));

    // Colores dependiendo del numero
    String colorNumero[] = { "Verde", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo",
            "Negro", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Rojo", "Negro", "Rojo",
            "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Negro", "Rojo", "Negro", "Rojo", "Negro",
            "Rojo", "Negro", "Rojo" };

    int dineroDisponible = 500;
    int dineroApostado = 30;
    int diasApostando = 0;

    public int tirar() {

        Random aleatorio = new Random();
        // Generar aleatorio entre 0 y 36
        int intAletorio = aleatorio.nextInt(37);
        return intAletorio;

    }

    public void controlJugada(int numeroRuleta) {

        if (colorNumero[numeroRuleta] == "Rojo") {
            System.out.println("ROJO el " + numeroRuleta);
            calcularApuesta(dineroApostado, true);
        } else if (colorNumero[numeroRuleta] == "Verde") {
            System.out.println("VERDE el " + numeroRuleta);
            calcularApuesta(dineroApostado, false);
        } else {
            System.out.println("NEGRO el " + numeroRuleta);
            calcularApuesta(dineroApostado, false);

        }

    }

    public int calcularApuesta(int apuesta, boolean gano) {
        if (gano) {
            this.dineroDisponible = this.dineroDisponible + apuesta;
            System.out.println("En este momento tengo " + dineroDisponible + " pe");
        } else {
            this.dineroDisponible = this.dineroDisponible - apuesta;
            System.out.println("En este momento tengo " + dineroDisponible + " pe");
        }

        return this.dineroDisponible;

    }

    public boolean controlDineroDisponible() {

        if ((dineroDisponible >= 30) && (dineroDisponible <= 3000)) {
            return true;
        } else {
            return false;
        }
    }

    public void opcionUno() throws InterruptedException, IOException {
        if (ruleta()) {
            this.diasApostando = this.diasApostando + 1;
            opcionUno();
        } else {

            if (controlSalida()) {
                System.out.println("GANOOOOOOOO!!!! ");
                System.out.println("Dias apostando: " + this.diasApostando + " dias");
                System.out.println("Dinero ganado: " + this.dineroDisponible + " pesos");
            } else {
                System.out.println("Perdio el dinero luego de " + this.diasApostando + " dias");
            }
        }

        /*
         * canalSalida.println("Presione una tecla para volver al Menu Principal");
         * String option = canalEntrada.readLine(); System.out.print("\033[H\033[2J");
         * System.out.flush();
         */

    }

    public boolean controlSalida() {
        if (dineroDisponible >= 3000) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ruleta() throws InterruptedException, IOException {

        boolean puedeSeguirApostando = true;

        for (int i = 0; i < 100; i++) {
            int a = tirar();
            controlJugada(a);
            if (controlDineroDisponible()) {
                System.out.println("TODAVIA TIENE PLATA");
            } else {
                System.out.println("LLEGUE A " + i + " TIROS");
                i = 101;
                puedeSeguirApostando = false;
            }
            // Thread.sleep(2000);
        }

        return puedeSeguirApostando;

    }

}
