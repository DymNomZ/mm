package core;

import java.awt.*;
import java.util.ArrayList;

public class MapConstructor {

    MapLoader mapLoader;

    ArrayList<Tile> tileData;
    Tile tiles[][];
    int mapIndexes[][];
    int mapRows, mapColumns;
    public static int mapHeight, mapWidth;

    public MapConstructor(String path){

        mapLoader = new MapLoader();
        mapLoader.loadMapData(path);

        tileData = mapLoader.getTiles();

        mapIndexes = mapLoader.getMapIndexes();
        mapRows = mapIndexes.length;
        mapColumns = mapIndexes[0].length;

        mapHeight = mapRows * Configs.TILE_SIZE;
        mapWidth = mapColumns * Configs.TILE_SIZE;

        loadMapTiles();
    }

    public void loadMapTiles(){

        tiles = new Tile[mapRows][mapColumns];

        for(int i = 0; i < mapRows; i++){
            for(int j = 0; j < mapColumns; j++){

                //check which tile in tile data matches index
                if(mapIndexes[i][j] != 0){
                    for(Tile t : tileData){
                        if(t.index == mapIndexes[i][j]){
                            tiles[i][j] = t;
                            break;
                        }
                    }
                }else{

                }

            }
        }
    }

    public void renderMap(Graphics2D g2){

        int wx, wy, sx, sy;
        int ts = Configs.TILE_SIZE;
        int px = Game.player.x;
        int py = Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        for(int i = 0; i < mapRows; i++){
            for(int j = 0; j < mapColumns; j++){

                wx = i * Configs.TILE_SIZE;
                wy = j * Configs.TILE_SIZE;
                sx = wx - px + psx;
                sy = wy - py + psy;

                if(
                    wx + ts > px - psx && wx - ts < px + psx &&
                    wy + ts > py - psy && wy - ts < py + psy
                ){
                    g2.drawImage(
                            tiles[j][i].image, sx, sy,
                            Configs.TILE_SIZE, Configs.TILE_SIZE,
                            null
                    );
                }

            }
        }
    }

    public boolean isColliding(Rectangle worldHitbox) {

        int ts = Configs.TILE_SIZE;

        // Get the range of tiles the hitbox could potentially overlap with
        // Convert hitbox world coordinates to tile grid coordinates
        int leftTileCol = worldHitbox.x / ts;
        int rightTileCol = (worldHitbox.x + worldHitbox.width -1) / ts; // -1 to handle edges correctly
        int topTileRow = worldHitbox.y / ts;
        int bottomTileRow = (worldHitbox.y + worldHitbox.height -1) / ts; // -1 to handle edges correctly

        for (int row = topTileRow; row <= bottomTileRow; row++) {
            for (int col = leftTileCol; col <= rightTileCol; col++) {
                // Boundary checks for the map
                if (col >= 0 && col < mapColumns && row >= 0 && row < mapRows) {
                    Tile tile = tiles[row][col]; // Assuming tiles[row][col] is the standard access
                    // Adjust if your array is tiles[col][row]
                    if (tile != null && tile.isSolid) {
                        // Create a Rectangle for the solid tile in world coordinates
                        Rectangle tileRect = new Rectangle(col * ts, row * ts, ts, ts);
                        if (worldHitbox.intersects(tileRect)) {
                            // System.out.println("Collision with solid tile at: " + col + "," + row);
                            return true; // Collision detected
                        }
                    }
                } else {
                    // Optional: Treat out-of-bounds as solid if player shouldn't leave map
                    // System.out.println("Collision with map boundary");
                    // return true;
                }
            }
        }
        return false; // No collision
    }

}
