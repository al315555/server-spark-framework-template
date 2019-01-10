package persistence.model;

import java.util.List;

public class ApplicationMapperList implements ApplicationMapper{

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
        StringBuilder stringBuilder = new StringBuilder("{\n");
        for ( ApplicationMapper element : applicationMapperList){
            stringBuilder.append(element.getJSON());
            stringBuilder.append(",\n");
        }
        stringBuilder.delete(stringBuilder.length()-2,stringBuilder.length());
        stringBuilder.append("\n};");
        return stringBuilder.toString();
    }

}
