package com.example.sexto;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class Renderiza implements Renderer {
	/* Textura */
	private Textura textura;
	private Textura textura2;
	/* Contexto */
	private Context contexto;

	public Renderiza(Context contexto) {
		this.contexto = contexto;
	}

	//@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		// 1
		/* Lee la textura */
		textura = new Textura(gl, contexto, "letraC.png");
		/* Area donde se mostrará la textura */
		textura.setVertices(0, 0, 2, 2);
		// 2
		textura2 = new Textura(gl, contexto, "pinguino.png");
		textura2.setVertices(1, 1, 3, 3);
		
		/* Habilita la textura */
		gl.glEnable(GL10.GL_TEXTURE_2D);
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 0);
	}
	
	//@Override
	public void onDrawFrame(GL10 gl) {
		/* Inicializa el buffer de color y de profundidad */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		/* Se selecciona la textura */
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textura.getCodigoTextura());
		/* Se muestra la textura */
		textura.muestra(gl);
		
		// 2
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textura2.getCodigoTextura());
		textura2.muestra(gl);
	}

	//@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);
		/* Matriz de Proyección */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();
		/* Proyección paralela */
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}
}