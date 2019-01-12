package persistence.model;

import com.google.gson.Gson;

public abstract class ApplicationMapper {
    public String getJSON(){return new Gson().toJson(this);}
}
