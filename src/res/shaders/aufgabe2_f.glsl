#version 330
in vec3 color;
out vec3 pixelFarbe;//Name beliebig
uniform float time;

vec3 red = vec3(1, 0, 0);
vec3 green = vec3(0, 1, 0);
vec3 blue = vec3(0, 0, 1);
vec3 changing = vec3(((sin(time*0.1)+1)/2),cos(time*0.5),((tan(time*0.15)+1)/2));

void main() {
    pixelFarbe = color;
}