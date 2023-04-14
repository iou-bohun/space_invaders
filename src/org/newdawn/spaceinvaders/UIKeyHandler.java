package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UIKeyHandler extends KeyAdapter {
    GameLobbyPanel glp;
    String idString = "";
    String pwString = "";

    public UIKeyHandler(GameLobbyPanel glp) {
        this.glp = glp;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //타이틀 화면 조작
        if (glp.gameState == glp.titleState) {
            if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
                if (glp.mu.commandNum == -2) {
                    glp.mu.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_RIGHT) {
                if (glp.mu.commandNum != 4 && glp.mu.commandNum != 5) {
                    glp.mu.commandNum++;
                    if (glp.mu.commandNum > 3) {
                        glp.mu.commandNum = 3;
                    }
                }
            }

            if (code == KeyEvent.VK_LEFT) {
                if (glp.mu.commandNum != 4 && glp.mu.commandNum != 5) {
                    glp.mu.commandNum--;
                    if (glp.mu.commandNum < 0) {
                        glp.mu.commandNum = 0;
                    }
                }

            }

            if (code == KeyEvent.VK_UP) {
                if (glp.mu.commandNum == 3) {
                    glp.mu.commandNum = 4;
                }
                if (glp.mu.commandNum == 1 || glp.mu.commandNum == 2) {
                    glp.mu.commandNum = 5;
                }
            }

            if (code == KeyEvent.VK_DOWN) {
                if (glp.mu.commandNum == 4) {
                    glp.mu.commandNum = 3;
                }
                if (glp.mu.commandNum == 5) {
                    glp.mu.commandNum = 1;
                }
            }

            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {

                if (glp.mu.commandNum == 0) {

                }

                if (glp.mu.commandNum == 1) {
                    glp.mu.commandNum = -2;
                    glp.gameState = glp.shopState;
                }

                if (glp.mu.commandNum == 2) {
                    glp.mu.commandNum = -2;
                    glp.gameState = glp.userState;
                }

                if (glp.mu.commandNum == 3) {
                    System.exit(0);
                }

                if (glp.mu.commandNum == 4) {
                    glp.gameState = glp.initialState;
                    glp.mu.commandNum = -1;
                }
            }
        }

        //상점 조작
        if (glp.gameState == glp.shopState) {
            glp.mu.coinLackState = false;
            glp.mu.possState = false;
            glp.mu.purchaseState = false;

            if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
                if (glp.mu.commandNum == -2) {
                    glp.mu.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_RIGHT) {
                if (glp.mu.commandNum != 4) {
                    glp.mu.commandNum++;
                    if (glp.mu.commandNum > 3) {
                        glp.mu.commandNum = 3;
                    }
                }
            }

            if (code == KeyEvent.VK_LEFT) {
                if (glp.mu.commandNum != 4) {
                    glp.mu.commandNum--;
                    if (glp.mu.commandNum < 0) {
                        glp.mu.commandNum = 0;
                    }
                }
            }

            if (code == KeyEvent.VK_UP) {
                if (glp.mu.commandNum == 4) {
                    glp.mu.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_DOWN) {
                glp.mu.commandNum = 4;
            }

            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {

                if (glp.mu.commandNum == 0) {
                    if (UserDB.coin >= MainUI.hpcoin) {
                        UserDB.HP_potion++;
                        UserDB.coin = UserDB.coin - MainUI.hpcoin;
                        glp.mu.purchaseState = true;
                    } else {
                        glp.mu.coinLackState = true;
                    }
                }

                if (glp.mu.commandNum == 1) {
                        if(UserDB.coin >= MainUI.spcoin) {
                            UserDB.speed_potion++;
                            UserDB.coin = UserDB.coin - MainUI.spcoin;
                            glp.mu.purchaseState = true;
                        }
                        else {
                            glp.mu.coinLackState = true;
                        }
                }

                if (glp.mu.commandNum == 2){
                    if(!UserDB.is_hard_ship && UserDB.coin >= MainUI.hscoin) {
                            UserDB.is_hard_ship = true;
                            UserDB.coin = UserDB.coin - MainUI.hscoin;
                            glp.mu.purchaseState = true;
                    }

                    else if (!UserDB.is_hard_ship /*&& UserDB.coin < MainUI.hscoin*/) {
                        glp.mu.coinLackState = true;
                    }

                    else {
                        glp.mu.possState = true;
                    }

                }

                if (glp.mu.commandNum == 3) {
                    if(!UserDB.is_lucky_ship && UserDB.coin >= MainUI.lscoin) {
                        UserDB.is_lucky_ship = true;
                        UserDB.coin = UserDB.coin - MainUI.lscoin;
                        glp.mu.purchaseState = true;
                    }

                    else if (!UserDB.is_lucky_ship /*&&UserDB.coin < MainUI.lscoin*/) {
                        glp.mu.coinLackState = true;
                    }

                    else {
                        glp.mu.possState = true;
                    }

                }

                if (glp.mu.commandNum == 4) {
                    glp.mu.commandNum = 1;
                    glp.gameState = glp.titleState;
                }
            }
        }

        //유저 상태창 조작
        if(glp.gameState == glp.userState){
            glp.mu.equipState = false;
            if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
                if (glp.mu.commandNum == -2) {
                    glp.mu.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_RIGHT) {
                if (glp.mu.commandNum != 3) {
                    if (UserDB.is_hard_ship && UserDB.is_lucky_ship) {
                        glp.mu.commandNum++;
                        if (glp.mu.commandNum > 2) {
                            glp.mu.commandNum = 2;
                        }
                    }
                    if(UserDB.is_hard_ship && !UserDB.is_lucky_ship){
                        glp.mu.commandNum++;
                        if (glp.mu.commandNum > 1) {
                            glp.mu.commandNum = 1;
                        }
                    }
                    if(!UserDB.is_hard_ship && UserDB.is_lucky_ship){
                        glp.mu.commandNum = 2;
                    }
                    else {return;}
                }
            }

            if (code == KeyEvent.VK_LEFT) {
                if (glp.mu.commandNum != 3) {
                    if (UserDB.is_hard_ship) {
                        glp.mu.commandNum--;
                        if (glp.mu.commandNum < 0) {
                            glp.mu.commandNum = 0;
                        }
                    }
                    if (!UserDB.is_hard_ship && UserDB.is_lucky_ship) {
                        glp.mu.commandNum = 0;
                    }
                    else {return;}
                }
            }

            if (code == KeyEvent.VK_UP) {
                if (glp.mu.commandNum == 3) {
                    glp.mu.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_DOWN) {
                glp.mu.commandNum = 3;
            }

            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {

                if (glp.mu.commandNum == 0) {
                    glp.mu.equipState = true;
                    UserDB.selected_ship = 0;
                }

                if (glp.mu.commandNum == 1) {
                    glp.mu.equipState = true;
                    UserDB.selected_ship = 1;
                }

                if (glp.mu.commandNum == 2) {
                    glp.mu.equipState = true;
                    UserDB.selected_ship = 2;
                }

                if (glp.mu.commandNum == 3) {
                    glp.mu.commandNum = 2;
                    glp.gameState = glp.titleState;
                }
            }
        }

        if(glp.gameState == glp.initialState){
            if (code == KeyEvent.VK_DOWN) {
                    glp.mu.commandNum++;
                    if (glp.mu.commandNum > 3) {
                        glp.mu.commandNum = 0;
                    }
            }

            if (code == KeyEvent.VK_UP) {
                    glp.mu.commandNum--;
                    if (glp.mu.commandNum < 0) {
                        glp.mu.commandNum = 3;
                    }
            }

            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {

                if (glp.mu.commandNum == 0) {
                    glp.gameState = glp.signInState;
                    glp.mu.commandNum = -1;
                }

                if (glp.mu.commandNum == 1) {
                    glp.gameState = glp.signUpState;
                }

                if (glp.mu.commandNum == 2) {
                    glp.gameState = glp.titleState;
                    glp.mu.commandNum = -2;
                }

                if (glp.mu.commandNum == 3) {
                    System.exit(0);
                }
            }
        }

        if (glp.gameState == glp.signInState) {
            glp.mu.unableLoginState = false;
            if (code == KeyEvent.VK_DOWN) {
                glp.mu.commandNum++;
                if (glp.mu.commandNum > 3) {
                    glp.mu.commandNum = 3;
                }
            }

            if (code == KeyEvent.VK_UP) {
                glp.mu.commandNum--;
                if (glp.mu.commandNum < 0) {
                    glp.mu.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {

                if(glp.mu.commandNum == 1){
                    loginDB();
                    if (UserDB.is_logged_in) {
                        glp.gameState = glp.titleState;
                        glp.mu.commandNum = -2;
                    }
                }

                if (glp.mu.commandNum == 2) {
                    loginDB();
                    if (UserDB.is_logged_in) {
                        glp.gameState = glp.titleState;
                        glp.mu.commandNum = -2;
                    }
                }

                if (glp.mu.commandNum == 3) {
                    idString = "";
                    pwString = "";
                    glp.gameState = glp.initialState;
                    glp.mu.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_TAB) {
                if(glp.mu.commandNum == 0){
                    glp.mu.commandNum++;
                }
            }
        }
    }

    public void keyTyped(KeyEvent e){
        char inputChar = e.getKeyChar();

        if(glp.gameState == glp.signInState){
            if(glp.mu.commandNum == 0 && inputChar != KeyEvent.VK_BACK_SPACE){
                if(idString.length() < 12) {
                    idString += inputChar;
                }
            }
            
            if(glp.mu.commandNum == 1 && inputChar != KeyEvent.VK_BACK_SPACE){
                if(pwString.length() < 12) {
                    pwString += inputChar;
                }
            }
            
            if(inputChar == KeyEvent.VK_BACK_SPACE){
                if(glp.mu.commandNum == 0 && idString.length() != 0) idString = idString.substring(0,idString.length()-1);
                if(glp.mu.commandNum == 1 && pwString.length() != 0) pwString = pwString.substring(0,pwString.length()-1);
            }
        }
    }

    public void loginDB() {
        Connection conn = UserDB.getConnection();
        //로그인 시도
        try {
            String query = "SELECT id,password FROM userdata WHERE id = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, idString);
            pstmt.setString(2, pwString);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String dataLoad = "SELECT * FROM userdata WHERE id = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(dataLoad);

                pstmt2.setString(1, idString);

                ResultSet rs2 = pstmt2.executeQuery();
                //플레이어 데이터 로드 - 데이터베이스 테이블에서 데이터 로드, UserDB의 static 변수에 저장
                while (rs2.next()) {
                    UserDB.userID = idString;
                    UserDB.nickname = rs2.getString("nickname");
                    UserDB.best_score = rs2.getInt("best_score");
                    UserDB.coin = rs2.getInt("coin");
                    UserDB.is_hard_ship = rs2.getBoolean("is_hard_ship");
                    UserDB.is_lucky_ship = rs2.getBoolean("is_lucky_ship");
                    UserDB.HP_potion = rs2.getInt("HP_potion");
                    UserDB.speed_potion = rs2.getInt("speed_potion");
                    UserDB.selected_ship = rs2.getInt("selected_ship");
                    UserDB.loggedIn();
                }
            } else {
                glp.mu.unableLoginState = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
