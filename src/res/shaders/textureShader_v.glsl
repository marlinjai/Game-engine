#version 330
#define M_PI 3.1415926535897932384626433832795
layout(location=0) in vec3 eckenAusJava;
layout(location=1) in vec4 colour;
layout(location=2) in vec3 normal;
layout(location=3) in vec2 uvs;

uniform mat4 modelMatrix;
uniform mat4 matProjection;
uniform mat4 viewMatrix;
out vec4 color;
out vec3 norm;
out vec3 aktuellePositionP;
out vec2 UVs;



vec4 eckenAusJavaNeu;
void main() {

    UVs = uvs;
    vec4 vertexdata = vec4(eckenAusJava, 1);
    mat3 normalMatrix = transpose(inverse(mat3 (modelMatrix)));

    norm = normalMatrix*normal;
    gl_Position = matProjection*viewMatrix*modelMatrix*vertexdata;
    aktuellePositionP = vec3(modelMatrix*vertexdata);
    color=colour;
}