package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.RestClient;

public class MainActivity extends Activity {
    @BindView(R.id.ButtonInlog)
    Button logInButton;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.editTextUserName)
    EditText editTextUserName;
    @BindView(R.id.imgViewUsername)
    ImageView imgViewUsername;
    @BindView(R.id.imgViewPassword)
    ImageView imgViewPassword;
    RestClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        imgViewUsername = findViewById(R.id.imgViewUsername);
        imgViewUsername.setImageResource(R.drawable.user);
        imgViewUsername.setPadding(10, 10, 10, 10);

        imgViewPassword = findViewById(R.id.imgViewPassword);
        imgViewPassword.setImageResource(R.drawable.password);
        imgViewPassword.setPadding(10, 10, 10, 10);
    }

    @OnClick(R.id.ButtonInlog)
    void StuurGegegevensDoor() {
        client = new RestClient(this);
        System.out.println(editTextUserName.getText().toString() + editTextPassword.getText().toString());
        client.getUserId(editTextUserName.getText().toString(), editTextPassword.getText().toString());
    }

    public void LogGebruikerIn(String id) {
        if (id.equals("null")) {
            Toast.makeText(this, "Uw wachtwoord of password klopt niet", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, GraphActivity.class);
            intent.putExtra("userId", id);
            startActivity(intent);
        }
    }

}
