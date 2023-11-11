package terreno;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Proyectil60mm extends Bala{
    public Proyectil60mm(int x, int y, int radio, double anguloLanzamiento, double velocidadLanzamiento, int contador,int danio) {
        super(x, y, radio,anguloLanzamiento, velocidadLanzamiento, contador,danio);
    }
    @Override
    public void dibujo(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(ejeX , ejeY, 2 * radiojugador, 2 * radiojugador);
    }
}