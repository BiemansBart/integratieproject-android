package teamtwaalf.politiekebarometer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

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

            if(graph.getType() == GraphType.line) {
                convertView = inflater.inflate(R.layout.graph_adapter_linegraph, parent, false);
                drawLineGraph(convertView, graph);
                System.out.println("DRAW LINE : CONVERTVIEW NULL");
            }
            if(graph.getType() == GraphType.bar){
                convertView = inflater.inflate(R.layout.graph_adapter_barchart, parent, false);
                drawBarChart(convertView, graph);
                System.out.println("DRAW BAR : CONVERTVIEW NULL");
            }
            if(graph.getType() == GraphType.pie){
                convertView = inflater.inflate(R.layout.graph_adapter_piechart, parent, false);
                drawPieChart(convertView, graph);
                System.out.println("DRAW PIE : CONVERTVIEW NULL");
            }

        } else {
            //Bevat nog een fout
            /*if(graph.getType() == GraphType.pie){
                View pieChart =  convertView.findViewById(R.id.pieChart);
                drawPieChart(pieChart, graph);
                System.out.println("DRAW PIE : CONVERTVIEW NOT NULL");
            }
            if(graph.getType() == GraphType.line) {
                View lineChart = convertView.findViewById(R.id.lineChart);
                drawLineGraph(lineChart, graph);
                System.out.println("DRAW LINE : CONVERTVIEW NOT NULL");

            }
            if(graph.getType() == GraphType.bar){
                View barChart = convertView.findViewById(R.id.barChart);
                drawBarChart(barChart, graph);
                System.out.println("DRAW BAR : CONVERTVIEW NOT NULL");
            }*/
        }
        return convertView;
    }


    private List<Entry> createEntries(Graph graph, int dataCounter) {
        List<Entry> entries = new ArrayList<Entry>();

        switch (dataCounter) {
            case 0:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    entries.add(new Entry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFirstSubject().get(i))));
                }
                break;
            case 1:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    entries.add(new Entry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataSecondSubject().get(i))));
                }
                break;
            case 2:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    entries.add(new Entry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataThirdSubject().get(i))));
                }
                break;
            case 3:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    entries.add(new Entry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFourthSubject().get(i))));
                }
                break;
            case 4:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    entries.add(new Entry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFifthSubject().get(i))));
                }
                break;
        }
        return entries;
    }

    private void drawLineGraph(View convertView, Graph graph){
        LineChart lineChart = convertView.findViewById(R.id.lineChart);
        LineData lineChartData = new LineData();
        LineDataSet dataSet = null;
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    dataSet = new LineDataSet(createEntries(graph, 0), graph.getSubject());
                    dataSet.setColor(Color.RED);
                    dataSet.setCircleColor(Color.RED);
                    break;
                case 1:
                    dataSet = new LineDataSet(createEntries(graph, 1), graph.getSecondSubject());
                    dataSet.setColor(Color.GREEN);
                    dataSet.setCircleColor(Color.GREEN);
                    break;
                case 2:
                    dataSet = new LineDataSet(createEntries(graph, 2), graph.getThirdSubject());
                    dataSet.setColor(Color.BLUE);
                    dataSet.setCircleColor(Color.BLUE);
                    break;
                case 3:
                    dataSet = new LineDataSet(createEntries(graph, 3), graph.getFourthSubject());
                    dataSet.setColor(Color.YELLOW);
                    dataSet.setCircleColor(Color.YELLOW);
                    break;
                case 4:
                    dataSet = new LineDataSet(createEntries(graph, 4), graph.getFifthSubject());
                    dataSet.setColor(Color.BLACK);
                    dataSet.setCircleColor(Color.BLACK);
                    break;
            }
            lineChartData.addDataSet(dataSet);

        }
        lineChart.setData(lineChartData);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setMinimumHeight(400);
        lineChart.invalidate();
    }

    private void drawBarChart(View convertView, Graph graph){
        BarChart barChart = convertView.findViewById(R.id.barChart);
        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        List<BarEntry> entriesGroup3 = new ArrayList<>();
        List<BarEntry> entriesGroup4 = new ArrayList<>();
        List<BarEntry> entriesGroup5 = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            entriesGroup1.add(new BarEntry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFirstSubject().get(i))));
            entriesGroup2.add(new BarEntry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataSecondSubject().get(i))));
            entriesGroup3.add(new BarEntry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataThirdSubject().get(i))));
            entriesGroup4.add(new BarEntry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFourthSubject().get(i))));
            entriesGroup5.add(new BarEntry(Integer.parseInt(graph.getLabels().get(i)), Integer.parseInt(graph.getGraphDataFifthSubject().get(i))));
        }

        BarDataSet set1 = new BarDataSet(entriesGroup1, graph.getSubject());
        BarDataSet set2 = new BarDataSet(entriesGroup2, graph.getSecondSubject());
        BarDataSet set3 = new BarDataSet(entriesGroup3, graph.getThirdSubject());
        BarDataSet set4 = new BarDataSet(entriesGroup4, graph.getFourthSubject());
        BarDataSet set5 = new BarDataSet(entriesGroup5, graph.getFifthSubject());
        set1.setColor(Color.RED);
        set2.setColor(Color.GREEN);
        set3.setColor(Color.BLUE);
        set4.setColor(Color.YELLOW);
        set5.setColor(Color.BLACK);

        BarData data = new BarData();
        data.addDataSet(set1);
        data.addDataSet(set2);
        data.addDataSet(set3);
        data.addDataSet(set4);
        data.addDataSet(set5);
        data.setBarWidth(0.2f); // set the width of each bar
        XAxis xAxis = barChart.getXAxis();
        xAxis.setCenterAxisLabels(true);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisRight().setEnabled(false);

        barChart.setMinimumHeight(400);
        barChart.setData(data);
        barChart.groupBars(0, 0.2f, 0.02f);
        barChart.invalidate();
    }

    private void drawPieChart(View convertView, Graph graph) {
        List<PieEntry> entries = new ArrayList<>();
        PieChart pieChart = convertView.findViewById(R.id.pieChart);

        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.setMinimumHeight(400);
        pieChart.invalidate();
    }


}

