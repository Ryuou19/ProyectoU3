package terreno;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tienda extends Pane {
    
    Scene escena=new Scene(this, 800, 600);
    Font font = Font.font("Monospaced", FontWeight.BOLD, 20);
    DropShadow dropShadow = new DropShadow();
    int jugadorActual = 0;
    private Stage primaryStage;
    int resolucion;
    int jugadores;
    int rondas;
    int entorno;
    int cantidad;

    public Tienda(int resolucion, int jugadores, int rondas, int entorno, int cantidad) {
        this.resolucion = resolucion;
        this.jugadores = jugadores;
        this.rondas = rondas;
        this.entorno = entorno;
        this.cantidad = cantidad;
    }
    
    
    
    public void inicializarInterfaz(Stage primaryStage, ListaJugadores listJugadores) {
        this.primaryStage = new Stage();
        tiendaJugador(listJugadores.lista.get(jugadorActual),listJugadores,this.primaryStage);
    }
       
    public void tiendaJugador(Jugador jugador,ListaJugadores listJugadores,Stage primaryStage){
        primaryStage.setTitle("Tienda de Armas");
        primaryStage.setResizable(false);
        Image fondo = new Image(getClass().getResourceAsStream("./img/fondotienda.jpg"));       
        ImageView imageView = new ImageView(fondo);   
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);       
        this.getChildren().add(imageView);
        HBox boxNombre= new HBox();
        Text textNombreJugador=new Text("JUGADOR-->"+jugador.nombre);
        boxNombre.getChildren().add(textNombreJugador);
        textNombreJugador.setFont(font);
        textNombreJugador.setFill(Color.WHITE);
        dropShadow.setColor(Color.rgb(135, 206, 250));
        dropShadow.setWidth(10.0); 
        dropShadow.setHeight(10.0); 
        textNombreJugador.setEffect(dropShadow);
        boxNombre.setLayoutX(30); 
        boxNombre.setLayoutY(10);
        
        HBox boxImagenJugador=new HBox();
        String imagen=jugador.color;
        Image tanque1 = new Image(getClass().getResourceAsStream(imagen));
        ImageView imagentanque1 = new ImageView(tanque1);
        imagentanque1.setFitWidth(150);
        imagentanque1.setFitHeight(150);
        boxImagenJugador.getChildren().add(imagentanque1);
        boxImagenJugador.setLayoutX(50); 
        boxImagenJugador.setLayoutY(-10);
        
        HBox boxSaldo=new HBox(10);
        Text textSaldoJugador=new Text("SALDO = "+jugador.saldo);
        boxSaldo.getChildren().add(textSaldoJugador);
        textSaldoJugador.setFont(font);
        textSaldoJugador.setFill(Color.WHITE);
        boxSaldo.setLayoutX(50); 
        boxSaldo.setLayoutY(150);
        
        HBox boxBalas60=new HBox(10);
        Text textBalas60=new Text("BALAS 60mm -- "+String.valueOf(jugador.getCantidad60()));   
        boxBalas60.getChildren().add(textBalas60);
        textBalas60.setFont(font);
        textBalas60.setFill(Color.WHITE);      
        boxBalas60.setLayoutX(40); 
        boxBalas60.setLayoutY(200);
        
        HBox boxBalas80=new HBox(10);
        Text textBalas80=new Text("BALAS 80mm -- "+String.valueOf(jugador.getCantidad80()));   
        boxBalas80.getChildren().add(textBalas80);
        textBalas80.setFont(font);
        textBalas80.setFill(Color.WHITE);      
        boxBalas80.setLayoutX(40); 
        boxBalas80.setLayoutY(300);
        
        HBox boxBalas105=new HBox(10);
        Text textBalas105=new Text("BALAS 105mm -- "+String.valueOf(jugador.getCantidad105()));   
        boxBalas105.getChildren().add(textBalas105);
        textBalas105.setFont(font);
        textBalas105.setFill(Color.WHITE);      
        boxBalas105.setLayoutX(40); 
        boxBalas105.setLayoutY(400);
        
        
        Button comprarBala60 = new Button("COMPRAR");
        comprarBala60.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #00FF00;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        comprarBala60.setLayoutX(250);
        comprarBala60.setLayoutY(240);
        
        Button comprarBala80 = new Button("COMPRAR");
        comprarBala80.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #0000FF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );       
        comprarBala80.setLayoutX(250);
        comprarBala80.setLayoutY(350);
        
        Button comprarBala105 = new Button("COMPRAR");
        comprarBala105.setLayoutX(250);
        comprarBala105.setLayoutY(460);
        comprarBala105.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        Button finalizarTienda = new Button("TERMINAR COMPRA");
        finalizarTienda.setLayoutX(400);
        finalizarTienda.setLayoutY(400);
        finalizarTienda.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FFFFFF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        comprarBala60.setOnAction(event -> {            
            System.out.println("Bala 60 comprada");
            jugador.saldo-=1000;
            textSaldoJugador.setText("SALDO = "+jugador.saldo);
            jugador.setCantidad60(jugador.getCantidad60()+1);
            textBalas60.setText("BALAS 60mm -- "+String.valueOf(jugador.getCantidad60()));
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
            
        });
        bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        comprarBala80.setOnAction(event -> {
            
            System.out.println("Bala 80 comprada");
            jugador.saldo-=2500;
            textSaldoJugador.setText("SALDO = "+jugador.saldo);
            jugador.setCantidad80(jugador.getCantidad80()+1);
            textBalas80.setText("BALAS 80mm -- "+String.valueOf(jugador.getCantidad80()));
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);                      
        });
        bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);    
        comprarBala105.setOnAction(event -> {
                    
            System.out.println("Bala 105 comprada");
            jugador.saldo-=4000;
            textSaldoJugador.setText("SALDO = "+jugador.saldo);
            jugador.setCantidad105(jugador.getCantidad105()+1);
            textBalas105.setText("BALAS 105mm -- "+String.valueOf(jugador.getCantidad105()));  
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        });
        
        finalizarTienda.setOnAction(event -> {            
            jugadorActual++; 
            System.out.println("JugadorActual= "+jugadorActual);
            System.out.println("Tamanio lista= "+listJugadores.lista.size());
            if (jugadorActual >= 2/*listJugadores.lista.size()/2*/) {
                rondas++;
                System.out.println("Rondas="+rondas);
                primaryStage.close();           
                Jugar juego = new Jugar(listJugadores,resolucion,rondas,jugadores,cantidad,entorno);//inicia el proceso de jugar
                juego.start(new Stage(),escena);
                
            } 
            else {          
                this.getChildren().clear();
                primaryStage.hide();
                tiendaJugador(listJugadores.lista.get(jugadorActual),listJugadores,primaryStage);
            }
        });
        
        this.getChildren().addAll(comprarBala60,comprarBala80,comprarBala105, boxNombre,boxImagenJugador, boxSaldo, boxBalas60,boxBalas80,boxBalas105,finalizarTienda);      
        primaryStage.setX(280); 
        primaryStage.setY(60);    
        primaryStage.setScene(escena);
        primaryStage.show();
    }
    
    public void bloquearBoton(Button comprarBala60,Button comprarBala80,Button comprarBala105,Jugador jugador){
        if(jugador.saldo<1000){
            comprarBala60.setDisable(true);
        }
        
        if(jugador.saldo<2500){
            comprarBala80.setDisable(true);
        }
        
        if(jugador.saldo<4000){
            comprarBala105.setDisable(true);
        }
    }
    
}




