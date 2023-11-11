package terreno;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Proyectil105mm extends Bala{
    public Proyectil105mm(int x, int y, int radiojugador, double anguloLanzamiento, double velocidadLanzamiento, int contador,int danio) {
        super(x, y, radiojugador, anguloLanzamiento, velocidadLanzamiento, contador,danio);
    }   
    @Override
    public void dibujo(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(ejeX , ejeY, 2 * radiojugador, 2 * radiojugador);
    }

}