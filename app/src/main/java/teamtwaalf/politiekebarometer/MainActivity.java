package teamtwaalf.politiekebarometer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Observable;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        addEventHandlers();


    }

    private void addEventHandlers() {
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient client = new RestClient(MainActivity.this);
                io.reactivex.Observable<String> observable = io.reactivex.Observable.create(subscriber -> {
                    try {
                        String result = client.getResult();
                        Log.d("LOGKEY",result);
                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                });

                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> testTextView.setText(result),
                exception -> Toast.makeText(MainActivity.this,exception.getMessage(),Toast.LENGTH_LONG).show());
            }
        });


    }

}
