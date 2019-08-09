package com.example.decimo;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;

/**
 * Programa de colisión en OpenGL ES 1.x.
 * Colisión y Sonido
 * 
 * @author Jhonny Felipez
 * @version 1.0 7/10/2015
 *
 */
public class MainActivity extends Activity {
	
	GLSurfaceView superficie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		superficie = new Renderiza(this);
		setContentView(superficie);
	}
}
