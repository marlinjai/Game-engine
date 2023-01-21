#version 330
#define M_PI 3.1415926535897932384626433832795
layout(location=0) in vec2 eckenAusJava;
layout(location=1) in vec3 colour;
out vec3 color ;
vec2 eckenAusJavaNeu;
void main() {
    float rotAngle = 1*M_PI;
    mat2 roation = mat2(cos(rotAngle), sin(rotAngle),
    -sin(rotAngle), cos(rotAngle)
    );
    eckenAusJavaNeu = roation*eckenAusJava;
    color = colour;
    gl_Position =vec4(eckenAusJavaNeu, 0.0,1.0);

}