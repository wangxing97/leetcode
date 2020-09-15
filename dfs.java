public class dfs {

    // 当前1-9行是否出现过1-9的标识，例如rowFlag[4][7] = true 代表第5行8这个数字已经存在
    boolean rowFlag[][] = new boolean[9][9];
    // 当前1-9列是否出现过1-9的标识，例如colFlag[6][2] = true 代表第7列3这个数字已经存在
    boolean colFlag[][] = new boolean[9][9];
    // 当前第1-9个3*3正方是否出现过1-9的标识，例如singleFlag[2][[3] = true 代表第3个正方形4这个数字已经存在
    boolean singleFlag[][] = new boolean[9][9];
    // 定义从第几个数开始深度搜索,数组最大是81个数，因此索引只会是0-80之间的数，包括0和80
    int dfsIndex = 0;

    public void solveSudoku(char[][] board) {
        // 先对上述三个Flag进行初始化
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                // 缺数的地方不进行填标识
                if(board[i][j] == '.')
                    continue;
                // 已经存在的数进行标识，将数转成int并且减1因为数最大是9
                int f = board[i][j] - '0' - 1;
                // i行f这个数存在
                rowFlag[i][f] = true;
                // j列这个数已经存在
                colFlag[j][f] = true;
                // 根据i，j的坐标判断当前数属于第几个正方形
                int singleIndex = (i/3) * 3 + j/3;
                singleFlag[singleIndex][f] = true;
            }
        }
        // 搜索
        dfs(board,dfsIndex);
    }

    public boolean dfs(char[][] board, int index){
        // 走完了
        if(index > 80){
            return true;
        }
        // 根据当前走的步数计算x，y
        int row = index / 9;
        int col = index % 9;
        // 根据x，y计算当前在第几个正方形
        int single = (row/3) * 3 + col/3;
        // 如果当前位置已经有数则进走下一步
        if (board[row][col] != '.'){
            return dfs(board,index+1);
        }
        // 将1-9每个数都放进去试试
        for(int i = 0; i < 9; i++){
            // 如果i这个数在行，列和正方形都没出现过，则进行递归尝试
            if(!rowFlag[row][i] && !colFlag[col][i] && !singleFlag[single][i]) {
                // 将数赋值给数独数组
                board[row][col] = (char) (i + 1 + '0');
                // 将其行，列，正方形标识改变
                rowFlag[row][i] = true;
                colFlag[col][i] = true;
                singleFlag[single][i] = true;
                // 继续下一步
                if (dfs(board, index+1)) {
                    return true;
                } else {
                    //递归失败，进行回溯
                    board[row][col] = '.';
                    rowFlag[row][i] = false;
                    colFlag[col][i] = false;
                    singleFlag[single][i] = false;
                }
            }
        }
        return false;
    }

    public void printSudo(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        dfs dfs = new dfs();
        dfs.solveSudoku(board);
        dfs.printSudo(board);
    }
}
