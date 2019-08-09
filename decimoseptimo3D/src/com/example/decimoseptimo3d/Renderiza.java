package com.example.decimoseptimo3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {
	/* Objeto */
	private Cubo cubo;
	/* Para la rotaci�n */
	private float rotX;
	private float rotY;
	private float antX;
	private float antY;

	public Renderiza(Context contexto) {
		super(contexto);
		/* Se inicia el renderizado */
		this.setRenderer(this);
		/* La ventana solicita recibir una entrada */
		this.requestFocus();
		/* Se establece que la ventana detecte el modo t�ctil */
		this.setFocusableInTouchMode(true);
		/* Se renderizar� al inicio o cuando se llame a requestRender() */
		this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		cubo = new Cubo();
		/* Se habilita el modo de sombreado plano */
		gl.glShadeModel(GL10.GL_FLAT);
		/* Se habilita el ocultamiento de superficies */
		gl.glEnable(GL10.GL_DEPTH_TEST);
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 0);
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		/* Se inicializa el buffer de color y de profundidad */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		/* Se inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		/* Rota el cubo */
		gl.glRotatef(rotX, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotY, 1.0f, 0.0f, 0.0f);
		cubo.dibuja(gl);
		/* Se asegura que se ejecute las anteriores instrucciones */
		gl.glFlush();
	}

	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);
		/* Matriz de Proyecci�n */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		/* Se inicializa la Matriz de Proyecci�n */
		gl.glLoadIdentity();
		/* Proyecci�n paralela */
		if (w <= h)
			gl.glOrthof(-2, 2, -2 * (float) h / (float) w, 2 * (float) h
					/ (float) w, -10, 10);
		else
			gl.glOrthof(-2 * (float) w / (float) h, 2 * (float) w / (float) h,
					-2, 2, -10, 10);
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/* Se inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}

	/**
	 * Maneja los eventos del movimiento en la pantalla t�ctil.
	 */
	// @Override
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