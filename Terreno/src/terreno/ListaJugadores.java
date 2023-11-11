package terreno;

public class ListaJugadores {
    private static ListaJugadores instance;
    
    private Jugador jugador1;
    private Jugador jugador2;
    private Terreno terreno;

    
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
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }  
    
    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }
}
