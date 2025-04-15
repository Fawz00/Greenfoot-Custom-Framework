public class Vector2D {
    public double x;
    public double y;

    public Vector2D() {
        this(0, 0);
    }
    public Vector2D(double s) {
        this(s, s);
    }
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static Vector2D up() {
        return new Vector2D(0, 1);
    }
    public static Vector2D down() {
        return new Vector2D(0, -1);
    }
    public static Vector2D left() {
        return new Vector2D(-1, 0);
    }
    public static Vector2D right() {
        return new Vector2D(1, 0);
    }

    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public Vector2D set(Vector2D v) {
        x = v.x;
        y = v.y;
        return this;
    }
    public Vector2D setX(double x) {
        this.x = x;
        return this;
    }
    public Vector2D setY(double y) {
        this.y = y;
        return this;
    }
    public Vector2D zero() {
        x = 0;
        y = 0;
        return this;
    }
    public Vector2D add(double s) {
        return new Vector2D(x + s, y + s);
    }
    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }
    public Vector2D sub(Vector2D v) {
        return new Vector2D(x - v.x, y - v.y);
    }
    public Vector2D mul(double s) {
        return new Vector2D(x * s, y * s);
    }
    public Vector2D mul(Vector2D v) {
        return new Vector2D(x * v.x, y * v.y);
    }
    public Vector2D negate() {
        return new Vector2D(-x, -y);
    }
    public Vector2D div(Vector2D v) {
        return new Vector2D(x / v.x, y / v.y);
    }
    public Vector2D div(double s) {
        return new Vector2D(x / s, y / s);
    }
    public double dot(Vector2D v) {
        return x * v.x + y * v.y;
    }
    public double cross(Vector2D v) {
        return x * v.y - y * v.x;
    }
    public double mag() {
        return Math.sqrt(x * x + y * y);
    }
    public double length() {
        return mag();
    }
    public Vector2D normalize() {
        double m = mag();
        if (m == 0) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(x / m, y / m);
    }
    public double angle() {
        return Math.atan2(y, x);
    }
    public double angle(Vector2D v) {
        return Math.acos(dot(v) / (mag() * v.mag()));
    }
    public Vector2D rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector2D(x * cos - y * sin, x * sin + y * cos);
    }
    public Vector2D project(Vector2D v) {
        return v.mul(dot(v) / v.dot(v));
    }
    public Vector2D reflect(Vector2D normal) {
        return normal.mul(2 * dot(normal)).sub(this);
    }
    public Vector2D lerp(Vector2D v, double t) {
        return v.sub(this).mul(t).add(this);
    }
    public Vector2D slerp(Vector2D v, double t) {
        double angle = Math.acos(dot(v) / (mag() * v.mag()));
        return rotate(angle * t);
    }
    public Vector2D nlerp(Vector2D v, double t) {
        return lerp(v, t).normalize();
    }
    public Vector2D moveTowards(Vector2D v, double maxDistanceDelta) {
        Vector2D a = v.sub(this);
        double m = a.mag();
        if(m <= maxDistanceDelta || m == 0) {
            return v;
        }
        return add(a.div(m).mul(maxDistanceDelta));
    }
    public Vector2D clampMagnitude(double maxLength) {
        double m = mag();
        if(m > maxLength) {
            return normalize().mul(maxLength);
        }
        return this;
    }
    public double distance(Vector2D v) {
        return sub(v).mag();
    }
    public double sqrDistance(Vector2D v) {
        return sub(v).dot(sub(v));
    }
    public double sqrDistance() {
        return x*x + y*y;
    }
    public Vector2D min(Vector2D v) {
        return new Vector2D(Math.min(x, v.x), Math.min(y, v.y));
    }
    public Vector2D max(Vector2D v) {
        return new Vector2D(Math.max(x, v.x), Math.max(y, v.y));
    }
    public Vector2D abs() {
        return new Vector2D(Math.abs(x), Math.abs(y));
    }
    public Vector2D floor() {
        return new Vector2D(Math.floor(x), Math.floor(y));
    }
    public Vector2D ceil() {
        return new Vector2D(Math.ceil(x), Math.ceil(y));
    }
    public Vector2D round() {
        return new Vector2D(Math.round(x), Math.round(y));
    }
    public Vector2D sign() {
        return new Vector2D(Math.signum(x), Math.signum(y));
    }
    public Vector2D fract() {
        return new Vector2D(x - Math.floor(x), y - Math.floor(y));
    }
    public Vector2D mod(double s) {
        return new Vector2D(x % s, y % s);
    }
    public Vector2D mod(Vector2D v) {
        return new Vector2D(x % v.x, y % v.y);
    }
    public Vector2D random() {
        return new Vector2D(Math.random(), Math.random());
    }
    public Vector2D random(Vector2D v) {
        return new Vector2D(Math.random() * v.x, Math.random() * v.y);
    }
    public Vector2D random(Vector2D min, Vector2D max) {
        return new Vector2D(Math.random() * (max.x - min.x) + min.x, Math.random() * (max.y - min.y) + min.y);
    }
}
