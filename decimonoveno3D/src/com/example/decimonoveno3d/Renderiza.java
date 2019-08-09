package com.example.decimonoveno3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {
	/* Objeto */
	private Cubo cubo;
	private Piso piso;
	/* Tamaño de la ventana en pixeles */
	private int alto;
	private int ancho;
	/* Para la rotación */
	private float rotX;
	private float rotY;
	private float antX;
	private float antY;
	/* Contexto */
	Context contexto;

	public Renderiza(Context contexto) {
		super(contexto);
		this.contexto = contexto;
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		cubo = new Cubo();
		piso = new Piso();
		/* Habilita el ocultamiento de superficies */
		gl.glEnable(GL10.GL_DEPTH_TEST);
		/* Color de fondo */
		gl.glClearColor(176 / 255f, 196 / 255f, 222 / 256f, 1);
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		/* Borra el buffer de la ventana y del z-buffer */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		/* Matriz del Modelo */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -10);
		gl.glRotatef(rotX, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotY, 1.0f, 0.0f, 0.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		/* Dibuja el Piso */
		piso.dibuja(gl);

		/* Dibuja el Cubo */
		cubo.dibuja(gl);
		gl.glFlush();
	}

	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		ancho = w;
		alto = h;
		gl.glViewport(0, 0, w, h);
		/* Matriz de Proyección */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 60, ancho / (float) alto, 1f, 100f);
		/* Matriz de la Camara o Vista */
		// GLU.gluLookAt(gl, ex, ey, ez, 0, 0, -1, 0, 1, 0);
		/*
		 	n=(ex,ey,ez)		-n=(0,0,-1)
		 	<----------------------------->
		 	-> posicion de la camara 
		 	-> posicion de la vista de la camara
		 	-> rote
		*/
		// GLU.gluLookAt(gl, 0, 0, 0, 0, 0, -1, 0, 1, 0);
		GLU.gluLookAt(gl, 0, -3, 0, 0, -3, -1, 0, 1, 0);
		/*
		 * TAREA
		 * boton a ver a dos objetos
		 * boton b pisicionar en el primer objecto para ver al otro objeto
		 * boton c posicionar en el segundo objeto para ver al otro objeto
		 */
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
			rotX = rotX + dx * 0.5625f; // 180 / 320 = 0.5625
			rotY = rotY + dy * 0.5625f;
			requestRender();
		}
		antX = x;
		antY = y;
		return true;
	}
}
