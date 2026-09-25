package clase.tema2.app1;

class TryAnidada {
    public static void main(String args[]) {
        try {
            int a = args.length, b = 42 / a;
            System.out.println("a = " + a);
            try {
                if (a == 1) a = a / (a - a);
                if (a == 2) {
                    int c[] = {1};
                    c[42] = 99;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("�ndice fuera de l�mites:" + e);
            }
        } catch (ArithmeticException e) {
            System.out.println("Divisi�n por cero:" + e);
        }
    }
}