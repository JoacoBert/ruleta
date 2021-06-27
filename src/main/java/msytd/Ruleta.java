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
    int maximoDineroObtenido = 0;
    int totalTiros = 0;

    boolean victoriaOpcionUno;

    public void limpiarVariables() {
        this.dineroDisponible = 500;
        this.dineroApostado = 30;
        this.diasApostando = 0;
        this.maximoDineroObtenido = 0;
        this.totalTiros = 0;
    }

    public void limpiarConsola() throws IOException {
        canalSalida.println("Presione una tecla para volver al Menu Principal");
        String option = canalEntrada.readLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public int tirar(boolean sinCero) {

        Random random = new Random();
        // Generar aleatorio entre 0 y 36
        int generarAleatorio = random.nextInt(37);

        if (sinCero && (generarAleatorio == 0)) {
            tirar(true);
            // System.out.println("Este numero no puede salir: " + generarAleatorio);
        }

        return generarAleatorio;

    }

    public void controlJugada(int numeroRuleta) {

        if (colorNumero[numeroRuleta] == "Rojo") {
            // System.out.println("ROJO el " + numeroRuleta);
            calcularApuesta(dineroApostado, true);
        } else if (colorNumero[numeroRuleta] == "Verde") {
            // System.out.println("VERDE el " + numeroRuleta);
            calcularApuesta(dineroApostado, false);
        } else {
            // System.out.println("NEGRO el " + numeroRuleta);
            calcularApuesta(dineroApostado, false);

        }

    }

    public int calcularApuesta(int apuesta, boolean gano) {
        if (gano) {
            this.dineroDisponible = this.dineroDisponible + apuesta;
            // System.out.println("En este momento tengo " + dineroDisponible + " pesos");
        } else {
            this.dineroDisponible = this.dineroDisponible - apuesta;
            // System.out.println("En este momento tengo " + dineroDisponible + " pesos");
        }

        return this.dineroDisponible;

    }

    public boolean controlDineroDisponible() {

        if ((dineroDisponible >= 30) && (dineroDisponible <= 3600)) {

            if (dineroDisponible > maximoDineroObtenido) {
                // System.out.println("Tengo mas plata que antes");
                maximoDineroObtenido = dineroDisponible;
            }
            return true;
        } else {
            return false;
        }
    }

    public void almacenarEstadisticas();



    public boolean opcionUno() throws InterruptedException, IOException {
        if (ruleta(100, false)) {
            this.diasApostando = this.diasApostando + 1;
            opcionUno();
        } else {
            if (controlSalida()) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("------------------------------------------------------");
                System.out.println("---------------  Opción: 100 tiros   -----------------");
                System.out.println("------------------------------------------------------");
                System.out.println("GANOOOOOOOO!!!! ");
                System.out.println("Dias apostando: " + this.diasApostando + " dias");
                System.out.println("Dinero ganado: " + this.dineroDisponible + " pesos");
                limpiarVariables();
                almacenarEstadisticas();
                //limpiarConsola();
                victoriaOpcionUno = true;

            } else {

                System.out.print("\033[H\033[2J");
                System.out.flush();
                this.totalTiros = this.totalTiros + (this.diasApostando * 100);
                System.out.println("------------------------------------------------------");
                System.out.println("---------------  Opción: 100 tiros   -----------------");
                System.out.println("------------------------------------------------------");
                System.out.println("Llego a la Ruina!!!");
                System.out.println("Total tiros: " + this.totalTiros);
                System.out.println("Máximo dinero obtenido: " + this.maximoDineroObtenido);
                System.out.println("Perdio el dinero luego de " + this.diasApostando + " dias");
            
                limpiarVariables();
                //limpiarConsola();
                victoriaOpcionUno = false;

            }
        }
        return victoriaOpcionUno;

    }

    public void opcionUnoRep(int cantidadRepeteciones) throws IOException, InterruptedException {

        int victorias = 0;
        for (int i = 0; i < cantidadRepeteciones; i++) {
            if (opcionUno()){
                victorias ++;
            }
        }
        System.out.println("Victorias: " + victorias);
        limpiarConsola();

    }

    public void opcionDos() throws InterruptedException, IOException {
        if (ruleta(200, false)) {
            // System.out.println("Dia " + this.diasApostando);
            // System.out.println("Dinero disponible: " + this.dineroDisponible);
            this.diasApostando = this.diasApostando + 1;
            this.dineroDisponible = 500;
            opcionDos();
        } else {
            if (controlSalida()) {

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("------------------------------------------------------");
                System.out.println("---------------  Opción: 200 tiros   -----------------");
                System.out.println("------------------------------------------------------");
                System.out.println("GANOOOOOOOO!!!! ");
                System.out.println("Dias apostando: " + this.diasApostando + " dias");
                System.out.println("Dinero ganado: " + this.dineroDisponible + " pesos");
                limpiarVariables();
                limpiarConsola();

            } else {
                this.totalTiros = this.totalTiros + (this.diasApostando * 200);

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("------------------------------------------------------");
                System.out.println("---------------  Opción: 200 tiros   -----------------");
                System.out.println("------------------------------------------------------");
                System.out.println("Llego a la Ruina!!!");
                System.out.println("Total tiros: " + this.totalTiros);
                System.out.println("Máximo dinero obtenido: " + this.maximoDineroObtenido);
                System.out.println("Perdio el dinero luego de " + this.diasApostando + " dias");
                limpiarVariables();
                limpiarConsola();

            }

        }

    }

    public void opcionCuatro() throws IOException, InterruptedException {
        if (ruleta(200, true)) {
            // System.out.println("Dia " + this.diasApostando);
            // System.out.println("Dinero disponible: " + this.dineroDisponible);
            this.diasApostando = this.diasApostando + 1;
            this.dineroDisponible = 500;
            opcionCuatro();
        } else {
            if (controlSalida()) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("------------------------------------------------------");
                System.out.println("---------------  Opción: 200 tiros   -----------------");
                System.out.println("------------------------------------------------------");
                System.out.println("GANOOOOOOOO!!!! ");
                System.out.println("Dias apostando: " + this.diasApostando + " dias");
                System.out.println("Dinero ganado: " + this.dineroDisponible + " pesos");
                limpiarVariables();
                limpiarConsola();

            } else {
                this.totalTiros = this.totalTiros + (this.diasApostando * 200);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("------------------------------------------------------");
                System.out.println("---------------  Opción: 200 tiros   -----------------");
                System.out.println("------------------------------------------------------");
                System.out.println("Llego a la Ruina!!!");
                System.out.println("Total tiros: " + this.totalTiros);
                System.out.println("Máximo dinero obtenido: " + this.maximoDineroObtenido);
                System.out.println("Perdio el dinero luego de " + this.diasApostando + " dias");
                limpiarVariables();
                limpiarConsola();

            }

        }

    }

    public boolean controlSalida() {
        if (dineroDisponible >= 3000) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ruleta(int cantidadTiros, boolean sinCero) throws InterruptedException, IOException {

        boolean puedeSeguirApostando = true;

        for (int i = 0; i < cantidadTiros; i++) {
            int numeroRuleta = tirar(sinCero);
            controlJugada(numeroRuleta);
            if (!controlDineroDisponible()) {
                // System.out.println("LLEGUE A " + i + " TIROS");
                this.totalTiros = i;
                i = cantidadTiros + 1;
                puedeSeguirApostando = false;
            }
            // Thread.sleep(2000);
        }

        return puedeSeguirApostando;

    }

}
