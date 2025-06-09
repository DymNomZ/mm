package core;

import world.Tile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent e) {
//        handleMouseClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        handleMouseClick(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Game.mousePosition.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Game.mousePosition.setLocation(e.getX(), e.getY());
    }

    private void handleMouseClick(int screenX, int screenY) {

        int px = (int) Game.player.x;
        int py = (int) Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        int mx = px - psx + screenX;
        int my = py - psy + screenY;

        int ts = Configs.TILE_SIZE;
        int tileCol = mx / ts;
        int tileRow = my / ts;

//        System.out.println("Screen Click: (" + screenX + ", " + screenY + ")");
//        System.out.println("World Coords: (" + mx + ", " + my + ") -- Calculated using player world (int): (" + px + ", " + py + ")");
//        System.out.println("Clicked Tile Coords (Col, Row): (" + tileCol + ", " + tileRow + ")");

        //call player plant seed function
        Game.player.plantSeed(mx, my);

        if (tileCol >= 0 && tileCol < Game.mapConstructor.getMapColumns() &&
                tileRow >= 0 && tileRow < Game.mapConstructor.getMapRows()) {

            Tile clickedTile = Game.mapConstructor.getTile(tileCol, tileRow);
            if (clickedTile != null) {
//                System.out.println("Clicked on Tile: " + clickedTile.name + " (Solid: " + clickedTile.isSolid + ")");

            } else {
                System.out.println("Clicked on a null tile (shouldn't happen if within bounds and map is initialized).");
            }
        } else {
            System.out.println("Clicked outside map bounds.");
        }
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
