package com.example.sanja.sanjaxo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivityXO extends AppCompatActivity {

    int c[][];
    int i, j, k = 0;
    Button b[][];
    TextView textView;
    AI ai;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_xo);

        setBoard();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add("Nova igra");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        setBoard();
        return true;
    }


    // Set up the game board.
    private void setBoard() {
        ai = new AI();
        b = new Button[3][3];
        c = new int[3][3];


        b[0][0] = (Button) findViewById(R.id.button1);
        b[0][1] = (Button) findViewById(R.id.button2);
        b[0][2] = (Button) findViewById(R.id.button3);
        b[1][0] = (Button) findViewById(R.id.button4);
        b[1][1] = (Button) findViewById(R.id.button5);
        b[1][2] = (Button) findViewById(R.id.button6);
        b[2][0] = (Button) findViewById(R.id.button7);
        b[2][1] = (Button) findViewById(R.id.button8);
        b[2][2] = (Button) findViewById(R.id.button9);

        for (i = 0; i <= 2; i++) {
            for (j = 0; j <= 2; j++)
                c[i][j] = 2;
        }


        Toast.makeText(getApplicationContext(), "Klikni za start!",
                Toast.LENGTH_SHORT).show();


        // add the click listeners for each button
        for (i = 0; i <= 2; i++) {
            for (j = 0; j <= 2; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if (!b[i][j].isEnabled()) {
                    b[i][j].setText("?");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActivityXO Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.sanja.sanjaxo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActivityXO Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.sanja.sanjaxo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    class MyClickListener implements View.OnClickListener {
        int x;
        int y;

        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                b[x][y].setText("O");
                c[x][y] = 0;
                textView.setText("");
                if (checkBoard()) {
                    ai.takeTurn();
                }
            }
        }
    }


    private class AI {
        public void takeTurn() {
            if (c[0][0] == 1 &&
                    ((c[0][1] == 0 && c[0][2] == 0) ||
                            (c[1][1] == 0 && c[2][2] == 0) ||
                            (c[1][0] == 0 && c[2][0] == 0))) {
                markSquare(0, 0);
            } else if (c[0][1] == 1 &&
                    ((c[1][1] == 0 && c[2][1] == 0) ||
                            (c[0][0] == 0 && c[0][2] == 0))) {
                markSquare(0, 1);
            } else if (c[0][2] == 1 &&
                    ((c[0][0] == 0 && c[0][1] == 0) ||
                            (c[2][0] == 0 && c[1][1] == 0) ||
                            (c[1][2] == 0 && c[2][2] == 0))) {
                markSquare(0, 2);
            } else if (c[1][0] == 1 &&
                    ((c[1][1] == 0 && c[1][2] == 0) ||
                            (c[0][0] == 0 && c[2][0] == 0))) {
                markSquare(1, 0);
            } else if (c[1][1] == 1 &&
                    ((c[0][0] == 0 && c[2][2] == 0) ||
                            (c[0][1] == 0 && c[2][1] == 0) ||
                            (c[2][0] == 0 && c[0][2] == 0) ||
                            (c[1][0] == 0 && c[1][2] == 0))) {
                markSquare(1, 1);
            } else if (c[1][2] == 1 &&
                    ((c[1][0] == 0 && c[1][1] == 0) ||
                            (c[0][2] == 0 && c[2][2] == 0))) {
                markSquare(1, 2);
            } else if (c[2][0] == 1 &&
                    ((c[0][0] == 0 && c[1][0] == 0) ||
                            (c[2][1] == 0 && c[2][2] == 0) ||
                            (c[1][1] == 0 && c[0][2] == 0))) {
                markSquare(2, 0);
            } else if (c[2][1] == 1 &&
                    ((c[0][1] == 0 && c[1][1] == 0) ||
                            (c[2][0] == 0 && c[2][2] == 0))) {
                markSquare(2, 1);
            } else if (c[2][2] == 2 &&
                    ((c[0][0] == 0 && c[1][1] == 0) ||
                            (c[0][2] == 0 && c[1][2] == 0) ||
                            (c[2][0] == 0 && c[2][1] == 0))) {
                markSquare(2, 2);
            } else {
                Random rand = new Random();

                int a = rand.nextInt(3);
                int b = rand.nextInt(3);
                while (a == 0 || b == 0 || c[a][b] != 2) {
                    a = rand.nextInt(3);
                    b = rand.nextInt(3);
                }
                markSquare(a, b);
            }
        }


        private void markSquare(int x, int y) {
            b[x][y].setEnabled(false);
            b[x][y].setText("X");
            c[x][y] = 0;
            checkBoard();
        }
    }


    // check the board to see if someone has won*
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[0][0] == 0 && c[1][1] == 0 && c[2][2] == 0)
                || (c[0][2] == 0 && c[1][1] == 0 && c[2][0] == 0)
                || (c[0][1] == 0 && c[1][1] == 0 && c[2][1] == 0)
                || (c[0][2] == 0 && c[1][2] == 0 && c[2][2] == 0)
                || (c[0][0] == 0 && c[0][1] == 0 && c[0][2] == 0)
                || (c[1][0] == 0 && c[1][1] == 0 && c[1][2] == 0)
                || (c[2][0] == 0 && c[2][1] == 0 && c[2][2] == 0)
                || (c[0][0] == 0 && c[1][0] == 0 && c[2][0] == 0)) {

            Toast.makeText(getApplicationContext(), "Game Over! Pobijedio si!",
                    Toast.LENGTH_SHORT).show();
            gameOver = true;
        } else if ((c[0][0] == 0 && c[1][1] == 0 && c[2][2] == 0)
                || (c[0][2] == 0 && c[1][1] == 0 && c[2][0] == 0)
                || (c[0][1] == 0 && c[1][1] == 0 && c[2][1] == 0)
                || (c[0][2] == 0 && c[1][2] == 0 && c[2][2] == 0)
                || (c[0][0] == 0 && c[0][1] == 0 && c[0][2] == 0)
                || (c[1][0] == 0 && c[1][1] == 0 && c[1][2] == 0)
                || (c[2][0] == 0 && c[2][1] == 0 && c[2][2] == 0)
                || (c[0][0] == 0 && c[1][0] == 0 && c[2][0] == 0)) {

            Toast.makeText(getApplicationContext(), "Game Over! Izgubio si!",
                    Toast.LENGTH_SHORT).show();
            boolean empty = false;
            for (i = 0; i <= 2; i++) {
                for (j = 0; j <= 2; j++) {
                    if (c[i][j] == 1) {
                        empty = true;
                        break;
                    }
                }
            }
            if (!empty) {
                gameOver = true;
                Toast.makeText(getApplicationContext(), "Game Over!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        return gameOver;
    }
}



