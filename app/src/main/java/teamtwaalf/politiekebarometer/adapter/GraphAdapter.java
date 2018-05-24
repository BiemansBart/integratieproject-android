package teamtwaalf.politiekebarometer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

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
    private final int COLORS[] = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK};
    // 0 = Line,
    // 1 = Pie
    // 2 = Bar
    // 3 = getal

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Graph graph = getItem(position);
        System.out.println("========================== GRAPHADAPTER GET VIEW ==========================");
        System.out.println("======= DATA GRAFIEK " + graph.getId() + "=======");
        System.out.println(graph.toString());

       // if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(graph.getType() == 0) {
                convertView = inflater.inflate(R.layout.graph_adapter_linegraph, parent, false);
                drawLineGraph(convertView, graph);
                System.out.println("DRAW LINE : CONVERTVIEW NULL");
                return convertView;
            }
            if(graph.getType() == 1){
                convertView = inflater.inflate(R.layout.graph_adapter_piechart, parent, false);
                drawPieChart(convertView, graph);
                System.out.println("DRAW PIE : CONVERTVIEW NULL");
                return convertView;
            }
            if(graph.getType() == 2){
                convertView = inflater.inflate(R.layout.graph_adapter_barchart, parent, false);
                drawBarChart(convertView, graph);
                System.out.println("DRAW BAR : CONVERTVIEW NULL");
                return convertView;
            }
        if(graph.getType() == 3){
            convertView = inflater.inflate(R.layout.graph_adapter_number, parent, false);
            drawNumber(convertView, graph);
            System.out.println("DRAW NUMBER : CONVERTVIEW NULL");
            return convertView;
        }


      /* } else {
            //Bevat nog een fout
            if(graph.getType() == 0) {
                View lineChart = convertView.findViewById(R.id.lineChart);
                drawLineGraph(lineChart, graph);
                System.out.println("DRAW LINE : CONVERTVIEW NOT NULL");
                return convertView;
            }
            if(graph.getType() == 1){
                View pieChart = convertView.findViewById(R.id.pieChart);
                drawPieChart(pieChart, graph);
                System.out.println("DRAW PIE : CONVERTVIEW NOT NULL");
                return convertView;
            }
            if(graph.getType() == 2){
                View barChart = convertView.findViewById(R.id.barChart);
                drawBarChart(barChart, graph);
                System.out.println("DRAW BAR : CONVERTVIEW NOT NULL");
                return convertView;
            }
        }*/
        return convertView;
    }


    private List<Entry> createEntries(Graph graph, int dataCounter) {
        List<Entry> entries = new ArrayList<Entry>();

        switch (dataCounter) {
            case 0:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    entries.add(new Entry(i, Integer.parseInt(graph.getGraphDataFirstSubject().get(i))));
                }
                break;
            case 1:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    if(!graph.getGraphDataSecondSubject().isEmpty()){
                        entries.add(new Entry(i, Integer.parseInt(graph.getGraphDataSecondSubject().get(i))));
                    }else{
                        entries.add(new Entry(-1, -1));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    if(!graph.getGraphDataThirdSubject().isEmpty()){
                        entries.add(new Entry(i, Integer.parseInt(graph.getGraphDataThirdSubject().get(i))));
                    }else{
                        entries.add(new Entry(-1, -1));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    if(!graph.getGraphDataFourthSubject().isEmpty()){
                        entries.add(new Entry(i, Integer.parseInt(graph.getGraphDataFourthSubject().get(i))));
                    }else{
                        entries.add(new Entry(-1, -1));
                    }
                }
                break;
            case 4:
                for (int i = 0; i < graph.getLabels().size(); i++) {
                    if(!graph.getGraphDataFifthSubject().isEmpty()){
                        entries.add(new Entry(i, Integer.parseInt(graph.getGraphDataFifthSubject().get(i))));
                    }else{
                        entries.add(new Entry(-1, -1));
                    }
                }
                break;
        }
        return entries;
    }

    private void drawLineGraph(View convertView, Graph graph){
        LineChart lineChart = convertView.findViewById(R.id.lineChart);
        TextView title = convertView.findViewById(R.id.titleLineChart);
        title.setText(graph.getTitle());
        LineData lineChartData = new LineData();
        LineDataSet dataSet = null;
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    dataSet = new LineDataSet(createEntries(graph, 0), graph.getSubject());
                    dataSet.setColor(COLORS[i]);
                    dataSet.setCircleColor(COLORS[i]);
                    break;
                case 1:
                    dataSet = new LineDataSet(createEntries(graph, 1), graph.getSecondSubject());
                    dataSet.setColor(COLORS[i]);
                    dataSet.setCircleColor(COLORS[i]);
                    break;
                case 2:
                    dataSet = new LineDataSet(createEntries(graph, 2), graph.getThirdSubject());
                    dataSet.setColor(COLORS[i]);
                    dataSet.setCircleColor(COLORS[i]);
                    break;
                case 3:
                    dataSet = new LineDataSet(createEntries(graph, 3), graph.getFourthSubject());
                    dataSet.setColor(COLORS[i]);
                    dataSet.setCircleColor(COLORS[i]);
                    break;
                case 4:
                    dataSet = new LineDataSet(createEntries(graph, 4), graph.getFifthSubject());
                    dataSet.setColor(COLORS[i]);
                    dataSet.setCircleColor(COLORS[i]);
                    break;
            }
                lineChartData.addDataSet(dataSet);
        }

        lineChart.setData(lineChartData);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisLeft().setAxisMinimum(0);
        lineChart.getXAxis().setAxisMinimum(0);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setMinimumHeight(400);
        lineChart.invalidate();
    }

    private void drawBarChart(View convertView, Graph graph){
        BarChart barChart = convertView.findViewById(R.id.barChart);
        TextView title = convertView.findViewById(R.id.titleBarChart);
        title.setText(graph.getTitle());
        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        List<BarEntry> entriesGroup3 = new ArrayList<>();
        List<BarEntry> entriesGroup4 = new ArrayList<>();
        List<BarEntry> entriesGroup5 = new ArrayList<>();

        for(int i = 0; i < graph.getLabels().size(); i++) {
            entriesGroup1.add(new BarEntry(i,Integer.parseInt(graph.getGraphDataFirstSubject().get(i))));
            if(!graph.getGraphDataSecondSubject().isEmpty()){
                entriesGroup2.add(new BarEntry(i,Integer.parseInt(graph.getGraphDataSecondSubject().get(i))));
            }else{
                entriesGroup2.add(new BarEntry(i,null));
            }
            if(!graph.getGraphDataThirdSubject().isEmpty()){
                entriesGroup3.add(new BarEntry(i,Integer.parseInt(graph.getGraphDataThirdSubject().get(i))));
            }else{
                entriesGroup3.add(new BarEntry(i,null));
            }
            if(!graph.getGraphDataFourthSubject().isEmpty()){
                entriesGroup4.add(new BarEntry(i,Integer.parseInt(graph.getGraphDataFourthSubject().get(i))));
            }else{
                entriesGroup4.add(new BarEntry(i,null));
            }
            if(!graph.getGraphDataFifthSubject().isEmpty()){
                entriesGroup5.add(new BarEntry(i,Integer.parseInt(graph.getGraphDataFifthSubject().get(i))));
            }else{
                entriesGroup5.add(new BarEntry(i,null));
            }
        }

        BarDataSet set1 = new BarDataSet(entriesGroup1, graph.getSubject());
        BarDataSet set2 = new BarDataSet(entriesGroup2, graph.getSecondSubject());
        BarDataSet set3 = new BarDataSet(entriesGroup3, graph.getThirdSubject());
        BarDataSet set4 = new BarDataSet(entriesGroup4, graph.getFourthSubject());
        BarDataSet set5 = new BarDataSet(entriesGroup5, graph.getFifthSubject());
        set1.setColor(COLORS[0]);
        set2.setColor(COLORS[1]);
        set3.setColor(COLORS[2]);
        set4.setColor(COLORS[3]);
        set5.setColor(COLORS[4]);

        BarData data = new BarData();
        data.addDataSet(set1);
        data.addDataSet(set2);
        data.addDataSet(set3);
        data.addDataSet(set4);
        data.addDataSet(set5);

        XAxis xAxis = barChart.getXAxis();
        ArrayList<String> xLabels = new ArrayList<>(graph.getLabels());
        System.out.println(xLabels.size());
        xAxis.setLabelCount(xLabels.size());
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value >= 0) {
                    if (xLabels.size() > (int) value) {
                        return xLabels.get((int)value);
                    } else return "";
                } else {
                    return "";
                }
            }
        });
        data.setBarWidth(0.1f); // set the width of each bar
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setAxisMinimum(0);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setData(data);
        barChart.groupBars(0, 0.4f, 0.02f);
        barChart.invalidate();
        LegendEntry[] legendEntries = barChart.getLegend().getEntries();
        for (LegendEntry l:legendEntries) {
            System.out.println("LABEL = " + l.label);
        }
    }

    private void drawPieChart(View convertView, Graph graph) {
        List<PieEntry> entries = new ArrayList<>();
        PieChart pieChart = convertView.findViewById(R.id.pieChart);
        TextView title = convertView.findViewById(R.id.titlePie);
        title.setText(graph.getTitle());
        List<Integer> values = new ArrayList<>();
        for (String s:graph.getGraphDataFirstSubject()) {
            values.add(Integer.parseInt(s));
        }

        for (int i = 0; i < graph.getLabels().size(); i++) {
            entries.add(new PieEntry(values.get(i), graph.getLabels().get(i)));
        }

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(COLORS);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.setMinimumHeight(400);
        pieChart.invalidate();
    }

    private void drawNumber(View convertView, Graph graph){
        TextView tvTitle = convertView.findViewById(R.id.titleNumber);
        TextView tvStart = convertView.findViewById(R.id.numberStart);
        TextView tvEnd = convertView.findViewById(R.id.numberEnd);
        TextView tvCount = convertView.findViewById(R.id.numberCount);

        tvTitle.setText(graph.getTitle());
        tvStart.setText(graph.getLabels().get(0));
        tvEnd.setText(graph.getLabels().get(graph.getLabels().size()-1));

        int count = 0;
        for (int i = 0; i < graph.getGraphDataFirstSubject().size(); i++) {
            count = count + Integer.parseInt(graph.getGraphDataFirstSubject().get(i));
        }
        System.out.println("Count number: " + count);
        tvCount.setText(String.valueOf(count));
    }

}

