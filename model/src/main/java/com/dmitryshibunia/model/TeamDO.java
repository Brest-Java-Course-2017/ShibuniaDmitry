package com.dmitryshibunia.model;

import java.util.Objects;

/**
 * Data object for team model and players quantity
 */
public class TeamDO {

    private Integer id;
    private String name;
    private Integer playersQuantity;

    public TeamDO(Integer id, String name, Integer playersQuantity) {
        this.id = id;
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
        TeamDO teamDO = (TeamDO) o;
        return Objects.equals(id, teamDO.id) &&
                Objects.equals(name, teamDO.name) &&
                Objects.equals(playersQuantity, teamDO.playersQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, playersQuantity);
    }

    @Override
    public String toString() {
        return "TeamDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playersQuantity=" + playersQuantity +
                '}';
    }
}
