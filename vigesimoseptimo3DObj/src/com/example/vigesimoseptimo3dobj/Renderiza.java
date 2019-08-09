package com.example.vigesimoseptimo3dobj;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

/**
 * Clase Renderiza (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 02/04/2014
 *
 */
public class Renderiza extends GLSurfaceView implements Renderer {
	
	/* Objeto */
	Objeto obj;
	
	/* Para la rotación */
	private float rotX;
	private float rotY;
	private float antX;
	private float antY;
	
	/* Contexto */
	Context contexto;
	
	public Renderiza(Context contexto){
		super(contexto);
		this.contexto = contexto;
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		
		//obj = new Objeto(contexto, "cubo2.obj");
		//obj = new Objeto(contexto, "rose+vase.obj");
		//obj = new Objeto(contexto, "al.obj");
		//obj = new Objeto(contexto, "f-16.obj");
		obj = new Objeto(contexto, "porsche.obj");
		
		/* Definición del color de la luz - Valores por defecto */
		float [] luz_ambiente = { 0, 0, 0, 1}; // I 
		float [] luz_difusa = { 1, 1, 1, 1 }; 
		float [] luz_especular = { 1, 1, 1, 1 }; 
		float [] luz_posicion = { 0, 0, 1, 0}; // L
		
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, luz_ambiente, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, luz_difusa, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, luz_especular, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, luz_posicion, 0);
        
		gl.glShadeModel(GL10.GL_SMOOTH); 
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_LIGHTING); 
		gl.glEnable(GL10.GL_LIGHT0); 
		gl.glEnable(GL10.GL_NORMALIZE); 
		gl.glClearColor(176/255f, 196/255f, 222/256f, 0);
	}
	
	// @Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -15);
		gl.glRotatef(rotX, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotY, 1.0f, 0.0f, 0.0f);
		obj.dibuja(gl);
	}
	
	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		if (w <= h)
			gl.glOrthof(-2, 2, -2 * (float) h / w, 2 * (float) h / w, 3, 50);
		else
			gl.glOrthof(-2 * (float) w / h, 2 * (float) w / h, -2, 2, 3, 50);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	/**
	 * Maneja los eventos del movimiento en la pantalla táctil. 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float x = e.getX();
		float y = e.getY();
		switch (e.getAction()) {
			case MotionEvent.ACTION_MOVE:
				float dx = x - antX;
				float dy = y - antY;
				rotX = rotX + dx * 0.5625f;
				rotY = rotY + dy * 0.5625f;
				requestRender();
		}
		antX = x;
		antY = y;
		return true;
	}
}
