package core;

import world.Tile;

import java.awt.*;
import java.util.ArrayList;

public class MapConstructor {

    MapLoader mapLoader;

    ArrayList<Tile> tileData;
    Tile tiles[][];
    int mapIndexes[][];
    int mapRows, mapColumns;
    public static int mapHeight, mapWidth;

    private int ts;

    public MapConstructor(String path){

        mapLoader = new MapLoader();
        mapLoader.loadMapData(path);

        tileData = mapLoader.getTiles();

        mapIndexes = mapLoader.getMapIndexes();
        mapRows = mapIndexes.length;
        mapColumns = mapIndexes[0].length;

        ts = Configs.TILE_SIZE;

        mapHeight = mapRows * ts;
        mapWidth = mapColumns * ts;

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

    public int getMapColumns() { return mapColumns; }

    public int getMapRows() { return mapRows; }

    public Tile getTile(int col, int row) {
        return tiles[row][col];
    }

    public void renderMap(Graphics2D g2){

        int wx, wy, sx, sy;
        int px = (int)Game.player.x;
        int py = (int)Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        for(int i = 0; i < mapColumns; i++){
            for(int j = 0; j < mapRows; j++){

                wx = i * ts;
                wy = j * ts;
                sx = wx - px + psx;
                sy = wy - py + psy;

                if(
                    wx + ts > px - psx && wx - ts < px + psx &&
                    wy + ts > py - psy && wy - ts < py + psy
                ){
                    g2.drawImage(
                            tiles[j][i].image, sx, sy,
                            ts, ts, null
                    );
                }

            }
        }
    }

    private void updateTile(int row, int col){

        //other implementations here

        //doors
        if(Game.keyHandler.fPressed) tiles[row][col] = Tools.openDoor;
    }

    public boolean isColliding(Rectangle rectangle) {

        // Get the range of tiles the hitbox could potentially overlap with
        // Convert hitbox world coordinates to tile grid coordinates
        int leftTileCol = rectangle.x / ts;
        int rightTileCol = (rectangle.x + rectangle.width -1) / ts; // -1 to handle edges correctly
        int topTileRow = rectangle.y / ts;
        int bottomTileRow = (rectangle.y + rectangle.height -1) / ts; // -1 to handle edges correctly

        for (int row = topTileRow; row <= bottomTileRow; row++) {
            for (int col = leftTileCol; col <= rightTileCol; col++) {
                // Boundary checks for the map
                if (col >= 0 && col < mapColumns && row >= 0 && row < mapRows) {
                    Tile tile = tiles[row][col];
                    if (tile != null && tile.isSolid) {
                        Rectangle tileRect = new Rectangle(col * ts, row * ts, ts, ts);
                        if (rectangle.intersects(tileRect)) {
                            // System.out.println("Collision with solid tile at: " + col + "," + row);

                            //Tiles that depend on collision (ie. doors)
                            updateTile(row, col);

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
