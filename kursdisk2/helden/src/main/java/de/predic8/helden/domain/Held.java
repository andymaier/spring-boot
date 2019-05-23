package de.predic8.helden.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Held {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private double gewicht;

    @ManyToMany
    private Set<Faehigkeit> faehigkeiten;

    public Held() {
    }

    public Held(String name, double gewicht, Faehigkeit... faehigkeiten) {
        this.name = name;
        this.gewicht = gewicht;
        this.faehigkeiten = new HashSet<>(Arrays.asList(faehigkeiten));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public Set<Faehigkeit> getFaehigkeiten() {
        return faehigkeiten;
    }

    public void setFaehigkeiten(Set<Faehigkeit> faehigkeiten) {
        this.faehigkeiten = faehigkeiten;
    }
}
