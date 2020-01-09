package com.example.pantallapreferenciasexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button previsualizarButton;
    private TextView textView;
    private Button cerrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        previsualizarButton = findViewById(R.id.previsualizarButton);
        textView = findViewById(R.id.textView);
        cerrarButton = findViewById(R.id.cerarButton);

        previsualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });

        cerrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int tamanoTexto = preferences.getInt("tamanoLetraSeekbar", 15);
        String colorTexto = preferences.getString("colores", "#D81B60");
        String colorFondo = preferences.getString("colorFondo", "#008577");
        boolean negritaFlag = preferences.getBoolean("switch_preference_1", false);
        boolean cursivaFlag = preferences.getBoolean("check_box_preference_1", false);

        int transparencia = preferences.getInt("transparenciaSeekbar", 50);
        int giro = preferences.getInt("girarLetraSeekbar", 90);

        textView.setTextSize(tamanoTexto);
        textView.setTextColor(Color.parseColor(colorTexto));
        textView.setBackgroundColor(Color.parseColor(colorFondo));
        textView.setTypeface(Typeface.DEFAULT);
        if(negritaFlag&&cursivaFlag){
            textView.setTypeface(textView.getTypeface(),Typeface.BOLD_ITALIC);
        }else {
            if(negritaFlag){
                textView.setTypeface(textView.getTypeface(),Typeface.BOLD);
            }
            if(cursivaFlag){
                textView.setTypeface(textView.getTypeface(),Typeface.ITALIC);
            }
        }

        textView.setAlpha(transparencia);
        textView.setRotation(giro);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settingMenu:
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
