package com.dmitryshibunia.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Player model
 */
public class Player {

    private Integer id;
    private Integer teamId;
    private String name;
    private String surname;
    private LocalDate acceptanceDate;

    public Player(Integer teamId, String name, String surname, LocalDate acceptanceDate) {
        this.teamId = teamId;
        this.name = name;
        this.surname = surname;
        this.acceptanceDate = acceptanceDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname;
    }

    public LocalDate getAcceptanceDate() {

        return acceptanceDate;
    }

    public void setAcceptanceDate(LocalDate acceptanceDate) {

        this.acceptanceDate = acceptanceDate;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", acceptanceDate=" + acceptanceDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name) &&
                Objects.equals(surname, player.surname) &&
                Objects.equals(acceptanceDate, player.acceptanceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, acceptanceDate);
    }
}
