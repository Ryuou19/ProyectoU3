package terreno;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class Victoria {
    Pane panel= new Pane();
    Image imagenFondo = new Image(getClass().getResourceAsStream("./img/fondoVictoria.png"));    
    ImageView fondo = new ImageView(imagenFondo);
    Button finalizar= new Button("TERMINAR");
    
    Image tanque=null;
    Image titulo=new Image(getClass().getResourceAsStream("./img/ganadorFinal.png"));          
    ImageView victoria=new ImageView(titulo);
    ImageView ganador;
    Image draw=new Image(getClass().getResourceAsStream("./img/draw.png"));
    ImageView empate=new ImageView(draw);
    
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    
    
    public void mostrarGanador(ListaJugadores listJugador){
        Globales.escena.setRoot(panel);
        fondo.setPreserveRatio(false);
        
        panel.getChildren().addAll(fondo,finalizar);
        finalizar.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
                                                
        calcularGanador(listJugador.lista);
        
        finalizar.setOnAction(event -> {
            Platform.exit();
        });
        
        widthListener= (obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
            fondo.setFitWidth(800*widthRatio);
            finalizar.setLayoutX(525*widthRatio);
            finalizar.setPrefWidth(195*widthRatio);
            finalizar.setFont(Font.font("Serif",FontWeight.BOLD, 24*widthRatio));

            if(tanque!=null){
                ganador.setLayoutX(520*widthRatio);
                ganador.setFitWidth(200*widthRatio);
                victoria.setLayoutX(300*widthRatio);            
                victoria.setFitWidth(500*widthRatio);
            }    
            
            empate.setLayoutX(300*widthRatio);           
            empate.setFitWidth(500*widthRatio);                                  
        };

        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            fondo.setFitHeight(800*heightRatio);
            finalizar.setLayoutY(460*heightRatio);
            finalizar.setPrefHeight(60*heightRatio);
            
            if(tanque!=null){
                ganador.setLayoutY(250*heightRatio);
                ganador.setFitHeight(200*heightRatio);
                victoria.setLayoutY(150*heightRatio);
                victoria.setFitHeight(300*heightRatio);
            }
           
            empate.setLayoutY(200*heightRatio);
            empate.setFitHeight(300*heightRatio);
            
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
            if(lista.get(i).asesinatosTotales==mayor && indice!=-1){
                indice=-1;
                break;
            }
            if(mayor<lista.get(i).asesinatosTotales){
                
                mayor=lista.get(i).asesinatosTotales;
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