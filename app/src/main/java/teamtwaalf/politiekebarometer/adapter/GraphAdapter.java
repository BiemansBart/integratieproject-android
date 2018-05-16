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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.model.Graph;

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

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(Integer.parseInt(graph.getLabels().get(0)), Integer.parseInt(graph.getGraphDataFirstSubject().get(0))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(1)), Integer.parseInt(graph.getGraphDataFirstSubject().get(1))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(2)), Integer.parseInt(graph.getGraphDataFirstSubject().get(2))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(3)), Integer.parseInt(graph.getGraphDataFirstSubject().get(3))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(4)), Integer.parseInt(graph.getGraphDataFirstSubject().get(4))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(5)), Integer.parseInt(graph.getGraphDataFirstSubject().get(5))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(6)), Integer.parseInt(graph.getGraphDataFirstSubject().get(6))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(7)), Integer.parseInt(graph.getGraphDataFirstSubject().get(7))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(8)), Integer.parseInt(graph.getGraphDataFirstSubject().get(8))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(9)), Integer.parseInt(graph.getGraphDataFirstSubject().get(9)))
            });
            series.setColor(Color.BLUE);
            graphView.addSeries(series);
            graphView.setTitle(graph.getTitle());
        }else{

            GraphView graphView = convertView.findViewById(R.id.graphAdapter);

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(Integer.parseInt(graph.getLabels().get(0)), Integer.parseInt(graph.getGraphDataFirstSubject().get(0))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(1)), Integer.parseInt(graph.getGraphDataFirstSubject().get(1))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(2)), Integer.parseInt(graph.getGraphDataFirstSubject().get(2))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(3)), Integer.parseInt(graph.getGraphDataFirstSubject().get(3))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(4)), Integer.parseInt(graph.getGraphDataFirstSubject().get(4))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(5)), Integer.parseInt(graph.getGraphDataFirstSubject().get(5))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(6)), Integer.parseInt(graph.getGraphDataFirstSubject().get(6))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(7)), Integer.parseInt(graph.getGraphDataFirstSubject().get(7))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(8)), Integer.parseInt(graph.getGraphDataFirstSubject().get(8))),
                    new DataPoint(Integer.parseInt(graph.getLabels().get(9)), Integer.parseInt(graph.getGraphDataFirstSubject().get(9)))
            });
            series.setColor(Color.RED);
            graphView.addSeries(series);
            graphView.setTitle(graph.getTitle());
        }



        return convertView;
    }
}
