package msytd;

/**
 * Hello world!
 *
 */
public class App {

    public void MenuPrincipal() {
    }

    public static void main(String[] args) {
        Ruleta intAletorio = new Ruleta();
        int a = intAletorio.tirar();
        System.out.println(a);

    }

}
