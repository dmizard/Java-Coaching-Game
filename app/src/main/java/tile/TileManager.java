package tile;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[15]; //15 - Количество плиток плюс 1 (т.е. у нас 14 плиток)
        //Считывание матрицы карты
        mapTileNumber = new int[gp.maxScreenColumn][gp.maxScreenRow];

        getTileImage();
            loadMap("src\\main\\resources\\maps\\kabinetjava.txt");

    }

    public void getTileImage(){

        try{
            //Загрузка плиток из папки res
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_default.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_up.png"));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_down.png"));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_left.png"));
            tile[3].collision = true;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_right.png"));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_upleft.png"));
            tile[5].collision = true;
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_upright.png"));
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_downleft.png"));
            tile[7].collision = true;
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_downright.png"));
            tile[8].collision = true;
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_door_open.png"));
            tile[9].collision = true;
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_brazhkin.png"));
            tile[10].collision = true;
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_computerplace_away.png"));
            tile[11].collision = true;
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_computerplace_empty.png"));
            tile[12].collision = true;
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_computerplace_table.png"));
            tile[13].collision = true;
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/deftiles/deftiles_trench.png"));
            tile[14].collision = true;

        }catch (IOException e){

            e.printStackTrace();

        }

    }

    public void loadMap(String filePath) {
        try (InputStream is = getClass().getResourceAsStream("/maps/kabinetjava.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            for (int row = 0; row < gp.maxScreenRow; row++) {
                String line = br.readLine();
                if (line == null) break;

                String[] numbers = line.trim().split("\\s+");
                for (int col = 0; col < Math.min(numbers.length, gp.maxScreenColumn); col++) {
                    mapTileNumber[col][row] = Integer.parseInt(numbers[col]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Отрисовка плиток
    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenColumn && row < gp.maxScreenRow) {

            int tileNum = mapTileNumber[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenColumn){

                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;

            }

        }

    }

}
