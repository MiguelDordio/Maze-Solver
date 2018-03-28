import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.util.ArrayList;

public class Maze_Solver extends JFrame{

    private static char[][] maze;
    private static ArrayList<String> mazeBuffer;

    public Maze_Solver(){
        setTitle("Maze");
        setSize(640,480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        input();
    }

    public static void input(){
        Scanner input=new Scanner(System.in);
        String menuOption;
        do {
            System.out.println("Chose an option:");
            System.out.println("1. Direct input using the console.");
            System.out.println("2. Adding a maze throught .txt file.");
            System.out.println("0. Exit.");
            menuOption=input.nextLine();

            if(menuOption.equals("1")) {
                console_input();
            }
            else if(menuOption.equals("2")) {
                file_input();
            } else if(menuOption.equals("0")) {
                leave();
            }

        } while(menuOption.equals("0"));

        if (searchPath(0, 0)){
            Maze_Solver view = new Maze_Solver();
            view.setVisible(true);
        }else{
            System.out.println("No exit\n");
        }
    }

    private static void console_input(){
        Scanner in = new Scanner(System.in);
        fill_maze(in);
    }

    private static void file_input(){
        System.out.println("Insert a file name: (ex: maze.txt)");
        Scanner input = new Scanner(System.in);
        String filename = input.nextLine();
        try {
            Scanner file=new Scanner(new File(filename));
            fill_maze(file);
        }catch(Exception e) {
            System.out.println(filename + "has a problem");
        }
    }

    private static void fill_maze(Scanner in){
        int numcols = 0;
        mazeBuffer=new ArrayList<>();
        while(in.hasNext()) {
            String nextLine=in.nextLine();
            mazeBuffer.add(nextLine);
            if(nextLine.length()>numcols) {
                numcols=nextLine.length();
            }
        }
        int numrows=mazeBuffer.size();
        maze = new char[numrows][numcols];
        for(int i=0;i<numrows;i++) {
            String row = mazeBuffer.get(i);
            for (int j = 0; j < numcols; j++) {
                if (row.length() >= j)
                    maze[i][j] = row.charAt(j);
            }
        }
    }

    private static void leave(){
        System.exit(0);
    }

    public static boolean searchPath(int x, int y) {
        maze[y][x] = 'o';
        if(x == maze[0].length-1 && y == maze.length-1)
            return true;
        if (y >= 0 && y < maze.length && x-1 >= 0 && x-1 < maze[y].length && maze[y][x - 1] == '.' && searchPath(x - 1, y))
            return true;
        if (y >= 0 && y < maze.length && x+1 >= 0 && x+1 < maze[y].length && maze[y][x + 1] == '.' && searchPath(x + 1, y))
            return true;
        if (y-1 >= 0 && y-1 < maze.length && x >= 0 && x < maze[y-1].length && maze[y - 1][x] == '.' && searchPath(x, y - 1))
            return true;
        if (y+1 >= 0 && y+1 < maze.length && x >= 0 && x < maze[y+1].length && maze[y + 1][x] == '.' && searchPath(x, y + 1))
            return true;
        maze[y][x] = '.';
        return false;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.translate(230, 180);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 'o':
                        color = Color.GREEN;
                        break;
                    case '.':
                        color = Color.WHITE;
                        break;
                    default:
                        color = Color.BLACK;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);

            }
        }
    }
}
