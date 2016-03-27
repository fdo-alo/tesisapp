package com.darkenrule.tesismaestria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {
    private View btnRadar;
	private View btnAR;
    private View btnPerfil;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        setListeners();
        
    }
    
    private void findViews() {
    	btnAR=findViewById(R.id.btnAR);
        btnPerfil = findViewById(R.id.btnPerfil);

    }
    
    private void setListeners() {
    	//btnRadar.setOnClickListener(this);
    	btnAR.setOnClickListener(this);
        btnPerfil.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if (v.getId()==btnAR.getId())  {
			//Intent i=new Intent(this, EyeViewActivity.class);
            //para prueba
            Intent i = new Intent(this, CarteleraActivity.class);
			startActivity(i);
		} else if(v.getId()==btnPerfil.getId()){
            Intent i=new Intent(this, Preferencias.class);
            startActivity(i);
        }


	}
}