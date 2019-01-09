package model.usuario;

import persistence.model.ApplicationMapper;

public class ApplicationUsuario implements ApplicationMapper {
    private long id, birthdate, last_login;
    private String name;

    public long getId() {
        return id;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }

    public long getLast_login() {
        return last_login;
    }

    public void setLast_login(long last_login) {
        this.last_login = last_login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getJSON() {
        return String.format("{\n\"id\":%d,\n\"name\":\"%s\"\n}", this.id, this.name);
    }
}
