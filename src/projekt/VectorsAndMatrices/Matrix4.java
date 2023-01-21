package projekt.VectorsAndMatrices;

//Alle Operationen ändern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurück
//dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {

    float[][] werte = new float[4][4];

    public Matrix4(float[][] werte) {
        this.werte = werte;
    }


    public Matrix4() {
        // TODO mit der Identitätsmatrix initialisieren
        for (int i = 0; i < this.werte.length; i++) {
            for (int j = 0; j < this.werte[i].length; j++) {
                if (i != j) {
                    this.werte[i][j] = 0;
                } else {
                    this.werte[i][j] = 1;
                }
            }
        }
    }

    public Matrix4(Matrix4 copy) {
        // TODO neues Objekt mit den Werten von "copy"
        for (int i = 0; i < this.werte.length; i++) {
            for (int j = 0; j < this.werte[i].length; j++) {
                this.werte[i][j] = copy.werte[i][j];
            }
        }
    }

    public Matrix4(float near, float far, float aspectRatio, float fov) {

        float tanHalfFov = (float) Math.tan(Math.toRadians(fov / 2.0f));
        float range = far - near;

        // TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzufügen
        this.werte[0][0] = (1.0f / (aspectRatio * tanHalfFov));//(2 * near) / (width);
        this.werte[0][1] = 0;
        this.werte[0][2] = 0;
        this.werte[0][3] = 0;
        this.werte[1][0] = 0;
        this.werte[1][1] = 1.0f / tanHalfFov;
        this.werte[1][2] = 0;
        this.werte[1][3] = 0;
        this.werte[2][0] = 0;
        this.werte[2][1] = 0;
        this.werte[2][2] = -((far + near) / range);
        this.werte[2][3] = -1;
        this.werte[3][0] = 0;
        this.werte[3][1] = 0;
        this.werte[3][2] = -((2 * near * far) / range);
        this.werte[3][3] = 0;
    }

    public Matrix4 multiply(Matrix4 other) {
        // TODO hier Matrizenmultiplikation "this = other * this" einfügen
        /*
         1st solution

        Matrix4 temp = new Matrix4(this);

        temp.werte[0][0] = other.werte[0][0] * this.werte[0][0] + other.werte[1][0] * this.werte[0][1] + other.werte[2][0] * this.werte[0][2] + other.werte[3][0] * this.werte[0][3];
        temp.werte[0][1] = other.werte[0][1] * this.werte[0][0] + other.werte[1][1] * this.werte[0][1] + other.werte[2][1] * this.werte[0][2] + other.werte[3][1] * this.werte[0][3];
        temp.werte[0][2] = other.werte[0][2] * this.werte[0][0] + other.werte[1][2] * this.werte[0][1] + other.werte[2][2] * this.werte[0][2] + other.werte[3][2] * this.werte[0][3];
        temp.werte[0][3] = other.werte[0][3] * this.werte[0][0] + other.werte[1][3] * this.werte[0][1] + other.werte[2][3] * this.werte[0][2] + other.werte[3][3] * this.werte[0][3];

        temp.werte[1][0] = other.werte[0][0] * this.werte[1][0] + other.werte[1][0] * this.werte[1][1] + other.werte[2][0] * this.werte[1][2] + other.werte[3][0] * this.werte[1][3];
        temp.werte[1][1] = other.werte[0][1] * this.werte[1][0] + other.werte[1][1] * this.werte[1][1] + other.werte[2][1] * this.werte[1][2] + other.werte[3][1] * this.werte[1][3];
        temp.werte[1][2] = other.werte[0][2] * this.werte[1][0] + other.werte[1][2] * this.werte[1][1] + other.werte[2][2] * this.werte[1][2] + other.werte[3][2] * this.werte[1][3];
        temp.werte[1][3] = other.werte[0][3] * this.werte[1][0] + other.werte[1][3] * this.werte[1][1] + other.werte[2][3] * this.werte[1][2] + other.werte[3][3] * this.werte[1][3];

        temp.werte[2][0] = other.werte[0][0] * this.werte[2][0] + other.werte[1][0] * this.werte[2][1] + other.werte[2][0] * this.werte[2][2] + other.werte[3][0] * this.werte[2][3];
        temp.werte[2][1] = other.werte[0][1] * this.werte[2][0] + other.werte[1][1] * this.werte[2][1] + other.werte[2][1] * this.werte[2][2] + other.werte[3][1] * this.werte[2][3];
        temp.werte[2][2] = other.werte[0][2] * this.werte[2][0] + other.werte[1][2] * this.werte[2][1] + other.werte[2][2] * this.werte[2][2] + other.werte[3][2] * this.werte[2][3];
        temp.werte[2][3] = other.werte[0][3] * this.werte[2][0] + other.werte[1][3] * this.werte[2][1] + other.werte[2][3] * this.werte[2][2] + other.werte[3][3] * this.werte[2][3];

        temp.werte[3][0] = other.werte[0][0] * this.werte[3][0] + other.werte[1][0] * this.werte[3][1] + other.werte[2][0] * this.werte[3][2] + other.werte[3][0] * this.werte[3][3];
        temp.werte[3][1] = other.werte[0][1] * this.werte[3][0] + other.werte[1][1] * this.werte[3][1] + other.werte[2][1] * this.werte[3][2] + other.werte[3][1] * this.werte[3][3];
        temp.werte[3][2] = other.werte[0][2] * this.werte[3][0] + other.werte[1][2] * this.werte[3][1] + other.werte[2][2] * this.werte[3][2] + other.werte[3][2] * this.werte[3][3];
        temp.werte[3][3] = other.werte[0][3] * this.werte[3][0] + other.werte[1][3] * this.werte[3][1] + other.werte[2][3] * this.werte[3][2] + other.werte[3][3] * this.werte[3][3];

        return temp;
    }
 */
        // optimised solution
        float[][] g = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        Matrix4 s = new Matrix4(g);

        for (int h = 0; h <= 3; h++) {
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    s.werte[h][i] += other.werte[j][i] * this.werte[h][j];
                }
            }
        }
        return s;
    }


    public Matrix4(float w00, float w01, float w02, float w03,
                   float w10, float w11, float w12, float w13,
                   float w20, float w21, float w22, float w23,
                   float w30, float w31, float w32, float w33) {
        this.werte[0][0] = w00;
        this.werte[0][1] = w01;
        this.werte[0][2] = w02;
        this.werte[0][3] = w03;
        this.werte[1][0] = w10;
        this.werte[1][1] = w11;
        this.werte[1][2] = w12;
        this.werte[1][3] = w13;
        this.werte[2][0] = w20;
        this.werte[2][1] = w21;
        this.werte[2][2] = w22;
        this.werte[2][3] = w23;
        this.werte[3][0] = w30;
        this.werte[3][1] = w31;
        this.werte[3][2] = w32;
        this.werte[3][3] = w33;
    }

    public Matrix4(Vec4f first, Vec4f second, Vec4f third, Vec4f fourth) {
        //erzeugt view Matrix
        this.werte[0][0] = first.getA();
        this.werte[0][1] = first.getR();
        this.werte[0][2] = first.getG();
        this.werte[0][3] = first.getB();
        this.werte[1][0] = second.getA();
        this.werte[1][1] = second.getR();
        this.werte[1][2] = second.getG();
        this.werte[1][3] = second.getB();
        this.werte[2][0] = third.getA();
        this.werte[2][1] = third.getR();
        this.werte[2][2] = third.getG();
        this.werte[2][3] = third.getB();
        this.werte[3][0] = fourth.getA();
        this.werte[3][1] = fourth.getR();
        this.werte[3][2] = fourth.getG();
        this.werte[3][3] = fourth.getB();
    }

    public Matrix4(float ex, float ey, float ez) {
        //erzeugt view Matrix
        this.werte[0][0] = 1;
        this.werte[0][1] = 0;
        this.werte[0][2] = 0;
        this.werte[0][3] = 0;
        this.werte[1][0] = 0;
        this.werte[1][1] = 1;
        this.werte[1][2] = 0;
        this.werte[1][3] = 0;
        this.werte[2][0] = 0;
        this.werte[2][1] = 0;
        this.werte[2][2] = 1;
        this.werte[2][3] = 0;
        this.werte[3][0] = ex;
        this.werte[3][1] = ey;
        this.werte[3][2] = ez;
        this.werte[3][3] = 1;
    }

    public Matrix4 translate(float x, float y, float z) {
        // TODO Verschiebung um x,y,z zu this hinzufügen
        float[][] translationMatrixData = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {x, y, z, 1}
        };
        Matrix4 move = new Matrix4(translationMatrixData);

        move = this.multiply(move);
        return move;
    }

    public Matrix4 scale(float uniformFactor) {
        // TODO gleichmäßige Skalierung um Faktor "uniformFactor" zu this hinzufügen
        float[][] scaleMatrixData = {
                {uniformFactor, 0, 0, 0},
                {0, uniformFactor, 0, 0},
                {0, 0, uniformFactor, 0},
                {0, 0, 0, 1}
        };
        Matrix4 move = new Matrix4(scaleMatrixData);

        move = this.multiply(move);
        return move;
    }

    public Matrix4 scale(float sx, float sy, float sz) {
        // TODO ungleichförmige Skalierung zu this hinzufügen
        float[][] scaleMatrixData = {
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        };
        Matrix4 move = new Matrix4(scaleMatrixData);

        move = this.multiply(move);
        return move;
    }

    public Matrix4 rotateX(float angle) {
        // TODO Rotation um X-Achse zu this hinzufügen
        float angleInRad = (float) (angle * Math.PI / 180.0F);
        float[][] rotateXData = {
                {1, 0, 0, 0},
                {0, (float) Math.cos(angleInRad), (float) -Math.sin(angleInRad), 0},
                {0, (float) Math.sin(angleInRad), (float) Math.cos(angleInRad), 0},
                {0, 0, 0, 1}
        };
        Matrix4 rotateX = new Matrix4(rotateXData);

        rotateX = this.multiply(rotateX);
        return rotateX;
    }

    public Matrix4 rotateY(float angle) {
        // TODO Rotation um Y-Achse zu this hinzufügen
        float angleInRad = (float) (angle * Math.PI / 180.0F);
        float[][] rotateYData = {
                {(float) Math.cos(angleInRad), 0, (float) -Math.sin(angleInRad), 0},
                {0, 1, 0, 0},
                {(float) Math.sin(angleInRad), 0, (float) Math.cos(angleInRad), 0},
                {0, 0, 0, 1}
        };
        Matrix4 rotateY = new Matrix4(rotateYData);

        rotateY = this.multiply(rotateY);
        return rotateY;
    }

    public Matrix4 rotateZ(float angle) {
        // TODO Rotation um Z-Achse zu this hinzufügen
        float angleInRad = (float) (angle * Math.PI / 180.0F);
        float[][] rotateZData = {
                {(float) Math.cos(angleInRad), (float) -Math.sin(angleInRad), 0, 0},
                {(float) Math.sin(angleInRad), (float) Math.cos(angleInRad), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix4 rotateZ = new Matrix4(rotateZData);

        rotateZ = this.multiply(rotateZ);
        return rotateZ;
    }


    public float[] getValuesAsArray() {
        // TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gefüllt) herausgeben
        // Siehe s.118 letzter Foliensatz
        float[] out = new float[16];
        out[0] = this.werte[0][0];
        out[1] = this.werte[0][1];
        out[2] = this.werte[0][2];
        out[3] = this.werte[0][3];

        out[4] = this.werte[1][0];
        out[5] = this.werte[1][1];
        out[6] = this.werte[1][2];
        out[7] = this.werte[1][3];

        out[8] = this.werte[2][0];
        out[9] = this.werte[2][1];
        out[10] = this.werte[2][2];
        out[11] = this.werte[2][3];

        out[12] = this.werte[3][0];
        out[13] = this.werte[3][1];
        out[14] = this.werte[3][2];
        out[15] = this.werte[3][3];
        return out;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.werte.length; i++) {
            for (int j = 0; j < this.werte[i].length; j++) {
                ret += " " + this.werte[i][j];
            }
        }
        return ret;
    }

    public void printMatrix() {
        for (int i = 0; i < this.werte.length; i++) {
            for (int j = 0; j < this.werte[i].length; j++) {
                System.out.print(this.werte[j][i] + "  ");
            }
            System.out.println();
        }
    }


    public Matrix4 initCameraTransform(Vec3f pos, Vec3f lookAt, Vec3f up) {
        Vec3f n = lookAt.normalize();
        Vec3f u = (up.cross(n)).normalize();
        Vec3f v = n.cross(u).normalize();
        Matrix4 ret = new Matrix4(u.getX(),v.getX(),n.getX(),0.0f,u.getY(),v.getY(),n.getY(),0.0f,u.getZ(),v.getZ(),n.getZ(),0.0f,-pos.getX(),-pos.getY(), -pos.getZ(),1.0f);
        return ret;
    }


    public static void main(String[] args) {
   /*     Matrix4 x = new Matrix4();
        System.out.println(x.toString());
        System.out.println();
        x.printMatrix();

        System.out.println();

        float[][] matA = {
                {0, 0, 10, 3},
                {1, 1, 4, 3},
                {2, 6, 3, 1},
                {0, 5, 1, 3}
        };

        Matrix4 kleinA = new Matrix4(matA);
        kleinA.printMatrix();
        System.out.println();
        kleinA.translate(3, 2, 0).printMatrix();

        System.out.println();
        System.out.println();

        x.multiply(kleinA).printMatrix();


        System.out.println("new try");
        float[][] translationMatrixData = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 2, 3, 1}
        };
        Matrix4 move = new Matrix4(translationMatrixData);

        move.printMatrix();
        s
    */


    }
}
