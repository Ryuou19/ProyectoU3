package terreno;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;// import usado para dibujar hitbox con el fin de visualizarla


public class Tank{
    public int posicionX;
    public int posicionY;
    public String color;
    public int jugadorTanque;
    private double angulo;
    private int cañonX;
    private int cañonY;
    int ancho;
    int alto;   
    int vida;
    
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
    
    public double getAngulo() {
        return angulo;
    }

    public int getVida() {
        return vida;
    }
       
    public void modificarCañon(GraphicsContext gc, double angulo,int jugador){
        this.angulo = angulo;
        int x = posicionX;
        int y = posicionY;
        
        int cx = x + 35;//valor usado para que la bala salga desde fuera de la hitbox del tanque, para que no se suicide de inmediato
        int cy = y + 35;
        double anguloRad = Math.toRadians(angulo);
        //calcular direccion del cañon de cada jugador,invirtiendo el sentido para el jugador 2
        if (jugador == 1) {
            cañonX = (int) (cx + Math.cos(anguloRad) * 2);
            cañonY = (int) (cy + Math.sin(anguloRad) * 2);
        } 
        else if (jugador == 2) {      
            cañonX = (int) (cx - Math.cos(anguloRad) * 2);
            cañonY = (int) (cy - Math.sin(anguloRad) * 2);
        }
        gc.save();
        gc.translate(cañonX, cañonY);
        gc.rotate(angulo);
        gc.restore();
    }
       
    public void agregarTanque(GraphicsContext gc, int ran, int[][]matriz,int vida) {
        int opcion = Jugar.getRandom();//opcion escogida de todas las posiciones posibles randomicamente
        int[][] positions = null;
        int x1, x2, x3, x4;//valores x de cada posicion de los tanques
        int y1, y2, y3, y4;//valores y de cada posicion de los tanques
        if(opcion == 0){
            if(jugadorTanque == 1){
                x1=120;x2=60;x3=210;x4=268;
                y1=530;y2=500;y3=480;y4=390;
                positions = new int[][]{{x1, x2, x3, x4},{y1, y2, y3,y4}};//se guardan en una matriz para mejor manejo
            }
            if(jugadorTanque == 2){
                x1=842; x2=1020; x3=750; x4=647;
                y1=580; y2=230; y3=370;y4=207;
                positions = new int[][]{{x1, x2, x3, x4},{y1, y2, y3,y4}};//mismo proceso
            }

        }
        if(opcion == 1){
            if(jugadorTanque == 1){
                x1=275;x2=370;x3=230;x4=230;
                y1=150;y2=335;y3=115;y4=115;
                positions = new int[][]{{x1, x2, x3, x4},{y1, y2, y3,y4}};
            }
            if(jugadorTanque == 2){
                x1=837;x2=1070;x3=750;x4=990;
                y1=280;y2=420;y3=350;y4=505;
                positions = new int[][]{{x1, x2, x3, x4},{y1, y2, y3,y4}};
            }

        }
        if(opcion == 2){
            if(jugadorTanque == 1){
                x1=120; x2=60; x3=175; x4=50;
                y1=335; y2=380; y3=360; y4=400;
                positions = new int[][]{{x1, x2, x3, x4},{y1, y2, y3,y4}};
            }
            if(jugadorTanque == 2){
                x1=900;x2=1020;x3=1110;x4=695;
                y1=580; y2=300; y3=220; y4=207;
                positions = new int[][]{{x1, x2, x3, x4},{y1, y2, y3,y4}};
            }

        }
        if (positions != null) {
            int[] x = positions[0];
            int[] y = positions[1];
            Image tanque = new Image(getClass().getResourceAsStream(color));
            gc.drawImage(tanque, x[ran], y[ran], 70, 70);
            posicionX = x[ran] + 6;//valores para ajustarse al canvas y terreno
            posicionY = y[ran] + 13;//valores para ajustarse al canvas y terreno
        }               
        int hitboxAncho=1;
        int hitboxLargo=2;
        for(int i=0;i<ancho+hitboxAncho;i++){
            for (int j=0;j<alto+hitboxLargo;j++){
               //actualizar la colision de la matriz
               int ajustar_posicion=6;//Esta variable se usa para colocar la hitbox del tanque en el lugar correcto, ya que por dimensiones del canvas necesita moverse 6 espacios hacia abajo para quedar correcto
               int posXMatriz = (posicionX / 3 + i);
               int posYMatriz = (posicionY / 3 + j+ajustar_posicion);
               if  (posXMatriz >= 0 && posXMatriz < matriz.length && posYMatriz >= 0 && posYMatriz < matriz[0].length){
                    if(jugadorTanque==1){
                        matriz[posXMatriz][posYMatriz] = 2;
                        //gc.setFill(Color.RED); codigo usado para visualizar la hitbox para revisar casos de prueba
                        //gc.fillOval(posXMatriz*3 ,posYMatriz*3, 3 , 3 ); 
                    }
                    else if(jugadorTanque==2){
                        matriz[posXMatriz][posYMatriz] = 3;
                        //gc.setFill(Color.RED); codigo usado para visualizar la hitbox para revisar casos de prueba
                        //gc.fillOval(posXMatriz*3 ,posYMatriz*3, 3 , 3 );
                    }
                }
            }    
        }           
    }
    //funcion que reduce la vida dependiendo del valor de la bala o del radio de explosion
    public int ajustar_vida(int vida, int danio){
        vida-=danio;
        this.vida=vida;
        return vida;
    }
}
