package terreno;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Bala {
    private static final double distancia = 40;
    private int danio;
    public double ejeX;
    public double ejeY;
    public int distanciaRecorrida = 0;
    public int alturaMaxima = 0;
    public int radiojugador;
    public double anguloLanzamiento; 
    public double velocidadLanzamiento; 
    public double velocidadX;
    public double velocidadY;
    public boolean eliminar=false;
    
    public int contador;
    //cambio-----
    public int getDanio() {
        return danio;
    }
    //sin cambio----


    
    public Bala(int x, int y, int radiojugador, double anguloLanzamiento, double velocidadLanzamiento,int contador, int danio, Jugador jugador) {
        this.radiojugador = radiojugador;
        this.anguloLanzamiento = Math.toRadians(anguloLanzamiento); 
        this.velocidadLanzamiento = velocidadLanzamiento;   
        double cañonX = x + distancia * Math.cos(this.anguloLanzamiento);
        double cañonY = y + distancia * Math.sin(this.anguloLanzamiento);
        this.ejeX = cañonX;
        this.ejeY = cañonY;
        this.velocidadX = velocidadLanzamiento * Math.cos(this.anguloLanzamiento);
        this.velocidadY = velocidadLanzamiento * Math.sin(this.anguloLanzamiento);
        this.contador = contador;
        this.danio=danio;
        if(danio==30){
            jugador.setCantidad60(jugador.getCantidad60()-1);
        }
        if(danio==40){
            jugador.setCantidad80(jugador.getCantidad80()-1);
        }
        if(danio==50){
            jugador.setCantidad105(jugador.getCantidad105()-1);
        }
        
        

    }

    public void dibujo(GraphicsContext gc, int tipo){
        if(tipo==30){
            gc.setFill(Color.GREEN);
        }
        if(tipo==40){
            gc.setFill(Color.BLUE);
        }
        if(tipo==50){
            gc.setFill(Color.RED);
        }
        gc.fillOval(ejeX , ejeY, 2 * radiojugador, 2 * radiojugador);
    }
    
    public void marcar() {
        eliminar=true;
    }
    
    public boolean eliminar(){
        return eliminar;
    }
      
    
     public void actualizarPosicion(double deltaTiempo, Bala player, int distancia, int altura,  HBox boxdistancia, HBox boxaltura,double posicionInicialY,double posicionInicialX){
        ejeX += velocidadX * deltaTiempo;       
        velocidadY -= Globales.gravedad * deltaTiempo;       
        ejeY += velocidadY * deltaTiempo;
        double nuevaPosX = player.ejeX + player.velocidadX * deltaTiempo;
        double nuevaPosY = player.ejeY + player.velocidadY * deltaTiempo;
        /*double nuevaPosX = posicionInicialX + player.velocidadLanzamiento * cos(player.anguloLanzamiento) * deltaTiempo;
        double nuevaPosY = posicionInicialY - (player.velocidadLanzamiento * sin(player.anguloLanzamiento) * deltaTiempo - (0.5 * gravedad * Math.pow(deltaTiempo, 2)));*/
        player.ejeX = (int) nuevaPosX;
        player.ejeY = (int) nuevaPosY;
        // Calcula la distancia recorrida
        distanciaRecorrida = (int) (ejeX - posicionInicialX);
        distancia = Math.abs(distanciaRecorrida);
        if(contador==0  )
        {
                int alturaActual = (int) (posicionInicialY - ejeY);
                if (alturaActual<=0)
                {
                    alturaMaxima=0;
                }
                else
                {
                    alturaActual = Math.max(alturaActual, 0); // Asegura que la altura no sea negativa
                    alturaMaxima = Math.max(alturaMaxima, alturaActual);
                }             
        }
        else
        {
            int alturaActual = (int) (posicionInicialY - ejeY);
            // Calcula la altura actual
            alturaActual = Math.max(alturaActual, 0); // Asegura que la altura no sea negativa
            alturaMaxima = Math.max(alturaMaxima, alturaActual);
            
        }
        Label distanciaLabel = (Label) boxdistancia.getChildren().get(1);
        distanciaLabel.setText(distancia + " Metros");
        Label alturaLabel = (Label) boxaltura.getChildren().get(1); 
        alturaLabel.setText(alturaMaxima   + " Metros");
        this.contador++;
         
    }
}