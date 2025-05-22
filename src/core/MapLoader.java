package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MapLoader {

    ArrayList<Tile> loadedTiles = new ArrayList<>();
    ArrayList<ZipEntry> pngEntries = new ArrayList<>();
    int[][] loadedMapIndexes, tileDataIndexes;

    public ArrayList<Tile> getTiles(){
        return loadedTiles;
    }

    public int[][] getMapIndexes(){
        return loadedMapIndexes;
    }

    public void loadMapData(String path){

        ZipFile zipFile;
        try {

            zipFile = new ZipFile(path);

            // Get an enumeration of the entries in the zip file
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            // Iterate over the entries
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                //read operations:
                if(entry.getName().endsWith("data.txt")){
                    readTileData(zipFile, entry);
                }
                if(entry.getName().endsWith("$.txt")){
                    readMap(zipFile, entry);
                }
            }

            //Store the pngs in an ArrayList first
            entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if(entry.getName().endsWith(".png")){
                    pngEntries.add(entry);
                }
            }

            //Iterate until png matches index
            for(int i = 0; i < tileDataIndexes.length; i++){
                for(ZipEntry z : pngEntries){
                    //extract index at the beginning of the png name
                    String imageIndex = z.getName().substring(0, z.getName().lastIndexOf('$'));

                    //we do this to prevent duplicates done by the creator
                    //if an index is used by multiple tiles, only the first tile that gets read keeps the index
                    //the rest are ignored
                    if(Integer.parseInt(imageIndex) == tileDataIndexes[i][0]){
                        loadedTiles.add(readImage(z, tileDataIndexes, i));
                    }
                }
            }

            //Check print
            //System.out.println(loaded_tiles.size());
            for(Tile t : loadedTiles){
                 System.out.println(
                     t.name + " " + t.index + " "
                     + t.isSolid + " " + t.isAnimated);
            }

            zipFile.close();

        } catch (IOException ex) {
            System.out.println(1);
        }
    }

    public Tile readImage(ZipEntry image, int[][] tileDataIndexes, int currIdx) throws IOException {

        BufferedImage tileImage;
        String tileName = image.getName().substring(image.getName().lastIndexOf("$")+1);

        tileImage = ImageIO.read(getClass().getResourceAsStream("/textures/"+ tileName));

        int tileIndex = tileDataIndexes[currIdx][0];
        boolean solidState = tileDataIndexes[currIdx][1] == 1;
        boolean animatedState = tileDataIndexes[currIdx][2] == 1;

        return new Tile(tileImage, tileName, tileIndex, solidState, animatedState);
    }

    public void readTileData(ZipFile zip, ZipEntry tileData){

        InputStream tileDataStream;
        BufferedReader reader;
        try {
            tileDataStream = zip.getInputStream(tileData);
            reader = new BufferedReader(new InputStreamReader(tileDataStream));

            String line = reader.readLine();
            int tdH = 0;
            int tdL = line.length() / 2;

            do{
                tdH++;
            }while ((reader.readLine()) != null);

            reader.close();

            tileDataStream = zip.getInputStream(tileData);
            reader = new BufferedReader(new InputStreamReader(tileDataStream));

            tileDataIndexes = new int[tdH][tdL];

            for(int i = 0; i < tdH; i++){

                String[] rawIndexes = reader.readLine().split(" ");

                for(int j = 0; j < tdL; j++) {
                    tileDataIndexes[i][j] = Integer.parseInt(rawIndexes[j]);
                }

            }

            reader.close();
            tileDataStream.close();

        } catch (IOException ex) {
        }
    }

    public void readMap(ZipFile zip, ZipEntry map){

        InputStream mapDataStream;
        BufferedReader reader;
        try {
            mapDataStream = zip.getInputStream(map);
            reader = new BufferedReader(new InputStreamReader(mapDataStream));

            int mapH = 0;
            int mapL = reader.readLine().split(" ").length; //because of spaces

            do{
                mapH++;
            }while ((reader.readLine()) != null);

            reader.close();

            mapDataStream = zip.getInputStream(map);
            reader = new BufferedReader(new InputStreamReader(mapDataStream));

            loadedMapIndexes = new int[mapH][mapL];

            for(int i = 0; i < mapH; i++){

                String[] rawIndexes = reader.readLine().split(" ");

                for(int j = 0; j < mapL; j++) {
                    loadedMapIndexes[i][j] = Integer.parseInt(rawIndexes[j]);
                }

            }

            reader.close();
            mapDataStream.close();

        } catch (IOException ex) {
        }

    }
}
