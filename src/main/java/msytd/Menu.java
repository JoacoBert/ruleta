package msytd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Menu implements Runnable {

    PrintWriter canalSalida = new PrintWriter(System.out, true);
    BufferedReader canalEntrada = new BufferedReader(new InputStreamReader(System.in));

    int cantidadRepeteciones = 1;
    int cantidadDineroApuesta = 30;

    public void MenuPrincipal() {
        boolean menu = true;
        Ruleta ruleta = new Ruleta();
        while (menu) {
            try {
                canalSalida.println("||==============================================================||");
                canalSalida.println("||                Ruleta del Casino de Pinamar                  ||");
                canalSalida.println("||==============================================================||");
                canalSalida.println("Seleccione una opción del menú");
                canalSalida.println("1 -- Ver premios ");
                canalSalida.println("2 -- Seleccionar Dinero a Apostar");
                canalSalida.println("3 -- Seleccionar Tipo Apuesta");
                canalSalida.println("4 -- Tirar 100 veces por día");
                canalSalida.println("5 -- Tirar 200 veces por día");
                canalSalida.println("6 -- Tirar 200 veces por día - Ruleta sin 0");
                canalSalida.println("0 -- Salir");
                int option = leerOpcion(0, 7);
                switch (option) {
                    case 1:
                        mostrarPremios();
                        break;
                    case 2:
                        cargarCantidadApostar();
                        ruleta.setDineroApuesta(cantidadDineroApuesta);
                        break;
                    case 3:
                        MenuApuestas();
                        break;
                    case 4:
                        cargarCantidadRep();
                        ruleta.opcionUnoRep(cantidadRepeteciones);
                        break;
                    case 5:
                        cargarCantidadRep();
                        ruleta.opcionDosRep(cantidadRepeteciones);
                        break;
                    case 6:
                        cargarCantidadRep();
                        ruleta.opcionCuatroRep(cantidadRepeteciones);
                        break;
                    case 0:
                        menu = false;
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private int leerOpcion(int valorMax, int valorMin) {
        int option = valorMax - 1;
        while (option < valorMax || option > valorMin) {
            try {
                option = Integer.valueOf(canalEntrada.readLine());
            } catch (Exception e) {
                option = valorMax - 1;
            }
        }
        return option;
    }

    private int cargarCantidadRep() throws NumberFormatException, IOException {

        canalSalida.println("Ingrese la cantidad de veces que quiere repetir el proceso");
        cantidadRepeteciones = Integer.valueOf(canalEntrada.readLine());
        return cantidadRepeteciones;
    }

    private int cargarCantidadApostar() throws NumberFormatException, IOException {

        canalSalida.println("Ingrese la cantidad de dinero que quiere apostar");
        cantidadDineroApuesta = Integer.valueOf(canalEntrada.readLine());
        return cantidadDineroApuesta;
    }

    private void mostrarPremios() throws NumberFormatException, IOException {

        canalSalida.println("||=====================================================================||");
        canalSalida.println("||              APUESTA            ||            PREMIO                ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||            Num 0 - 36           ||    36 veces la cant. apostada    ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||       2 Num consecutivos        ||    18 veces la cant. apostada    ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||          Fila (Calle)           ||    12 veces la cant. apostada    ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||        4 numeros unidos         ||    9 veces la cant. apostada     ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||   2 filas consec. (Semicalle)   ||    6 veces la cant. apostada     ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||  Menor(1 a 18)/Mayor(19 a 36)   ||    2 veces la cant. apostada     ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||      Color (Rojo o Negro)       ||    2 veces la cant. apostada     ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||         Pares y nones           ||    2 veces la cant. apostada     ||");
        canalSalida.println("||=====================================================================||");

        canalSalida.println("Presione una tecla para volver al Menu Principal");
        String option = canalEntrada.readLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public void MenuApuestas() {
        boolean menu = true;
        Ruleta ruleta = new Ruleta();
        while (menu) {
            try {
                canalSalida.println("=======================================================================");
                canalSalida.println("Seleccione la apuesta a realizar");
                canalSalida.println("1 -- Numero (0 - 36) ");
                canalSalida.println("2 -- 2 numeros consecutivos");
                canalSalida.println("3 -- Fila (Calle)");
                canalSalida.println("4 -- 4 numeros unidos");
                canalSalida.println("5 -- 2 filas consec. (Semicalle)");
                canalSalida.println("6 -- Menor(1 a 18)/Mayor(19 a 36))");
                canalSalida.println("7 -- Color (Rojo o Negro)");
                canalSalida.println("8 -- Pares y nones ");
                canalSalida.println("0 -- Volver al menu principal");
                int option = leerOpcion(0, 8);
                switch (option) {
                    case 1:
                        ruleta.setApuesta("Numero");
                        menu = false;
                        break;
                    case 2:
                        ruleta.setApuesta("2Numeros");
                        menu = false;
                        break;
                    case 3:
                        ruleta.setApuesta("Calle");
                        menu = false;
                        break;
                    case 4:
                        ruleta.setApuesta("4Numeros");
                        menu = false;
                        break;
                    case 5:
                        ruleta.setApuesta("Semicalle");
                        menu = false;
                        break;
                    case 6:
                        ruleta.setApuesta("Menor");
                        menu = false;
                        break;
                    case 7:
                        ruleta.setApuesta("Color");
                        menu = false;
                        break;
                    case 8:
                        ruleta.setApuesta("Pares");
                        menu = false;
                        break;
                    case 0:
                        menu = false;
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        MenuPrincipal();

    }

}
