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
            lista.add(aux);
        }
    }


    public void generarTurnoAleatorio() {
        if (turnosDisponibles.isEmpty()) {
            for (Jugador jugador : lista) {
                if (!jugador.estaEliminado()) {
                    turnosDisponibles.add(lista.indexOf(jugador));
                }
            }
            Collections.shuffle(turnosDisponibles);
        }
        indiceActual = turnosDisponibles.isEmpty() ? -1 : turnosDisponibles.remove(0);
    }

    public Jugador getJugadorActual() {
        return lista.get(indiceActual);
    }
    
    public void eliminarJugador(int indiceJugador) {
        if (indiceJugador < 0 || indiceJugador >= lista.size()) {
            // Índice fuera de rango, no se hace nada
            return;
        }

        lista.get(indiceJugador).eliminar();


    }
    public boolean quedaUnoVivo()
    {
        int cantidadMuetos=0;
        for(Jugador aux: lista)
        {
            if(aux.estaEliminado())
            {
                cantidadMuetos++;
            }
        }
        if(cantidadMuetos==lista.size()-1)
        {
            return true;
        }
        return false;
    }
    public void revivir()
    {
        for (Jugador aux : lista)
        {
            aux.eliminado=false; // no esta eliminado
            aux.setVida(100);
        }
    }
}