package terreno;

import java.util.ArrayList;
import java.util.Collections;

public class ListaJugadores {
    private static ListaJugadores instance;    
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador3;
    private Jugador jugador4;
    private Terreno terreno;
    public ArrayList<Jugador> lista= new ArrayList<>();
    public ArrayList<Integer> turnosDisponibles = new ArrayList<>();
    public int indiceActual;
    
    private ListaJugadores() 
    {}
    
    
    public static ListaJugadores getInstance() {
        if (instance == null) {
            instance = new ListaJugadores();
        }
        return instance;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {      
        this.jugador1 = jugador1;
        lista.add(jugador1);
    }

    public Jugador getJugador2() {     
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
        lista.add(jugador2);
    }  
    
    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }
    
    public Jugador getJugador3() {     
        return jugador3;
    }

    public void setJugador3(Jugador jugador3) {
        this.jugador3 = jugador3;
        lista.add(jugador3);
    } 
    
    public Jugador getJugador4() {     
        return jugador4;
    }

    public void setJugador4(Jugador jugador4) {
        this.jugador4 = jugador4;
        lista.add(jugador4);
    }  
    public ArrayList<Jugador> getLista(){
        return lista;
    }
    public void instanciarJugadores(int cantidadJugadores)
    {
        for(int i=0;i<cantidadJugadores;i++)
        {
            Jugador aux = new Jugador(i,"Juan");
            lista.add(aux);
        }

    }
     public void generarTurnoAleatorio() {
        if (turnosDisponibles.isEmpty()) {
            // Si todos los jugadores han sido seleccionados, se reiniciar la lista

            for (int i = 0; i < lista.size(); i++) {
                turnosDisponibles.add(i);
            }
            Collections.shuffle(turnosDisponibles);
        }
        indiceActual = turnosDisponibles.remove(0);

    }
    public Jugador getJugadorActual() {
        return lista.get(indiceActual);
    }
    
}