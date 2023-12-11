package terreno;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musica {
    
     private static MediaPlayer player;
     
    public Musica() {
    }

    public static void sonido_click() {
        reproducirSonido("src/terreno/music/click.mp3", 1.0);
    }

    public static void sonido_compra() {
        reproducirSonido("src/terreno/music/dinero.mp3", 0.7);
    }

    public static void agregar_musica() {
        reproducirMusica("src/terreno/music/musica.mp3", 0.5);
    }

    public static void agregar_musica_terreno() {
        reproducirMusica("src/terreno/music/music_terreno.mp3", 0.2);
    }

    public static void sonido_disparo() {
        reproducirSonido("src/terreno/music/disparo.mp3", 0.5);
    }
    


    public static void sonido_colision() {
        reproducirSonido("src/terreno/music/colision.mp3", 0.5);
    }

    public static void agregar_musica_tienda() {
        reproducirMusica("src/terreno/music/tienda.mp3", 0.1);
    }
    
        public static void agregar_musica_win() {
        reproducirMusica("src/terreno/music/win.mp3", 1.0);
    }
       public static void agregar_musica_empate() {
        reproducirMusica("src/terreno/music/empate.mp3", 0.9);
    }
       public static void agregar_musica_clasificatoria() {
        reproducirMusica("src/terreno/music/clasificatoria.mp3", 0.5);
    }
    public static void detenerMusica() {
        if (player != null) {
            player.stop();
        }
    }

   

    private static void reproducirSonido(String path, double volume) {
        File archivo = new File(path);
        Media media = new Media(archivo.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    private static void reproducirMusica(String path, double volume) {
        detenerMusica();

        File archivo = new File(path);
        Media media = new Media(archivo.toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(volume);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }
}

