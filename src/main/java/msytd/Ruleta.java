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

    // Creacion de variables globales
    // Colores dependiendo del numero
    String colorNumero[] = { "Verde", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo",
            "Negro", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Rojo", "Negro", "Rojo",
            "Negro", "Rojo", "Negro", "Rojo", "Negro", "Rojo", "Negro", "Negro", "Rojo", "Negro", "Rojo", "Negro",
            "Rojo", "Negro", "Rojo" };

    int filas[] = { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };

    int dineroDisponible = 500;
    int diasApostando = 0;
    int maximoDineroObtenido = 0;
    int totalTiros = 0;
    int cantIntentos = 0;

    public static int dineroApostado = 30;
    public static int gananciaApuesta = 2;
    public static int numeroApostado;
    public static int numeroApostado2;

    public static String tipoApuesta = "Color";
    public static String colorApuesta = "Rojo";
    public static String mMApuesta;
    public static String pIApuesta;

    boolean victoriaOpcion;
    // ArrayList para almacenar estadisticas
    ArrayList diasArrayList = new ArrayList();
    ArrayList dineroArrayList = new ArrayList();
    ArrayList tirosArrayList = new ArrayList();
    ArrayList intentosArrayList = new ArrayList();

    // Se limpian las variables para la proxima iteracion
    public void limpiarVariables() {
        this.dineroDisponible = 500;
        this.diasApostando = 0;
        this.maximoDineroObtenido = 0;
        this.totalTiros = 0;
    }

    // Se utiliza para limpiar la consola y volver al menu
    public void limpiarConsola() throws IOException {
        canalSalida.println("Presione una tecla para volver al Menu Principal");
        String option = canalEntrada.readLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Genera 2 numeros consecutivos del tablero
    public void dosNumerosConsecutivos() throws IOException {

        int numeroAleatorio = tirar(false);
        int numeroAleatorio2;

        if (numeroAleatorio == 0) {
            numeroAleatorio2 = numeroAleatorio + 1;
        } else if (numeroAleatorio == 36) {
            numeroAleatorio2 = numeroAleatorio - 1;
        } else {
            Random random = new Random();
            int mayorMenor = random.nextInt(2);
            if (mayorMenor == 0) {
                numeroAleatorio2 = numeroAleatorio + 1;

            } else {
                numeroAleatorio2 = numeroAleatorio - 1;
            }
        }

        numeroApostado = numeroAleatorio;
        numeroApostado2 = numeroAleatorio2;

        System.out.println("---------------------------------------------");
        System.out.println("Los números a apostar son el " + numeroApostado + " y el " + numeroApostado2);

    }

    // Genera 4 numeros del tablero
    public void cuatroNumerosConsecutivos() {
        System.out.println("EN PROCESO");
    }

    // Genera un aleatorio que es el inicio de una fila
    public int setCalle() {
        Random random = new Random();
        int iFila = random.nextInt(11);

        System.out.println("Se aposto a la fila que comienza con el número " + filas[iFila]);
        return filas[iFila];
    }

    // Genera un aleatorio que es el inicio de una fila y toma la siguiente o
    // anterior
    public void setSemiCalle() {
        int fila = setCalle();
        int filaContigua;

        if (fila == 1) {
            filaContigua = fila + 3;
        } else if (fila == 34) {
            filaContigua = fila - 3;
        } else {
            Random random = new Random();
            int mayorMenor = random.nextInt(2);
            if (mayorMenor == 0) {
                filaContigua = fila + 3;

            } else {
                filaContigua = fila - 3;
            }
        }

        System.out.println("Se aposto a la fila que comienza con el número " + filaContigua);

        numeroApostado = fila;
        numeroApostado2 = filaContigua;
    }

    // Genera un aleatorio entre 0 y 1, y a partir de ese num selecciona Mayor o
    // Menor
    public String setMayorMenor() {

        Random random = new Random();
        int aleatorio = random.nextInt(2);
        String tipoApuestaMM;

        if (aleatorio == 0) {
            tipoApuestaMM = "Mayor";
        } else {
            tipoApuestaMM = "Menor";
        }

        System.out.println("Se aposto por " + tipoApuestaMM);

        return tipoApuestaMM;
    }

    // Genera un aleatorio entre 0 y 1, y a partir de ese num selecciona Rojo o
    // Negro
    public String setColor() {

        Random random = new Random();
        int aleatorio = random.nextInt(2);
        String tipoApuestaColor;

        if (aleatorio == 0) {
            tipoApuestaColor = "Rojo";
        } else {
            tipoApuestaColor = "Negro";
        }

        System.out.println("Se aposto por " + tipoApuestaColor);
        return tipoApuestaColor;
    }

    // Genera un aleatorio entre 0 y 1, y a partir de ese num selecciona Pares o
    // Nones
    public String setPares() {
        Random random = new Random();
        int aleatorio = random.nextInt(2);
        String tipoApuestaPares;

        if (aleatorio == 0) {
            tipoApuestaPares = "Pares";
        } else {
            tipoApuestaPares = "Nones";
        }

        System.out.println("Se aposto por " + tipoApuestaPares);

        return tipoApuestaPares;

    }

    // Girar ruleta
    public int tirar(boolean sinCero) throws IOException {

        Random random = new Random();
        // Generar aleatorio entre 0 y 36
        int generarAleatorio = random.nextInt(37);

        if (sinCero && (generarAleatorio == 0)) {
            tirar(true);
            // System.out.println("Este numero no puede salir: " + generarAleatorio);
        }

        return generarAleatorio;

    }

    public void setDineroApuesta(int dineroApuesta) {
        this.dineroApostado = dineroApuesta;
    }

    // Setea que tipo de apuesta se selecciono y cuando paga en caso de victoria.
    // Por defecto: Color y Rojo.
    public void setApuesta(String apuestaSeleccionada) throws IOException {
        this.tipoApuesta = apuestaSeleccionada;
        switch (apuestaSeleccionada) {
            case "Numero":
                /*
                 * System.out.println(apuestaSeleccionada); System.out.println(numeroApostado);
                 */
                numeroApostado = tirar(false);
                System.out.println("---------------------------------------------");
                System.out.println("El número a apostar es el " + numeroApostado);
                this.gananciaApuesta = 36;
                break;
            case "2Numeros":
                dosNumerosConsecutivos();
                this.gananciaApuesta = 18;
                break;
            case "Calle":
                numeroApostado = setCalle();
                this.gananciaApuesta = 12;
                break;
            case "4Numeros":
                cuatroNumerosConsecutivos();
                break;
            case "Semicalle":
                setSemiCalle();
                this.gananciaApuesta = 6;
                break;
            case "Menor":
                this.gananciaApuesta = 2;
                this.mMApuesta = setMayorMenor();
                break;
            case "Color":
                this.gananciaApuesta = 2;
                this.colorApuesta = setColor();
                break;
            case "Pares":
                this.gananciaApuesta = 2;
                this.pIApuesta = setPares();
                break;
        }

        limpiarConsola();

    }

    // CONTROLES PARA APUESTAS

    public void controlNumero(int numeroRuleta, int numApostado) {

        if (numeroRuleta == numApostado) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else {
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        }
    }

    public void controlDosNumeros(int numeroRuleta, int numApostado, int numApostado2) {

        if ((numeroRuleta == numApostado) || (numeroRuleta == numApostado2)) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else {
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        }
    }

    public void controlCalle(int numeroRuleta, int numApostado) {

        if ((numeroRuleta == numApostado)
                || (numeroRuleta == (numApostado + 1) || (numeroRuleta == (numApostado + 2)))) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else {
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        }
    }

    public void controlSemiCalle(int numeroRuleta, int numApostado, int numApostado2) {

        if ((numeroRuleta == numApostado)
                || (numeroRuleta == (numApostado + 1) || (numeroRuleta == (numApostado + 2)))) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else if ((numeroRuleta == numApostado2)
                || (numeroRuleta == (numApostado2 + 1) || (numeroRuleta == (numApostado2 + 2)))) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else {
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        }
    }

    public void controlMenor(int numeroRuleta, String mmApuesta) {

        if ((numeroRuleta <= 18) && (mmApuesta == "Menor")) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else if ((numeroRuleta > 18) && (mmApuesta == "Mayor")) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else {
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        }
    }

    public void controlColor(int numeroRuleta, String color) {

        if (colorNumero[numeroRuleta] == color) {
            // System.out.println("ROJO el " + numeroRuleta);
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else {
            // System.out.println("NEGRO el " + numeroRuleta);
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        }
    }

    public void controlPares(int numeroRuleta, String piApuesta) {

        if (numeroRuleta == 0) {
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        } else if ((numeroRuleta % 2 == 0) && (piApuesta == "Pares")) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else if ((numeroRuleta % 2 != 0) && (piApuesta == "Nones")) {
            calcularApuesta(dineroApostado, gananciaApuesta, true);
        } else {
            calcularApuesta(dineroApostado, gananciaApuesta, false);
        }
    }

    // Controla dependiendo la apuesta seleccionada.
    public void controlJugada(int numeroRuleta) {
        // System.out.println(this.tipoApuesta);
        switch (this.tipoApuesta) {
            case "Numero":
                /*
                 * System.out.println("Numero apostado " + this.numeroApostado);
                 * System.out.println("Numero ruleta " + numeroRuleta);
                 */
                controlNumero(numeroRuleta, this.numeroApostado);
                break;
            case "2Numeros":
                controlDosNumeros(numeroRuleta, this.numeroApostado, this.numeroApostado2);
                break;
            case "Calle":
                controlCalle(numeroRuleta, this.numeroApostado);
                break;
            case "4Numeros":
                cuatroNumerosConsecutivos();
                break;
            case "Semicalle":
                controlSemiCalle(numeroRuleta, this.numeroApostado, this.numeroApostado2);
                break;
            case "Menor":
                controlMenor(numeroRuleta, this.mMApuesta);
                break;
            case "Color":
                controlColor(numeroRuleta, this.colorApuesta);
                break;
            case "Pares":
                controlPares(numeroRuleta, this.pIApuesta);
                break;
        }

    }

    // Control para saber si gano o perdio dinero
    public int calcularApuesta(int apuesta, int valorApuesta, boolean gano) {

        // Se resta la apuesta que realizo
        this.dineroDisponible = this.dineroDisponible - apuesta;

        if (gano) {
            /*
             * System.out.println("apuesta " + apuesta); System.out.println("Dinero " +
             * dineroDisponible); System.out.println("Dinero ganado " + (apuesta *
             * valorApuesta));
             */
            this.dineroDisponible = this.dineroDisponible + (apuesta * valorApuesta);
            // System.out.println("En este momento tengo " + dineroDisponible + " pesos");
        }

        return this.dineroDisponible;

    }

    // Control para saber si con el dinero disponible puede seguir apostando
    public boolean controlDineroDisponible() {

        if ((dineroDisponible >= this.dineroApostado) && (dineroDisponible <= 3000)) {

            if (dineroDisponible > maximoDineroObtenido) {
                // System.out.println("Tengo mas plata que antes");
                maximoDineroObtenido = dineroDisponible;
            }
            return true;
        } else {
            return false;
        }
    }

    // Guarda las estadisticas para el final de la demostracion. Cuando se repite n
    // veces
    public void almacenarEstadisticas() throws IOException {
        diasArrayList.add(this.diasApostando);
        dineroArrayList.add(this.dineroDisponible);
        tirosArrayList.add(this.totalTiros);
        intentosArrayList.add(this.cantIntentos);
    }

    // Recorre los arrayList de estadisticas y las muestra en una tabla.
    public void mostrarEstadisticas() throws IOException {

        if (diasArrayList.size() == 0) {
            System.out.println("No hay estadisticas para mostrar porque no hubo victorias");
            limpiarConsola();
        } else {
            System.out.println("Tipo de apuesta: " + this.tipoApuesta);

            canalSalida.println(
                    "||========================================================================================||");
            canalSalida.println(
                    "||  Nº. VICTORIA  || DIAS APOSTANDO ||  DINERO GANADO ||  Nº. INTENTO ||  CANTIDAD TIROS  ||");
            canalSalida.println(
                    "||----------------------------------------------------------------------------------------||");

            for (int j = 0; j < diasArrayList.size(); j++) {

                canalSalida.println("||        " + (j + 1) + "       ||        " + diasArrayList.get(j)
                        + "      ||      " + dineroArrayList.get(j) + "      ||     " + intentosArrayList.get(j)
                        + "     ||       " + tirosArrayList.get(j) + "       ||");
                canalSalida.println(
                        "||----------------------------------------------------------------------------------------||");

            }

            diasArrayList.clear();
            dineroArrayList.clear();
            tirosArrayList.clear();
            intentosArrayList.clear();
            this.cantIntentos = 0;
            limpiarConsola();
        }
    }

    public void estadVictoria(int tiros) throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("------------------------------------------------------");
        System.out.println("---------------  Opción: " + tiros + " tiros   -----------------");
        System.out.println("------------------------------------------------------");
        System.out.println("GANOOOOOOOO!!!! ");
        System.out.println("Tipo de apuesta: " + this.tipoApuesta);
        System.out.println("Dias apostando: " + this.diasApostando + " dias");
        System.out.println("Dinero ganado: " + this.dineroDisponible + " pesos");
        System.out.println("Total tiros: " + this.totalTiros);
        System.out.println("Intento: " + this.cantIntentos);
        limpiarConsola();
    }

    public void estadDerrota(int tiros) throws IOException {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        this.totalTiros = this.totalTiros + (this.diasApostando * 100);
        System.out.println("------------------------------------------------------");
        System.out.println("---------------  Opción: " + tiros + " tiros   -----------------");
        System.out.println("------------------------------------------------------");
        System.out.println("Llego a la Ruina!!!");
        System.out.println("Tipo de apuesta: " + this.tipoApuesta);
        System.out.println("Total tiros: " + this.totalTiros);
        System.out.println("Máximo dinero obtenido: " + this.maximoDineroObtenido);
        System.out.println("Perdio el dinero luego de " + this.diasApostando + " dias");
        limpiarConsola();

    }

    // Tira 100 veces por dia la ruleta hasta ganar o quedar en banca rota.
    public boolean opcionUno(int cantidadRepeteciones) throws InterruptedException, IOException {
        if (ruleta(100, false)) {
            this.diasApostando = this.diasApostando + 1;
            opcionUno(cantidadRepeteciones);
        } else {
            if (controlSalida()) {
                this.totalTiros = this.totalTiros + (this.diasApostando * 100);

                if (cantidadRepeteciones == 1) {
                    estadVictoria(100);
                }

                almacenarEstadisticas();
                limpiarVariables();

                victoriaOpcion = true;

            } else {
                if (cantidadRepeteciones == 1) {
                    estadDerrota(100);
                }
                limpiarVariables();
                victoriaOpcion = false;

            }
        }
        return victoriaOpcion;

    }

    // Repite la opcion uno N veces.
    public void opcionUnoRep(int cantidadRepeteciones) throws IOException, InterruptedException {

        int victorias = 0;
        for (int i = 0; i < cantidadRepeteciones; i++) {
            if (opcionUno(cantidadRepeteciones)) {
                victorias++;
            }
            this.cantIntentos = i;

        }

        if (cantidadRepeteciones > 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Total intentos: " + cantidadRepeteciones);
            System.out.println("Victorias: " + victorias);
            System.out.println("Derrotas: " + (cantidadRepeteciones - victorias));
            canalSalida.println("Presione una tecla para ver las estadisticas de las victorias");
            String option = canalEntrada.readLine();

            mostrarEstadisticas();
        }

    }

    // Tira 200 veces por dia la ruleta hasta ganar o quedar en banca rota.
    public boolean opcionDos(int cantidadRepeteciones) throws InterruptedException, IOException {
        if (ruleta(200, false)) {
            this.diasApostando = this.diasApostando + 1;
            // this.dineroDisponible = 500;
            opcionDos(cantidadRepeteciones);
        } else {
            if (controlSalida()) {
                this.totalTiros = this.totalTiros + (this.diasApostando * 200);

                if (cantidadRepeteciones == 1) {
                    estadVictoria(200);
                }
                almacenarEstadisticas();
                limpiarVariables();
                victoriaOpcion = true;

            } else {
                this.totalTiros = this.totalTiros + (this.diasApostando * 200);
                if (cantidadRepeteciones == 1) {
                    estadDerrota(200);
                }
                limpiarVariables();
                victoriaOpcion = false;

            }

        }
        return victoriaOpcion;

    }

    // Repite la opcion dos N veces.
    public void opcionDosRep(int cantidadRepeteciones) throws IOException, InterruptedException {

        int victorias = 0;
        for (int i = 0; i < cantidadRepeteciones; i++) {
            if (opcionDos(cantidadRepeteciones)) {
                victorias++;
            }
        }

        if (cantidadRepeteciones > 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Total intentos: " + cantidadRepeteciones);
            System.out.println("Victorias: " + victorias);
            System.out.println("Derrotas: " + (cantidadRepeteciones - victorias));
            canalSalida.println("Presione una tecla para ver las estadisticas de las victorias");
            String option = canalEntrada.readLine();

            mostrarEstadisticas();
        }

    }

    // Tira 200 veces por dia la ruleta sin el 0 hasta ganar o quedar en banca rota.
    public boolean opcionCuatro(int cantidadRepeteciones) throws IOException, InterruptedException {
        if (ruleta(200, true)) {
            this.diasApostando = this.diasApostando + 1;
            // this.dineroDisponible = 500;
            opcionCuatro(cantidadRepeteciones);
        } else {
            if (controlSalida()) {
                this.totalTiros = this.totalTiros + (this.diasApostando * 200);
                if (cantidadRepeteciones == 1) {
                    estadVictoria(200);
                }
                almacenarEstadisticas();
                limpiarVariables();
                victoriaOpcion = true;
            } else {
                this.totalTiros = this.totalTiros + (this.diasApostando * 200);
                if (cantidadRepeteciones == 1) {
                    estadDerrota(200);
                }
                limpiarVariables();
                victoriaOpcion = false;

            }

        }

        return victoriaOpcion;

    }

    // Repite la opcion cuatro N veces.
    public void opcionCuatroRep(int cantidadRepeteciones) throws IOException, InterruptedException {

        int victorias = 0;
        for (int i = 0; i < cantidadRepeteciones; i++) {
            if (opcionCuatro(cantidadRepeteciones)) {
                victorias++;
            }
        }

        if (cantidadRepeteciones > 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Total intentos: " + cantidadRepeteciones);
            System.out.println("Victorias: " + victorias);
            System.out.println("Derrotas: " + (cantidadRepeteciones - victorias));
            canalSalida.println("Presione una tecla para ver las estadisticas de las victorias");
            String option = canalEntrada.readLine();

            mostrarEstadisticas();
        }
    }

    // Controla si puede cumplio el objetivo para dejar de jugar y levantarse de la
    // ruleta.
    public boolean controlSalida() {
        if (dineroDisponible >= 3000) {
            return true;
        } else {
            return false;
        }
    }

    // Main de la App
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
