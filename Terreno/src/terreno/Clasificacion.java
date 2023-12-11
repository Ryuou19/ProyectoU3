
package terreno;

import java.util.ArrayList;
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
    Button finalizar= new Button("CONTINUAR");
    public ArrayList <HBox> hboxJugadores=new ArrayList<>();
    public ArrayList<Integer> listAsesinatos=new ArrayList<>();
    
    Image tanque=null;
    Image titulo=new Image(getClass().getResourceAsStream("./img/ganador.gif"));          
    ImageView victoria;
    ImageView ganador;
    Image draw=new Image(getClass().getResourceAsStream("./img/empate.gif"));
    ImageView empate=new ImageView(draw);
    
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    
  
    
    
    public void mostrarTabla(Tienda tienda, ListaJugadores listJugador){
        Musica.agregar_musica_clasificatoria();
        Globales.escena.setRoot(panel);
        tabla.setPreserveRatio(false);
        
        panel.getChildren().addAll(tabla,finalizar);
        finalizar.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
       
        
        for(int i=0;i<Globales.jugadores_def;i++){
            HBox caja=new HBox(40);
            hboxJugadores.add(caja);
        }
        
        for(Jugador jugador:listJugador.lista){
            Text asesinatos=new Text(jugador.asesinatos+"("+jugador.asesinatosTotales+")");          
            Text suicidios=new Text(jugador.suicidios+"("+jugador.suicidiosTotales+")");
            Text saldo;
            if(jugador.asesinatos-jugador.suicidios>=0){
                saldo=new Text(jugador.saldo-10000+"(+"+((jugador.asesinatos-jugador.suicidios)*5000)+")");
            }
            else{
                saldo=new Text(jugador.saldo-10000+"("+((jugador.asesinatos-jugador.suicidios)*5000)+")");
            }            
            hboxJugadores.get(jugador.jugador).getChildren().addAll(asesinatos,suicidios,saldo);
            panel.getChildren().add(hboxJugadores.get(jugador.jugador));
        }
               
        calcularGanador(listJugador.lista);
        
        finalizar.setOnAction(event -> {
            for(Jugador jugador:listJugador.lista){
                jugador.asesinatos=0;
                jugador.suicidios=0;
            }            
            if(Globales.rondas_def>0 && Globales.rondas_def!=1){        
                tienda.inicializarInterfaz(listJugador);
                System.out.println("Rondas="+Globales.rondas_def);
            }
            if(Globales.rondas_def==1){
                Victoria victory=new Victoria();
                victory.mostrarGanador(listJugador);
            }                      
        });
        
        widthListener= (obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
            tabla.setFitWidth(800*widthRatio);
            finalizar.setLayoutX(525*widthRatio);
            finalizar.setPrefWidth(195*widthRatio);
            finalizar.setFont(Font.font("Serif",FontWeight.BOLD, 24*widthRatio));
            for(int i=0;i<hboxJugadores.size();i++){
                hboxJugadores.get(i).setLayoutX(155*widthRatio);
                for(Node texto:hboxJugadores.get(i).getChildren()){
                    Text textoActual = (Text) texto;
                    textoActual.setFont(Font.font("Monospaced",FontWeight.BOLD, 18*widthRatio));
                }
            }
            if(tanque!=null){
                ganador.setLayoutX(520*widthRatio);            
                ganador.setFitWidth(200*widthRatio);            
                victoria.setLayoutX(490*widthRatio);            
                victoria.setFitWidth(260*widthRatio);
            }
            for(int i=0;i<Globales.jugadores_def;i++){
                hboxJugadores.get(i).setSpacing(45*widthRatio);
            }
            
            
            empate.setLayoutX(490*widthRatio);           
            empate.setFitWidth(270*widthRatio);
            
            
                       
        };

        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            tabla.setFitHeight(800*heightRatio);
            finalizar.setLayoutY(460*heightRatio);
            finalizar.setPrefHeight(60*heightRatio);
            int movimiento=0;
            for(int i=0;i<hboxJugadores.size();i++){
                hboxJugadores.get(i).setLayoutY((195+movimiento)*heightRatio);
                if(i>=2){
                    movimiento+=15;
                }
                movimiento+=90;
            }
            if(tanque!=null){
                ganador.setLayoutY(250*heightRatio);
                ganador.setFitHeight(200*heightRatio);
                victoria.setLayoutY(150*heightRatio);
                victoria.setFitHeight(150*heightRatio);
            }
           
            empate.setLayoutY(300*heightRatio);
            empate.setFitHeight(100*heightRatio);
            
        };
        
        widthListener.changed(null, null, panel.getWidth());
        heightListener.changed(null, null, panel.getHeight()); 
        panel.widthProperty().addListener(widthListener);
        panel.heightProperty().addListener(heightListener);
        
    }
    
    
    public void calcularGanador(ArrayList<Jugador> lista){
        int mayor=0;
        int indice=-1;
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).asesinatos==mayor && indice!=-1){
                indice=-1;
                break;
            }
            if(mayor<lista.get(i).asesinatos){
                
                mayor=lista.get(i).asesinatos;
                indice=i;
            }        
        }
        if(indice==-1){          
            panel.getChildren().add(empate);
        }
     
        ///////////////////////////
        
        if(indice==0){
            tanque=new Image(getClass().getResourceAsStream("./img/tanque1.png"));
        }
        if(indice==1){
            tanque=new Image(getClass().getResourceAsStream("./img/tanque2.png"));
        }
        if(indice==2){
            tanque=new Image(getClass().getResourceAsStream("./img/tanque3.png"));
        }
        if(indice==3){
            tanque=new Image(getClass().getResourceAsStream("./img/tanque4.png"));
        }
        if(indice==4){
            tanque=new Image(getClass().getResourceAsStream("./img/tanque5.png"));
        }
        if(indice==5){
            tanque=new Image(getClass().getResourceAsStream("./img/tanque6.png"));
        }
        if(tanque!=null){
            victoria=new ImageView(titulo);
            ganador=new ImageView(tanque);
            panel.getChildren().addAll(ganador,victoria);
            
        }                 
    }
}
