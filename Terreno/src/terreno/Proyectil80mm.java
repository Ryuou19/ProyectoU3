package terreno;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Proyectil80mm extends Bala{
    public Proyectil80mm(int x, int y, int radio, double anguloLanzamiento, double velocidadLanzamiento, int contador, int danio) {
        super(x, y, radio,anguloLanzamiento, velocidadLanzamiento, contador,danio);
    }
    @Override
    public void dibujo(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillOval(ejeX , ejeY, 2 * radiojugador, 2 * radiojugador);
    }
}
