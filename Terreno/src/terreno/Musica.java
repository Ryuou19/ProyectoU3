package terreno;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musica {
     // se crea un objeto MediaPlayer para poder manipularlo
     private static MediaPlayer player;
     
    public Musica() {
    }
    //sonido de los clicks
    public static void sonido_click() {
        reproducirSonido("src/terreno/music/click.mp3", 1.0);
    }
    //sonido de las compras
    public static void sonido_compra() {
        reproducirSonido("src/terreno/music/dinero.mp3", 0.7);
    }
    //musica de la pantalla inicial
    public static void agregar_musica() {
        reproducirMusica("src/terreno/music/musica.mp3", 0.5);
    }
    //musica de cuando empieza el juego
    public static void agregar_musica_terreno() {
        reproducirMusica("src/terreno/music/music_terreno.mp3", 0.2);
    }
    //sonido de cuando se dispara la bala
    public static void sonido_disparo() {
        reproducirSonido("src/terreno/music/disparo.mp3", 0.5);
    }
    

    //sonido al impactar la bala
    public static void sonido_colision() {
        reproducirSonido("src/terreno/music/colision.mp3", 0.5);
    }
    // musica de tienda
    public static void agregar_musica_tienda() {
        reproducirMusica("src/terreno/music/tienda.mp3", 0.1);
    }
    //musica cuando se gana
     public static void agregar_musica_win() {
        reproducirMusica("src/terreno/music/win.mp3", 1.0);
    }
    //musica de cuando se empata 
     public static void agregar_musica_empate() {
        reproducirMusica("src/terreno/music/empate.mp3", 0.9);
    }
     //musica de tabla de clasificatoria
       public static void agregar_musica_clasificatoria() {
        reproducirMusica("src/terreno/music/clasificatoria.mp3", 0.5);
    }
     //Se detiene la musica
    public static void detenerMusica() {
        if (player != null) {
            player.stop();
        }
    }

   
    //Reproduce un sonido solo una vez
    private static void reproducirSonido(String path, double volume) {
        File archivo = new File(path);
        Media media = new Media(archivo.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }
    //Reproduce la musica
    private static void reproducirMusica(String path, double volume) {
        detenerMusica(); //Detiene la musica antes de reproducir una nueva

        File archivo = new File(path);
        Media media = new Media(archivo.toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(volume);
        player.setCycleCount(MediaPlayer.INDEFINITE); //se reproduce en loop
        player.play(); 
    }
}



