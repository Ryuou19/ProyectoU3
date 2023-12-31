package terreno;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;



public class  Jugador {

    String color = "";
    public int jugador; // variable para saber el indice del jugador en la lista
    public boolean activo=true;//si esta funcionando y tiene balas
    String nombre="";
    //BALAS INICIALES DE CADA JUGADOR
    int cantidad105=0;
    int cantidad80=0;
    int cantidad60=0;
    int vida;   
    //SALDO DE CADA JUGADOR
    int saldo;
    //posiciones del tanque
    int posicionInicalX=0;
    int posicionInicialY=100;
    //kda del tanque
    public int asesinatos;
    public int suicidios;
    public int asesinatosTotales;
    public int suicidiosTotales;
    public Random rand;
    private Tank tanque;
    int random;
    String tipo;//si es jugador o bot
    public ArrayList <Integer> carrito=new ArrayList<>();//carrito que guarda todas las compras hechas en una tienda
    public ArrayList <Text> datosClasificacion=new ArrayList<>();//datos registrados en la ronda
    public boolean eliminado = false;//si esta muerto el jugador

    public int getVida() {
        return vida;
    }
   
    public int getCantidad105() {
        return cantidad105;
    }

    public void setCantidad105(int cantidad105) {
        this.cantidad105 = cantidad105;
    }

    public int getCantidad80() {
        return cantidad80;
    }

    public void setCantidad80(int cantidad80) {
        this.cantidad80 = cantidad80;
    }

    public int getCantidad60() {
        return cantidad60;
    }

    public void setCantidad60(int cantidad60) {
        this.cantidad60 = cantidad60;
    }


    public Tank getTanque() {
        return tanque;
    }
  
    public Jugador(int jugador,String nombre,String tipo){ // constructor de jugador     
        this.jugador = jugador;
        this.rand=new Random();
        this.random=rand.nextInt(4);
        this.saldo=10000;
        this.nombre=nombre;
        this.vida=100;
        this.tipo=tipo;
        //de esta forma no pasamos como parametro la imagenes haciendo mas facil la inicializacion de la clase en la pantalla de inizicio 
        if (jugador==0)
        {
            this.color="./img/tanque1.png";
        }
        if(jugador==1)
        {
            this.color="./img/tanque2.png";
        }
        if(jugador==2)
        {
            this.color="./img/tanque3.png";
        }
        if(jugador==3)
        {
            this.color="./img/tanque4.png";
        }
        if(jugador==4)
        {
            this.color="./img/tanque5.png";
        }
        if (jugador==5)
        {
            this.color="./img/tanque6.png";
        }      
    }
    
    public void creaTanque(GraphicsContext gc,  int vida, int validar, Terreno terreno){
        if(validar==0){//verifica si se crea desde un terreno completamente nuevo o solo de un cambio de turno
            this.random=rand.nextInt(4);
        }
        Tank tanque = new Tank(color, jugador);
        tanque.agregarTanque(gc,this.random,vida,terreno,posicionInicalX,posicionInicialY);
        this.tanque=tanque;
    }
    public void descativar()  // metodo para marcar al tanque como desactivado
    {
        this.activo=false;
    }
    public void eliminar() {
        this.eliminado = true;
    } // metodo para marcar al tanque como eliminado

    public boolean estaEliminado() {
        return eliminado;
    } //metodo para preguntar si el jugador esta eliminado
    
    public void agregar_saldo(int cantidad){//metodo para agregar dinero
        this.saldo+=cantidad;
        System.out.println("Saldo disponible= "+this.saldo);
    }
    
    //funcion que reduce la vida dependiendo del valor de la bala o del radio de explosion
    public int ajustar_vida(int vida, int danio){
        vida-=danio;
        this.vida=vida;
        return vida;
    }
    public static Boolean comprobarMunicion(int tipo, ListaJugadores listJugador){//comprueba si es que ls bala ingresada que posee el jugador esta vacia 
        if(tipo==1){
            return listJugador.getJugadorActual().getCantidad60() == 0;
        }
        if(tipo==2){
            return listJugador.getJugadorActual().getCantidad80() == 0;
        }
        if(tipo==3){
            return listJugador.getJugadorActual().getCantidad105() == 0;
        }    
        return false;
    }
    
    //metodo que entrega el dinero correspondiente a cada jugador por su kda
    public static void pagar_ronda(ListaJugadores listJugador){
        for(Jugador jugador: listJugador.lista){
            jugador.saldo+=10000;
            if(jugador.asesinatos!=0){
                jugador.saldo+=5000*jugador.asesinatos;
            }
            if(jugador.suicidios!=0){
                jugador.saldo-=5000*jugador.suicidios;
            }
        }
    }
    
    public static boolean revisarBalasDisponibles(ListaJugadores listJugador){//revisa las balas disponibles de todos los jugadores para ver si es que hay que finalizar la ronda
        for(Jugador jugador : listJugador.getLista()){
            if(jugador.getCantidad60()!=0){
                return false;
            }
            if(jugador.getCantidad80()!=0){
                return false;
            }
            if(jugador.getCantidad105()!=0){
                return false;
            }
        }
        return true;
    }
    
    public static boolean tiene_balas(ListaJugadores listJugador){ //comprobamos si el jugador actual le quedan balas
       if( listJugador.getJugadorActual().getCantidad60()<=0 && listJugador.getJugadorActual().getCantidad80()<=0 && listJugador.getJugadorActual().getCantidad105()<=0)
       {
           return false;
       }
        return true;
    }
}