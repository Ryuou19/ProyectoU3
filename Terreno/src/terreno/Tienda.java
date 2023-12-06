package terreno;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tienda  {
       
    Rectangle marco = new Rectangle(1920, 1080);  
    int jugadorActual = 0;
    int ultimaOpcion;
    public ArrayList<Integer> carrito = new ArrayList<>();
          
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
    
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
    int cambio=1;
    
    public Tienda() {
        
    }
    
    
    
    public void inicializarInterfaz( ListaJugadores listJugadores) {      
        tiendaJugador(listJugadores.lista.get(jugadorActual),listJugadores);
        
    }
       
    public void tiendaJugador(Jugador jugador,ListaJugadores listJugadores){
        System.out.println("GLOBALES: "+Globales.jugadores_def);
        Globales.cambiarResolucion(Globales.alto_resolucion+cambio,Globales.ancho_resolucion+cambio);
        if(jugador.tipo.equals("bot")){
            comprarBot(listJugadores.lista.get(jugadorActual));           
            jugadorActual++;   
            if (jugadorActual == listJugadores.lista.size()) {  
                return;   
            }          
            tiendaJugador(listJugadores.lista.get(jugadorActual),listJugadores);
        }
        Pane panel=new Pane();
              
        Scene escena=new Scene(panel, Globales.alto_resolucion,Globales.ancho_resolucion);
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
        
        
        comprarBala60 = new Button("COMPRAR");
        comprarBala60.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #00FF00;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        comprarBala80 = new Button("COMPRAR");
        comprarBala80.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #0000FF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );       
        
        comprarBala105 = new Button("COMPRAR");
        comprarBala105.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        finalizarTienda = new Button("TERMINAR COMPRA");
        finalizarTienda.setLayoutX(400);
        finalizarTienda.setLayoutY(400);
        finalizarTienda.setStyle(
            "-fx-background-color: #000000; " +
            "-fx-text-fill: #FFFFFF;" +  
            "-fx-border-color: #FFFFFF;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;"  
        );
        
        revertirCompra = new Button("DEVOLVER COMPRA");       
        revertirCompra.setStyle(
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
            textSaldoJugador.setText("$"+jugador.saldo);
            jugador.setCantidad60(jugador.getCantidad60()+1);
            textBalas60.setText("60mm -- "+String.valueOf(jugador.getCantidad60()));
            ultimaOpcion=1;
            carrito.add(ultimaOpcion);
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
            carrito.add(ultimaOpcion);
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
            carrito.add(ultimaOpcion);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        });
        
        revertirCompra.setOnAction(event -> {  
            
            if (carrito.size()-1 >= 0) { // Verificar si la lista tiene al menos un elemento
                ultimaOpcion=carrito.get(carrito.size()-1);
            }
            if(carrito.size()-1<0){
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
            carrito.remove(carrito.size()-1);
            ultimaOpcion=0;
            textSaldoJugador.setText("$"+jugador.saldo);
            bloquearBoton(comprarBala60,comprarBala80,comprarBala105,jugador);
        });
        
        finalizarTienda.setOnAction(event -> {            
            jugadorActual++; 
            carrito.clear();
            System.out.println("JugadorActual= "+jugadorActual);
            System.out.println("Tamanio lista= "+listJugadores.lista.size());
            
            panel.widthProperty().removeListener(widthListener);
            panel.heightProperty().removeListener(heightListener);
            if (jugadorActual >= Globales.jugadores_def/*listJugadores.lista.size()/2*/) {
                Globales.rondas_def--;  
                if(Globales.rondas_def<=0){
                    Globales.stage.close();
                }
                if(cambio==1){
                    Globales.alto_resolucion--;
                }
                Jugar juego = new Jugar(listJugadores);//inicia el proceso de jugar
                juego.start();
                
            }
            
            if (jugadorActual < Globales.jugadores_def/*listJugadores.lista.size()/2*/) {
                panel.getChildren().clear();
                if(cambio==1){
                    cambio--;
                }
                else{
                 cambio++;   
                }
                
                tiendaJugador(listJugadores.lista.get(jugadorActual),listJugadores);          
            }
            
                   
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
            
            comprarBala80.setLayoutX(450*widthRatio);
            comprarBala80.setPrefWidth(120*widthRatio);
            
            comprarBala105.setLayoutX(600*widthRatio);
            comprarBala105.setPrefWidth(120*widthRatio);
            
            revertirCompra.setLayoutX(435*widthRatio);
            revertirCompra.setPrefWidth(150*widthRatio);
            
            finalizarTienda.setLayoutX(72*widthRatio);
            finalizarTienda.setPrefWidth(150*widthRatio);
            
           
            
        };

        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800;       
                    
            textNombreJugador.setLayoutY(320*heightRatio);
            textNombreJugador.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*heightRatio));
            
            imagentanque.setFitHeight(150*heightRatio);   
            imagentanque.setLayoutY(270*heightRatio);
            
            textSaldoJugador.setLayoutY(420*heightRatio);
            textSaldoJugador.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*heightRatio));
            
            textBalas60.setLayoutY(335*heightRatio);
            textBalas60.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*heightRatio));
            textBalas80.setLayoutY(335*heightRatio);
            textBalas80.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*heightRatio));
            textBalas105.setLayoutY(335*heightRatio);
            textBalas105.setFont(Font.font(textNombreJugador.getFont().getFamily(),FontWeight.BOLD, 20*heightRatio));
            
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
            
            
        };
        // Después de configurar los ChangeListeners, forzar una actualización inicial
        widthListener.changed(null, null, panel.getWidth());
        heightListener.changed(null, null, panel.getHeight()); 
        panel.widthProperty().addListener(widthListener);
        panel.heightProperty().addListener(heightListener);
        
        panel.getChildren().addAll(marco,comprarBala60,comprarBala80,comprarBala105,imagentanque,
          textSaldoJugador, textBalas60,textBalas80,textBalas105,finalizarTienda,revertirCompra,textNombreJugador);        
        Globales.cambiarEscena(escena);        
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
    
    /*   public void iniciarJuego(ListaJugadores listJugadores) {
    // Iniciar el juego con la lista de jugadores
    Jugar juego = new Jugar(listJugadores);
    juego.start(Globales.escena);

    // Después de iniciar el juego, limpiar y cerrar la escena actual de la tienda
    limpiarTienda();
    Globales.cerrarEscenaActual(); // Debes definir este método en tu clase Globales para cerrar la escena actual. 
    }
    */
}