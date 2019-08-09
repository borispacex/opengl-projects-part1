package com.example.decimoprimero;

import java.util.Calendar;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {
	private Rectangulo rectangulo;
	private float angulo, incAngulo;
	
	private final MainActivity mActivity;
	
	/* Variables para obtener el tiempo */
	private long inicio, fin, duracion;
	private float tiempo_real;
	private float tiempoRotacion;
	private final float PERIODO_DE_LA_ROTACION = 1/30f; // en segundos
	int hora, minutos, segundos;
	Calendar calendario;
	
	public Renderiza(Context contexto, MainActivity activity) {
		super(contexto);
		this.mActivity = activity;
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}	
	
	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		rectangulo = new Rectangulo();
	
		/* Inicializa las variables */
		angulo = 0;
		incAngulo = 2;
		inicio = System.currentTimeMillis();
		tiempoRotacion = PERIODO_DE_LA_ROTACION;
		
		gl.glClearColor(0, 0, 0, 0);
	}
	
	// @Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		calendario = Calendar.getInstance();
	    hora = calendario.get(Calendar.HOUR_OF_DAY);
	    minutos = calendario.get(Calendar.MINUTE);
	    segundos = calendario.get(Calendar.SECOND);
		mActivity.actualizaTexto(hora, minutos, segundos);

		gl.glPushMatrix();
		gl.glRotatef(angulo, 0, 0, 1);
		rectangulo.dibuja(gl);
		gl.glPopMatrix();
		
		/* Obtiene el tiempo real*/
		fin = System.currentTimeMillis();
		duracion = fin - inicio;
		tiempo_real = duracion / 1000f;
		inicio = fin;
		
		/* Incrementa y verifica el límite */
		tiempoRotacion = tiempoRotacion - tiempo_real;
		if (tiempoRotacion < 0.001) { 
			tiempoRotacion = PERIODO_DE_LA_ROTACION;
			angulo = angulo + incAngulo;
			if (angulo > 360)
				angulo = angulo - 360;
		}
	}
	
	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e){
		if (e.getAction() == MotionEvent.ACTION_UP){
			incAngulo = incAngulo == 0 ? 2 : 0;
			requestRender(); // Llama por defecto
		}
		return true;
	}
}
