package projekt.ObjectLoaderFirstTry;

import projekt.VectorsAndMatrices.Vec2f;
import projekt.VectorsAndMatrices.Vec3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ModelLoader {

    public ModelLoader(Model m, String path) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(new File(path)));
        String line;
        while ((line = read.readLine()) != null) {
            if (line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                Vec3f v = new Vec3f(x, y, z);
                m.vertices.add(v);
            }
            if (line.startsWith("vn ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                Vec3f vn = new Vec3f(x, y, z);
                m.normals.add(vn);
            }
            if (line.startsWith("vt ")) {
                float u1 = Float.valueOf(line.split(" ")[1]);
                float v1 = Float.valueOf(line.split(" ")[2]);
                Vec2f vt = new Vec2f(u1,v1);
                m.textures.add(vt);
            }

            if (line.startsWith("f ")) {
                int x1 = Integer.valueOf(line.split(" ")[1].split("/")[0]);
                int y1 = Integer.valueOf(line.split(" ")[2].split("/")[0]);
                int z1 = Integer.valueOf(line.split(" ")[3].split("/")[0]);

                int uv1 =  Integer.valueOf(line.split(" ")[1].split("/")[1]);
                int uv2 =  Integer.valueOf(line.split(" ")[2].split("/")[1]);
                int uv3 =  Integer.valueOf(line.split(" ")[3].split("/")[1]);

                int n1 =  Integer.valueOf(line.split(" ")[1].split("/")[2]);
                int n2 =  Integer.valueOf(line.split(" ")[2].split("/")[2]);
                int n3 =  Integer.valueOf(line.split(" ")[3].split("/")[2]);

                Faces face = new Faces(new Vec3f(x1,y1,z1),new Vec3f(uv1,uv2,uv3), new Vec3f(n1,n2,n3));
                m.facesList.add(face);
            }
        }
        m.textureArray = new float[m.vertices.size()*2];
        m.normalsArray = new float[m.vertices.size()*3];
    }


}
