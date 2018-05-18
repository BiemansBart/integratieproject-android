package teamtwaalf.politiekebarometer.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by robbe on 15/05/2018.
 */

public class Graph {
    @SerializedName("GraphId")
    private int Id;
    private int Type;
    private List<String> Labels ;
    @SerializedName("GraphData")
    private List<String> GraphDataFirstSubject;
    private List<String> GraphDataSecondSubject;
    private List<String> GraphDataThirdSubject;
    private List<String> GraphDataFourthSubject;
    private List<String> GraphDataFifthSubject;
    private String Title;
    private String Subject;
    private String SecondSubject;
    private String ThirdSubject;
    private String FourthSubject;
    private String FifthSubject;


    //Constructor
    public Graph(int id, int type, List<String> labels, List<String> graphDataFirstSubject, List<String> graphDataSecondSubject, List<String> graphDataThirdSubject, List<String> graphDataFourthSubject, List<String> graphDataFifthSubject, String title, String subject, String secondSubject, String thirdSubject, String fourthSubject, String fifthSubject) {
        Id = id;
        Type = type;
        Labels = labels;
        GraphDataFirstSubject = graphDataFirstSubject;
        GraphDataSecondSubject = graphDataSecondSubject;
        GraphDataThirdSubject = graphDataThirdSubject;
        GraphDataFourthSubject = graphDataFourthSubject;
        GraphDataFifthSubject = graphDataFifthSubject;
        Title = title;
        Subject = subject;
        SecondSubject = secondSubject;
        ThirdSubject = thirdSubject;
        FourthSubject = fourthSubject;
        FifthSubject = fifthSubject;

    }


    //GETTERS & SETTERS

    public void setId(int id) {
        Id = id;
    }
    public void setType(int type) {
        Type = type;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }
    public void setSecondSubject(String secondSubject) {
        SecondSubject = secondSubject;
    }
    public void setThirdSubject(String thirdSubject) {
        ThirdSubject = thirdSubject;
    }
    public void setFourthSubject(String fourthSubject) {
        FourthSubject = fourthSubject;
    }
    public void setFifthSubject(String fifthSubject) {
        FifthSubject = fifthSubject;
    }
    public void setLabels(List<String> labels) {
        Labels = labels;
    }
    public void setGraphDataFirstSubject(List<String> graphDataFirstSubject) {
        GraphDataFirstSubject = graphDataFirstSubject;
    }
    public void setGraphDataSecondSubject(List<String> graphDataSecondSubject) {
        GraphDataSecondSubject = graphDataSecondSubject;
    }
    public void setGraphDataThirdSubject(List<String> graphDataThirdSubject) {
        GraphDataThirdSubject = GraphDataThirdSubject;
    }
    public void setGraphDataFourthSubject(List<String> graphDataFourthSubject) {
        GraphDataFourthSubject = graphDataFourthSubject;
    }
    public void setGraphDataFifthSubject(List<String> graphDataFifthSubject) {
        GraphDataFifthSubject = graphDataFifthSubject;
    }

    public int getId() {
        return Id;
    }

    public int getType() {
        return Type;
    }

    public List<String> getLabels() {
        return Labels;
    }

    public List<String> getGraphDataFirstSubject() {
        return GraphDataFirstSubject;
    }

    public List<String> getGraphDataSecondSubject() {
        return GraphDataSecondSubject;
    }

    public List<String> getGraphDataThirdSubject() {
        return GraphDataThirdSubject;
    }

    public List<String> getGraphDataFourthSubject() {
        return GraphDataFourthSubject;
    }

    public List<String> getGraphDataFifthSubject() {
        return GraphDataFifthSubject;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubject() {
        return Subject;
    }

    public String getSecondSubject() {
        return SecondSubject;
    }

    public String getThirdSubject() {
        return ThirdSubject;
    }

    public String getFourthSubject() {
        return FourthSubject;
    }

    public String getFifthSubject() {
        return FifthSubject;
    }

    @Override
    public String toString() {


        return "Graph{" +
                "Id=" + Id +
                ", Type=" + Type +
                ", Labels=" + Labels +
                ", GraphDataFirstSubject=" + GraphDataFirstSubject +
                ", GraphDataSecondSubject=" + GraphDataSecondSubject +
                ", GraphDataThirdSubject=" + GraphDataThirdSubject +
                ", GraphDataFourthSubject=" + GraphDataFourthSubject +
                ", GraphDataFifthSubject=" + GraphDataFifthSubject +
                ", Title='" + Title + '\'' +
                ", Subject='" + Subject + '\'' +
                ", SecondSubject='" + SecondSubject + '\'' +
                ", ThirdSubject='" + ThirdSubject + '\'' +
                ", FourthSubject='" + FourthSubject + '\'' +
                ", FifthSubject='" + FifthSubject + '\'' +
                '}';

    }
}
