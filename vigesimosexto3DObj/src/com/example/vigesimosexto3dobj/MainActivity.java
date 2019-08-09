package com.example.vigesimosexto3dobj;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Programa que despliega un archivo de formata *.MD2 en OpenGL ES 1.x.
 * 
 * @author Jhonny Felipez
 * @version 1.0 01/01/2015
 * 
 */
public class MainActivity extends Activity {
	
	private Button boton;
	String cuadros[] = {"stand", "run", "attack", "paina", "painb", "painc", "jump", "flip", "salute", "bumflop",
			"wavealt", "sniffsniff", "cstand", "cwalk", "crattack", "crpain", "crdeath", "datha", "dathb", "dathc", "boom"};
	int iCuadro = 0;
	Renderiza renderiza;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout pantalla = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
		GLSurfaceView superficie = new GLSurfaceView(this);
		renderiza = new Renderiza(this);
		superficie.setRenderer(renderiza);
		pantalla.addView(superficie);
		boton = (Button) pantalla.findViewById(R.id.btnCambia);
		boton.bringToFront();
		setContentView(pantalla);
	}
	
	public void btnCambia(View v){
		iCuadro = iCuadro + 1;
		if (iCuadro == cuadros.length)
			iCuadro = 0;
		renderiza.setNombreCuadro(cuadros[iCuadro]);
		runOnUiThread(new Runnable() {
            // @Override
            public void run() {
                boton.setText(String.format("%s", cuadros[iCuadro]));
            }
        });
	}
}
