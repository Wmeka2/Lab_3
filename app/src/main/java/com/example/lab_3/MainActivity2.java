package com.example.lab_3;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //CHANGED :
        // We have declared these variables above
        // So we can use them in methods outside main method to set values
        // If we also Declare them here they will give errors may crash app for
        // setting values in textview
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        //To load Values from SharedPreferences when app starts
        loadData();
        //set textview with values of text variable
        updateViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //When button is clicked we save Data in Shared Preference
                saveData();

                // After saving data in share preference
                // we fetch them to send to next Activity
                // We have to include this method here otherwise,
                // it will send old values to NameActivity
                loadData();

                Intent intent = new Intent(MainActivity2.this, NameActivity.class);

                //sending value of text (updated by loadData() method) variable to NameActivity 
                intent.putExtra(TEXT, text);
                startActivity(intent);

                //removed finish() method for back and forth in activity

                //set textview from edittext
                textView.setText(editText.getText().toString());

            }
        });


    }

    public void saveData() {


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, editText.getText().toString());
        editor.apply();

        Toast.makeText(this, "Info Saved", Toast.LENGTH_LONG).show();
    }


    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        text = sharedPreferences.getString(TEXT, "");
    }




    public void updateViews() {
        textView.setText(text);
    }

    ActivityResultLauncher<Intent> nameActivityFoResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == 1) {
                                editText.setText("");
                                editText.setHint("Enter New User Name");
                            }

                            else if (result.getResultCode() == 0) {
                                Toast.makeText(MainActivity2.this, "Happy User", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
    @Override
    protected void onPause() {
        super.onPause();
    }
}