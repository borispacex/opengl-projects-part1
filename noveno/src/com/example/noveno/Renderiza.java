package com.example.noveno;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {
	/* Objeto */
	private RectanguloGrafico rectangulografico;
	private Rectangulo r1, r2;
	private float despX_r1 = -5, despX_r2 =  5;
	private float incX_r1 = 0.05f, incX_r2 = -0.05f;
	public Renderiza(Context contexto) {
		super(contexto);
		this.setRenderer(this);	//	inicia el renderizado
		this.requestFocus();	// La ventana solicita recibir una entrada
		this.setFocusableInTouchMode(true);	//	Establece que la ventana pueda detectar el modo táctil .
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);	// El renderizado se llama continuamente para renderizar la escena .
	}

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		rectangulografico = new RectanguloGrafico();
		r1 = new Rectangulo(0, 0, 2, 1);
		r2 = new Rectangulo(0, 0, 2, 1);
		gl.glClearColor(0, 0, 0, 0);	// Color de fondo
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	// Inicializa el buffer de color y de profundidad 
		gl.glMatrixMode(GL10.GL_MODELVIEW);	// Inicializa la Matriz del Modelo - Vista
		
		
		
		// inicializa rectangulo 1
		gl.glPushMatrix();
		gl.glTranslatef(0, despX_r1, 0);
		r1.x = 0;
		r1.y = despX_r1;
		gl.glColor4f(1, 0, 0, 1);
		rectangulografico.dibuja(gl);
		gl.glPopMatrix();
		despX_r1 = despX_r1 + incX_r1;
		if(despX_r1 < -5 || 5 < despX_r1){
			incX_r1 = - incX_r1;
		}
		// inicializa rectangulo 2
		gl.glPushMatrix();
		gl.glTranslatef(0, despX_r2, 0);
		r2.x = 0;
		r2.y = despX_r2;
		gl.glColor4f(0, 0, 1, 1);
		rectangulografico.dibuja(gl);
		gl.glPopMatrix();
		despX_r2 = despX_r2 + incX_r2;
		if(despX_r2 < -5 || 5 < despX_r2){
			incX_r2 = - incX_r2;
		}
		
		if(seSobreponen(r1,r2)){
			incX_r1 = -incX_r1;
			incX_r2 = -incX_r2;
		}
		gl.glFlush();
	}
	public boolean seSobreponen(Rectangulo r1, Rectangulo r2) {
		return (r1.x < r2.x + r2.ancho && r1.x + r1.ancho >  r2.x  &&
				r1.y < r2.y + r2.alto && r1.y	+ r1.alto > r2.y);
	}
	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);	// Ventana de despliegue
		gl.glMatrixMode(GL10.GL_PROJECTION);	// Matriz de Proyección
		gl.glLoadIdentity();	// Inicializa la Matriz de Proyección
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);	// Proyección paralela
		gl.glMatrixMode(GL10.GL_MODELVIEW);	// Matriz del Modelo - Vista
		gl.glLoadIdentity();	// Inicializa la Matriz del Modelo - Vista
	}
}