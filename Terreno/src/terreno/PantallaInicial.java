package terreno;

import java.io.File;
import java.io.IOException;
import javax.swing.Timer; 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionListener;


public class PantallaInicial extends Application {
           
    ListaJugadores list=ListaJugadores.getInstance();
    int cantidadJugadores=2;
    String musicPath;//ruta de musica
    AudioInputStream audioInput;//audio del sistema
    float volume;//volumen
    Clip clip;//reproductor
    FloatControl control;//para controlar la musica
    Pane panel = new Pane();
    Globales global=new Globales();
    MenuOpciones options = new MenuOpciones();
    Image icono = new Image(getClass().getResourceAsStream("./img/tanque menu.gif"));
    Image imagen = new Image(getClass().getResource("./img/tanque menu.gif").toExternalForm());
    Image titulo1=new Image(getClass().getResource("./img/text.gif").toExternalForm());
    ImageView imageView = new ImageView(imagen);        
    ImageView titulo = new ImageView(titulo1);
    HBox boxopciones=new HBox();
    Button opciones = new Button("OPCIONES");  
    HBox boxcomenzar=new HBox();
    Button comenzar = new Button("!!!JUGAR!!!");
    Rectangle marco = new Rectangle(1600, 800);  
    int validar=0;
    public PantallaInicial() {
    }
      
    
    
    public static void main(String[] args) {
        launch(args);
    }
      
    @Override   
    public void start(Stage primaryStage) {
        Globales.escena=new Scene(panel,Globales.alto_resolucion,Globales.ancho_resolucion); 
        Globales.stage=primaryStage;
        Globales.stage.setResizable(false);
        Globales.stage.getIcons().add(icono); 
        Globales.stage.setWidth(Globales.alto_resolucion);
        Globales.stage.setHeight(Globales.ancho_resolucion);    
        musicPath = "src/terreno/music/BAIXO-SLOWED.wav";
        //musica(musicPath); 
        
        
        //imagen tanque inicial con sus propiedades y estilo        
        imageView.setFitWidth(450);
        imageView.setFitHeight(300);
        titulo.setLayoutX(390); 
        titulo.setLayoutY(150); 
        
        //titulo inicial con gif  
        titulo.setLayoutX(390); 
        titulo.setLayoutY(150); 
        titulo.setFitWidth(600);
        titulo.setFitHeight(150);
        
        //fondo   
        marco.setFill(Color.rgb(148, 161, 147, 1.0));
        
        //boton comenzar
         
        Font font = Font.font("Serif", FontWeight.NORMAL, 29);//fuente para el texto del boton
        comenzar.setFont(font);
        boxcomenzar.getChildren().add(comenzar);
        comenzar.setLayoutX(510);
        comenzar.setLayoutY(500);
        comenzar.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );//estilo del boton
        
        //boton opciones        
        opciones.setFont(font);
        boxopciones.getChildren().add(opciones);
        opciones.setLayoutX(518);
        opciones.setLayoutY(580);
        opciones.setStyle(
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
        
        
        comenzar.setOnAction(e -> {         
            Jugar juego = new Jugar(list);//inicia el proceso de jugar
            //list.instanciarJugadores(cantidadJugadores);
            juego.start(Globales.stage,Globales.escena);
            volume = -20.0f;//al comenzar a jugar, se baja un poco el volumen
            control.setValue(volume);
        });
        
        opciones.setOnAction(e -> {
            if(validar==0){
                options.start(Globales.stage,list,panel);
                validar=1;
            }
            if(validar==1){
                Globales.escena.setRoot(options.paneOpciones);
                global.cambiarEscena(Globales.escena);
                //volume=-20.0f;
                //control.setValue(volume);
            }       
        });
        
        ajustarResolucion();
        
        //se añade todo al panel
        panel.getChildren().addAll(marco,comenzar,imageView, titulo,opciones);    
                
        Globales.stage.setScene(Globales.escena);
        Globales.stage.show();
    }
    //funcion que procesa la musica
    public void musica(String musica){     
        try{
            audioInput = AudioSystem.getAudioInputStream(new File(musica));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            //control de volumen
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //volumen (rango: -80.0 a 6.0206)     
            volume = -20.0f;            
            control.setValue(volume);
            
            Timer timer = new Timer(0, new ActionListener() {//funcion que genera delay al inicio de la ejecucion para la musica, para adaptarse al fade inicial
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    clip.start();
                }
            });
            
            timer.setRepeats(false);
            timer.start();
        //manejo de exepciones
        }catch(UnsupportedAudioFileException e){
            System.out.println(e.toString());
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }  
    
    public void mostrar_inicio(Stage stage){
        stage.setTitle("!TANK WAR!");
        stage.getIcons().add(icono);
        stage.setScene(Globales.escena);  
        detenerMusica();
        musicPath="src/terreno/music/BAIXO-SLOWED.wav";                
        musica(musicPath);
    }
    
    
    public void detenerMusica() {
        if (clip != null && clip.isOpen()) {
            clip.stop();
        }
    }
    
    public void ajustarResolucion(){
        if(Globales.alto_resolucion==800){
            Font font = Font.font("Serif", FontWeight.NORMAL, 21);//fuente para el texto del boton
                   
            imageView.setFitWidth(350);
            imageView.setFitHeight(280);
            imageView.setLayoutX(220); 
            imageView.setLayoutY(200); 
            
            titulo.setLayoutX(200); 
            titulo.setLayoutY(100); 
            titulo.setFitWidth(400);
            titulo.setFitHeight(100);
            
            comenzar.setPrefWidth(150); 
            comenzar.setMinHeight(20); 
            comenzar.setPrefHeight(50);
            comenzar.setFont(font);
            comenzar.setLayoutX(320);
            comenzar.setLayoutY(500);
            
            
            opciones.setPrefWidth(150); 
            opciones.setMinHeight(20); 
            opciones.setPrefHeight(50);
            opciones.setFont(font);
            opciones.setLayoutX(320);
            opciones.setLayoutY(560);
        }
        
        if(Globales.alto_resolucion==900){
            Font font = Font.font("Serif", FontWeight.NORMAL, 25);//fuente para el texto del boton
            Globales.stage.setY(1);
            imageView.setFitWidth(400);
            imageView.setFitHeight(300);
            imageView.setLayoutX(260); 
            imageView.setLayoutY(200); 
             
            titulo.setFitWidth(450);
            titulo.setFitHeight(120);         
            titulo.setLayoutX(220); 
            titulo.setLayoutY(85);
            
            comenzar.setPrefWidth(180); 
            comenzar.setMinHeight(20); 
            comenzar.setPrefHeight(57);
            comenzar.setFont(font);
            comenzar.setLayoutX(360);
            comenzar.setLayoutY(520);
            
            
            opciones.setPrefWidth(180); 
            opciones.setMinHeight(20); 
            opciones.setPrefHeight(57);
            opciones.setFont(font);
            opciones.setLayoutX(360);
            opciones.setLayoutY(580);
        }
    }
    
}