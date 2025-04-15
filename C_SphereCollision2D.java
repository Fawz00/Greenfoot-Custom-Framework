public class C_SphereCollision2D extends Component implements I_Collision2D {
    private Vector2D offset;
    private float radius;

    public C_SphereCollision2D() {
        this(new Vector2D());
    }
    public C_SphereCollision2D(float radius) {
        this(new Vector2D(), radius);
    }
    public C_SphereCollision2D(Vector2D offset) {
        this(offset, 1.0f);
    }
    public C_SphereCollision2D(Vector2D offset, float radius) {
        this.offset = offset;
        this.radius = radius;
    }

    public boolean detectCollision(C_SphereCollision2D other) {
        Vector2D pos1 = owner.getComponent(C_Transform2D.class).getTransform().location.add(offset);
        Vector2D pos2 = other.owner.getComponent(C_Transform2D.class).getTransform().location.add(other.offset);

        return pos1.sub(pos2).mag() < radius + other.radius || detectWorldCollision();
    }

    @Override
    public boolean detectWorldCollision(Vector2D location) {
        MainWorld world = owner.getCurrentWorld();
        if (world == null) {
            return false;
        }
    
        Vector2D pos = location.add(offset);
        
        // Tentukan ukuran blok dunia
        int gridStep = 1; // Ukuran grid dunia
        int minGridX = (int) Math.floor((pos.x - radius) / gridStep);
        int maxGridX = (int) Math.ceil((pos.x + radius) / gridStep);
        int minGridY = (int) Math.floor((pos.y - radius) / gridStep);
        int maxGridY = (int) Math.ceil((pos.y + radius) / gridStep);
    
        // Loop melalui setiap blok dalam bounding box
        for (int gridY = minGridY; gridY <= maxGridY; gridY++) {
            for (int gridX = minGridX; gridX <= maxGridX; gridX++) {
                // Ambil posisi tengah blok
                Vector2D blockPos = new Vector2D(gridX * gridStep, gridY * gridStep);
    
                // Periksa apakah ada tabrakan dengan masing-masing sisi blok
                if (isCircleCollidingWithBlock(pos, blockPos, radius, gridStep)) {
                    S_TarrainBlock currentBlock = world.getBlockAt(new Vector2D(gridX, gridY));
                    if (currentBlock != null && currentBlock.solid) {
                        return true; // Jika ada blok solid, berarti terjadi tabrakan
                    }
                }
            }
        }
    
        return false;
    }
    
    // Fungsi untuk memeriksa apakah lingkaran tabrakan dengan blok
    private boolean isCircleCollidingWithBlock(Vector2D circlePos, Vector2D blockPos, float radius, int gridStep) {
        // Temukan batas blok
        float blockLeft = (float)blockPos.x;
        float blockRight = (float)blockPos.x + gridStep;
        float blockTop = (float)blockPos.y;
        float blockBottom = (float)blockPos.y + gridStep;
    
        // Tentukan jarak terdekat dari titik tengah lingkaran ke sisi blok
        float nearestX = (float)Math.max(blockLeft, Math.min(circlePos.x, blockRight));
        float nearestY = (float)Math.max(blockTop, Math.min(circlePos.y, blockBottom));
    
        // Hitung jarak antara titik terdekat dan pusat lingkaran
        float distanceX = (float)circlePos.x - nearestX;
        float distanceY = (float)circlePos.y - nearestY;
    
        // Periksa apakah jarak lebih kecil dari radius lingkaran
        return (distanceX * distanceX + distanceY * distanceY) < (radius * radius);
    }

    @Override
    public boolean detectWorldCollision() {
        return detectWorldCollision(owner.getComponent(C_Transform2D.class).getTransform().location);
    }
    
}
