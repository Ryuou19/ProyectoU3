package terreno;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tienda  {
       
    Rectangle marco = new Rectangle(1920, 1080);  
    int jugadorActual = 0;
    Jugador jugador;
    int ultimaOpcion;
          
    Text textNombreJugador;
    String imagen;
    Image tanque;
    ImageView imagentanque;
    
    Text textSaldoJugador;
    Text textBalas60;
    Text textBalas80;
    Text textBalas105;
    
    Button comprarBala60;
    Button comprarBala80;
    Button comprarBala105;
    Button finalizarTienda;
    Button revertirCompra;
    Button cambiarJugadorDerecha;
    Button cambiarJugadorIzquierda;
    HBox cambiar=new HBox(2);
    
    
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    int cambio=1;
    
    //TANQUE 1
    Image tanque1 = new Image(getClass().getResourceAsStream("./img/tanque1.png"));
    ImageView imagentanque1 = new ImageView(tanque1);

    //TANQUE 2
    Image tanque2 = new Image(getClass().getResourceAsStream("./img/tanque2.png"));
    ImageView imagentanque2 = new ImageView(tanque2);
    
    //TANQUE 3
    Image tanque3 = new Image(getClass().getResourceAsStream("./img/tanque3.png"));
    ImageView imagentanque3 = new ImageView(tanque3);
        
    //TANQUE 4
    Image tanque4 = new Image(getClass().getResourceAsStream("./img/tanque4.png"));
    ImageView imagentanque4 = new ImageView(tanque4);
    
    //TANQUE 5
    Image tanque5 = new Image(getClass().getResourceAsStream("./img/tanque5.png"));
    ImageView imagentanque5 = new ImageView(tanque5);
        
    //TANQUE 6
    Image tanque6 = new Image(getClass().getResourceAsStream("./img/tanque6.png"));
    ImageView imagentanque6 = new ImageView(tanque6);
       
    //LISTA TANQUES
    ImageView[] imagenes={imagentanque1,imagentanque2,imagentanque3,imagentanque4,imagentanque5,imagentanque6};
    
    public Tienda() {       
    }
      
    public void inicializarInterfaz( ListaJugadores listJugadores) {      
        tiendaJugador(listJugadores);       
    }
       
    public void tiendaJugador(ListaJugadores listJugadores){
        cambiarJugador(listJugadores);
        System.out.println("GLOBALES: "+Globales.jugadores_def);
        //Globales.cambiarResolucion(Globales.alto_resolucion+cambio,Globales.ancho_resolucion+cambio);
        
        for(Jugador jugadorBot:listJugadores.lista){
            if(jugadorBot.tipo.equals("bot")){
            comprarBot(jugadorBot);           
            }
        }
        
        Pane panel=new Pane();       
        Globales.stage.setTitle("Tienda de Armas");
                           
        marco.setFill(Color.rgb(148, 161, 147, 1.0));
     
        textNombreJugador=new Text(jugador.nombre);   
        textNombreJugador.setFill(Color.WHITE);
   
        imagen=jugador.color;
        tanque = new Image(getClass().getResourceAsStream(imagen));
        imagentanque = new ImageView(tanque);
        
        textSaldoJugador=new Text("$"+jugador.saldo);     
        textSaldoJugador.setFill(Color.GREEN);
        
        textBalas60=new Text("60mm -- "+String.valueOf(jugador.getCantidad60()));        
        textBalas60.setFill(Color.WHITE);      
        
        textBalas80=new Text("80mm -- "+String.valueOf(jugador.getCantidad80()));   
        textBalas80.setFill(Color.WHITE);      
        
        textBalas105=new Text("105mm -- "+String.valueOf(jugador.getCantidad105()));      
        textBalas105.setFill(Color.WHITE);      
        
        
        comprarBala60 = new Button("$1000");
        comprarBala60.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #00FF00;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        comprarBala80 = new Button("$2500");
        comprarBala80.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #0000FF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );       
        
        comprarBala105 = new Button("$4000");
        comprarBala105.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        finalizarTienda = new Button("TERMINAR");
        finalizarTienda.setLayoutX(400);
        finalizarTienda.setLayoutY(400);
        finalizarTienda.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FFFFFF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        revertirCompra = new Button("DEVOLVER");       
        revertirCompra.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FFFFFF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        cambiarJugadorDerecha = new Button(">");       
        cambiarJugadorDerecha.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FFFFFF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        cambiarJugadorIzquierda = new Button("<");       
        cambiarJugadorIzquierda.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FFFFFF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        cambiar.getChildren().addAll(cambiarJugadorIzquierda,cambiarJugadorDerecha);
        
        
        bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        comprarBala60.setOnAction(event -> {            
            System.out.println("Bala 60 comprada");
            jugador.saldo-=1000;
            textSaldoJugador.setText("$"+jugador.saldo);
            jugador.setCantidad60(jugador.getCantidad60()+1);
            textBalas60.setText("60mm -- "+String.valueOf(jugador.getCantidad60()));
            ultimaOpcion=1;
            jugador.carrito.add(ultimaOpcion);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
            
        });
        bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        comprarBala80.setOnAction(event -> {        
            System.out.println("Bala 80 comprada");
            jugador.saldo-=2500;
            textSaldoJugador.setText("$"+jugador.saldo);
            jugador.setCantidad80(jugador.getCantidad80()+1);
            textBalas80.setText("80mm -- "+String.valueOf(jugador.getCantidad80()));
            ultimaOpcion=2;
            jugador.carrito.add(ultimaOpcion);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);                      
        });
        bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);    
        comprarBala105.setOnAction(event -> {                   
            System.out.println("Bala 105 comprada");
            jugador.saldo-=4000;
            textSaldoJugador.setText("$"+jugador.saldo);
            jugador.setCantidad105(jugador.getCantidad105()+1);
            textBalas105.setText("105mm -- "+String.valueOf(jugador.getCantidad105()));  
            ultimaOpcion=3;
            jugador.carrito.add(ultimaOpcion);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        });
        
        revertirCompra.setOnAction(event -> {  
            
            if (jugador.carrito.size()-1 >= 0) { // Verificar si la lista tiene al menos un elemento
                ultimaOpcion=jugador.carrito.get(jugador.carrito.size()-1);
            }
            if(jugador.carrito.size()-1<0){
                return;
            }
            if(ultimaOpcion==1){
                jugador.saldo+=1000;
                jugador.setCantidad60(jugador.getCantidad60()-1);
                textBalas60.setText("60mm -- "+String.valueOf(jugador.getCantidad60()));  
            }
            if(ultimaOpcion==2){
                jugador.saldo+=2500;
                jugador.setCantidad80(jugador.getCantidad80()-1);
                textBalas80.setText("80mm -- "+String.valueOf(jugador.getCantidad80()));  
            }
            if(ultimaOpcion==3){
                jugador.saldo+=4000;
                jugador.setCantidad105(jugador.getCantidad105()-1);
                textBalas105.setText("105mm -- "+String.valueOf(jugador.getCantidad105()));  
            }
            jugador.carrito.remove(jugador.carrito.size()-1);
            ultimaOpcion=0;
            textSaldoJugador.setText("$"+jugador.saldo);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        });
        
        cambiarJugadorDerecha.setOnAction(event -> {                   
            jugadorActual++;
            if(jugadorActual==listJugadores.lista.size()){
                jugadorActual=0;
            }
            cambiarJugador(listJugadores);
            cambiarValores(listJugadores);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,listJugadores.lista.get(jugadorActual));
        });
        
        cambiarJugadorIzquierda.setOnAction(event -> {                   
            jugadorActual--;
            if(jugadorActual<0){
                jugadorActual=listJugadores.lista.size()-1;
            }
            cambiarJugador(listJugadores);
            cambiarValores(listJugadores);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,listJugadores.lista.get(jugadorActual));
        });
        
        finalizarTienda.setOnAction(event -> {            
            
            for(Jugador jugadorlimpio:listJugadores.lista){
                jugadorlimpio.carrito.clear();
            }                
            panel.widthProperty().removeListener(widthListener);
            panel.heightProperty().removeListener(heightListener);

            Globales.rondas_def--;  
            
            if(cambio==1){
                Globales.alto_resolucion--;
            }
            Jugar juego = new Jugar(listJugadores);//inicia el proceso de jugar
            juego.start();              
        });
        
        widthListener= (obs, oldWidth, newWidth) -> {
            double widthRatio = newWidth.doubleValue() / 800; 
                       
            textNombreJugador.setLayoutX(114*widthRatio);      
            textNombreJugador.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*widthRatio));
           
            imagentanque.setFitWidth(150*widthRatio);         
            imagentanque.setLayoutX(70*widthRatio);
            
            textSaldoJugador.setLayoutX(112*widthRatio);            
            textSaldoJugador.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*widthRatio));
                      
            textBalas60.setLayoutX(305*widthRatio);         
            textBalas60.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*widthRatio));          
            
            textBalas80.setLayoutX(455*widthRatio);         
            textBalas80.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*widthRatio));
                     
            textBalas105.setLayoutX(605*widthRatio);    
            textBalas105.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*widthRatio));
                    
            comprarBala60.setLayoutX(300*widthRatio);
            comprarBala60.setPrefWidth(120*widthRatio);
            comprarBala60.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 18*widthRatio));
            
            comprarBala80.setLayoutX(450*widthRatio);
            comprarBala80.setPrefWidth(120*widthRatio);
            comprarBala80.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 18*widthRatio));
            
            comprarBala105.setLayoutX(600*widthRatio);
            comprarBala105.setPrefWidth(120*widthRatio);
            comprarBala105.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 18*widthRatio));
            
            revertirCompra.setLayoutX(435*widthRatio);
            revertirCompra.setPrefWidth(150*widthRatio);
            revertirCompra.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 15*widthRatio));
            
            finalizarTienda.setLayoutX(72*widthRatio);
            finalizarTienda.setPrefWidth(150*widthRatio);
            finalizarTienda.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 15*widthRatio));
            
            cambiar.setLayoutX(95*widthRatio);
            cambiarJugadorDerecha.setPrefWidth(50*widthRatio);
            cambiarJugadorDerecha.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 15*widthRatio));
            cambiarJugadorIzquierda.setPrefWidth(50*widthRatio);
            cambiarJugadorIzquierda.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 15*widthRatio));
            
        };

        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800;       
                    
            textNombreJugador.setLayoutY(320*heightRatio);
            
            imagentanque.setFitHeight(150*heightRatio);   
            imagentanque.setLayoutY(270*heightRatio);
            
            textSaldoJugador.setLayoutY(420*heightRatio);
            
            textBalas60.setLayoutY(335*heightRatio);
            
            textBalas80.setLayoutY(335*heightRatio);
          
            textBalas105.setLayoutY(335*heightRatio);
            
            
            comprarBala60.setLayoutY(360*heightRatio);
            comprarBala60.setPrefHeight(40*heightRatio);
            
            comprarBala80.setLayoutY(360*heightRatio);
            comprarBala80.setPrefHeight(40*heightRatio);
            
            comprarBala105.setLayoutY(360*heightRatio);
            comprarBala105.setPrefHeight(40*heightRatio);
            
            revertirCompra.setLayoutY(440*heightRatio);
            revertirCompra.setPrefHeight(40*heightRatio);
            
            finalizarTienda.setLayoutY(440*heightRatio);       
            finalizarTienda.setPrefHeight(40*heightRatio);
            
            cambiar.setLayoutY(240*heightRatio);
            cambiarJugadorDerecha.setPrefHeight(30*heightRatio);        
            cambiarJugadorIzquierda.setPrefHeight(30*heightRatio);
            
            
        };
        //despues de configurar los ChangeListeners, forzar una actualización inicial
        widthListener.changed(null, null, panel.getWidth());
        heightListener.changed(null, null, panel.getHeight()); 
        panel.widthProperty().addListener(widthListener);
        panel.heightProperty().addListener(heightListener);
        
        panel.getChildren().addAll(marco,comprarBala60,comprarBala80,comprarBala105,imagentanque,
          textSaldoJugador, textBalas60,textBalas80,textBalas105,finalizarTienda,revertirCompra,textNombreJugador,
          cambiar);        
        Globales.escena.setRoot(panel);        
    }
    
    public void bloquearBoton(Button comprarBala60,Button comprarBala80,Button comprarBala105,Jugador jugador){
        if(jugador.saldo<1000){
            comprarBala60.setDisable(true);
        }
        else if(jugador.saldo>=1000){
            comprarBala60.setDisable(false);
        }
        
        if(jugador.saldo<2500){
            comprarBala80.setDisable(true);
        }
        else if(jugador.saldo>=2500){
            comprarBala80.setDisable(false);
        }
        
        if(jugador.saldo<4000){
            comprarBala105.setDisable(true);
        }
        else if(jugador.saldo>=4000){
            comprarBala105.setDisable(false);
        }
             
    }
    
    public void comprarBot(Jugador jugador){
        Random random = new Random();
        while(jugador.saldo >= 1000 || jugador.saldo >= 2500 || jugador.saldo >= 4000) {
            int opcion = random.nextInt(3) + 1;    
            if (opcion == 1 && jugador.saldo >= 1000) {
                jugador.saldo -= 1000;
                jugador.setCantidad60(jugador.getCantidad60() + 1);
                System.out.println("Bala 60 comprada por el bot");
            } else if (opcion == 2 && jugador.saldo >= 2500) {
                jugador.saldo -= 2500;
                jugador.setCantidad80(jugador.getCantidad80() + 1);
                System.out.println("Bala 80 comprada por el bot");
            } else if (opcion == 3 && jugador.saldo >= 4000) {
                jugador.saldo -= 4000;
                jugador.setCantidad105(jugador.getCantidad105() + 1);
                System.out.println("Bala 105 comprada por el bot");
            }
            
        }     
    }
    
    public void cambiarJugador(ListaJugadores listJugadores){
        jugador=listJugadores.lista.get(jugadorActual);     
    }
    
    public void cambiarValores(ListaJugadores listJugadores){
        textNombreJugador.setText(listJugadores.lista.get(jugadorActual).nombre);
        textSaldoJugador.setText("$"+listJugadores.lista.get(jugadorActual).saldo);
        textBalas60.setText("60mm -- "+listJugadores.lista.get(jugadorActual).cantidad60);
        textBalas80.setText("80mm -- "+listJugadores.lista.get(jugadorActual).cantidad80);
        textBalas105.setText("105mm -- "+listJugadores.lista.get(jugadorActual).cantidad105);          
        tanque = new Image(getClass().getResourceAsStream(jugador.color));
        imagentanque.setImage(tanque);
    }
}