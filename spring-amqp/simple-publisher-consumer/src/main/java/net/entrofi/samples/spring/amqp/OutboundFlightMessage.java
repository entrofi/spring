package net.entrofi.samples.spring.amqp;


import java.util.Date;

public class OutboundFlightMessage {

    private final String flightNumber;

    private final Date date;

    private final String origin;

    private final String destination;

    private final int capacity;

    public OutboundFlightMessage(String flightNumber, Date date, int capacity, String origin, String destination) {
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getDate() {
        return date;
    }


    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public String toString() {
        return flightNumber + "-" + origin + "-" + destination
                +  "-" + capacity +  "-" + date;
    }
}
