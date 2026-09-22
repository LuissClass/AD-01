package clase.tema1.ej3;

public class Main {
    public static void main(String[] args) {
        OperacionesCRUD crud = new OperacionesCRUD();
        Menu menu = new Menu(crud);
        menu.iniciar();
    }
}