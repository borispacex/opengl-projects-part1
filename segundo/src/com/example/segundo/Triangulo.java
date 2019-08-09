package com.example.segundo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo {
	/**
	* 		    2
	* 		    /\
	* 		   /  \
	* 		  /    \
	* 		 /      \
	* 		/________\
	* 		0        1
	*/
	
	private float vertices[] = {
			-0.5f, -0.5f, // 0
			0.5f, -0.5f, // 1
			0, 0.5f // 2
 
	};
	FloatBuffer bufVertices;
	public Triangulo(){
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
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
