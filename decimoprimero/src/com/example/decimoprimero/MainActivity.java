package com.example.decimoprimero;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Este es un programa simple de doble buffer. Presionando la pantalla gira el
 * rectángulo. Al presionar nuevamente la pantalla se detiene la rotación en OpenGL ES 1.x.
 */
public class MainActivity extends Activity {
	
	private TextView texto1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout pantalla = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
		GLSurfaceView superficie = new GLSurfaceView(this);
		Renderiza renderiza = new Renderiza(this, this);
		superficie.setRenderer(renderiza);
		pantalla.addView(superficie);
		texto1 = (TextView) pantalla.findViewById(R.id.texto1);
		texto1.bringToFront();
		setContentView(pantalla);
	}
	
	public void actualizaTexto(final int hora, final int minutos, final int segundos) {
        runOnUiThread(new Runnable() {
            // @Override
            public void run() {
                texto1.setText(String.format("%02d:%02d:%02d", hora, minutos, segundos));
            }
        });
    }
}