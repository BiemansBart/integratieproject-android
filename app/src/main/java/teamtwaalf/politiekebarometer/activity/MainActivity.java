package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.RestClient;

public class MainActivity extends Activity {
    Button logInButton;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.editTextUserName)
    EditText editTextUserName;
    @BindView(R.id.testTextView)
    EditText testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logInButton = findViewById(R.id.ButtonInlog);
        RestClient rc = new RestClient(this);
        rc.InitialiseGraphList();
        addEventHandlers();
    }

    private void addEventHandlers() {
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);

              /*  RestClient client = new RestClient(MainActivity.this);
                io.reactivex.Observable<String> observable = io.reactivex.Observable.create(subscriber -> {
                    try {
                        String result = client.getResult();
                        Log.d("LOGKEY",result);
                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                });

                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> testTextView.setText(result),
                exception -> Toast.makeText(MainActivity.this,exception.getMessage(),Toast.LENGTH_LONG).show());*/
            }
        });


    }

}
