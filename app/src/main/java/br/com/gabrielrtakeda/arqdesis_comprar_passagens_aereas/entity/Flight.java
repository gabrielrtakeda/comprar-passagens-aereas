package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity;

import java.io.Serializable;

/**
 * Created by gtakeda on 10/9/15.
 */
public class Flight implements Comparable<Flight>, Serializable {
    public static final String UNKNOWN = "Não encontrado";

    private String name;
    private String carrier;
    private String origin;
    private String arrival;
    private String image;
    private String description;

    public Flight(
            String name,
            String carrier,
            String origin,
            String arrival,
            String image,
            String description
    ) {
        this.setName(name)
                .setCarrier(carrier)
                .setOrigin(origin)
                .setArrival(arrival)
                .setImage(image)
                .setDescription(description);
    }

    public String getName() {
        return name;
    }

    public Flight setName(String name) {
        this.name = name;
        return this;
    }

    public String getCarrier() {
        return carrier;
    }

    public Flight setCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public Flight setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getArrival() {
        return arrival;
    }

    public Flight setArrival(String arrival) {
        this.arrival = arrival;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Flight setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Flight setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "name='" + name + '\'' +
                ", carrier='" + carrier + '\'' +
                ", origin='" + origin + '\'' +
                ", arrival='" + arrival + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(Flight flight) {
        if (this.getName().equals(flight.getName())
                && getCarrier().equals(flight.getCarrier())
                && getOrigin().equals(flight.getOrigin())
                && getImage().equals(flight.getImage())
                && getDescription().equals(flight.getDescription())
                && getArrival().equals(flight.getArrival())) {
            return 0;
        }
        return this.getName().compareTo(flight.getName());
    }
}
