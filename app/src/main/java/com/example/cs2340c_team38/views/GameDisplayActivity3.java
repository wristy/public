package com.example.cs2340c_team38.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340c_team38.R;
import com.example.cs2340c_team38.databinding.ActivityGameDisplay3Binding;
import com.example.cs2340c_team38.model.MoveDown;
import com.example.cs2340c_team38.model.MoveLeft;
import com.example.cs2340c_team38.model.MoveRight;
import com.example.cs2340c_team38.model.MoveUp;
import com.example.cs2340c_team38.model.Observer;
import com.example.cs2340c_team38.model.Player;
import com.example.cs2340c_team38.model.TileType;
import com.example.cs2340c_team38.viewmodels.GameDisplayViewModel3;


public class GameDisplayActivity3 extends AppCompatActivity implements Observer {

    private final TileType[][] tileMap = {{TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL, TileType.WALL, TileType.EXIT, TileType.EXIT, TileType.WALL, TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS},
            {TileType.GRASS, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.GRASS},
            {TileType.WALL, TileType.LAVA, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.LAVA, TileType.WALL},
            {TileType.WALL, TileType.LAVA, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.LAVA, TileType.WALL},
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.LAVA, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.LAVA, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.LAVA, TileType.LAVA, TileType.LAVA, TileType.LAVA, TileType.WALL, TileType.FLOOR, TileType.LAVA, TileType.WALL},
            {TileType.WALL, TileType.LAVA, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.LAVA, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.LAVA, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.WALL},
            {TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.LAVA, TileType.WALL, TileType.WALL, TileType.LAVA, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.LAVA, TileType.LAVA, TileType.WALL, TileType.WALL, TileType.LAVA, TileType.LAVA, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.LAVA, TileType.LAVA, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL},
            {TileType.GRASS, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.GRASS},
            {TileType.GRASS, TileType.GRASS, TileType.WALL, TileType.WALL, TileType.WALL, TileType.ENTRANCE, TileType.ENTRANCE, TileType.WALL, TileType.WALL, TileType.WALL, TileType.GRASS, TileType.GRASS}};
    String playerName;
    int difficulty;
    int characterSpriteId;
    int currScore;
    int[] currScore3;
    private GameDisplayViewModel3 viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityGameDisplay3Binding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_game_display3);
        viewModel = new ViewModelProvider(this).get(GameDisplayViewModel3.class);
        binding.setViewModel(viewModel);

        playerName = getIntent().getStringExtra("PLAYER_NAME");
        difficulty = getIntent().getIntExtra("DIFFICULTY", 3);
        characterSpriteId = getIntent().getIntExtra("CHARACTER_SPRITE", -1);

        viewModel.setPlayerName(playerName);
        viewModel.setDifficulty(difficulty);
        viewModel.setDrawableImage(characterSpriteId);
        currScore = getIntent().getIntExtra("currentScore", 100);
        TextView scoreText = findViewById(R.id.textView6);
        scoreText.setText("Score: " + currScore);
        currScore3 = new int[]{currScore};
        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (currScore3[0] != 0) {
                    currScore3[0] = currScore3[0] - 1;
                    scoreText.setText("Score: " + currScore3[0]);
                }
                h.postDelayed(this, 1000);
            }
        };
        h.postDelayed(r, 1000);
        viewModel.getEndEvent().observe(this, message -> {
            Intent intent = new Intent(GameDisplayActivity3.this, EndActivity.class);
            intent.putExtra("finalScore", currScore3[0]);
            intent.putExtra("currName", playerName);
            startActivity(intent);
        });

        // Movements

        int startY = 18;
        int startX = 5;

        GridLayout gridLayout = findViewById(R.id.gameGrid);

        for (int x = 0; x < gridLayout.getColumnCount(); x++) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(0, 1, 1);
            params.columnSpec = GridLayout.spec(x, 1, 1);
            params.setGravity(Gravity.FILL);
            params.width = 0;
            gridLayout.addView(new Space(this), params);
        }
        for (int y = 0; y < gridLayout.getRowCount(); y++) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(y, 1, 1);
            params.columnSpec = GridLayout.spec(0, 1, 1);
            params.setGravity(Gravity.FILL);
            params.width = 0;
            gridLayout.addView(new Space(this), params);
        }

        Player player = Player.getPlayer();
        player.addObserver(this);
        player.setPosition(startX, startY);
        player.setCurrentTile(tileMap[startY][startX]);
        moveViewToPosition(findViewById(R.id.imageView), startY, startX);


        Button upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(v -> {
            player.setMoveStrategy(new MoveUp());
            player.move(tileMap);
            Toast.makeText(GameDisplayActivity3.this, String.format("X: %d, Y: %d",
                            player.getX(), player.getY()),
                    Toast.LENGTH_SHORT).show();
        });

        Button downButton = findViewById(R.id.downButton);
        downButton.setOnClickListener(v -> {
            player.setMoveStrategy(new MoveDown());
            player.move(tileMap);
            Toast.makeText(GameDisplayActivity3.this, String.format("X: %d, Y: %d",
                            player.getX(), player.getY()),
                    Toast.LENGTH_SHORT).show();
        });

        Button leftButton = findViewById(R.id.leftButton);
        leftButton.setOnClickListener(v -> {
            player.setMoveStrategy(new MoveLeft());
            player.move(tileMap);
            Toast.makeText(GameDisplayActivity3.this, String.format("X: %d, Y: %d",
                            player.getX(), player.getY()),
                    Toast.LENGTH_SHORT).show();
        });

        Button rightButton = findViewById(R.id.rightButton);
        rightButton.setOnClickListener(v -> {
            player.setMoveStrategy(new MoveRight());
            player.move(tileMap);
            Toast.makeText(GameDisplayActivity3.this, String.format("X: %d, Y: %d",
                            player.getX(), player.getY()),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void update(int x, int y) {
        moveViewToPosition(findViewById(R.id.imageView), y, x);
        // Check if the player is on the EXIT tile
        if (tileMap[y][x] == TileType.EXIT) {
            // Launch the next activity
            Intent intent = new Intent(GameDisplayActivity3.this, EndActivity.class);

            // Add extras to the intent using instance variables
            intent.putExtra("currName", playerName);
            intent.putExtra("DIFFICULTY", difficulty);
            intent.putExtra("CHARACTER_SPRITE", characterSpriteId);
            intent.putExtra("finalScore", currScore3[0]);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Player.getPlayer().removeObserver(this); // Unregister the activity when it's destroyed
    }


    private void moveViewToPosition(View view, int newRow, int newColumn) {
        // Get the current layout parameters of the view
        GridLayout.LayoutParams params = (GridLayout.LayoutParams) view.getLayoutParams();

        // Update the position
        params.rowSpec = GridLayout.spec(newRow);
        params.columnSpec = GridLayout.spec(newColumn);

        // Apply the updated layout parameters
        view.setLayoutParams(params);

        // Request the parent GridLayout to re-layout its children
        view.getParent().requestLayout();
    }
}