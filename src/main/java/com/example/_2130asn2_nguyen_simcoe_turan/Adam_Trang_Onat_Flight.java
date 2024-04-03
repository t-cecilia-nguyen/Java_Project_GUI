package com.example._2130asn2_nguyen_simcoe_turan;

public class Adam_Trang_Onat_Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private double flightFare;

    public Adam_Trang_Onat_Flight(String flightNumber, String source, String destination, double flightFare) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.flightFare = flightFare;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public double getFlightFare() {
        return this.flightFare;
    }
}

