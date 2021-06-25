package msytd;

import java.util.Random;

public class Ruleta {

    public int tirar() {

            Random aleatorio = new Random();
            // Producir nuevo int aleatorio entre 0 y 99
            int intAletorio = aleatorio.nextInt(37);

        
        return intAletorio;
    }
}
