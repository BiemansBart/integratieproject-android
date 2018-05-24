package teamtwaalf.politiekebarometer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.model.AlertMessage;

/**
 * Created by biema on 24/05/2018.
 */

public class AlertAdapter extends ArrayAdapter<AlertMessage> {

    public AlertAdapter(Context context, List<AlertMessage> alertMessages){
        super(context,-1,alertMessages);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        AlertMessage message = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alert_adapter_alertmessage,parent,false);
            TextView messageText = convertView.findViewById(R.id.textViewAlertMessage);
            messageText.setText(message.getMessage());
        }
        return convertView;

    }
}
