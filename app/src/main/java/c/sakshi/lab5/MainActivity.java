package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view) {
        EditText e = (EditText) view.findViewById(R.id.username);
//        String s = e.getText().toString();
        goToActivity2();
    }

    public void goToActivity2() {
        Intent intent = new Intent(this, Main2Activity.class);
//        intent.putExtra("message", s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
