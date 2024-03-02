package com.example.storedatausingfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();
                if (!text.equals("")){

                    writeData(text);

                }else {
                    Toast.makeText(MainActivity.this, "Please enter some data", Toast.LENGTH_SHORT).show();
                }
            }
        });



        dataRead();

    }




    //for data writing from file>>>>>>>>>>>>>>>>>>>>>>>>>
    private void writeData(String text) {
        try {
            //for file open
            FileOutputStream fileOutputStream = openFileOutput("data.txt", Context.MODE_PRIVATE);
            //for add data in created file
            fileOutputStream.write(text.getBytes());
            //for file close
            fileOutputStream.close();
            Toast.makeText(this, "Data is saved", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //for data reading from file>>>>>>>>>>>>>>>>>>>>>
    private void dataRead(){
        try {
            //for data retrieve from file
            FileInputStream fileInputStream = openFileInput("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuffer stringBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine())!=null){

                stringBuffer.append(line).append("\n");
            }
            editText.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}