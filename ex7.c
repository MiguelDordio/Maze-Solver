#include <stdbool.h>
#include <stdio.h>

int row, column;

void print_maze(char maze[row][column], int row, int column) {
    for (int i = 0; i < row ; i++) {
        for (int j = 0; j < column; j++) {
            printf("%c", maze[i][j]);
        }
        printf("\n");
    }
}

bool searchPath(char maze[row][column], int x, int y, int row, int column) {
    maze[y][x] = 'o';
    if(x == column-1 && y == row-1)
        return true;
    if(y >= 0 && y < row && x >= 0 && x < column){
        if (maze[y][x - 1] == '.' && searchPath(maze, x - 1, y, row, column))
            return true;
        if (maze[y][x + 1] == '.' && searchPath(maze, x + 1, y, row, column))
            return true;
        if (maze[y - 1][x] == '.' && searchPath(maze, x, y - 1, row, column))
            return true;
        if (maze[y + 1][x] == '.' && searchPath(maze, x, y + 1, row, column))
            return true;
    }
    maze[y][x] = '.';
    return false;
}

void Maze(){
    scanf("%d %d",&column,&row);
    char maze[row][column];
    for(int i = 0; i < row; i++){
        for(int j = 0; j < column; j++){
            scanf(" %c", &maze[i][j]);
        }
    }
    if (searchPath(maze, 0, 0, row, column)){
        print_maze(maze, row, column);
    }else{
        printf("+ sem caminho\n");
    }
}

int main(){
    Maze();
    return 0;
}