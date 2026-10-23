package clase.tema2.app5;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Menu m = new Menu();
        try {
            m.menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
