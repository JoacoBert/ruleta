package msytd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    boolean victoriaOpcion;
    ArrayList diasArrayList = new ArrayList();
    ArrayList dineroArrayList = new ArrayList();

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

        if ((dineroDisponible >= 30) && (dineroDisponible <= 3000)) {

            if (dineroDisponible > maximoDineroObtenido) {
                // System.out.println("Tengo mas plata que antes");
                maximoDineroObtenido = dineroDisponible;
            }
            return true;
        } else {
            return false;
        }
    }

    public void almacenarEstadisticas() throws IOException {
        diasArrayList.add(this.diasApostando);
        dineroArrayList.add(this.dineroDisponible);
    }

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
                almacenarEstadisticas();
                limpiarVariables();
                // limpiarConsola();
                victoriaOpcion = true;

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
                // limpiarConsola();
                victoriaOpcion = false;

            }
        }
        return victoriaOpcion;

    }

    public void opcionUnoRep(int cantidadRepeteciones) throws IOException, InterruptedException {

        int victorias = 0;
        for (int i = 0; i < cantidadRepeteciones; i++) {
            if (opcionUno()) {
                victorias++;
            }
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Total intentos: " + cantidadRepeteciones);
        System.out.println("Victorias: " + victorias);
        System.out.println("Derrotas: " + (cantidadRepeteciones - victorias));
        canalSalida.println("Presione una tecla para ver las estadisticas de las victorias");
        String option = canalEntrada.readLine();

        mostrarEstadisticas();

    }

    public void mostrarEstadisticas() throws IOException {
        canalSalida.println(
                "||========================================================================================||");
        canalSalida.println(
                "||  Nº. VICTORIA  || DIAS APOSTANDO ||  DINERO GANADO ||  Nº. INTENTO ||  CANTIDAD TIROS  ||");
        canalSalida.println(
                "||----------------------------------------------------------------------------------------||");

        for (int j = 0; j < diasArrayList.size(); j++) {

            canalSalida.println("||           " + (j + 1) + "          ||            " + diasArrayList.get(j)
                    + "          ||        " + dineroArrayList.get(j) + "           ||");
            canalSalida.println("||-------------------------------------------------------------------------||");
        }

        diasArrayList.clear();
        dineroArrayList.clear();
        limpiarConsola();
    }

    public boolean opcionDos() throws InterruptedException, IOException {
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
                almacenarEstadisticas(this.diasApostando, this.dineroDisponible);
                limpiarVariables();
                limpiarConsola();
                victoriaOpcion = true;

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
                // limpiarConsola();
                victoriaOpcion = false;

            }

        }
        return victoriaOpcion;

    }

    public void opcionDosRep(int cantidadRepeteciones) throws IOException, InterruptedException {

        int victorias = 0;
        for (int i = 0; i < cantidadRepeteciones; i++) {
            if (opcionDos()) {
                victorias++;
            }
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Total intentos: " + cantidadRepeteciones);
        System.out.println("Victorias: " + victorias);
        System.out.println("Derrotas: " + (cantidadRepeteciones - victorias));
        canalSalida.println("Presione una tecla para ver las estadisticas de las victorias");
        String option = canalEntrada.readLine();

        canalSalida.println("||=========================================================================||");
        canalSalida.println("||     Nº. VICTORIA     ||     DIAS APOSTANDO     ||     DINERO GANADO     ||");
        canalSalida.println("||-------------------------------------------------------------------------||");

        for (int j = 0; j < diasArrayList.size(); j++) {

            canalSalida.println("||           " + (j + 1) + "          ||            " + diasArrayList.get(j)
                    + "          ||        " + dineroArrayList.get(j) + "           ||");
            canalSalida.println("||-------------------------------------------------------------------------||");
        }

        diasArrayList.clear();
        dineroArrayList.clear();
        limpiarConsola();

    }

    public boolean opcionCuatro() throws IOException, InterruptedException {
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
                almacenarEstadisticas(this.diasApostando, this.dineroDisponible);
                limpiarVariables();
                // limpiarConsola();
                victoriaOpcion = true;
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
                // limpiarConsola();
                victoriaOpcion = false;

            }

        }

        return victoriaOpcion;

    }

    public void opcionCuatroRep(int cantidadRepeteciones) throws IOException, InterruptedException {

        int victorias = 0;
        for (int i = 0; i < cantidadRepeteciones; i++) {
            if (opcionCuatro()) {
                victorias++;
            }
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Total intentos: " + cantidadRepeteciones);
        System.out.println("Victorias: " + victorias);
        System.out.println("Derrotas: " + (cantidadRepeteciones - victorias));
        canalSalida.println("Presione una tecla para ver las estadisticas de las victorias");
        String option = canalEntrada.readLine();

        canalSalida.println(
                "||========================================================================================||");
        canalSalida.println(
                "||  Nº. VICTORIA  || DIAS APOSTANDO ||  DINERO GANADO ||  Nº. INTENTO ||  CANTIDAD TIROS  ||");
        canalSalida.println(
                "||----------------------------------------------------------------------------------------||");

        for (int j = 0; j < diasArrayList.size(); j++) {

            canalSalida.println("||           " + (j + 1) + "          ||            " + diasArrayList.get(j)
                    + "          ||        " + dineroArrayList.get(j) + "           ||");
            canalSalida.println("||-------------------------------------------------------------------------||");
        }

        diasArrayList.clear();
        dineroArrayList.clear();
        limpiarConsola();

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
