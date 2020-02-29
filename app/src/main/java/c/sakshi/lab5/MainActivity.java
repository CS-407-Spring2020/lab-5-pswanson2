package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText e;
    EditText p;

    public void clickFunction(View view) {
        e = (EditText) findViewById(R.id.username);
        p = (EditText) findViewById(R.id.password);

        String user_str = e.getText().toString();
        String pass_str = p.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("username", user_str).apply();

        goToActivity2(user_str);
    }

    public void goToActivity2(String user) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("username", user);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            String user = sharedPreferences.getString(usernameKey, "");
            goToActivity2(user);
        } else {
            setContentView(R.layout.activity_main);
        }


    }


}
