package edu.angelo.studio04;

// Darian Espinoza
// CS 3372

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import edu.angelo.studio03.LightsOutBoard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameBoard = new LightsOutBoard(4,3,3);
        lightButtons = new Button[][] {{
                findViewById(R.id.button00),
                findViewById(R.id.button01),
                findViewById(R.id.button02)
        }, {
                findViewById(R.id.button10),
                findViewById(R.id.button11),
                findViewById(R.id.button12)
        },  {
                findViewById(R.id.button20),
                findViewById(R.id.button21),
                findViewById(R.id.button22)
        }};
    }

    private final int NUM_ROWS = 4;
    private final int NUM_COLUMNS = 3;
    private final int[] colors = new int[] {
            Color.rgb(170, 170, 170), // a light gray
            Color.rgb(102, 136, 204), // a grayish blue
            Color.rgb(0, 85, 255)     // an intense blue
    };

    /**
     * LightsOutBoard object for keeping track of the light value of each button
     */
    private LightsOutBoard gameBoard;

    /**
     * 2D array of buttons for changing the light color of each button
     */
    private Button[][] lightButtons;

    /**
     * updates the gameBoard every time a new event happens to reflect any changes in button color
     * or completion/un-completion of gameBoard
     */
    private void updateGameBoard() {
        // indices for running through lightButtons array
        int rowIndex;
        int colIndex;
        for (rowIndex = 0; rowIndex < 4; rowIndex += 1) {
            for (colIndex = 0; colIndex < 3; colIndex += 1) {
                // store light value in button color, then use that to reference index in colors array
                int buttonColor = gameBoard.getLightColor(rowIndex, colIndex);
                lightButtons[rowIndex][colIndex].setBackgroundColor(colors[buttonColor]);
            }
        }
        final TextView textView = findViewById(R.id.textView);
        if (gameBoard.isCleared()) {
            textView.setText("A WINNER IS YOU");
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.rgb(12, 122, 36));
        } else {
            // gameBoard has not been cleared
            textView.setText("Can you turn all the lights out?");
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundColor(Color.WHITE);
        }
    }
    
}
