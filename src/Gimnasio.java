import java.io.BufferedReader;

public class Gimnasio {

    /**
     * ENTREGA UT3
     *
     * @author - (Manuel)
     * <p>
     * <p>
     * Un gimnasio oferta clases de yoga, pilates y spinning
     * en sus diferentes salas. En cada sala se realiza una actividad de la misma duración
     * que se repite a lo largo del día un nº de veces. Puede haber salas
     * con la misma actividad pero de duración diferentes (ej. en la sala 1
     * se hace spinning en clases de 40 minutos y en la sala 3 se
     * hace también spinning pero de duración 70 minutos).
     * <p>
     * El precio de las clases depende de su duración.
     * El precio base es 3.0€ y se añade a este precio 0.20€ por cada período completo de 15 minutos.
     * <p>
     * <p>
     * La primera clase comienza siempre a las 10.30 (a.m) y la última no puede acabar después
     * de las 8.30 (p.m). En todas las salas entre clase y clase (incluida la última)
     * hay un descanso de 10 minutos
     * <p>
     * El gimnasio quiere mostrar al usuario el nº de clases que se dan en cada sala con su precio
     * y además quiere efectuar ciertos cálculos dependiendo del nº de personas que se han inscrito
     * a las actividades de las diferentes salas
     */
    private String nombre;
    private int inscritos_yoga;
    private int inscritos_pilates;
    private int inscritos_Spining;
    private int totalAcumulado;
    private int salaMaximoSpinning;
    private int inscritosMaximoSpinning;

    private final int HORA_PRIMERA_CLASE = 10;

    private final int MINUTOS_PRIMERA_CLASE = 30;

    private final int HORA_ULTIMA_CLASE = 8;

    private final int MINUTOS_ULTIMA_CLASE = 30;

    private final int DESCANSO = 10;

    private final double PRECIO_BASE = 3.0;

    private final double PRECIO_15MINUTOS = 0.20;

    private final char YOGA = 'Y';

    private final char PILATES = 'P';

    private final char SPINNING = 'S';

    /**
     * Constructor  -
     * Recibe un único parámetro, el nombre del gimnasio
     * e inicializa el resto de atributos adecuadamente
     */

    public Gimnasio(String nombre) {
        this.nombre = nombre;
        this.inscritos_yoga = 0;
        this.inscritos_pilates = 0;
        this.inscritos_Spining = 0;
        this.totalAcumulado = 0;
        this.salaMaximoSpinning = 0;
        this.inscritosMaximoSpinning = 0;
    }

    /**
     * Accesor para el nombre del gimnasio
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Mutador para el nombre del gimnasio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para el importe total acumulado
     * entre todos los inscritos en el gimnasio
     */
    public double getImporteTotal() {
        return this.totalAcumulado;
    }

    /**
     * Este método recibe 4 parámetros:
     * - sala: el nº de sala donde se hace la actividad
     * - tipo: el tipo de actividad, un carácter 'Y' yoga, 'P' pilates 'S' spinning
     * - horas y minutos : duración de la actividad en nº horas y minutos
     * - inscritos: el nº de personas inscritas en esa actividad en la sala
     * <p>
     * Por ej, tarificarClaseEnSala(4, 'P', 1, 5, 15) significa que en la sala 4 se hace
     * pilates, las clases duran 1 hora y  5 minutos y se han inscrito en esta sala 15
     * <p>
     * A partir de esta información el método debe calcular:
     * - total inscritos por tipo de actividad (independientemente de la sala)
     * - la sala y el máximo nº de inscritos en spinning en esa sala
     * <p>
     * Utiliza   una sentencia switch  para analizar el tipo de actividad
     * <p>
     * También el método calculará y mostrará posteriormente la siguiente información:
     * - el precio de la clase en la sala (basándose en su duración). En el ejemplo anterior
     * la clase de pilates duraba 1 hora y 5 minutos . Como el total de minutos
     * de duración es 65 su precio será: 3 + 0,20 *  4 = 3,8 ya que son 4 los períodos completos de
     * 15 minutos que hay
     * - ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡nº de veces que la clase se ofertará en la sala (dependerá de su duración. No olvidar que entre clase
     * y clase siempre hay un descanso) no hecho no se me ocurria como hacerlo
     * - ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡falta la hora de finalización de la última clase (hora y minutos) - !!Ver resultados de ejecución!! no hecho no se me ocurria como hacerlo
     * - el método además irá registrando el importe total que lleva acumulado el gimnasio entre todas
     * las personas inscritas
     */
    public void tarificarClaseEnSala(int sala, char tipo, int horas, int minutos, int inscritos) {
        StringBuilder resultado = new StringBuilder();

        resultado.append("Sala Nº:");
        resultado.append(sala);
        resultado.append("\t\t");

        resultado.append("Actividad: ");

            switch (tipo) {
                case YOGA:
                    resultado.append("YOGA");
                    this.inscritos_yoga+=inscritos;
                    break;
                case PILATES:
                    resultado.append("PILATES");
                    this.inscritos_pilates+=inscritos;
                    break;
                case SPINNING:
                    resultado.append("SPINNING");
                    this.inscritos_Spining += inscritos;


                    if (inscritos > this.inscritosMaximoSpinning){
                        this.inscritosMaximoSpinning = inscritos;
                        this.salaMaximoSpinning = sala;
                    }
                    break;
            }
        resultado.append("\nLongitud");


        int duracionClase = horas * 60 + minutos;
        resultado.append(duracionClase).append(" min. ");
        resultado.append("\t\t").append("Descanso");
        resultado.append(DESCANSO).append(" min.\n ");

        double precioClase = PRECIO_BASE + (PRECIO_15MINUTOS * (duracionClase/15));
        this.totalAcumulado += precioClase * inscritos;
        resultado.append("Precio clase: ").append(String.format("%.2f€", precioClase));
        resultado.append("\n");


        int vecesOfertada = this.getMinutosAbierto()/(duracionClase+DESCANSO);
        resultado.append("Clase ofertada en sala: ");
        resultado.append(vecesOfertada);
        resultado.append(" veces al dia\n");
        resultado.append("La ultima clase termina a las: ");


        int minutosCierreGimnasio = (HORA_ULTIMA_CLASE + 12)*60 + MINUTOS_ULTIMA_CLASE;
        int minutoSobrantes = this.getMinutosAbierto() % (duracionClase + DESCANSO);
        int minutosUltimaActividad = minutosCierreGimnasio - minutoSobrantes;


        //int minutosSobrantes = this.getMinutosAbierto() % (duracionClase + DESCANSO);
        //int horasSobrantes = minutosSobrantes/60;
        //minutosSobrantes %= 60;

        resultado.append(minutosUltimaActividad / 60);
        resultado.append(" y ");
        resultado.append(minutosUltimaActividad % 60);
        resultado.append(" minutos\n");

        resultado.append("Total inscritos en sala: ").append(inscritos).append("\n");


        //resultado.append(HORA_ULTIMA_CLASE + 12 -horasSobrantes);


        System.out.println(resultado);
    }

    private int getMinutosAbierto(){

        int horaCierrePm = HORA_ULTIMA_CLASE+12;

        return (horaCierrePm * 60 + MINUTOS_ULTIMA_CLASE) - (HORA_PRIMERA_CLASE* 60 + MINUTOS_PRIMERA_CLASE);
    }



    /**
     * nº sala en la que hay más inscritos para spinning
     */
    public int getSala() { //no hecho no se me ocurria como hacerlo
      return salaMaximoSpinning;
    }

    /**
     * Devuelve el nombre de la actividad con más inscritos independientemente de la sala
     */
    public String getActividad() {
        if (this.inscritos_yoga > this.inscritos_Spining && this.inscritos_yoga > this.inscritos_pilates) {

            return "YOGA";
        } else if (this.inscritos_pilates>this.inscritos_Spining) {
            return "PILATES";
        }else {
            return "SPINNING";
        }
    }
}


