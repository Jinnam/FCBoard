package service;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class BoardDao {
    private final String driverClassName = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1:3306/injava?useUnicode=true&characterEncoding=euckr";
    private final String username = "root";
    private final String password = "java0000";
    
    // 테이블 : board , 기능 : 데이터 수정 
    public int updateBoard(Board board) {
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "UPDATE board SET board_title=?, board_content=? WHERE board_no=? AND board_pw=?";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,board.getBoardTitle());
            statement.setString(2,board.getBoardContent());
            statement.setInt(3,board.getBoardNo());
            statement.setString(4,board.getBoardPw());
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(connection, statement, null);
        }
        return rowCount;
    }
    
    // 테이블 : board , 기능 : 데이터 삭제 
    public int deleteBoard(Board board) {
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM board WHERE board_no=? AND board_pw=?";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,board.getBoardNo());
            statement.setString(2,board.getBoardPw());
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(connection, statement, null);
        }
        return rowCount;
    }
    
    // 테이블 : board , 기능 : 하나의 데이터 가져오기
    public Board selectBoardByKey(int boardNo) {
        Board board = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        String sql = "SELECT board_title, board_content, board_user, board_date FROM board WHERE board_no=?";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, boardNo);
            resultset = statement.executeQuery();
            if(resultset.next()) {
                board = new Board();
                board.setBoardNo(boardNo);
                board.setBoardTitle(resultset.getString("board_title"));
                board.setBoardContent(resultset.getString("board_content"));
                board.setBoardUser(resultset.getString("board_user"));
                board.setBoardDate(resultset.getString("board_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(connection, statement, resultset);
        }
        return board;
    }
    
    // 테이블 : board , 기능 : 한 페이지의 데이터 가져오기 
    public List<Board> selectBoardListPerPage(int beginRow, int pagePerRow) {
        List<Board> list = new ArrayList<Board>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        String sql = "SELECT board_no, board_title, board_user, board_date FROM board ORDER BY board_date DESC LIMIT ?, ?";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, beginRow);
            statement.setInt(2, pagePerRow);
            resultset = statement.executeQuery();
            while(resultset.next()) {
                Board board = new Board();
                board.setBoardNo(resultset.getInt("board_no"));
                board.setBoardTitle(resultset.getString("board_title"));
                board.setBoardUser(resultset.getString("board_user"));
                board.setBoardDate(resultset.getString("board_date"));
                list.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(connection, statement, resultset);
        }
        return list;
    }
    
    // 테이블 : board , 기능 : 전체 로우 카운터 가져오기
    public int selectTotalBoardCount() {
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        String sql = "SELECT COUNT(*) FROM board";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery();
            if(resultset.next()) {
                rowCount = resultset.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(connection, statement, resultset);
        }
        return rowCount;
    }
    
    // 테이블 : board , 기능 : 데이터 입력하기
    public int insertBoard(Board board) {
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO board(board_pw, board_title, board_content, board_user, board_date) values(?,?,?,?,now())";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,board.getBoardPw());
            statement.setString(2,board.getBoardTitle());
            statement.setString(3,board.getBoardContent());
            statement.setString(4,board.getBoardUser());
            rowCount = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(connection, statement, null);
        }
        return rowCount;
    }
    
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(this.driverClassName);
            connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    private void close(Connection connection, Statement statement, ResultSet resultset) {
        if(resultset != null) {
            try {
                resultset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}