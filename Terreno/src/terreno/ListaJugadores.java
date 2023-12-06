    package terreno;

    import java.util.ArrayList;
    import java.util.Collections;

    public class ListaJugadores {
        private static ListaJugadores instance;
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
            String nombre="";
            //agregamos la cantidad de jguadores normales,
            for(int i=0;i<cantidadNormales;i++)
            {
                if(i==0){
                    nombre="MESSI";
                }
                if(i==1){
                    nombre="ALEXIS";
                }
                if(i==2){
                    nombre="VIDAL";
                }
                if(i==3){
                    nombre="SUAZO";
                }
                if(i==4){
                    nombre="YONE";
                }
                if(i==5){
                    nombre="YASUO";
                }
                Jugador aux = new Jugador(i,nombre,"jugador");
                lista.add(aux);
            }
            //agregamos la cantidad de jguadors que seran bots
            for(int i=cantidadNormales;i<cantidadJugadores;i++)
            {
                if(i==0){
                    nombre="MESSI";
                }
                if(i==1){
                    nombre="ALEXIS";
                }
                if(i==2){
                    nombre="VIDAL";
                }
                if(i==3){
                    nombre="SUAZO";
                }
                if(i==4){
                    nombre="YONE";
                }
                if(i==5){
                    nombre="YASUO";
                }

                Jugador aux= new Jugador(i,nombre,"bot");
                lista.add(aux);
            }
            generarTurnoAleatorio();
        }


        public void generarTurnoAleatorio() {
            if (turnosDisponibles.isEmpty()) {
                for (Jugador jugador : lista) {
                    if (!jugador.estaEliminado()) {
                        if(jugador.activo){
                            turnosDisponibles.add(jugador.jugador);
                        }

                    }
                }
            }
            if (!turnosDisponibles.isEmpty()) {
                Collections.shuffle(turnosDisponibles);
                indiceActual = turnosDisponibles.remove(0);
                System.out.println("lista de turnos ->" + turnosDisponibles);
                System.out.println("lista de jugadores largo ->" + turnosDisponibles.size());
                for(Jugador jugar : lista)
                {
                    System.out.println("jugador:"+jugar.jugador+"saldo"+jugar.saldo);
                }
            }
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
            turnosDisponibles.clear();

        }
        public void desactivarJugador(int indiceJugador)
        {
            if (indiceJugador < 0 || indiceJugador >= lista.size()) {
                // Índice fuera de rango, no se hace nada
                return;
            }
            lista.get(indiceJugador).descativar();
            turnosDisponibles.clear();

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
            int cantidadMaximaDeMuertos=lista.size()-1;
            if(cantidadMuetos==cantidadMaximaDeMuertos)
            {
                return true;
            }
            return false;
        }
        public boolean quedaUnoActivo()
        {
            int cantidad_inactivos=0;
            for(Jugador aux: lista)
            {
                if(!aux.activo)
                {
                    cantidad_inactivos++;
                }
                int cantidadMaximaDeMuertos=lista.size();
                if(cantidad_inactivos==cantidadMaximaDeMuertos)
                {
                    return true;
                }
            }
            return false;
        }
        public void revivir()
        {
            for (Jugador aux : lista)
            {
                aux.eliminado=false; // no esta eliminado
                aux.activo=true; // lo marcamos como activo
                aux.setVida(100);
            }
        }
        
    }