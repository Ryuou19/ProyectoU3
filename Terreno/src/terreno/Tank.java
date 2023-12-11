package terreno;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;// import usado para dibujar hitbox con el fin de visualizarla


public class Tank{
    Globales c=new Globales();
    public int posicionX; //posicion X del tanque en el canvas
    int largo_imagen=0; // largo de la imagen del tanque
    int ancho_imagen=0; // ancho de la imagen del tanque
    public int posicionY=300;// todos los tanques comienzan con altura 300 que es de donde caen
    public String color;
    public int jugadorTanque;
    private double angulo;
    private int cañonX;
    private int cañonY;
    int ancho;
    int alto;   
    int vida=100;
    int dañoAltura=10;//se refiere a la altura en donde se comenzara a hacer el daño

    public Tank(String color, int jugador){
        this.color=color;
        this.jugadorTanque = jugador;
        this.ancho=19;
        this.alto=8;
        this.vida = 100; 
    }
    
    public int getCañonX() {
        return cañonX;
    }

    public int getCañonY() {
        return cañonY;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void modificarCañon(GraphicsContext gc, double angulo){
        this.angulo = angulo;
        int x = posicionX;
        int y = posicionY;
        
        int cx = x + 35;//valor usado para que la bala salga desde fuera de la hitbox del tanque, para que no se suicide de inmediato
        int cy = y + 35;
        double anguloRad = Math.toRadians(angulo);

        cañonX = (int) (cx + Math.cos(anguloRad) * 2);
        cañonY = (int) (cy + Math.sin(anguloRad) * 2);

        gc.save();
        gc.translate(cañonX, cañonY);
        gc.rotate(angulo);
        gc.restore();
    }
       
    public void agregarTanque(GraphicsContext gc, int ran,int vida,Terreno terreno,int posicionX, int posicionY) {
      this.posicionX= posicionX;
      this.posicionY = posicionY;
    }
    public void crearHitbox( GraphicsContext gc, Terreno terreno,Jugador jugador) {
        // antes de crear la hitbox del tanque se inician el hancho y el alto de la hitbox que dependera de la resolucion
        int hitboxAncho=0;
        int hitboxLargo=0;
        if(largo_imagen==70)
        {
            hitboxAncho=1;
            hitboxLargo=2;
        }
        if(largo_imagen==80)
        {
            hitboxAncho=4;
            hitboxLargo=5;
        }
        if(largo_imagen==90)
        {
            hitboxAncho=7;
            hitboxLargo=8;
        }
        int marcar_hitbox = jugador.jugador + 2; // marcar_hitbox es el valor que tendra la hitbox del tanque el tanque 0 tendra un valor 2 dentro de la matriz y asi
        System.out.println("marca de la hitbox="+marcar_hitbox);
        for(int i=0;i<ancho+hitboxAncho;i++){ // se inician los ciclos para hacer la hitbox del tanque en funcion del ancho y el alto que se inicizaron por la resolucion
            for (int j=0;j<alto+hitboxLargo;j++){

               //actualizar la colision de la matriz
               int ajustar_posicion=6;//Esta variable se usa para colocar la hitbox del tanque en el lugar correcto, ya que por dimensiones del canvas necesita moverse 6 espacios hacia abajo para quedar correcto
               int posXMatriz = (posicionX / 3 + i);
               int posYMatriz = (posicionY / 3 + j+ajustar_posicion);

                if (posXMatriz >= 0 && posXMatriz < terreno.matriz.length && posYMatriz >= 0 && posYMatriz < terreno.matriz[0].length){ // revisamos los bordes antes de colcar la hitbox
                        terreno.matriz[posXMatriz][posYMatriz] = marcar_hitbox;
                        gc.setFill(Color.GREEN);
                        gc.fillOval(posXMatriz*3 ,posYMatriz*3, 3 , 3 );
                }
            }
        }
    }
    public boolean esta_dentro_de_terreno(Terreno terreno) { //funcion para comprobar que el tanque este dentro del terreno
        int limiteDerecho = terreno.matriz.length * 3; // Límite derecho de la matriz
        int limiteInferior = terreno.matriz[0].length * 3; // Límite inferior de la matriz

        if (posicionX >= 0 && posicionY >= 0) {
            if (posicionX + ancho <= limiteDerecho && posicionY + alto <= limiteInferior) {
                return true; // retornamos true si es verdad
            }
        }
        return false;
    }
    public boolean estaSobreDuna(Terreno terreno) { // funcion para preguntar si el tanque esta sobre las dunas del terrno
        int hitboxAncho = 1;
        int contadorDunas = 0;
        for(int i = 0; i < ancho + hitboxAncho; i++) {
            for(int j = alto; j < alto + 2; j++) {
                int ajustar_posicion = 6;
                int posXMatriz = (posicionX / 3 + i);
                int posYMatriz = (posicionY / 3 + j + ajustar_posicion);
                if (posXMatriz >= 0 && posXMatriz < terreno.dunas.length && posYMatriz >= 0 && posYMatriz < terreno.dunas[0].length) {
                    if(terreno.dunas[posXMatriz][posYMatriz] == 1) {
                        contadorDunas++;
                    }
                    if(contadorDunas==3)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /*public void caidaTanque(GraphicsContext gc, Terreno terrain, int tipoTerreno) {
        float contador[] = {0};
        AnimationTimer animation = new AnimationTimer() {

            @Override
            public void handle(long now) {
                boolean caidaCompleta = true;
                if (tipoTerreno == 0) {
                    terrain.terreno_nieve(gc, 0.0, 100, 1,terrain,alto_resolucion,ancho_resolucion);

                }
                if (tipoTerreno == 1) {
                    terrain.terreno_desierto(gc, 0.0, 100, 1,terrain,alto_resolucion,ancho_resolucion);

                }
                if (tipoTerreno == 2) {
                    terrain.terreno_aram(gc, 0.0, 100, 1,terrain,alto_resolucion,ancho_resolucion);

                }
                for (Jugador jugador : listaJugador.getLista()) {

                    Tank tanque = jugador.getTanque();
                    if (!tanque.estaSobreDuna(terrain)) {
                        tanque.posicionY += tanque.gravedad;
                        caidaCompleta = false;
                    }

                    tanque.dibuarTanque(gc);
                }
                if (caidaCompleta) {
                    this.stop();
                }
            }
        };
        animation.start();
    }*/

    
    
    public void dibuarTanque(GraphicsContext gc)
    {
        //creamos las dimenciones que tendra la imagen dependiendo de la resolucion

        if(Globales.alto_resolucion>699 && Globales.alto_resolucion<=799)
        {
            largo_imagen=70;
            ancho_imagen=70;
        }
        if(Globales.alto_resolucion>799 && Globales.alto_resolucion<=899)
        {
            largo_imagen=80;
            ancho_imagen=80;
        }
        if(Globales.alto_resolucion>899 && Globales.alto_resolucion<=1919)
        {
           ancho_imagen=90;
           largo_imagen=90;
        }
        //dibujamos el tanque con sus dimenciones correspondientes
           Image tanque = new Image(getClass().getResourceAsStream(color));
           gc.drawImage(tanque, posicionX-7, posicionY-12, largo_imagen, ancho_imagen);
    }



}