package com.example.vigesimotercero3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;

public class Renderiza implements Renderer {
	/* Objeto */
	Esfera esfera;

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		esfera = new Esfera(1, 48, 48);
		/* Definición del color de la luz - Valores por defecto */
		// float luz_ambiente[] = { 0, 0, 0, 1}; // I
		// float luz_difusa[] = { 1, 1, 1, 1 };
		// float luz_especular[] = { 1, 1, 1, 1 };
		// float luz_posicion[] = { 0, 0, 2, 0}; // L w == 0 (direccional) w == 1 (posicional)
		
		/*
		float luz_ambiente[] = { 0.2f, 0.2f, 0.2f, 1 }; // I
		float luz_difusa[] = { 1, 1, 0, 1 };
		float luz_especular[] = { 1, 1, 0, 1 };
		// cambiar posicion de foco aqui
		float luz_posicion[] = { 2, 0, 0, 0 }; // L w == 0 (direccional) w == 1 (posicional)
		*/
		float luz_ambiente[] = { 1, 1, 1, 1 }; // I
		float luz_difusa[] = { 1, 1, 1, 1 };
		float luz_especular[] = { 1, 1, 0, 1 };
		float luz_posicion[] = { 2, 0, 0, 0 }; // L 
		
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, luz_ambiente, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, luz_difusa, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, luz_especular, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, luz_posicion, 0);
		/* Definición del color del material - Valores por defecto */
		/*
		float mat_ambiente [] = {0.2f, 0.2f, 0.2f, 1}; // K
		float mat_difuso [] = {0.8f, 0.8f, 0.8f, 1};
		float mat_especular [] = {0f, 0f, 0f, 1};
		float mat_brillo = 0f;
		*/
		/*
		float mat_ambiente[] = { 1, 0.5f, 0.5f, 1 }; // K
		float mat_difuso[] = { 1, 0.5f, 0.5f, 1 };
		float mat_especular[] = { 1, 0.5f, 0.5f, 1 };
		float mat_brillo = 255f;
		*/
		
		float mat_ambiente [] = {0, 0, 0, 1}; // K
		float mat_difuso [] = {0.2f, 0, 0, 1};
		float mat_especular [] = {1, 0, 0, 1};
		float mat_brillo = 255f;
		
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambiente,
				0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_difuso, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR,
				mat_especular, 0);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_brillo);

		/* Se habilita la interpolación del sombreado */
		gl.glShadeModel(GL10.GL_SMOOTH);
		/* Se habilita el ocultamiento de superficies */
		gl.glEnable(GL10.GL_DEPTH_TEST);
		/* Se habilita la iluminación */
		gl.glEnable(GL10.GL_LIGHTING);
		/* Se habilita la luz 0 */
		gl.glEnable(GL10.GL_LIGHT0);
		/* Se habilita la normalización */
		gl.glEnable(GL10.GL_NORMALIZE);
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 1);
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -15);
		esfera.dibuja(gl);
		gl.glFlush();
	}

	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		if (w <= h)
			gl.glOrthof(-2, 2, -2 * h / (float) w, 2 * h / (float) w, 3, 50);
		else
			gl.glOrthof(-2 * w / (float) h, 2 * w / (float) h, -2, 2, 3, 50);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}
