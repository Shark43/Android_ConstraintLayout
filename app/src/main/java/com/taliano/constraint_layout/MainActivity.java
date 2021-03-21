package com.taliano.constraint_layout;

import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ConstraintLayout layout;
    ImageView img;
    Button btnIndietro;
    Button btnAvanti;
    int screenHeight;
    int screenWidth;
    int imgWidth;
    int imgPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainLayout);
        img = findViewById(R.id.imageView);
        btnIndietro = findViewById(R.id.btnIndietro);
        btnAvanti = findViewById(R.id.btnAvanti);

        btnAvanti.setOnClickListener(this);
        btnIndietro.setOnClickListener(this);
        init();
    }

    //con init leggo dimensioni dello schermo e immagine così capisco lo spazio che ho
    public void init(){
        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point(); //oggetto point

        display.getSize(size); //restiuisce dentro size le dimensioni dello schermo
        screenWidth = size.x; //larghezza schermo
        screenHeight = size.y;//altezza schermo
        //alert(Integer.toString(screenWidth) + " " + Integer.toString(screenHeight));
        alert("Larghezza =" + Integer.toString(screenWidth));
        alert("Altezza =" + Integer.toString(screenHeight));

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) img.getLayoutParams();
        imgWidth = params.width;
        imgPosition = (screenWidth-imgWidth)/2;
        params.setMarginStart(imgPosition);
        img.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) img.getLayoutParams();
        int SPOSTAMENTO = 20;
        if(v.getId() == btnAvanti.getId()){
            if(params.getMarginStart() + imgWidth < screenWidth - SPOSTAMENTO)
                params.setMarginStart(params.getMarginStart() + SPOSTAMENTO);
        } else if(v.getId() == btnIndietro.getId()){
            if(params.getMarginStart()  + imgWidth > SPOSTAMENTO)
                params.setMarginStart(params.getMarginStart() - SPOSTAMENTO);
        }
        img.setLayoutParams(params);
    }

    public void alert(String s){
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }
}
