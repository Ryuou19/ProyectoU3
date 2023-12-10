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
    Button finalizar= new Button("FINALIZAR");
    
    Image tanque=null;
    Image titulo=new Image(getClass().getResourceAsStream("./img/ganadorFinal.png"));          
    ImageView victoria=new ImageView(titulo);
    ImageView ganador;
    Image draw=new Image(getClass().getResourceAsStream("./img/draw.png"));
    ImageView empate=new ImageView(draw);
    
    Image crown=new Image(getClass().getResourceAsStream("./img/corona.png"));
    ImageView corona=new ImageView(crown);
    
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    
    
    public void mostrarGanador(ListaJugadores listJugador){
        Globales.escena.setRoot(panel);
        fondo.setPreserveRatio(false);
        fondo.setX(-12);
        fondo.setY(-36);
        panel.getChildren().addAll(fondo,finalizar);
        finalizar.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FFD700;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
                                                
        calcularGanador(listJugador.lista);
        
        finalizar.setOnAction(event -> {
            Platform.exit();
        });
        
        widthListener= (obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
            fondo.setFitWidth(825*widthRatio);
            fondo.setX(-13*widthRatio);
       
            finalizar.setLayoutX(305*widthRatio);
            finalizar.setPrefWidth(195*widthRatio);
            finalizar.setFont(Font.font("Serif",FontWeight.BOLD, 24*widthRatio));

            if(tanque!=null){
                ganador.setLayoutX(300*widthRatio);
                ganador.setFitWidth(200*widthRatio);
                victoria.setLayoutX(150*widthRatio);            
                victoria.setFitWidth(500*widthRatio);
            }    
            
            empate.setLayoutX(160*widthRatio);           
            empate.setFitWidth(500*widthRatio); 
            corona.setLayoutX(300*widthRatio);           
            corona.setFitWidth(200*widthRatio);   
            
        };

        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            fondo.setFitHeight(881*heightRatio);
            fondo.setY(-41*heightRatio);
            finalizar.setLayoutY(560*heightRatio);
            finalizar.setPrefHeight(50*heightRatio);
            
            if(tanque!=null){
                ganador.setLayoutY(320*heightRatio);
                ganador.setFitHeight(250*heightRatio);
                victoria.setLayoutY(180*heightRatio);
                victoria.setFitHeight(350*heightRatio);
            }
           
            empate.setLayoutY(120*heightRatio);
            empate.setFitHeight(500*heightRatio);
            corona.setLayoutY(110*heightRatio);           
            corona.setFitHeight(250*heightRatio);
            
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
            if(mayor<lista.get(i).asesinatosTotales){              
                mayor=lista.get(i).asesinatosTotales;
                System.out.println("MAYOR= "+mayor);
                indice=i;
            }        
        }
        if(indice!=-1){
            lista.remove(indice);
        }       
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).asesinatosTotales==mayor && indice!=-1){
                System.out.println("Encontro copia");
                indice=-1;
                break;
            }
        }
        
        
        if(indice==-1){          
            panel.getChildren().addAll(empate,corona);
            Musica.agregar_musica_empate();
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
            Musica.agregar_musica_win();
            victoria=new ImageView(titulo);
            ganador=new ImageView(tanque);
            panel.getChildren().addAll(ganador,victoria,corona);          
        }                 
    }
}
