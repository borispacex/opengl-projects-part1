package com.example.primero;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Linea {
	/*
	 * 	v0-----------v1
	 * */
	
	private float vertices[] = {
		-0.7f,0.5f, // v0
		-0.2f,0.5f, //v1
		 
		 -0.2f,0.5f,
		 0.1f,0.9f,
		 
		 0.1f,0.9f,
		 0.4f,0.5f,
		 
		 0.4f,0.5f,
		 0.9f, 0.5f,
		 
		 -0.7f,0.5f,
		 -0.2f, 0.2f,
		 
		 0.9f, 0.5f,
		 0.4f, 0.2f,
		 
		 -0.6f, -0.3f,
		 -0.2f, 0.2f,
		 
		 0.4f, 0.2f,
		 0.8f, -0.3f,
		 
		 -0.6f, -0.3f,
		 0.1f, 0,
		 
		 0.1f, 0,
		 0.8f, -0.3f,

		 
		 
	};
	FloatBuffer bufVertices;
	public Linea(){
		//en aca se multiplica por 4 porque es float
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices = bufByte.asFloatBuffer();
		bufVertices.put(vertices);
		bufVertices.rewind();
	}
	
	public void dibuja(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glColor4f(1, 0, 0, 1);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		gl.glDrawArrays(GL10.GL_LINES, 0, 20);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
