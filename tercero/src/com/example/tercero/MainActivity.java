package com.example.tercero;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GLSurfaceView s=new GLSurfaceView(this);
		Renderiza r=new Renderiza();//para los objetos
		s.setRenderer(r);//pueda renderizar
		
		setContentView(s);//visualizamos
		//setContentView(R.layout.activity_main)
	}

	

}

