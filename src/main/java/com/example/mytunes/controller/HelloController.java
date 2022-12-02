package com.example.mytunes.controller;

import com.example.mytunes.model.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.stage.Stage;

public class HelloController
{


    @FXML
    private ListView PLTracklist = new ListView();

    @FXML
    private ListView playlist = new ListView();

    @FXML
    private ListView songlist = new ListView();

    @FXML
    private Label antalSange, kunsterNavn, playlistNavn, sangTitel, timePlayed, totalTime;

    @FXML
    private Button playKnap, sound;

    @FXML
    private ImageView songImage;

    @FXML
    private TextField søgFelt;

    public void initialize(){
        List<Songs> sange = bdi.getAlleSange();
        for (Songs songs : sange){
            songlist.getItems().add(songs);
        }
        List<Playlist> playlist1 = pdi.getAllPlaylist();
        for (Playlist playlists : playlist1){
            playlist.getItems().add(playlists);
        }
    }

    public int sanglisteLængde(){

        return songlist.getItems().size();
    }

    @FXML
    void fjernPLKnap(ActionEvent event)
    {
        Dialog<ButtonType> dialog = new Dialog();

        // Her sættes vinduet op
        dialog.setTitle("Fjern playliste");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label infoLabel = new Label("Er du sikker på du vil fortsætte?");

        dialog.getDialogPane().setContent(infoLabel);

        // Her afsluttes dialogen med at man kan trykke på OK
        Optional<ButtonType> knap = dialog.showAndWait();
        // Derefter kan vi henter felternes indhold ud og gøre hvad der skal gøres...
        if (knap.get() == ButtonType.OK)
            try{
                List valgteIndeks = playlist.getSelectionModel().getSelectedIndices();
                        for (Object indeks : valgteIndeks)
                            playlist.getItems().remove((int) indeks);
            } catch (Exception e){
                System.err.println("Noget gik galt, tjek om der er insat et valid ordrenr");
                System.err.println("Fejl: " + e.getMessage());
            }

    }

    @FXML
    void opretPLKnap(ActionEvent event) throws IOException
    {
        Dialog<ButtonType> pldialog = new Dialog();

        // Her sættes vinduet op
        pldialog.setTitle("Create Playlist");
        pldialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField plNavn = new TextField();
        plNavn.setPromptText("Navngiv playliste...");
        VBox opsæt = new VBox(plNavn);
        pldialog.getDialogPane().setContent(opsæt);

        // Her afsluttes dialogen med at man kan trykke på OK
        Optional<ButtonType> knap = pldialog.showAndWait();
        // Derefter kan vi henter felternes indhold ud og gøre hvad der skal gøres...
        if (knap.get() == ButtonType.OK)
            try{
                Playlist pl = new Playlist(plNavn.getText());
                playlist.getItems().add(pl);
                playlist.scrollTo(playlist.getItems().size()-1);
                pdi.opretPlaylist(pl);

            } catch (Exception e){
                System.err.println("Fejl: " + e.getMessage());
            }
    }

    @FXML
    void playKnap(ActionEvent event)
    {

    }

    @FXML
    void previousKnap(ActionEvent event)
    {
    }

    @FXML
    void skipKnap(ActionEvent event)
    {
    }

    @FXML
    void sletSangKnap(ActionEvent event)
    {
        Dialog<ButtonType> dialog = new Dialog();

        // Her sættes vinduet op
        dialog.setTitle("Slet sang");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label infoLabel = new Label("Er du sikker på du vil fortsætte?");

        dialog.getDialogPane().setContent(infoLabel);

        // Her afsluttes dialogen med at man kan trykke på OK
        Optional<ButtonType> knap = dialog.showAndWait();
        // Derefter kan vi henter felternes indhold ud og gøre hvad der skal gøres...
        if (knap.get() == ButtonType.OK)
            try{
                List valgteIndeks = songlist.getSelectionModel().getSelectedIndices();
                                for (Object indeks : valgteIndeks)
                                    songlist.getItems().remove((int) indeks);
            } catch (Exception e){
                System.err.println("Noget gik galt, tjek om der er insat et valid ordrenr");
                System.err.println("Fejl: " + e.getMessage());
            }

    }

    @FXML
    void soundKnap(ActionEvent event)
    {
    }

    /*@FXML
    void søgKnap(ActionEvent event)
    {
    //Bliver brugt som protected void nedenfor for nu
    }*/

    @FXML
    void tilføjSangKnap(ActionEvent event) throws IOException
    {
        Dialog<ButtonType> sangdialog = new Dialog();

        // Her sættes vinduet op
        sangdialog.setTitle("New/Edit Song");
        sangdialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        //HBox m titel
        Label titleLabel = new Label("Title:");
        TextField titleText = new TextField();
        titleText.setPromptText("Title...");
        HBox titleH = new HBox(titleLabel, titleText);
        titleH.setSpacing(8);

        //HBox m Kunstner
        Label artistLabel = new Label("Artist:");
        TextField artistText = new TextField();
        artistText.setPromptText("Artist...");
        HBox artistH = new HBox(artistLabel, artistText);
        artistH.setSpacing(8);

        //HBox m Kategori
        Label categoryLabel = new Label("Category");
        TextField categoryText = new TextField();
        categoryText.setPromptText("Genre...");
        HBox categoryH = new HBox(categoryLabel, categoryText);
        categoryH.setSpacing(8);

        //HBox m årstal
        Label yearLabel = new Label("Year:");
        TextField yearText = new TextField();
        yearText.setPromptText("Year...");
        HBox yearH = new HBox(yearLabel, yearText);
        yearH.setSpacing(8);

        //HBox m varrighed
        Label durationLabel = new Label("Time:");
        TextField durationText = new TextField();
        durationText.setPromptText("Time...");
        HBox durationH = new HBox(durationLabel, durationText);
        durationH.setSpacing(8);

        //HBox m fil
        Label fileLabel = new Label("File:");
        TextField fileText = new TextField();
        fileText.setPromptText("File.mp3...");

        Button fileButton = new Button("Choose...");
        fileButton.setId("filKnap");
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter ("Mp3 files", "mp3"));

        fileButton.setOnAction(e -> {// Måske løst, mangler event til knappen?
            try {

                Runtime.getRuntime().exec("explorer C:\\");


                /*VBox minvbox = new VBox();

                                       Stage stage = (Stage) minvbox.getScene().getWindow();
                                       File file = fileChooser.showOpenDialog(stage);
                                       System.out.println("Valgt filnavn: " + file.getName());*/

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
            //fileText.setText(file.getPath());


        HBox fileH = new HBox(fileLabel, fileText, fileButton);
        fileH.setSpacing(8);

        //VBox med opsæt
        VBox opsæt = new VBox(titleH, artistH, categoryH, yearH, durationH, fileH);
        opsæt.setSpacing(16);
        sangdialog.getDialogPane().setContent(opsæt);

        // Her afsluttes dialogen med at man kan trykke på OK
        Optional<ButtonType> knap = sangdialog.showAndWait();
        // Derefter kan vi henter felternes indhold ud og gøre hvad der skal gøres...
        if (knap.get() == ButtonType.OK)
            try{
                Songs s = new Songs(titleText.getText(), artistText.getText(), categoryText.getText(),Integer.parseInt(yearText.getText()),Float.parseFloat(durationText.getText()));
                songlist.getItems().add(s);
                songlist.scrollTo(songlist.getItems().size()-1);
                bdi.tilføjSang(s);

                // Sangene har et songID i databasen. Skal vi tælle op med songList length + 1
                // for at gøre det automatisk? Eller anden løsning?


            } catch (Exception e){
                System.err.println("Fejl: " + e.getMessage());
            }
    }

    @FXML
    protected void søgKnap(ActionEvent e)
    {
        List<Songs> sange = bdi.getSøgSange(søgFelt.getText());
        songlist.getItems().clear();
        for(Songs song: sange)
        {
            //songlist.appendText(song.getTitle() + ", "+ song.getArtist() + ", "+ song.getCategory() + "\n");
            //songlist.add(Songs.toString());
        }
    }

    SongDAO bdi = new SongDAOImpl();
    PlaylistDAO pdi = new PlaylistDAOImpl();

}