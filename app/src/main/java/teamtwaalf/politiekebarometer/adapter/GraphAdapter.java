package teamtwaalf.politiekebarometer.adapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;

import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.model.Graph;
import teamtwaalf.politiekebarometer.model.GraphType;

/**
 * Created by robbe on 15/05/2018.
 */

public class GraphAdapter extends ArrayAdapter<Graph> {
    public GraphAdapter(Context context, ArrayList<Graph> graphs) {
        super(context, -1, graphs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Graph graph = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.graph_adapter_layout, parent, false);
            GraphView graphView = convertView.findViewById(R.id.graphAdapter);
            for (int i = 0; i < 5; i++) {
                graphView.addSeries(CreateGraphSeries(graph, i));
            }
           graphView.setTitle(graph.getTitle());

        }else{

            GraphView graphView = convertView.findViewById(R.id.graphAdapter);
            graphView.removeAllSeries();
            for (int i = 0; i < 5; i++) {
                graphView.addSeries(CreateGraphSeries(graph, i));
            }
            graphView.setTitle(graph.getTitle());
        }
        System.out.println(graph.toString());

        return convertView;
    }

    private Series CreateGraphSeries(Graph graph, int dataSetCount){
        DataPoint[] dp = new DataPoint[graph.getLabels().size()];
        for (int i = 0; i < graph.getLabels().size(); i++) {
            switch (dataSetCount){
                case 0:
                    dp[i] = new DataPoint(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFirstSubject().get(i)));
                    break;
                case 1:
                    dp[i] = new DataPoint(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataSecondSubject().get(i)));
                    break;
                case 2:
                    dp[i] = new DataPoint(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataThirdSubject().get(i)));
                    break;
                case 3:
                    dp[i] = new DataPoint(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFourthSubject().get(i)));
                    break;
                case 4:
                    dp[i] = new DataPoint(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFifthSubject().get(i)));
                    break;
            }
        }

        if(graph.getType() == GraphType.line){
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
            series.setColor(setSeriesColor(dataSetCount));
            return series;
        }
        if(graph.getType() == GraphType.bar){
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dp);
            series.setColor(setSeriesColor(dataSetCount));
            return series;
        }
        return null;
    }

    private int setSeriesColor(int dataSetCount){
        int c = Color.BLACK;
        switch (dataSetCount){
            case 0:
               c = Color.BLUE; break;
            case 1:
                c = Color.RED; break;
            case 2:
                c = Color.MAGENTA; break;
            case 3:
                c = Color.GREEN; break;
            case 4:
                c = Color.YELLOW; break;
        }
        return c;
    }
}
