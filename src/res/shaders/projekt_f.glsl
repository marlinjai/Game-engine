#version 330
#define M_PI 3.1415926535897932384626433832795
in vec4 color;
in vec3 norm;
in vec3 aktuellePositionP;
in vec2 UVs;
out vec4 pixelFarbe;//Name beliebig
uniform float angle;
uniform vec3 cameraPosition;
uniform sampler2D sampler;



vec3 lichtPosition = vec3 (10,0,10);

vec3 normale = normalize(norm);
vec3 L1 = normalize(lichtPosition - aktuellePositionP);
vec3 R1 = reflect(-L1,normale);
vec3 V = normalize(cameraPosition-aktuellePositionP);



vec3 changing = vec3(sin(angle),cos(angle),((tan(angle))));
float n = 20; //Oberflächenhärte
float kd = 0.2; //materialabhängiger Faktor difuses Licht
float ka = 1.2; //materialabhängiger Faktor ambientes Licht
float ks = 0.8; //materialabhängiger Faktor spiegelndes Licht

float ambientLight =1*ka;
float difuseLight = (max(0,dot(L1,normale)))*kd;
float specularLight = pow(max(0,dot(R1,V)),n)*ks;

float a = color.w;

vec3 rgbColor = color.rgb;

void main() {

    vec4 textel = texture(sampler, UVs);

    float gesammtLichtIntensitaet = (ambientLight+ difuseLight + specularLight);
//    if (textel.r == 0) {pixelFarbe = vec4 (rgbColor*gesammtLichtIntensitaet,a);}
    pixelFarbe = vec4 ((textel.rgb*rgbColor*gesammtLichtIntensitaet),a);

}