package com.dmitryshibunia.model;

import java.util.Objects;

/**
 * Data object for team model and players quantity
 */
public class TeamDTO {

    private Integer id;
    private String name;
    private Integer playersQuantity;

    public TeamDTO(Integer id, String name, Integer playersQuantity) {
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
        TeamDTO teamDTO = (TeamDTO) o;
        return Objects.equals(id, teamDTO.id) &&
                Objects.equals(name, teamDTO.name) &&
                Objects.equals(playersQuantity, teamDTO.playersQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, playersQuantity);
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playersQuantity=" + playersQuantity +
                '}';
    }
}
