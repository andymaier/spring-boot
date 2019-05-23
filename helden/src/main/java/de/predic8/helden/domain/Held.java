package de.predic8.helden.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Held {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;

  private double gewicht;

  @ManyToMany
  private Set<Faehigkeit> faehikeiten;

  public Held() {
  }

  public Held(String name, double gewicht, Faehigkeit... faehikeiten) {
    this.name = name;
    this.gewicht = gewicht;
    this.faehikeiten = new HashSet<>(Arrays.asList(faehikeiten));
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

  public Set<Faehigkeit> getFaehikeiten() {
    return faehikeiten;
  }

  public void setFaehikeiten(Set<Faehigkeit> faehikeiten) {
    this.faehikeiten = faehikeiten;
  }
}
