package msytd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Menu implements Runnable {

    PrintWriter canalSalida = new PrintWriter(System.out, true);
    BufferedReader canalEntrada = new BufferedReader(new InputStreamReader(System.in));

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
                canalSalida.println("2 -- Tirar 100 veces por día");
                canalSalida.println("3 -- Tirar 200 veces por día");
                canalSalida.println("0 -- Salir");
                int option = leerOpcion(0, 3);
                switch (option) {
                    case 1:
                        mostrarPremios();
                        break;
                    case 2:
                        ruleta.opcionUno();
                        break;
                    case 3:
                        ruleta.opcionDos();
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

    private int leerOpcion(int fromValue, int toValue) {
        int option = fromValue - 1;
        while (option < fromValue || option > toValue) {
            try {
                option = Integer.valueOf(canalEntrada.readLine());
            } catch (Exception e) {
                option = fromValue - 1;
            }
        }
        return option;
    }

    private void mostrarPremios() throws NumberFormatException, IOException {

        canalSalida.println("||=====================================================================||");
        canalSalida.println("||              APUESTA            ||            PREMIO                ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||            Num 0 -36            ||    36 veces la cant. apostada    ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||       2 Num consecutivos        ||    18 veces la cant. apostada    ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||          Fila (Calle)           ||    12 veces la cant. apostada    ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||        4 numeros unidos         ||    9 veces la cant. apostada     ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||   2 filas consec. (Semicalle)   ||    6 veces la cant. apostada     ||");
        canalSalida.println("||---------------------------------------------------------------------||");
        canalSalida.println("||  Menor(1 a 18)/Mayor(19 a 36)   ||    3 veces la cant. apostada     ||");
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

    @Override
    public void run() {
        MenuPrincipal();

    }

}
