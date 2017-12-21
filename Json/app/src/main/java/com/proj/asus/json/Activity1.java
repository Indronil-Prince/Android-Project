package com.proj.asus.json;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity implements View.OnClickListener{

    private Button FixtureButton, UCLButton, UELButton, EnglishButton, SpainButton, ItalyButton, GermanyButton, FranceButton, PortugalButton, PointsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        FixtureButton= (Button) findViewById(R.id.FixButton);
        UCLButton= (Button) findViewById(R.id.UclButton);
        UELButton= (Button) findViewById(R.id.UelButton);
        EnglishButton= (Button) findViewById(R.id.EngButton);
        SpainButton= (Button) findViewById(R.id.EspButton);
        ItalyButton= (Button) findViewById(R.id.ItaButton);
        GermanyButton= (Button) findViewById(R.id.GerButton);
        FranceButton= (Button) findViewById(R.id.FraButton);
        PortugalButton= (Button) findViewById(R.id.PorButton);
        PointsButton= (Button) findViewById(R.id.PointButton);

        FixtureButton.setOnClickListener(Activity1.this);
        UCLButton.setOnClickListener(Activity1.this);
        UELButton.setOnClickListener(Activity1.this);
        EnglishButton.setOnClickListener(Activity1.this);
        SpainButton.setOnClickListener(Activity1.this);
        ItalyButton.setOnClickListener(Activity1.this);
        GermanyButton.setOnClickListener(Activity1.this);
        FranceButton.setOnClickListener(Activity1.this);
        PortugalButton.setOnClickListener(Activity1.this);
        PointsButton.setOnClickListener(Activity1.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.FixButton) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.UclButton) {
            Intent intent0 = new Intent(this, UCLActivity.class);
            startActivity(intent0);
        }
        else if (view.getId() == R.id.UelButton) {
            Intent intent1 = new Intent(this, UELActivity.class);
            startActivity(intent1);
        }
        else if (view.getId() == R.id.EngButton) {
            Intent intent2 = new Intent(this, EnglandActivity.class);
            startActivity(intent2);
        }
        else if (view.getId() == R.id.EspButton) {
            Intent intent3 = new Intent(this, SpainActivity.class);
            startActivity(intent3);
        }
        else if (view.getId() == R.id.ItaButton) {
            Intent intent4 = new Intent(this, ItalyActivity.class);
            startActivity(intent4);
        }
        else if (view.getId() == R.id.GerButton) {
            Intent intent5 = new Intent(this, GermanyActivity.class);
            startActivity(intent5);
        }
        else if (view.getId() == R.id.FraButton) {
            Intent intent6 = new Intent(this, FranceActivity.class);
            startActivity(intent6);
        }
        else if (view.getId() == R.id.PorButton) {
            Intent intent7 = new Intent(this, PortugalActivity.class);
            startActivity(intent7);
        }
        else if (view.getId() == R.id.PointButton) {
            Intent intent8 = new Intent(this, PointActivity.class);
            startActivity(intent8);
        }
    }
}
