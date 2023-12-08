
package terreno;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Clasificacion {
    Pane panel= new Pane();
    Image imagenTabla = new Image(getClass().getResourceAsStream("./img/tabla.png"));    
    ImageView tabla = new ImageView(imagenTabla);
    Button finalizar= new Button("Cerrar");
    public ArrayList <HBox> hboxJugadores=new ArrayList<>();
    
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    
    
    public void mostrarTabla(Tienda tienda, ListaJugadores listJugador){
        Globales.escena.setRoot(panel);
        tabla.setPreserveRatio(false);
        
        panel.getChildren().addAll(tabla,finalizar);
        
       
        
        for(int i=0;i<Globales.jugadores_def;i++){
            HBox caja=new HBox(35);
            hboxJugadores.add(caja);
        }
        
        for(Jugador jugador:listJugador.lista){
            Text asesinatos=new Text(jugador.asesionatos+"("+jugador.asesinatosTotales+")");          
            Text suicidios=new Text(jugador.suicidios+"("+jugador.suicidiosTotales+")");          
            Text saldo=new Text(jugador.saldo-10000+"("+((jugador.asesionatos-jugador.suicidios)*5000)+")");
            hboxJugadores.get(jugador.jugador).getChildren().addAll(asesinatos,suicidios,saldo);
            panel.getChildren().add(hboxJugadores.get(jugador.jugador));
        }
        
        
        
        
        finalizar.setOnAction(event -> {
            for(Jugador jugador:listJugador.lista){
                jugador.asesionatos=0;
                jugador.suicidios=0;
            }            
            if(Globales.rondas_def>0){        
                tienda.inicializarInterfaz(listJugador);
                System.out.println("Rondas="+Globales.rondas_def);
            }
            if(Globales.rondas_def==0){
                Platform.exit();
            }                      
        });
        
        widthListener= (obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
            tabla.setFitWidth(800*widthRatio);
            finalizar.setLayoutX(200*widthRatio);
            finalizar.setPrefWidth(100*widthRatio);
            
            for(int i=0;i<hboxJugadores.size();i++){
                hboxJugadores.get(i).setLayoutX(155*widthRatio);
                for(Node texto:hboxJugadores.get(i).getChildren()){
                    Text textoActual = (Text) texto;
                    textoActual.setFont(Font.font("Monospaced",FontWeight.BOLD, 20*widthRatio));
                }
            }
                       
        };

        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            tabla.setFitHeight(800*heightRatio);
            finalizar.setLayoutY(200*heightRatio);
            finalizar.setPrefHeight(60*heightRatio);
            int movimiento=0;
            for(int i=0;i<hboxJugadores.size();i++){
                hboxJugadores.get(i).setLayoutY((195+movimiento)*heightRatio);
                if(i>=2){
                    movimiento+=15;
                }
                movimiento+=90;
            }
            
            
            
        };
        
        widthListener.changed(null, null, panel.getWidth());
        heightListener.changed(null, null, panel.getHeight()); 
        panel.widthProperty().addListener(widthListener);
        panel.heightProperty().addListener(heightListener);
        
    }
}
