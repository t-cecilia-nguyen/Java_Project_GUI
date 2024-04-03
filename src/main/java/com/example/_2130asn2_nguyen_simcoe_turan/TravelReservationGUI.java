// COMP2130 Assignment 2
// Simcoe, Adam - 101442161
// Nguyen, Trang - 100749684
// Turan, Onat - 101216227

package com.example._2130asn2_nguyen_simcoe_turan;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Iterator;

import static com.example._2130asn2_nguyen_simcoe_turan.Adam_Trang_Onat_TravelReservationSystem.*;

public class TravelReservationGUI extends Application {
    BorderPane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("AdTrOn Airways Travel Reservation System");
        final DecimalFormat df = new DecimalFormat("0.00");

        // Icon
        Image icon = new Image(getClass().getResourceAsStream("/plane.png"));
        stage.getIcons().add(icon);

        // Background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResourceAsStream("/background.jpg"), 700, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Create menu bar
        MenuBar menuBar = new MenuBar();

        // Menu
        Menu addMenu = new Menu("Add");
        Menu bookMenu = new Menu("Book");
        Menu displayMenu = new Menu("Display");
        Menu helpMenu = new Menu("Help");

        // ADD Items
        MenuItem addPassenger = new MenuItem("Add a Passenger...");
        MenuItem addFlight = new MenuItem("Add a Flight...");

        // Add a Passenger
        addPassenger.setOnAction(e -> {
            // Create text fields
            TextField firstNameField = new TextField();
            TextField lastNameField = new TextField();
            TextField ageField = new TextField();

            // Create labels for text fields
            Label firstNameLabel = new Label("First Name:");
            Label lastNameLabel = new Label("Last Name:");
            Label ageLabel = new Label("Age:");
            Label messageLabel = new Label();

            // Create button for submit
            Button submit = new Button("Submit");

            // Set action for submit
            submit.setOnAction(e1 -> {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int age = 0;

                // Check for null or empty values
                if (firstName.isEmpty() || lastName.isEmpty()) {
                    messageLabel.setText("Error: Please fill in all fields.");
                    return;
                }

                // Parse int for age
                try {
                    age = Integer.parseInt(ageField.getText());
                } catch (NumberFormatException e2) {
                    messageLabel.setText("Error: Invalid age input.\nPlease enter a valid number.");
                    return;
                }

                Adam_Trang_Onat_Passenger passenger = new Adam_Trang_Onat_Passenger(firstName, lastName, age);
                getPassengers().add(passenger);
                messageLabel.setText("Passenger created successfully!");

                // Clear the text fields
                firstNameField.clear();
                lastNameField.clear();
                ageField.clear();
            });

            // Set up the layout
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10));

            // Add labels and text fields to the grid
            grid.add(firstNameLabel, 0, 0);
            grid.add(firstNameField, 1, 0);
            grid.add(lastNameLabel, 0, 1);
            grid.add(lastNameField, 1, 1);
            grid.add(ageLabel, 0, 2);
            grid.add(ageField, 1, 2);
            grid.add(submit, 1, 3);
            grid.add(messageLabel, 1, 5);

            layout.setTop(menuBar);
            layout.setCenter(grid);
        });

        // Add a Flight
        addFlight.setOnAction(e -> {
            // Create text fields
            TextField flightNumberField = new TextField();
            TextField sourceField = new TextField();
            TextField destinationField = new TextField();
            TextField flightFareField = new TextField();

            // Create labels for text fields
            Label flightNumberLabel = new Label("Flight Number:");
            Label sourceLabel = new Label("Source:");
            Label destinationLabel = new Label("Destination:");
            Label flightFareLabel = new Label("Flight Fare:");
            Label messageLabel = new Label();

            // Create button for submit
            Button submit = new Button("Submit");

            // Set action for submit
            submit.setOnAction(e1 -> {
                String flightNumber = flightNumberField.getText();
                String source = sourceField.getText();
                String destination = destinationField.getText();
                double flightFare = 0;

                // Check for null or empty values
                if (flightNumber.isEmpty() || source.isEmpty() || destination.isEmpty()) {
                    messageLabel.setText("Error: Please fill in all fields.");
                    return;
                }

                // Parse double for fare
                try {
                    flightFare = Double.parseDouble(flightFareField.getText());
                } catch (NumberFormatException e2) {
                    messageLabel.setText("Error: Invalid input.\nPlease enter a valid number.");
                    return;
                }

                Adam_Trang_Onat_Flight flight = new Adam_Trang_Onat_Flight(flightNumber, source, destination, flightFare);
                getFlights().add(flight);
                messageLabel.setText("Flight created successfully!");

                // Clear the text fields
                flightNumberField.clear();
                sourceField.clear();
                destinationField.clear();
                flightFareField.clear();
            });

            // Set up the layout
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10));

            // Add labels and text fields to the grid
            grid.getChildren().clear();
            grid.add(flightNumberLabel, 0, 0);
            grid.add(flightNumberField, 1, 0);
            grid.add(sourceLabel, 0, 1);
            grid.add(sourceField, 1, 1);
            grid.add(destinationLabel, 0, 2);
            grid.add(destinationField, 1, 2);
            grid.add(flightFareLabel, 0, 3);
            grid.add(flightFareField, 1, 3);
            grid.add(submit, 1, 4);
            grid.add(messageLabel, 1, 6);

            layout.setTop(menuBar);
            layout.setCenter(grid);
        });

        // BOOK Items
        MenuItem bookFlight = new MenuItem("Book a Flight Reservation...");

        // Book a Flight Reservation
        bookFlight.setOnAction(e -> {
            // Create text fields
            TextField flightNumberField = new TextField();
            TextField firstNameField = new TextField();
            TextField lastNameField = new TextField();
            DatePicker dateOfTravelField = new DatePicker();

            // Create labels for text fields
            Label flightNumberLabel = new Label("Flight Number to Book:");
            Label firstNameLabel = new Label("First Name to Book:");
            Label lastNameLabel = new Label("Last Name to Book:");
            Label dateOfTravelLabel = new Label("Date Of Travel:");
            Label messageLabel = new Label();
            Label messageLabel2 = new Label();

            // Create button for submit
            Button submit = new Button("Submit");

            // Create ListView for Flights
            ListView<String> listViewFlights = new ListView<>();
            ObservableList<String> flightItems = FXCollections.observableArrayList();

            Iterator<Adam_Trang_Onat_Flight> iterator = getFlights().iterator();
            while (iterator.hasNext()) {
                Adam_Trang_Onat_Flight flight = iterator.next();
                String flightInfo = "Flight number: " + flight.getFlightNumber() +
                        ", Source: " + flight.getSource() +
                        ", Destination: " + flight.getDestination();
                flightItems.add(flightInfo);
            }
            listViewFlights.setItems(flightItems);

            // Create ListView for Passengers
            ListView<String> listViewPassengers = new ListView<>();
            ObservableList<String> passengerItems = FXCollections.observableArrayList();

            Iterator<Adam_Trang_Onat_Passenger> iterator2 = getPassengers().iterator();
            while (iterator2.hasNext()) {
                Adam_Trang_Onat_Passenger passenger = iterator2.next();
                String passengerInfo = "Passenger: " + passenger.getFirstName() + " " + passenger.getLastName() +
                        ", Age: " + passenger.getAge();
                passengerItems.add(passengerInfo);
            }
            listViewPassengers.setItems(passengerItems);

            // Set action for submit
            submit.setOnAction(e1 -> {
                String flightNumber = flightNumberField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                LocalDate selectedDate = dateOfTravelField.getValue();

                // Check for null or empty values
                if (flightNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                    messageLabel.setText("Error: Please fill in all fields.");
                    return;
                }

                // Validate selected date
                if (selectedDate == null) {
                    messageLabel.setText("Error: Please select a valid date.");
                    return;
                }
                LocalDate currentDate = LocalDate.now();
                if (selectedDate.isBefore(currentDate)) {
                    messageLabel.setText("Error: Please enter a future date.");
                    return;
                }

                // Find flight and passenger
                Adam_Trang_Onat_Flight selectedFlight = findFlight(flightNumber);
                Adam_Trang_Onat_Passenger selectedPassenger = findPassenger(firstName, lastName);
                if (selectedFlight != null && selectedPassenger != null) {
                    Adam_Trang_Onat_Reservation reservation = new Adam_Trang_Onat_Reservation(selectedFlight, selectedPassenger, selectedDate);
                    if (getReservations().add(reservation)) {
                        currentIndex = getReservations().size() - 1;

                        // Clear layout
                        layout.getChildren().clear();
                        layout.setTop(menuBar);

                        // Create new grid
                        GridPane newGrid = new GridPane();
                        newGrid.setHgap(10);
                        newGrid.setVgap(10);
                        newGrid.setPadding(new Insets(10));

                        // Create Listview for ticket display
                        ListView<String> listViewDisplayInfo = new ListView<>();
                        ObservableList<String> displayInfoItems = FXCollections.observableArrayList();

                        // Display ticket info
                        if (currentIndex >= 0 && currentIndex < getReservations().size()) {
                            reservation = getReservations().get(currentIndex);
                            String displayInfo = "AdTrOn Airways Ticket Receipt:"
                            + "\nFlight Number: " + reservation.getFlight().getFlightNumber()
                            + "\nSource: " + reservation.getFlight().getSource()
                            + "\nDestination: " + reservation.getFlight().getDestination()
                            + "\nDate of Travel: " + reservation.getDateOfTravel()
                            + "\nPassenger Name: " + reservation.getPassenger().getFirstName() + " " + reservation.getPassenger().getLastName()
                            + "\nPassenger Age: " + reservation.getPassenger().getAge()
                            + "\nTotal Fare (inc. taxes): $" + df.format(reservation.calculateTotalFare());
                            displayInfoItems.add(displayInfo);
                        } else {
                            messageLabel.setText("No reservations available to display.");
                        }
                        listViewDisplayInfo.setItems(displayInfoItems);

                        // Add the new ListView to the new grid
                        newGrid.add(messageLabel, 0,0);
                        newGrid.add(listViewDisplayInfo, 0, 2);
                        layout.setCenter(newGrid);
                        messageLabel.setText("Reservation has been booked successfully.\nThank you for choosing AdTrOn Airways.");
                    } else {
                        messageLabel.setText("Error: Failed to add reservation.\nPlease try again.");
                    }
                } else {
                    messageLabel.setText("Error: Selected flight or passenger not found.");
                    messageLabel2.setText("Please check your input.");
                }

                // Clear the text fields
                flightNumberField.clear();
                firstNameField.clear();
                lastNameField.clear();
                dateOfTravelField.setValue(null);
            });

            // Set up the layout
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10));

            // Add labels and text fields to the grid
            grid.add(flightNumberLabel, 0, 0);
            grid.add(flightNumberField, 1, 0);
            grid.add(firstNameLabel, 0, 1);
            grid.add(firstNameField, 1, 1);
            grid.add(lastNameLabel, 0, 2);
            grid.add(lastNameField, 1, 2);
            grid.add(dateOfTravelLabel,0,3);
            grid.add(dateOfTravelField,1,3);
            grid.add(submit, 1, 4);
            grid.add(messageLabel, 1, 5);
            grid.add(messageLabel2, 1, 6);
            grid.add(listViewFlights, 1,7);
            grid.add(listViewPassengers, 2,7);

            layout.setTop(menuBar);
            layout.setCenter(grid);
        });

        // DISPLAY Items
        MenuItem displayReservation = new MenuItem("Display Reservation Ticket...");

        // Display Reservation
        displayReservation.setOnAction(e -> {
            Label messageLabel= new Label();
            Button previousTicket = new Button("Previous Ticket");
            Button nextTicket = new Button("Next Ticket");

            // New grid
            GridPane newGrid = new GridPane();
            newGrid.setHgap(10);
            newGrid.setVgap(10);
            newGrid.setPadding(new Insets(10));

            // Create Listview for ticket display
            ListView<String> listViewDisplayInfo = new ListView<>();
            ObservableList<String> displayInfoItems = FXCollections.observableArrayList();

            int lastIndex = getReservations().size() - 1;

            // Display last ticket info
            if (lastIndex >= 0) {
                Adam_Trang_Onat_Reservation reservation = getReservations().get(lastIndex);
                String displayInfo = "AdTrOn Airways Ticket Receipt:"
                        + "\nFlight Number: " + reservation.getFlight().getFlightNumber()
                        + "\nSource: " + reservation.getFlight().getSource()
                        + "\nDestination: " + reservation.getFlight().getDestination()
                        + "\nDate of Travel: " + reservation.getDateOfTravel()
                        + "\nPassenger Name: " + reservation.getPassenger().getFirstName() + " " + reservation.getPassenger().getLastName()
                        + "\nPassenger Age: " + reservation.getPassenger().getAge()
                        + "\nTotal Fare (inc. taxes): $" + df.format(reservation.calculateTotalFare());
                displayInfoItems.add(displayInfo);
                messageLabel.setText("Displaying Ticket Information:");
            } else {
                messageLabel.setText("No reservations available to display.");
            }
            // Set the items to the new ListView
            listViewDisplayInfo.setItems(displayInfoItems);

            // Add the new ListView to the new grid
            newGrid.add(messageLabel, 0, 0);
            newGrid.add(listViewDisplayInfo, 0, 2);
            newGrid.add(previousTicket,0,3);
            newGrid.add(nextTicket, 1,3);
            layout.setCenter(newGrid);

            // Set action for Previous
            previousTicket.setOnAction(e2 -> {
                messageLabel.setText("Displaying for index: " + currentIndex);
                if (currentIndex > 0) {
                    currentIndex--;
                    if (currentIndex >= 0 && currentIndex < getReservations().size()) {
                        Adam_Trang_Onat_Reservation reservation = getReservations().get(currentIndex);
                        String displayInfo = "AdTrOn Airways Ticket Receipt:"
                                + "\nFlight Number: " + reservation.getFlight().getFlightNumber()
                                + "\nSource: " + reservation.getFlight().getSource()
                                + "\nDestination: " + reservation.getFlight().getDestination()
                                + "\nDate of Travel: " + reservation.getDateOfTravel()
                                + "\nPassenger Name: " + reservation.getPassenger().getFirstName() + " " + reservation.getPassenger().getLastName()
                                + "\nPassenger Age: " + reservation.getPassenger().getAge()
                                + "\nTotal Fare (inc. taxes): $" + df.format(reservation.calculateTotalFare());
                        displayInfoItems.clear();
                        displayInfoItems.add(displayInfo);
                        messageLabel.setText("Displaying Ticket Information:");
                    } else {
                        messageLabel.setText("No reservations available to display.");
                    }
                } else {
                    messageLabel.setText("No reservations available to display.");
                }
            });

            // Set action for Next
            nextTicket.setOnAction(e3 -> {
                messageLabel.setText("Displaying for index: " + currentIndex);
                if (currentIndex >= 0 && currentIndex < getReservations().size() - 1) {
                    currentIndex++;
                    if (currentIndex >= 0 && currentIndex < getReservations().size()) {
                        Adam_Trang_Onat_Reservation reservation = getReservations().get(currentIndex);
                        String displayInfo = "AdTrOn Airways Ticket Receipt:"
                                + "\nFlight Number: " + reservation.getFlight().getFlightNumber()
                                + "\nSource: " + reservation.getFlight().getSource()
                                + "\nDestination: " + reservation.getFlight().getDestination()
                                + "\nDate of Travel: " + reservation.getDateOfTravel()
                                + "\nPassenger Name: " + reservation.getPassenger().getFirstName() + " " + reservation.getPassenger().getLastName()
                                + "\nPassenger Age: " + reservation.getPassenger().getAge()
                                + "\nTotal Fare (inc. taxes): $" + df.format(reservation.calculateTotalFare());
                        displayInfoItems.clear();
                        displayInfoItems.add(displayInfo);
                        messageLabel.setText("Displaying Ticket Information:");
                    } else {
                        messageLabel.setText("No next reservations able to display");
                    }
                } else {
                    messageLabel.setText("No reservations available to display.");
                }
            });
            listViewDisplayInfo.setItems(displayInfoItems);
        });

        // HELP Items
        MenuItem showClock = new MenuItem("Show Time Zone Clock...");
        MenuItem showAllPassengers = new MenuItem("Show All Passengers");
        MenuItem showAllFlights = new MenuItem("Show All Flights");

        // Show Time Zone Clock
        showClock.setOnAction(e -> {
            MultiTimeClock clockApp = new MultiTimeClock();
            clockApp.start(new Stage());
        });

        // Show All Passengers
        showAllPassengers.setOnAction(e -> {
            // Create Listview for all passengers
            ListView<String> listViewAllPassengers = new ListView<>();
            ObservableList<String> allPassengersItems = FXCollections.observableArrayList();

            Iterator<Adam_Trang_Onat_Passenger> iterator = getPassengers().iterator();
            int index = 1;

            while (iterator.hasNext()) {
                Adam_Trang_Onat_Passenger passenger = iterator.next();
                StringBuilder passengerInfo = new StringBuilder();
                passengerInfo.append(index).append(". First Name: ").append(passenger.getFirstName())
                        .append("\n   Last Name: ").append(passenger.getLastName())
                        .append("\n   Age: ").append(passenger.getAge()).append("\n\n");
                allPassengersItems.add(passengerInfo.toString());
                index++;
            }
            listViewAllPassengers.setItems(allPassengersItems);

            GridPane newGrid = new GridPane();
            newGrid.setHgap(10);
            newGrid.setVgap(10);
            newGrid.setPadding(new Insets(10));
            newGrid.add(listViewAllPassengers, 0, 0);

            // Open in new window
            Stage newStage = new Stage();
            newStage.setScene(new Scene(newGrid));
            newStage.setTitle("All Passengers");
            // Icon
            newStage.getIcons().add(icon);
            newStage.show();
        });

        // Show All Flights
        showAllFlights.setOnAction(e -> {
            // Create Listview for all flights
            ListView<String> listViewAllFlights = new ListView<>();
            ObservableList<String> allFlightsItems = FXCollections.observableArrayList();

            Iterator<Adam_Trang_Onat_Flight> iterator = getFlights().iterator();
            int index = 1;
            while (iterator.hasNext()) {
                Adam_Trang_Onat_Flight flight = iterator.next();
                StringBuilder flightInfo = new StringBuilder();
                flightInfo.append(index).append(". Flight Number: ").append(flight.getFlightNumber())
                        .append("\n   Source: ").append(flight.getSource())
                        .append("\n   Destination: ").append(flight.getDestination())
                        .append("\n   Flight Fare: ").append(flight.getFlightFare()).append("\n\n");
                allFlightsItems.add(flightInfo.toString());
                index++;
            }
            listViewAllFlights.setItems(allFlightsItems);

            GridPane newGrid = new GridPane();
            newGrid.setHgap(10);
            newGrid.setVgap(10);
            newGrid.setPadding(new Insets(10));
            newGrid.add(listViewAllFlights, 0, 0);

            // Open in new window
            Stage newStage = new Stage();
            newStage.setScene(new Scene(newGrid));
            newStage.setTitle("All Flights");
            // Icon
            newStage.getIcons().add(icon);
            newStage.show();
        });

        // All Items
        addMenu.getItems().add(addPassenger);
        addMenu.getItems().add(addFlight);
        bookMenu.getItems().add(bookFlight);
        displayMenu.getItems().add(displayReservation);
        helpMenu.getItems().add(showClock);
        helpMenu.getItems().add(showAllPassengers);
        helpMenu.getItems().add(showAllFlights);

        // Main menu bar
        menuBar.getMenus().addAll(addMenu);
        menuBar.getMenus().addAll(bookMenu);
        menuBar.getMenus().addAll(displayMenu);
        menuBar.getMenus().addAll(helpMenu);

        layout = new BorderPane();
        layout.setBackground(new Background(backgroundImage));
        layout.setTop(menuBar);
        Scene scene = new Scene(layout, 700, 500);
        stage.setScene(scene);
        stage.show();
    }
}
