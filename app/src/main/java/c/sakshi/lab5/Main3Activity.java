package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {

    int nodeid = -1;

    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        e = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        nodeid = intent.getIntExtra("noteid", -1);

        if (nodeid != -1) {
            Note note = Main2Activity.notes.get(nodeid);
            String noteContent = note.getContent();
            e.setText(noteContent);
        }
    }

    public void saveMethod(View view) {
        // get username from shared preferences
        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(usernameKey, "");

        // get SQLite database instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // get content user entered
        e = (EditText) findViewById(R.id.editText);
        String content = e.getText().toString();

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (nodeid == -1) {
            title = "NOTE_" +  (Main2Activity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (nodeid + 1);
            dbHelper.updateNote(title, date, content);
        }

        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
