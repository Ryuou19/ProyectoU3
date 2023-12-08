
package terreno;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
            HBox caja=new HBox(20);
            hboxJugadores.add(caja);
        }
        
        for(Jugador jugador:listJugador.lista){
            Text asesinatos=new Text(Integer.toString(jugador.asesionatos));          
            Text suicidios=new Text(Integer.toString(jugador.suicidios));          
            Text saldo=new Text(Integer.toString(jugador.saldo-10000));
            hboxJugadores.get(jugador.jugador).getChildren().addAll(asesinatos,suicidios,saldo);
            panel.getChildren().add(hboxJugadores.get(jugador.jugador));
        }
        
        
        
        
        finalizar.setOnAction(event -> {            
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
            
        
            
        };

        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            tabla.setFitHeight(800*heightRatio);
            finalizar.setLayoutY(200*heightRatio);
            finalizar.setPrefHeight(60*heightRatio);
            hboxJugadores.get(0).setLayoutY(heightRatio);
            
            
        };
        
        widthListener.changed(null, null, panel.getWidth());
        heightListener.changed(null, null, panel.getHeight()); 
        panel.widthProperty().addListener(widthListener);
        panel.heightProperty().addListener(heightListener);
        
    }
}
