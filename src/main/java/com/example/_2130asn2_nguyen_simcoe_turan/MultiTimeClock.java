package com.example._2130asn2_nguyen_simcoe_turan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MultiTimeClock {

    private static final String[] TIME_ZONES = {"UTC", "Europe/Moscow", "America/New_York", "Europe/London", "Asia/Tokyo", "Asia/Istanbul", "Australia/Sydney", "America/Toronto"};

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Time Zone Clock");

        // Icon
        javafx.scene.image.Image icon = new Image(getClass().getResourceAsStream("/plane.png"));
        primaryStage.getIcons().add(icon);

        // Background image
//        BackgroundSize backgroundSize = new BackgroundSize(640, 380, true, true, true, false);
//        BackgroundImage background = new BackgroundImage(new Image("file:src/main/resources/world-map.jpg"),
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);


        BackgroundSize backgroundSize = new BackgroundSize(640, 380, true, true, true, false);
        BackgroundImage background = new BackgroundImage(new Image(getClass().getResourceAsStream("/world-map.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background backgroundObject = new Background(background);

        StackPane root = new StackPane();
        root.setPadding(new Insets(10));
        root.setBackground(backgroundObject);

        // Display the time
        Text timeText = new Text();
        timeText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        root.getChildren().add(timeText);

        // Update time when ComboBox value changes
        ComboBox<String> timeZoneList = new ComboBox<>();
        timeZoneList.getItems().addAll(TIME_ZONES);
        timeZoneList.setValue(TIME_ZONES[0]);

        timeZoneList.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplayedTime(newValue, timeText);
        });

        StackPane.setAlignment(timeZoneList, javafx.geometry.Pos.TOP_LEFT);
        root.getChildren().add(timeZoneList);
        Scene scene = new Scene(root, 640, 380);
        primaryStage.setScene(scene);

        // Update time every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String selectedTimeZone = timeZoneList.getValue();
            updateDisplayedTime(selectedTimeZone, timeText);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        primaryStage.show();
    }

    private void updateDisplayedTime(String timeZone, Text timeText) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String time = timeFormat.format(new Date());
        timeText.setText(timeZone + ": " + time);
    }
}
