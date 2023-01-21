#version 330
out vec3 pixelFarbe;//Name beliebig
uniform float time;
vec3 red = vec3(1, 0, 0);
vec3 green = vec3(0, 1, 0);
vec3 blue = vec3(0, 0, 1);
vec3 changing = vec3(((sin(time*0.1)+1)/2),cos(time*0.5),((tan(time*0.15)+1)/2));

struct circle2D {
    vec2 Midpoint;
    float radius;
    vec3 color;
};

circle2D c1 = circle2D(vec2(650.0, 860.0), 225.5, vec3(0.34, 0.52, 0.15));
circle2D c2 = circle2D(vec2(850.0, 260.0), 205.5, vec3(0.54, 0.35, 0.84));
circle2D c3 = circle2D(vec2(550.0, 360.0), 125.5, vec3(0.42, 0.71, 0.39));

mat2 rotate(float angle);
bool isInCIrcle(circle2D);
//bool isOnLine(line2D testLine);

float x = gl_FragCoord.x;
float y = gl_FragCoord.y;
vec2 currentPos1 = gl_FragCoord.xy;
vec3 color;
vec2[3] edges = vec2[3](vec2(180.0, 430.5), vec2(769, 475), vec2(956, 648));


struct triangle2D {
    vec2[3] edges;
    vec3 color;
};

struct rect2D{
    mat4x2 rect;
    vec3 color;
};

struct line2D {
    vec2 start;
    float length;
    vec2 direction;
    float thickness;
};



/*bool isOnLine(line2D testLine);
{
if(currentPos1 = testLine.start - currentPos1 )
}
*/

rect2D rect1= rect2D(mat4x2(100.0, 100.0, 400.0, 100.0, 100.0, 640.0, 400.0, 640.0), changing);



mat2 rotate(float angle) {
    return mat2(
    cos(angle), -sin(angle),
    sin(angle), cos(angle)
    );
}

bool isInCIrcle(circle2D c1)
{
    if (distance(c1.Midpoint, currentPos1) <= c1.radius) {
        return true;
    }
    else {
        return false;
    }
}

bool isInRect(rect2D rect1);
bool isInRect(rect2D rect1) {

    if (
    ((currentPos1.x < min(min(rect1.rect[0], rect1.rect[1]), min(rect1.rect[2], rect1.rect[3])).x) ||
    currentPos1.x > max(max(rect1.rect[0], rect1.rect[1]), max(rect1.rect[2], rect1.rect[3])).x
    )||(
    (currentPos1.y < min(min(rect1.rect[0], rect1.rect[1]), min(rect1.rect[2], rect1.rect[3])).y) ||
    currentPos1.y > max(max(rect1.rect[0], rect1.rect[1]), max(rect1.rect[2], rect1.rect[3])).y
    )
    )
    { return false; }
    else return true;
}


void main() {

    vec2 p1 = vec2(0.0, 523.0);
    vec2 p2 = vec2(407.0, 0.0);
    vec2 p3 = vec2(940.0, 30.0);
    vec2 p4 = vec2(0.0, 0.0);

    bool b1 = isInCIrcle(c1);
    bool b2 = isInCIrcle(c2);
    bool b3 = isInCIrcle(c3);


    currentPos1 = rotate(-0.5)* currentPos1;
    bool b4 = isInRect(rect1);
    if (b1) { pixelFarbe = c1.color; }
    else if (b4) { pixelFarbe = rect1.color; }
    else if (b2) { pixelFarbe = c2.color; }
    else if (b3) { pixelFarbe = c3.color; }
    else if (x>p2.x || y > p1.y) { pixelFarbe = vec3(0.0, 0.0, 1.0); }
    else {
        pixelFarbe = vec3(1.0, 1.0, 0.0);
    }


}