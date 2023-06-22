package com.example.melodyhub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.example.melodyhub.homepage_artist_podcaster_controller.*;
import static com.example.melodyhub.LoginSignupPage.*;

public class history_page_controller implements Initializable {

    private static boolean type;
    @FXML
    private ImageView banner;

    @FXML
    private ImageView home_button;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane play_bar;

    @FXML
    private VBox recommended;

    @FXML
    private VBox side_bar;

    @FXML
    private BorderPane songsPane;

    @FXML
    private Label song_name_label;

    @FXML
    private Slider play_progress_bar;

    @FXML
    private ListView<Button> historyList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        if (songs == null) {
            songs = new ArrayList<File>();

            directory = new File("src/main/resources/com/example/melodyhub/musics");

            files = directory.listFiles();

            if (files != null) {

                for (File file : files) {

                    songs.add(file);
                }
            }

            //        media = new Media(songs.get(songNumber).toURI().toString());
            //        mediaPlayer = new MediaPlayer(media);

            media = homepage_artist_podcaster_controller.media;
            mediaPlayer = homepage_artist_podcaster_controller.mediaPlayer;

            song_name_label.setText(songs.get(songNumber).getName());
            song_name_label.setWrapText(true);

        } else {
            song_name_label.setText(songs.get(songNumber).getName());
            song_name_label.setWrapText(true);

//            // updating the current playing time
            play_progress_bar.setValue(current_play_time);
            continueTimer();


        }
        sendMessage("see history");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","search");
        sendMessage(jsonObject.toString());
        try {
            ArrayList<String> list = objectMapper.readValue(getMessage(),new TypeReference<ArrayList<String>>() {});
            for(String str :list)
            {
                Button button = new Button();
                button.setText(str);
                button.setTextFill(Color.CORAL);
                button.setOnMouseClicked(event ->{
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(button.getText());
                    clipboard.setContent(content);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("copied to clipboard");
                    alert.showAndWait();
                });
                historyList.getItems().add(button);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


    // media playing functions ------------------------------------------------------

    public void playMedia() {

        beginTimer();
        mediaPlayer.play();
    }

    public void pauseMedia() {

        cancelTimer();
        mediaPlayer.pause();
    }

    public void resetMedia() {

        play_progress_bar.setValue(0);
        mediaPlayer.seek(Duration.seconds(0));
    }

    public void previousMedia() {

        if(songNumber > 0) {

            songNumber--;

            mediaPlayer.stop();

            if(running) {

                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            song_name_label.setText(songs.get(songNumber).getName());

            playMedia();
        }
        else {

            songNumber = songs.size() - 1;

            mediaPlayer.stop();

            if(running) {

                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            song_name_label.setText(songs.get(songNumber).getName());

            playMedia();
        }
    }

    public void nextMedia() {

        if(songNumber < songs.size() - 1) {

            songNumber++;

            mediaPlayer.stop();

            if(running) {

                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            song_name_label.setText(songs.get(songNumber).getName());

            playMedia();
        }
        else {

            songNumber = 0;

            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            song_name_label.setText(songs.get(songNumber).getName());

            playMedia();
        }
    }

    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                running = true;
                current_play_time = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();

                play_progress_bar.setMin(0);  // Minimum value
                play_progress_bar.setMax(end);  // Maximum value, where `totalDuration` is the duration of the song in seconds


                play_progress_bar.setValue(current_play_time);

                if(current_play_time == end) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    // for continuing the timer and setting the progress bar
    public void continueTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                running = true;
                current_play_time = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();

                play_progress_bar.setMin(0);  // Minimum value
                play_progress_bar.setMax(end);  // Maximum value, where `totalDuration` is the duration of the song in seconds


                play_progress_bar.setValue(current_play_time);

                if(current_play_time == end) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {

        running = false;
        timer.cancel();
    }

    public void set_play_time(){

        double playbackPosition = play_progress_bar.getValue();
        mediaPlayer.seek(Duration.seconds(playbackPosition));
        playMedia();

    }




    @FXML
    void open_home(MouseEvent event) throws IOException {
        FXMLLoader loader=null;
        if(type==false)
        // Load the FXML file for the new page
        {
            loader = new FXMLLoader(getClass().getResource("HomePage_artist&podcater.fxml"));
        }else
            loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();

        // Create a new Scene based on the loaded FXML file
        Scene newScene = new Scene(root);

        // Get the current Stage from any component in the existing scene
        Stage currentStage = (Stage) home_button.getScene().getWindow();

        // Set the new Scene on the Stage
        currentStage.setScene(newScene);

    }

}
