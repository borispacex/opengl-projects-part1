package com.example.tercero;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class Renderiza  implements Renderer {
	private EstrellasColores estrellasC;
	//private Estrellas estrellas;
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		estrellasC = new EstrellasColores();
		// estrellas = new Estrellas();
		gl.glClearColor(200f/255, 191f/255, 231f/255, 0);//colores
		
	}
	@Override
	public void onDrawFrame(GL10 gl) {
			
		/* Inicializa el buffer de color */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		estrellasC.dibuja(gl);
		// estrellas.dibuja(gl);
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		//vamos a utilizar toda nuestra area
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
