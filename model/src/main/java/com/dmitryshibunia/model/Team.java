package com.dmitryshibunia.model;

import java.util.Objects;

/**
 * Team model
 */
public class Team {

    private Integer id;
    private String name;
    private Integer playersQuantity;

    public Team(String name, Integer playersQuantity) {

        this.name = name;
        this.playersQuantity = playersQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlayersQuantity() {
        return playersQuantity;
    }

    public void setPlayersQuantity(Integer playersQuantity) {
        this.playersQuantity = playersQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(playersQuantity, team.playersQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, playersQuantity);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playersQuantity=" + playersQuantity +
                '}';
    }
}
