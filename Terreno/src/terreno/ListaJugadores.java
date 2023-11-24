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
    public ArrayList<Jugador> ronda= new ArrayList<>();
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
    
    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }
    
     
    public ArrayList<Jugador> getLista(){
        return lista;
    }
    public void instanciarJugadores(int cantidadJugadores, int cantidadBots)
    {
        int cantidadNormales= cantidadJugadores-cantidadBots; //le quitamos la cantidad de bots a los jugadores
        //agregamos la cantidad de jguadores normales,
        for(int i=0;i<cantidadNormales;i++)
        {
            Jugador aux = new Jugador(i,"Juan","jugador");
            lista.add(aux);
        }
        //agregamos la cantidad de jguadors que seran bots
        for(int i=cantidadNormales;i<cantidadJugadores;i++)
        {
            Jugador aux= new Jugador(i,"robarto","bot");
        }
    }
    
    public void copiarLista(){
        ronda= (ArrayList<Jugador>) lista.clone();
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
        if(indiceActual>ronda.size()-1){
            return ronda.get(indiceActual-1);
        }
        return ronda.get(indiceActual);
    }
    
    public void eliminarJugador(int indiceJugador) {
        if (indiceJugador < 0 || indiceJugador >= lista.size()) {
            // Índice fuera de rango, no se hace nada
            return;
        }

        // Eliminar el jugador de la lista de jugadores
        ronda.remove(indiceJugador);

        // Actualizar la lista de turnos disponibles
        turnosDisponibles.removeIf(indice -> indice == indiceJugador);

        // Ajustar los índices en turnosDisponibles para los jugadores que vienen después del eliminado
        for (int i = 0; i < turnosDisponibles.size(); i++) {
            if (turnosDisponibles.get(i) > indiceJugador) {
                turnosDisponibles.set(i, turnosDisponibles.get(i) - 1);
            }
        }
    }
    
}