package core;

import java.awt.*;
import java.util.ArrayList;

public class MapConstructor {

    MapLoader mapLoader;

    ArrayList<Tile> tileData;
    Tile tiles[][];
    int mapIndexes[][];
    int mapHeight, mapLength;

    public MapConstructor(String path){

        mapLoader = new MapLoader();
        mapLoader.loadMapData(path);

        tileData = mapLoader.getTiles();

        mapIndexes = mapLoader.getMapIndexes();
        mapHeight = mapIndexes.length;
        mapLength = mapIndexes[0].length;

        loadMapTiles();
    }

    public int[][] getMapIndexes(){
        return mapIndexes;
    }

    public int getMapHeight(){
        return mapHeight;
    }

    public int getMapLength(){
        return mapLength;
    }

    public void loadMapTiles(){

        tiles = new Tile[mapHeight][mapLength];

        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapLength; j++){

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

        for(int i = 0; i < mapLength; i++){
            for(int j = 0; j < mapHeight; j++){
                g2.drawImage(
                        tiles[i][j].image,
                        i * Configs.TILE_SIZE,
                        j * Configs.TILE_SIZE,
                        Configs.TILE_SIZE, Configs.TILE_SIZE,
                        null
                );
            }
        }
    }

}
