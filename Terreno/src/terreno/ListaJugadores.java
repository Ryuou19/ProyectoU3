    package terreno;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Iterator;

    public class ListaJugadores {
        private static ListaJugadores instance;
        private Terreno terreno;
        public ArrayList<Jugador> lista= new ArrayList<>();

        public ArrayList<Integer> turnosDisponibles = new ArrayList<>();
        public int indiceActual;
        public int ultimo_indice=0;
        public int ultimo_indice_lista=0;
        public int contador_lista_turnos=0;
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
        ArrayList<Integer> turnosDisponiblesAux = new ArrayList<>();

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
        }


        public void generarTurnoAleatorio() {
            if (turnosDisponibles.isEmpty()) {
                ArrayList<Integer> indicesNormales = new ArrayList<>();
                ArrayList<Integer> indicesBots = new ArrayList<>();
                for (Jugador jugador : lista) {
                    if (!jugador.estaEliminado()) {
                        if(jugador.activo){
                            if ("jugador".equals(jugador.tipo)) {
                                indicesNormales.add(jugador.jugador);
                            } else if ("bot".equals(jugador.tipo)) {
                                indicesBots.add(jugador.jugador);
                            }
                        }
                    }
                }

                Collections.shuffle(indicesNormales);
                Collections.shuffle(indicesBots);

                turnosDisponibles.addAll(indicesNormales);
                turnosDisponibles.addAll(indicesBots);
                if(turnosDisponibles.size()>1){
                    while (turnosDisponibles.get(0) == ultimo_indice_lista) {
                        Collections.shuffle(turnosDisponibles);
                    }

                    // Actualizar el último índice después de la mezcla
                    ultimo_indice_lista = turnosDisponibles.get(turnosDisponibles.size() - 1);

                }else {Collections.shuffle(turnosDisponibles);}

                System.out.println("Lista de turnos creada -> " + turnosDisponibles);
            }
            if (!turnosDisponibles.isEmpty()) {
                indiceActual = turnosDisponibles.remove(0);
                System.out.println("lista de turnos una vez actualizada ->"+turnosDisponibles);
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
            Iterator<Integer> iterator = turnosDisponibles.iterator(); // si esta en la lista el jugador que fue eliminado lo eliminamos
            while (iterator.hasNext()) {
                if (iterator.next() == indiceJugador) {
                    iterator.remove();
                }
            }

        }
        public void desactivarJugador(int indiceJugador)
        {
            if (indiceJugador < 0 || indiceJugador >= lista.size()) {
                //indice fuera de rango, no se hace nada
                return;
            }
            lista.get(indiceJugador).descativar();

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
            int cantidadMaximaDeMuertos=lista.size();
            int cantidad_inactivos=0;
            for(Jugador aux: lista)
            {
                if(!aux.activo)
                {
                    cantidad_inactivos++;
                }

            }
            if(cantidad_inactivos==cantidadMaximaDeMuertos)
            {
                return true;
            }
            return false;
        }
        public void revivir_jugadores()
        {
            for (Jugador aux : lista)
            {
                aux.eliminado=false; // no esta eliminado
                aux.activo=true; // lo marcamos como activo
                //reinicio de atributos
                aux.vida=100;
                aux.suicidios=0;
                aux.saldo=10000;
                aux.asesionatos=0;
                aux.cantidad80=2;
                aux.cantidad105=0;
                aux.cantidad60=0;
                aux.asesinatosTotales=0;
                aux.suicidiosTotales=0;

            }
        }
        
        public void revivir()
        {
            for (Jugador aux : lista)
            {
                aux.eliminado=false; // no esta eliminado
                aux.activo=true; // lo marcamos como activo
                //reinicio de vida
                aux.vida=100;              
            }
        }


    }