
package terreno;

import java.io.File;
import java.io.IOException;
import javax.swing.Timer; 
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
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
     
    public static void main(String[] args) {
        launch(args);
    }
    String musicPath;//ruta de musica
    AudioInputStream audioInput;//audio del sistema
    float volume;//volumen
    Clip clip;//reproductor
    FloatControl control;//para controlar la musica
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("!TANK WAR!");
        musica(); 
        Pane panel = new Pane();//panel de la interfaz inicial
        
        //imagen tanque inicial con sus propiedades y estilo
        Image imagen = new Image(getClass().getResource("./img/tanque menu.gif").toExternalForm());
        ImageView imageView = new ImageView(imagen);
        imageView.setLayoutX(440); 
        imageView.setLayoutY(270); 
        double nuevoAncho = 300; 
        double nuevoAlto = 200;  
        imageView.setFitWidth(nuevoAncho);
        imageView.setFitHeight(nuevoAlto);
        
        //titulo inicial con gif  
        imagen=new Image(getClass().getResource("./img/text.gif").toExternalForm());
        ImageView titulo = new ImageView(imagen);
        titulo.setLayoutX(390); 
        titulo.setLayoutY(150); 
        nuevoAncho=400;
        nuevoAlto=100;
        titulo.setFitWidth(nuevoAncho);
        titulo.setFitHeight(nuevoAlto);
        
        //fondo
        Rectangle marco = new Rectangle(1200, 700);  
        marco.setFill(Color.rgb(148, 161, 147, 1.0));
        
        //boton comenzar
        HBox boxcomenzar=new HBox();
        Button comenzar = new Button("!!!JUGAR!!!");  
        Font font = Font.font("Serif", FontWeight.NORMAL, 24);//fuente para el texto del boton
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
        
        comenzar.setOnAction(e -> {         
            volume -= 6.0f;//al comenzar a jugar, se baja un poco el volumen
            control.setValue(volume);
            primaryStage.close();
            Jugar juego = new Jugar();//inicia el proceso de jugar
            juego.start(new Stage());
        });
        
        //se añade todo al panel
        panel.getChildren().addAll(marco,comenzar,imageView, titulo);    
        Scene scene = new Scene(panel, 1200, 700);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), panel);//fade blanco inicial
        fadeTransition.setFromValue(0); 
        fadeTransition.setToValue(1); 
        fadeTransition.play();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //funcion que procesa la musica
    public void musica(){
        musicPath = "src/terreno/music/BAIXO-SLOWED.wav";
        try{
            audioInput = AudioSystem.getAudioInputStream(new File(musicPath));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            //control de volumen
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            //volumen (rango: -80.0 a 6.0206)     
            volume = -20.0f;            
            control.setValue(volume);
            
            Timer timer = new Timer(1200, new ActionListener() {//funcion que genera delay al inicio de la ejecucion para la musica, para adaptarse al fade inicial
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
}