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
    Pane panel= new Pane();//panel
    Image imagenFondo = new Image(getClass().getResourceAsStream("./img/fondoVictoria.png"));//imagen de fondo
    ImageView fondo = new ImageView(imagenFondo);//view
    Button finalizar= new Button("FINALIZAR");//boton para salir
    
    Image tanque=null;//tanque 
    Image titulo=new Image(getClass().getResourceAsStream("./img/ganadorFinal.png"));//texto final de victoria
    ImageView victoria=new ImageView(titulo);//view
    ImageView ganador;//view del tanque ganador
    Image draw=new Image(getClass().getResourceAsStream("./img/pruebadraw.png"));//imagen de empate
    ImageView empate=new ImageView(draw);//view
    
    Image crown=new Image(getClass().getResourceAsStream("./img/corona.png"));//corona del final
    ImageView corona=new ImageView(crown);//view
    
    //variables del metodo listener
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    
    //METODO QUE MUESTRA LA INTERFAZ DEL GANADOR
    public void mostrarGanador(ListaJugadores listJugador){
        Globales.escena.setRoot(panel);//SE DEFINE EL PANEL EN LA ESCENA
        fondo.setPreserveRatio(false);
        fondo.setX(-12);
        fondo.setY(-36);
        panel.getChildren().addAll(fondo,finalizar);//SE AGREGAN NODOS AL PANEL
        finalizar.setStyle(//ESTILO
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FFD700;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
        //CALCULA EL GANADOR TOTAL             
        calcularGanador(listJugador.lista);
        
        //SALIR AL MENU
        finalizar.setOnAction(event -> {
            Platform.exit();
        });
        
        //METODO LISTENER PARA AJUSTAR NODOS A LA ESCENA
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
        
        //METODO LISTENER PARA AJUSTAR NODOS A LA ESCENA
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
           
            empate.setLayoutY(350*heightRatio);
            empate.setFitHeight(100*heightRatio);
            corona.setLayoutY(110*heightRatio);           
            corona.setFitHeight(250*heightRatio);           
        };
        
        //REINICIAMOS VARIABLES LISTENER
        widthListener.changed(null, null, panel.getWidth());
        heightListener.changed(null, null, panel.getHeight()); 
        panel.widthProperty().addListener(widthListener);
        panel.heightProperty().addListener(heightListener);
        
    }
    
    //METODO QUE CALCULA EL GANADOR TOTAL DEL PARTIDO
    //MUY PARECIDO A CALCULAR GANADOR DE UNA RONDA EN CLASIFICACION
    //PERO UTILIZA DISTINTOS VALORES Y NODOS VISUALES
    public void calcularGanador(ArrayList<Jugador> lista){
        int mayor=0;
        int indice=-1;
        //busca el mayor
        for(int i=0;i<lista.size();i++){
            if(mayor<lista.get(i).asesinatosTotales){              
                mayor=lista.get(i).asesinatosTotales;
                System.out.println("MAYOR= "+mayor);
                indice=i;
            }        
        }

        for(int i=0;i<lista.size();i++){//busca si es que el valor mayor existe 2 veces
            if(i!=indice){//se descarta el indice mismo del mayor
                if(lista.get(i).asesinatosTotales==mayor && indice!=-1){
                    indice=-1;
                    break;
                }
            }          
        }
        
        //se declara empate
        if(indice==-1){          
            panel.getChildren().addAll(empate,corona);
            Musica.agregar_musica_empate();
        }
            
        //si hay ganador se hace el proceso de eleccion del tanque ganador
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
        if(tanque!=null){//si hay un ganador
            Musica.agregar_musica_win();
            victoria=new ImageView(titulo);
            ganador=new ImageView(tanque);
            panel.getChildren().addAll(ganador,victoria,corona);          
        }                 
    }
}
