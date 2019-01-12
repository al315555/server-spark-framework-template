package persistence.model;

import com.google.gson.Gson;

import java.util.List;

public class ApplicationMapperList extends ApplicationMapper{

    private List<ApplicationMapper> applicationMapperList = null;

    public List<ApplicationMapper> getApplicationMapperList() {
        return applicationMapperList;
    }

    public void setApplicationMapperList(List<ApplicationMapper> applicationMapperList) {
        this.applicationMapperList = applicationMapperList;
    }

    public ApplicationMapperList(List<ApplicationMapper> list){
        this.applicationMapperList = list;
    }

    @Override
    public String getJSON() {
        return "{"+new Gson().toJson(applicationMapperList)+"}";
    }

}
