package com.example._2130asn2_nguyen_simcoe_turan;

import java.util.ArrayList;
import java.util.Iterator;

public class Adam_Trang_Onat_TravelReservationSystem {
    private static ArrayList<Adam_Trang_Onat_Passenger> passengers = new ArrayList();
    private static ArrayList<Adam_Trang_Onat_Flight> flights = new ArrayList();
    private static ArrayList<Adam_Trang_Onat_Reservation> reservations = new ArrayList();
    public static int currentIndex = -1;
    public static ArrayList<Adam_Trang_Onat_Passenger> getPassengers() {
        return passengers;
    }
    public static ArrayList<Adam_Trang_Onat_Flight> getFlights() {
        return flights;
    }

    public static ArrayList<Adam_Trang_Onat_Reservation> getReservations() {
        return reservations;
    }

    public static Adam_Trang_Onat_Passenger findPassenger(String firstName, String lastName) {
        Iterator var4 = passengers.iterator();
        Adam_Trang_Onat_Passenger passenger;
        do {
            if (!var4.hasNext()) {
                return null;
            }
            passenger = (Adam_Trang_Onat_Passenger)var4.next();
        } while(!passenger.getFirstName().equals(firstName) || !passenger.getLastName().equals(lastName));

        return passenger;
    }

    public static Adam_Trang_Onat_Flight findFlight(String flightNumber) {
        Iterator var3 = flights.iterator();
        while(var3.hasNext()) {
            Adam_Trang_Onat_Flight flight = (Adam_Trang_Onat_Flight)var3.next();
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}
