package com.example.sexto;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;

public class MainActivity extends Activity {
	private GLSurfaceView superficie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Ventana sin t�tulo */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* Establece las banderas de la ventana de esta Actividad */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/* Se crea el objeto GLSurfaceView */
		superficie = new GLSurfaceView(this);
		/* Se crea el objeto Renderiza */
		Renderiza renderiza = new Renderiza(this);
		/* GLSurfaceView <- Renderiza : Inicia el renderizado */
		superficie.setRenderer(renderiza);
		/*
		 * Activity <- GLSurfaceView : Coloca la Vista de la Superficie del
		 * OpenGL como un Contexto de �sta Actividad.
		 */
		setContentView(superficie);
		// setContentView(R.layout.activity_main);
	}
}