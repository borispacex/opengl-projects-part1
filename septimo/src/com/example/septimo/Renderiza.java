package com.example.septimo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {
	/* Objeto */
	private Triangulo triangulo;	//	Incremento del ángulo de la animación
	private float angulo = 0;		//	Se almacena el desplazamiento del triángulo
	private float tx, ty; 			//¿ Se realiza la animación ?
	private boolean animacion = true;
	public Renderiza(Context contexto) {
		super(contexto);
		this.setRenderer(this);	//	inicia el renderizado
		this.requestFocus();	// La ventana solicita recibir una entrada
		this.setFocusableInTouchMode(true);	//	Establece que la ventana pueda detectar el modo táctil .
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);	// El renderizado se llama continuamente para renderizar la escena .
	}

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		triangulo = new Triangulo();
		gl.glClearColor(0, 0, 0, 0);	// Color de fondo
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	// Inicializa el buffer de color y de profundidad 
		gl.glMatrixMode(GL10.GL_MODELVIEW);	// Inicializa la Matriz del Modelo - Vista
		gl.glLoadIdentity();
		// gl.glTranslatef(1, 1, 0);	// (x,y,z)	para trasladar	
		// gl.glScalef(2, 1, 1);	// (x,y,z)	para escalar
		// gl.glRotatef(-45, 0, 0, 1);	// (grados,x,y,z)
		/*
		gl.glTranslatef(1.5f, 1.5f, 0);		// T tx, ty
		gl.glRotatef(90, 0, 0, 1);			// R angulo
		gl.glTranslatef(-1.5f, -1.5f, 0);	// T -tx, -ty
		*/
		gl.glTranslatef(1, ty, 0);
		triangulo.dibuja(gl);
		if (ty > 5) {
			animacion = false;
		}
		if(animacion) {
			ty = ty + 0.1f;
		}else {
			ty = ty - 0.1f;
		}
		if (ty < -5) {
			animacion = true;
		}
		
		
		
		/*
		// Calcula el desplazamiento
		float theta = (float) Math.toRadians(angulo);
		tx = (float) (Math.cos(theta) * 2);
		ty = (float) (Math.sin(theta) * 2);
		// Traslada
		gl.glTranslatef(tx, ty, 0);
		triangulo.dibuja(gl);
		// Se asegura que se ejecute las anteriores instrucciones
		gl.glFlush();
		if (animacion) {
			angulo = angulo + 3;
			if (angulo > 720)
				angulo = 0;
		}
		*/
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

	/*Maneja los eventos de la pantalla táctil. */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		/* Se considera cuando el dedo toca la pantalla. */
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			animacion = !animacion;
		}
		return true;
	}
}