package world;

import core.SpriteLoader;

public class Door extends Tile{

    public Door(){
        super(
            SpriteLoader.DEBUG_DOOR, "door_open",
            99, false, false
        );
    }
}
