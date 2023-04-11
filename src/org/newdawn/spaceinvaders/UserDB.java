package org.newdawn.spaceinvaders;


import java.sql.*;


public final class UserDB {

    public static Connection conn;

    //DB 저장용 데이터 변수
    public static String userID;
    public static String nickname;
    //스테이지 진행도(스테이지를 얼마나 클리어했는지)
    public static int stage_process = 0;
    //각 스테이지 당 최고 스코어 기록
    public static int stage1_best_score = 0;
    public static int stage2_best_score = 0;
    public static int stage3_best_score = 0;
    public static int stage4_best_score = 0;
    public static int stage5_best_score = 0;
    //상점관련, 보유 코인 수
    public static int coin = 0;
    //우주선 보유 여부
    public static boolean is_hard_ship = false;
    public static boolean is_lucky_ship = false;
    //포션 보유 개수
    public static int HP_potion = 0;
    public static int speed_potion = 0;
    public static int selected_ship = 0;

    static {
        String url = "jdbc:mysql://localhost:3306/space-invaders?allowMultiQueries=true";
        String user = "user1";
        String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }
    private UserDB() {

    }
}