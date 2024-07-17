package com.example.p5_curd_sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextAge;
    private Button buttonAdd;
    private Button buttonRead;
    private Button buttonUpdate;
    private Button buttonDelete;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonRead = findViewById(R.id.buttonRead);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewResult = findViewById(R.id.textViewResult);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                boolean result = databaseHelper.addData(name, age);
                if (result) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = databaseHelper.readData();
                if (cursor.getCount() == 0) {
                    textViewResult.setText("No Data Found");
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                while (cursor.moveToNext()) {
                    stringBuilder.append("ID: ").append(cursor.getInt(0)).append("\n");
                    stringBuilder.append("Name: ").append(cursor.getString(1)).append("\n");
                    stringBuilder.append("Age: ").append(cursor.getInt(2)).append("\n\n");
                }
                textViewResult.setText(stringBuilder.toString());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(editTextId.getText().toString());
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                boolean result = databaseHelper.updateData(id, name, age);
                if (result) {
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(editTextId.getText().toString());
                boolean result = databaseHelper.deleteData(id);
                if (result) {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Deletion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}