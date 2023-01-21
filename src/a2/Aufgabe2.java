package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2"/*, 700, 700*/);
	}

	int id;

	@Override
	protected void init() {
		// folgende Zeile läd automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		float[] dreiecke = {
				0.5F,0.5F,0F,0F,1F,
				-0.9F,0.4F,0F,1F,0F,
				0F, -0,3F,1F,0F,0F
		};

		id = shaderProgram.getId();


		int vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);

		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		glBufferData(GL_ARRAY_BUFFER,
				dreiecke, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT,
				false, 20, 0);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(1, 3, GL_FLOAT,
				false, 20, 8);
		glEnableVertexAttribArray(1);

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
	}

	@Override
	public void update() {
		float angle = (float) ((Math.sin(System.currentTimeMillis())/1000%360));
		//System.out.println(angle);
		int myValueLoc = glGetUniformLocation(id,"time");
		glUniform1f(myValueLoc, angle);
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren

		// hier vorher erzeugte VAOs zeichnen
		glDrawArrays(GL_TRIANGLES,0,3);
	}

	@Override
	public void destroy() {
	}

	@Override
	protected void ProcessInput(long window) {

	}
}
