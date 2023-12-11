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
    private Pane panel= new Pane();//panel que guarda todo lo visual
    private Image imagenTabla = new Image(getClass().getResourceAsStream("./img/tabla.png"));//fondo
    private ImageView tabla = new ImageView(imagenTabla);//view de fondo
    private Button finalizar= new Button("CONTINUAR");//boton para salir de clasificacion
    private ArrayList <HBox> hboxJugadores=new ArrayList<>();//cajas de los jugadores que contienen sus estadisticas 
    private ArrayList<Integer> listAsesinatos=new ArrayList<>();//lista que guarda los asesinatos de cada jugador
    
    private Image tanque=null;//tanque ganador
    private Image titulo=new Image(getClass().getResourceAsStream("./img/ganador.gif"));//titulo de ganador ronda
    private ImageView victoria;//view que guarda el titulo ganador
    private ImageView ganador;//view que guarda al tanque ganador
    private Image draw=new Image(getClass().getResourceAsStream("./img/empate.gif"));//imagen de empate
    private ImageView empate=new ImageView(draw);//view de empate
    
    //metodo listener para los ajustes de resolucion
    private ChangeListener<Number> widthListener;
    private ChangeListener<Number> heightListener;
       
    //Metodo para iniciar la tabla de clasificacion y mostrar datos de la ronda cada jugador
    public void mostrarTabla(Tienda tienda, ListaJugadores listJugador){
        Musica.agregar_musica_clasificatoria();//musica
        Globales.escena.setRoot(panel);//la escena se cambia a esta
        tabla.setPreserveRatio(false);
        
        panel.getChildren().addAll(tabla,finalizar);//agregar nodos
        finalizar.setStyle(//estilo de boton 
            "-fx-background-color: #000000; " + 
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-border-color: #FF0000;" + 
            "-fx-border-width: 3px;" +  
            "-fx-background-radius: 0;" 
        );
        //se inicializan las cajas de estadisticas de los jugadores
        for(int i=0;i<Globales.jugadores_def;i++){
            HBox caja=new HBox(40);
            hboxJugadores.add(caja);
        }
        //se definen las estadisticas de cada jugador en la ronda
        for(Jugador jugador:listJugador.lista){
            Text asesinatos=new Text(jugador.asesinatos+"("+jugador.asesinatosTotales+")");          
            Text suicidios=new Text(jugador.suicidios+"("+jugador.suicidiosTotales+")");
            Text saldo;
            if(jugador.asesinatos-jugador.suicidios>=0){//diferencia de oro positiva
                saldo=new Text(jugador.saldo-10000+"(+"+((jugador.asesinatos-jugador.suicidios)*5000)+")");
            }
            else{//diferencia de oro negativa
                saldo=new Text(jugador.saldo-10000+"("+((jugador.asesinatos-jugador.suicidios)*5000)+")");
            }            
            hboxJugadores.get(jugador.jugador).getChildren().addAll(asesinatos,suicidios,saldo);
            panel.getChildren().add(hboxJugadores.get(jugador.jugador));//se agrega la box al panel
        }
    
        calcularGanador(listJugador.lista);//calcula el ganador con las estadisticas
        
        finalizar.setOnAction(event ->{//se desea continuar a la siguiente ronda
            for(Jugador jugador:listJugador.lista){//se reinician los valores para iniciar la siguiente ronda
                jugador.asesinatos=0;
                jugador.suicidios=0;
            }            
            if(Globales.rondas_def>0 && Globales.rondas_def!=1){//si aun quedan rondas        
                tienda.inicializarInterfaz(listJugador);
                System.out.println("Rondas="+Globales.rondas_def);
            }
            if(Globales.rondas_def==1){//si ya se acabaron las rondas
                Victoria victory=new Victoria();
                victory.mostrarGanador(listJugador);
            }                      
        });
        //metodo listener para ajustar nodos a la resolucion correspondiente (Ignorar, es puro "fxml")
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
            if(tanque!=null){//solo si hay un ganador aparece esto
                ganador.setLayoutX(520*widthRatio);            
                ganador.setFitWidth(200*widthRatio);            
                victoria.setLayoutX(490*widthRatio);            
                victoria.setFitWidth(260*widthRatio);
            }
            for(int i=0;i<Globales.jugadores_def;i++){
                hboxJugadores.get(i).setSpacing(45*widthRatio);//espacio entre datos de los jugadores
            }
            empate.setLayoutX(490*widthRatio);           
            empate.setFitWidth(270*widthRatio);                                
        };
        
        //metodo listener para ajustar nodos a la resolucion correspondiente (Ignorar, es puro "fxml")
        heightListener=(obs, oldHeight, newHeight) -> {
            double heightRatio = newHeight.doubleValue() / 800; 
            tabla.setFitHeight(800*heightRatio);
            finalizar.setLayoutY(460*heightRatio);
            finalizar.setPrefHeight(60*heightRatio);
            int movimiento=0;//variable usada para crear distancia hacia abajo en la tabla a medida que se definen las posiciones de los elementos
            for(int i=0;i<hboxJugadores.size();i++){
                hboxJugadores.get(i).setLayoutY((195+movimiento)*heightRatio);
                if(i>=2){//if creado por dimensiones de la imagen, que hacia que se viera imperfecto el movimiento hacia abajo
                    movimiento+=15;
                }
                movimiento+=90;
            }
            if(tanque!=null){//solo si hay un ganador aparece esto
                ganador.setLayoutY(250*heightRatio);
                ganador.setFitHeight(200*heightRatio);
                victoria.setLayoutY(150*heightRatio);
                victoria.setFitHeight(150*heightRatio);
            }          
            empate.setLayoutY(300*heightRatio);
            empate.setFitHeight(100*heightRatio);
        };
        
        //se "reactualiza" los valores del listener para que se realice el cambio de size en los nodos 
        widthListener.changed(null, null, panel.getWidth());
        heightListener.changed(null, null, panel.getHeight()); 
        panel.widthProperty().addListener(widthListener);
        panel.heightProperty().addListener(heightListener);      
    }
    
    //Metodo que calcula al ganador mediante una busqueda simple basandose en un mayor
    private void calcularGanador(ArrayList<Jugador> lista){
        int mayor=0;//valor mayor de asesinatos
        int indice=-1;//indice que guarda al jugador ganador, si no se encuentra un mayor se queda en -1 y simboliza un empate
        for(int i=0;i<lista.size();i++){//se busca primero el valor mayor y se guarda su indice
            if(mayor<lista.get(i).asesinatos){              
                mayor=lista.get(i).asesinatos;
                indice=i;
            }        
        }       
        for(int i=0;i<lista.size();i++){//busca si es que el valor mayor existe 2 veces
            if(i!=indice){//se descarta el indice mismo del mayor
                if(lista.get(i).asesinatos==mayor && indice!=-1){
                    indice=-1;
                    break;
                }
            }          
        }
        if(indice==-1){//si hubo un empate con almenos 2 jugadores          
            panel.getChildren().add(empate);
        }  
        else{//si hubo un ganador con mayor numero de asesinatos
            Image[] imagenesTanque=new Image[6];//lista de imagenes de tanques
            for(int i=0;i<imagenesTanque.length;i++) {//se inicializan las imagenes
                imagenesTanque[i]=new Image(getClass().getResourceAsStream("./img/tanque"+(i+1)+".png"));
            }
            tanque=imagenesTanque[indice];//se define el tanque ganaor
            victoria=new ImageView(titulo);//view de ganador
            ganador=new ImageView(tanque);//view de tanque ganador
            panel.getChildren().addAll(ganador,victoria);//se agrega al panel   
        }                     
    }
}
