package core;

import world.Tile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        handleMouseClick(e.getX(), e.getY());
    }

    private void handleMouseClick(int screenX, int screenY) {

        int worldMouseX = Game.player.x - Game.player.sx + screenX;
        int worldMouseY = Game.player.y - Game.player.sy + screenY;

        int tileCol = worldMouseX / Configs.TILE_SIZE;
        int tileRow = worldMouseY / Configs.TILE_SIZE;

        System.out.println("Screen Click: (" + screenX + ", " + screenY + ")");
        System.out.println("World Coords: (" + worldMouseX + ", " + worldMouseY + ")");
        System.out.println("Clicked Tile Coords (Col, Row): (" + tileCol + ", " + tileRow + ")");

        if (tileCol >= 0 && tileCol < Game.mapConstructor.getMapColumns() &&
                tileRow >= 0 && tileRow < Game.mapConstructor.getMapRows()) {

            Tile clickedTile = Game.mapConstructor.getTile(tileCol, tileRow);
            if (clickedTile != null) {
                System.out.println("Clicked on Tile: " + clickedTile.name + " (Solid: " + clickedTile.isSolid + ")");

            } else {
                System.out.println("Clicked on a null tile (shouldn't happen if within bounds and map is initialized).");
            }
        } else {
            System.out.println("Clicked outside map bounds.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
