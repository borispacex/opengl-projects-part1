package com.example.primero;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class Renderiza implements Renderer{
	
	private Linea linea;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		linea = new Linea();
		gl.glClearColor(0, 1, 0.5f , 1);
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);//con esto se pinta el color de fondo
		gl.glLineWidth(10);
		linea.dibuja(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		
	}	
}
