package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UIKeyHandler extends KeyAdapter {
    GameLobbyPanel glp;

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

                }

                if (glp.mu.commandNum == 3) {
                    System.exit(0);
                }

                if (glp.mu.commandNum == 4) {

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
                    if (UserDB.coin > MainUI.hpcoin) {
                        UserDB.HP_potion++;
                        UserDB.coin = UserDB.coin - MainUI.hpcoin;
                        glp.mu.purchaseState = true;
                    } else {
                        glp.mu.coinLackState = true;
                    }
                }

                if (glp.mu.commandNum == 1) {
                        if(UserDB.coin > MainUI.spcoin) {
                            UserDB.speed_potion++;
                            UserDB.coin = UserDB.coin - MainUI.spcoin;
                            glp.mu.purchaseState = true;
                        }
                        else {
                            glp.mu.coinLackState = true;
                        }
                }

                if (glp.mu.commandNum == 2){
                    if(!UserDB.is_hard_ship && UserDB.coin > MainUI.hscoin) {
                            UserDB.is_hard_ship = true;
                            UserDB.coin = UserDB.coin - MainUI.hscoin;
                            glp.mu.purchaseState = true;
                    }

                    else if (!UserDB.is_hard_ship && UserDB.coin < MainUI.hscoin) {
                        glp.mu.coinLackState = true;
                    }

                    else {
                        glp.mu.possState = true;
                    }

                }

                if (glp.mu.commandNum == 3) {
                    if(!UserDB.is_lucky_ship && UserDB.coin > MainUI.lscoin) {
                        UserDB.is_lucky_ship = true;
                        UserDB.coin = UserDB.coin - MainUI.lscoin;
                        glp.mu.purchaseState = true;
                    }

                    else if (!UserDB.is_lucky_ship && UserDB.coin < MainUI.lscoin) {
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

    }
}
