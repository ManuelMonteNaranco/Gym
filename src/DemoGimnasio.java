import java.util.Scanner;
    /**
     *  class DemoGimnasio
     *  Clase para testear la clase Gimnasio
     *
     */
    public class DemoGimnasio {

        private  Gimnasio gimnasio;
        private Scanner teclado;

        /**
         * Constructor de la clase DemoGimnasio
         */
        public DemoGimnasio() {
            teclado = new Scanner(System.in);
            gimnasio = new Gimnasio("GYM");
        }

        /**
         *
         */
        public void demo() {
            System.out.println("\n********** Tarificación clases GIMNASIO : " +
                    gimnasio.getNombre() + " **********\n\n");

            gimnasio.tarificarClaseEnSala(1, 'S', 1, 15, 30);
            pausa();
            gimnasio.tarificarClaseEnSala(2, 'Y', 0, 45, 40);
            pausa();
            gimnasio.tarificarClaseEnSala(3, 'S', 1, 30, 20);
            pausa();
            gimnasio.tarificarClaseEnSala(4, 'P', 1, 5, 15);
            pausa();
            gimnasio.tarificarClaseEnSala(5, 'Y', 2, 0, 8);
            pausa();
            gimnasio.tarificarClaseEnSala(6, 'Y', 1, 30, 13);
            pausa();

            System.out.println("\nTotal importe entre todos los inscritos: " +
                    gimnasio.getImporteTotal()+ "€");
            System.out.println("\nActividad con más inscritos: " +
                    gimnasio.getActividad());
            System.out.println("\nSala de Spinning con más inscritos: " +
                    gimnasio.getSala());
        }

        /**
         *  hacer una pausa
         */
        private void pausa() {
            System.out.println("\nPulse tecla para continuar");
            teclado.nextLine();
            System.out.println("\u000C");
        }



    }



