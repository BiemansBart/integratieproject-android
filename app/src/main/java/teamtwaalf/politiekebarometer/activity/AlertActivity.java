package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.RestClient;
import teamtwaalf.politiekebarometer.adapter.AlertAdapter;
import teamtwaalf.politiekebarometer.model.AlertMessage;

public class AlertActivity extends Activity {
    @BindView(R.id.LvActivity)
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);
        RestClient restClient = new RestClient(this);
        restClient.getUserAlerts(getIntent().getStringExtra("userId"));

    }
    public void showUserAlerts(List<AlertMessage> alerts){
        AlertAdapter adapter = new AlertAdapter(this,alerts);
        lv.setAdapter(adapter);
    }
}
