package terreno;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Terreno{
    public int[][] matriz;
    public int pixel;
    public int[][]dunas;
    public int [][] explosion;
    public int radio=0;
    private int contador=0;
    Image nieve = new Image(getClass().getResourceAsStream("./img/frozen.jpg"));//imagen nieve
    Image desierto  = new Image(getClass().getResourceAsStream("./img/desiertoo.jpg"));//imagen desierto
    Image lol = new Image(getClass().getResourceAsStream("./img/bosque.jpg"));//imagen bosque
    ListaJugadores listJugador=ListaJugadores.getInstance();





    public Terreno(int alto, int ancho, int pixel,GraphicsContext gc ) {
        this.pixel=pixel;
        this.matriz=new int[alto][ancho];
        this.dunas=new int[alto][ancho];
        this.explosion=new int[alto][ancho];

    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void iniciar() {//inicializamos todas las matrices usadas en el juego
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++) {
                matriz[i][j]=0;
            }
        }
        for(int i=0;i<dunas.length;i++) {
            for(int j=0;j<dunas[i].length;j++) {
                dunas[i][j]=0;
            }
        }
        for(int i=0;i<explosion.length;i++) {
            for(int j=0;j<explosion[i].length;j++) {
                explosion[i][j]=0;
            }
        }
    }
    public void agregarImagenDeFondo(GraphicsContext gc) {//agregamos las imagenes de fondo
        if (Jugar.getRandom() == 0) {
            gc.drawImage(nieve, 0, 0, 500 * pixel, 320 * pixel);
        }
        if (Jugar.getRandom() == 1) {
            gc.drawImage(desierto, 0, 0, 500 * pixel, 320 * pixel);
        }
        if (Jugar.getRandom() == 2) {
            gc.drawImage(lol, 0, 0, 500 * pixel, 320 * pixel);
        }

    }

    public void terreno_nieve(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno,int alto, int ancho) {//terreno nevado
        
        int escala = this.pixel;
        double nivel_mar = 0.5;
        double amplitud = 0.15;
        double frecuencia = 0.03;
        agregarImagenDeFondo(gc);
        
        for (int i = 0; i < alto/2; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(255, 255, 255));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.21;
        frecuencia = 0.0485;
        for (int i = alto/2; i < 326; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(255, 255, 255));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.2;
        frecuencia = 0.03;
        for (int i = 326; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(255, 255, 255));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        if(contador==0){
            colocarTanquesTerreno(gc, angulo, vida, validar, terreno, alto, ancho);
        }
        contador++;
    }

    public void terreno_desierto(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno,int alto, int ancho) {//terreno desertico
     
        int escala = this.pixel;
        double nivel_mar = 0.5;
        double frecuencia1 = 0.05;
        double frecuencia2 = 0.04;
        double frecuencia3 = 0.06;
        double amplitud1 = 0.14;
        double amplitud2 = 0.12;
        double amplitud3 = 0.16;

        agregarImagenDeFondo(gc);

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud1 * Math.sin(frecuencia1 * nx * alto)
                            + amplitud2 * Math.sin(frecuencia2 * nx * alto)
                            + amplitud3 * Math.sin(frecuencia3 * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(128, 64, 0));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        double amplitud = 0.07;
        double frecuencia = 0.08;
        for (int i = 120; i < 180; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(128, 64, 0));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        if(contador==0){
            colocarTanquesTerreno(gc, angulo, vida, validar, terreno, alto, ancho);
        }
        contador++;
    }

    public void terreno_aram(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno,int alto, int ancho) {//terreno de bosque
       
        int escala = this.pixel;
        double nivel_mar = 0.5;
        double amplitud = 0.17;
        double frecuencia = 0.03;
        agregarImagenDeFondo(gc);


        amplitud = 0.07;
        frecuencia = 0.1;
        for (int i = 0; i < 70; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(210, 180, 140));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.21;
        frecuencia = 0.045;
        for (int i = 50; i < 330; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(210, 180, 140));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.1;
        frecuencia = 0.045;
        for (int i = 130; i < 250; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(210, 180, 140));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }

        amplitud = 0.3;
        frecuencia = 0.01;
        for (int i = 326; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (dunas[i][j] != -1) {
                    double nx = (double) i / alto;
                    double ny = (double) j / ancho;
                    double altura_dunas = nivel_mar + amplitud * Math.sin(frecuencia * nx * alto);
                    if (ny >= altura_dunas) {
                        gc.setFill(Color.rgb(210, 180, 140));
                        gc.fillRect(i * escala, j * escala, escala, escala);
                        dunas[i][j] = 1;
                    }
                }
            }
        }
        if(contador==0){
            colocarTanquesTerreno(gc, angulo, vida, validar, terreno, alto, ancho);
        }
        contador++;
    }

    public int colision_terreno(GraphicsContext gc, Bala bala, int dunas[][], int matriz[][], int tipo) {
        int x = (int) bala.ejeX / pixel;//traspasamos la posicion x a relacion escala de la matriz y no de los pixeles
        int y = (int) bala.ejeY / pixel;//traspasamos la posicion y a relacion escala de la matriz y no de los pixeles
        if (x >= 0 && x < 500 && y >= 0 && y < 300) {
            int valorMatriz=matriz[x][y];
            //System.out.println("el valor en la matriz es ->"+valorMatriz);
            if (valorMatriz>=2) {
                bala.marcar();
                if(valorMatriz==2){
                    return 1;
                }
                if(valorMatriz==3){
                    return 2;
                }
                if(valorMatriz==4){
                    return 3;
                }
                if(valorMatriz==5){
                    return 4;
                }
                if(valorMatriz==6){
                    return 5;
                }
                if(valorMatriz==7){
                    return 6;
                }
                
                
            } else if (dunas[x][y] == 1) {//colision en el terreno
                bala.marcar();
                matriz[x][y] = 0;
                if(tipo==1){
                    radio=5;
                }
                if(tipo==2){
                    radio=10;
                }
                if(tipo==3){
                    radio=30;
                }
                for (int i = x - radio; i <= x + radio; i++) {//realiza la explosion del disparo dependiendo del tipo de bala en base a un radio
                    for (int j = y - radio; j <= y + radio; j++) {
                        // Calcula la distancia desde el punto de impacto (i-j)
                        double distancia= Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
                        // Verifica que la celda esté dentro del radio circular (pero no en el límite)
                        if (i >= 0 && i < dunas.length && j >= 0 && j < dunas[i].length && distancia < radio) {//verifica los limites de la matriz
                            dunas[i][j] = -1;
                            explosion[i][j]=1;//se añade un valor de explosion a la matriz dicha
                            int x1 = i * pixel;
                            int y1 = j * pixel;
                            gc.clearRect(x1, y1, pixel, pixel);//se limpia la zona de la matriz que fue destruida
                        }
                    }
                }
            }
        }
        if (x > 500 || x < 0 ) {
            bala.marcar();
        }
        return 0;
    }
    public void colocarTanquesTerreno(GraphicsContext gc, Double angulo, int vida, int validar, Terreno terreno, int alto, int ancho) {
        for (Jugador jugador : listJugador.getLista()) {
            jugador.creaTanque(gc, vida, validar, terreno);
            jugador.getTanque().modificarCañon(gc, angulo);
        }
    }
    public void borrarHitboxAnterior() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] >= 2) {
                    matriz[i][j] = 0;
                }
            }
        }
    }
}  
