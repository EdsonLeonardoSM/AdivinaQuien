package pruebas.adivinaquien;

/**
 *
 * @author Alonso
 */

import java.sql.Time;
import java.sql.Timestamp;

public class Partida {

    private String jugador1;
    private String jugador2;
    private String ganador;
    private Timestamp fecha;
    private Time duracion;
    private String objetoGanador;

    public Partida(String jugador1, String jugador2, String ganador,
                   Timestamp fecha, Time duracion, String objetoGanador) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ganador = ganador;
        this.fecha = fecha;
        this.duracion = duracion;
        this.objetoGanador = objetoGanador;
    }

    // Getters
    public String getJugador1() { return jugador1; }
    public String getJugador2() { return jugador2; }
    public String getGanador() { return ganador; }
    public Timestamp getFecha() { return fecha; }
    public Time getDuracion() { return duracion; }
    public String getObjetoGanador() { return objetoGanador; }

}