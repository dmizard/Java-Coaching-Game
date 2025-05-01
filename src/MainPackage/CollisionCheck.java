package MainPackage;

import EntityPackage.Entity;

/*Проверка коллизии клеток персонажа на
 * клетки матрицы карты, где ему нельзя ходить.*/
public class CollisionCheck {

    GamePanel gp;

    public CollisionCheck(GamePanel gp){

        this.gp = gp;

    }

    public void checkTile(Entity entity){
        /*Вычисление рамок для коллизии между клетками
         * хитбокса игрока и стенами (клетками матрицы).*/
        int entityLeftCollisionX = entity.x + entity.solidArea.x;
        int entityRightCollisionX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityUpCollisionY = entity.y + entity.solidArea.y;
        int entityDownCollisionY = entity.y + entity.solidArea.y + entity.solidArea.height;
        //*Вычисление, где он сталкивается.
        int entityLeftCol = entityLeftCollisionX / gp.tileSize;
        int entityRightCol = entityRightCollisionX / gp.tileSize;
        int entityUpRow = entityUpCollisionY / gp.tileSize;
        int entityDownRow = entityDownCollisionY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){

            case "up":
                entityUpRow = (entityUpCollisionY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityUpRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityUpRow];
                //Работа проверки самих плиток перед игроком по поводу коллизии
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
                break;
            case "down":
                entityDownRow = (entityDownCollisionY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityDownRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityDownRow];
                //Работа проверки самих плиток перед игроком по поводу коллизии
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
                break;
            case "left":
                entityLeftCol = (entityLeftCollisionX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityUpRow];
                tileNum2 = gp.tileM.mapTileNumber[entityLeftCol][entityDownRow];
                //Работа проверки самих плиток перед игроком по поводу коллизии
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
                break;
            case "right":
                entityRightCol = (entityRightCollisionX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityRightCol][entityUpRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityDownRow];
                //Работа проверки самих плиток перед игроком по поводу коллизии
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
                break;

        }

    }

}
