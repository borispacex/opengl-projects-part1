package com.example.decimocuarto3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;

public class Renderiza implements Renderer {
	/* Objeto */
	private Cubo cubo;
	/* Para la rotación */
	private float rotX;
	private float rotY;
	private float rotZ;

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		cubo = new Cubo();
		/* Habilita el modo de sombreado plano */
		gl.glShadeModel(GL10.GL_FLAT);
		/* Habilita el ocultamiento de superficies */
		gl.glEnable(GL10.GL_DEPTH_TEST);
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 0);
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		/* Inicializa el buffer de color y de profundidad */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		/* Rota el cubo */
		gl.glRotatef(rotX, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(rotY, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotZ, 0.0f, 0.0f, 1.0f);
		cubo.dibuja(gl);
		/* Se asegura que se ejecute las anteriores instrucciones */
		gl.glFlush();
		rotX = rotX + 0.8f;
		rotY = rotY + 0.7f;
		rotZ = rotZ + 0.6f;
	}

	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);
		/* Matriz de Proyección */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();
		/* Proyección paralela */
		if (w <= h)
			gl.glOrthof(-2, 2, -2 * (float) h / (float) w, 2 * (float) h
					/ (float) w, -10, 10);
		else
			gl.glOrthof(-2 * (float) w / (float) h, 2 * (float) w / (float) h,
					-2, 2, -10, 10);
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}
}