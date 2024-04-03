package com.example._2130asn2_nguyen_simcoe_turan;

import java.time.LocalDate;

public class Adam_Trang_Onat_Reservation {
    private Adam_Trang_Onat_Flight flight;
    private Adam_Trang_Onat_Passenger passenger;
    private LocalDate dateOfTravel;
    private double totalFare;

    public Adam_Trang_Onat_Reservation(Adam_Trang_Onat_Flight flight, Adam_Trang_Onat_Passenger passenger, LocalDate dateOfTravel) {
        this.flight = flight;
        this.passenger = passenger;
        this.dateOfTravel = dateOfTravel;
        this.totalFare = this.calculateTotalFare();
    }

    public Adam_Trang_Onat_Flight getFlight() {
        return this.flight;
    }

    public Adam_Trang_Onat_Passenger getPassenger() {
        return this.passenger;
    }

    public LocalDate getDateOfTravel() {
        return this.dateOfTravel;
    }

    public double getTotalFare() {
        return this.totalFare;
    }

    public double calculateTotalFare() {
        return this.flight.getFlightFare() * 1.13;
    }
}
