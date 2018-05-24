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

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.model.Graph;
import teamtwaalf.politiekebarometer.model.GraphType;

/**
 * Created by robbe on 15/05/2018.
 */

public class GraphAdapter extends ArrayAdapter<Graph> {
    public GraphAdapter(Context context, ArrayList<Graph> graphs) {
        super(context, -1, graphs);
        for (int i = 0; i < graphs.size(); i++) {
            System.out.println(graphs.get(i).toString());
        }
    }
    private final int COLORS[] = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK};
    // 0 = Line,
    // 1 = Pie
    // 2 = Bar
    // 3 = getal

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Graph graph = getItem(position);
        System.out.println("Position: " + position);
        System.out.println("========================== GRAPHADAPTER GET VIEW ==========================");
        System.out.println("======= DATA GRAFIEK " + graph.getId() + "=======");
        System.out.println(graph.toString());

        //if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (graph.getType() == 0) {
                convertView = inflater.inflate(R.layout.graph_adapter_linegraph, parent, false);
                drawLineGraph(convertView, graph);
                System.out.println("DRAW LINE : CONVERTVIEW NULL");
                return convertView;
            }
            if (graph.getType() == 1) {
                convertView = inflater.inflate(R.layout.graph_adapter_piechart, parent, false);
                drawPieChart(convertView, graph);
                System.out.println("DRAW PIE : CONVERTVIEW NULL");
                return convertView;
            }
            if (graph.getType() == 2) {
                convertView = inflater.inflate(R.layout.graph_adapter_barchart, parent, false);
                drawBarChart(convertView, graph);
                System.out.println("DRAW BAR : CONVERTVIEW NULL");
                return convertView;
            }
            if (graph.getType() == 3) {
                convertView = inflater.inflate(R.layout.graph_adapter_number, parent, false);
                drawNumber(convertView, graph);
                System.out.println("DRAW NUMBER : CONVERTVIEW NULL");
                return convertView;
            }
        return convertView;


     /* } else {
            //Bevat nog een fout
            if(graph.getType() == 0) {
               // View lineChart = convertView.findViewById(R.id.lineChart);
                //drawLineGraph(lineChart, graph);
                System.out.println("DRAW LINE : CONVERTVIEW NOT NULL");
                return convertView;
            }
            if(graph.getType() == 1){
               // View pieChart = convertView.findViewById(R.id.pieChart);
                //drawPieChart(pieChart, graph);
                System.out.println("DRAW PIE : CONVERTVIEW NOT NULL");
                return convertView;
            }
            if(graph.getType() == 2){
                //View barChart = convertView.findViewById(R.id.barChart);
                //drawBarChart(barChart, graph);
                System.out.println("DRAW BAR : CONVERTVIEW NOT NULL");
                return convertView;
            }
            if(graph.getType() == 3){
                //View number = convertView.findViewById(R.layout.graph_adapter_number);
                //drawNumber(number, graph);
                System.out.println("DRAW NUMBER : CONVERTVIEW NULL");
                return convertView;
            }
        }*/
    }

    private void drawLineGraph(View convertView, Graph graph){
        LineChart lineChart = convertView.findViewById(R.id.lineChart);
        TextView title = convertView.findViewById(R.id.titleLineChart);
        title.setText(graph.getTitle());
        LineData lineChartData = new LineData();
        LineDataSet dataSetSubject1 = null;
        LineDataSet dataSetSubject2 = null;
        LineDataSet dataSetSubject3 = null;
        LineDataSet dataSetSubject4 = null;
        LineDataSet dataSetSubject5 = null;
        for (int i = 0; i < 5; i++) {
            List<Entry> entries = new ArrayList<>();
            switch (i) {
                case 0:
                    if(!graph.getGraphDataFirstSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataFirstSubject().size(); j++) {
                            entries.add(new Entry(j, Integer.parseInt(graph.getGraphDataFirstSubject().get(j))));
                        }
                        dataSetSubject1 = new LineDataSet(entries, graph.getSubject());
                        dataSetSubject1.setColor(COLORS[i]);
                        dataSetSubject1.setCircleColor(COLORS[i]);
                    }
                    lineChartData.addDataSet(dataSetSubject1);
                    break;
                case 1:
                    if(!graph.getGraphDataSecondSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataSecondSubject().size(); j++) {
                            entries.add(new Entry(j, Integer.parseInt(graph.getGraphDataSecondSubject().get(j))));
                        }
                        dataSetSubject2 = new LineDataSet(entries, graph.getSecondSubject());
                        dataSetSubject2.setColor(COLORS[i]);
                        dataSetSubject2.setCircleColor(COLORS[i]);
                    }
                    lineChartData.addDataSet(dataSetSubject2);
                    break;
                case 2:
                    if(!graph.getGraphDataThirdSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataThirdSubject().size(); j++) {
                            entries.add(new Entry(j, Integer.parseInt(graph.getGraphDataThirdSubject().get(j))));
                        }
                        dataSetSubject3 = new LineDataSet(entries, graph.getThirdSubject());
                        dataSetSubject3.setColor(COLORS[i]);
                        dataSetSubject3.setCircleColor(COLORS[i]);
                    }
                    lineChartData.addDataSet(dataSetSubject3);
                    break;
                case 3:
                    if(!graph.getGraphDataFourthSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataFourthSubject().size(); j++) {
                            entries.add(new Entry(j, Integer.parseInt(graph.getGraphDataFourthSubject().get(j))));
                        }
                        dataSetSubject4 = new LineDataSet(entries, graph.getFourthSubject());
                        dataSetSubject4.setColor(COLORS[i]);
                        dataSetSubject4.setCircleColor(COLORS[i]);
                    }
                    lineChartData.addDataSet(dataSetSubject4);
                    break;
                case 4:
                    if(!graph.getGraphDataFifthSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataFifthSubject().size(); j++) {
                            entries.add(new Entry(j, Integer.parseInt(graph.getGraphDataFifthSubject().get(j))));
                        }
                        dataSetSubject5 = new LineDataSet(entries, graph.getFifthSubject());
                        dataSetSubject5.setColor(COLORS[i]);
                        dataSetSubject5.setCircleColor(COLORS[i]);
                    }
                    lineChartData.addDataSet(dataSetSubject5);
                    break;
            }
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
        BarData barData = new BarData();
        BarDataSet datasetSubject1 = null;
        BarDataSet datasetSubject2 = null;
        BarDataSet datasetSubject3 = null;
        BarDataSet datasetSubject4 = null;
        BarDataSet datasetSubject5 = null;

        for (int i = 0; i < 5; i++) {
            List<BarEntry> entries = new ArrayList<>();
            switch (i){
                case 0:
                    if(!graph.getGraphDataFirstSubject().isEmpty()){
                    for (int j = 0; j < graph.getGraphDataFirstSubject().size(); j++) {
                        entries.add(new BarEntry(j, Integer.parseInt(graph.getGraphDataFirstSubject().get(j))));
                    }
                        datasetSubject1 = new BarDataSet(entries, graph.getSubject());
                        datasetSubject1.setColor(COLORS[i]);
                    }
                    barData.addDataSet(datasetSubject1);
                    break;
                case 1:
                    if(!graph.getGraphDataSecondSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataSecondSubject().size(); j++) {
                            entries.add(new BarEntry(j, Integer.parseInt(graph.getGraphDataSecondSubject().get(j))));
                        }
                        datasetSubject2 = new BarDataSet(entries, graph.getSecondSubject());
                        datasetSubject2.setColor(COLORS[i]);
                    }
                    barData.addDataSet(datasetSubject2);
                    break;
                case 2:
                    if(!graph.getGraphDataThirdSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataThirdSubject().size(); j++) {
                            entries.add(new BarEntry(j, Integer.parseInt(graph.getGraphDataThirdSubject().get(j))));
                        }
                        datasetSubject3 = new BarDataSet(entries, graph.getThirdSubject());
                        datasetSubject3.setColor(COLORS[i]);
                    }
                    barData.addDataSet(datasetSubject3);
                    break;
                case 3:
                    if(!graph.getGraphDataFourthSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataFourthSubject().size(); j++) {
                            entries.add(new BarEntry(j, Integer.parseInt(graph.getGraphDataFourthSubject().get(j))));
                        }
                        datasetSubject4 = new BarDataSet(entries, graph.getFourthSubject());
                        datasetSubject4.setColor(COLORS[i]);
                    }
                    barData.addDataSet(datasetSubject4);
                    break;
                case 4:
                    if(!graph.getGraphDataFifthSubject().isEmpty()){
                        for (int j = 0; j < graph.getGraphDataFifthSubject().size(); j++) {
                            entries.add(new BarEntry(j, Integer.parseInt(graph.getGraphDataFifthSubject().get(j))));
                        }
                        datasetSubject5 = new BarDataSet(entries, graph.getFifthSubject());
                        datasetSubject5.setColor(COLORS[i]);
                    }
                    barData.addDataSet(datasetSubject5);
                    break;
            }

        }



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
        barData.setBarWidth(0.1f); // set the width of each bar
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setAxisMinimum(0);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setData(barData);
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

