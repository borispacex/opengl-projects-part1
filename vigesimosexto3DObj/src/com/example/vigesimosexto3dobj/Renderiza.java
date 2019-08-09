package com.example.vigesimosexto3dobj;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

/**
 * Clase Renderiza (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 01/01/2015
 *
 */
public class Renderiza implements Renderer {
	
	/* Objeto */
	MD2 md2 = new MD2();
	boolean estaBienElModelo;
	float angulo = 30;
	Context contexto;
	String nombreCuadro = "stand";
	
	public Renderiza(Context contexto){
		this.contexto = contexto;
	}
	
	public void setNombreCuadro(String nombreCuadro){
		this.nombreCuadro = nombreCuadro;
	}
	
	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
	 
		try {
			estaBienElModelo = md2.leeArchivoMD2(contexto, gl, "Ogros.md2", "igdosh.png");
		} catch (IOException e) {
			System.err.println("Error parsing :");
			e.printStackTrace();
		}		

		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glEnable(GL10.GL_NORMALIZE);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		gl.glClearColor(176/255f, 196/255f, 222/256f, 0);
	}
	
	// @Override
	public void onDrawFrame(GL10 gl) {
		if (estaBienElModelo)
			md2.animacion(nombreCuadro);

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		/* Definición del color de la luz */
		float luz_ambiente[] = {0.3f, 0.3f, 0.3f, 1.0f};
		float luz_difusa[] = {0.7f, 0.7f, 0.7f, 1.0f};
		float luz_posicion[] = {-0.2f, 0.3f, 1, 0.0f};
		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, luz_ambiente, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, luz_difusa, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, luz_posicion, 0);
		
		if (estaBienElModelo) {
			gl.glPushMatrix();
			gl.glTranslatef(0.0f, 0.0f, -10.0f);
			gl.glRotatef(-angulo, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
			gl.glScalef(0.1f, 0.1f, 0.1f);
			md2.dibuja(gl);
			gl.glPopMatrix();
		}
		
		angulo = angulo + 0.7f;
		if (angulo > 360) {
			angulo = angulo - 360;
		}
		
		// Avanza la animacion
		if (estaBienElModelo)
			md2.avanza(0.025f);
	}
	
	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45, (float)w / (float)h, 1, 200);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}
