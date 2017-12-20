package passit.gania.gimmesome.passit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddPiece extends AppCompatActivity implements View.OnClickListener {

    MainActivity main;
    int playerChances;
    int victimChances;
    int hitMax;
    String pieceName;
    Boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piece);
        Button btnSavePiece = (Button) findViewById(R.id.btnSavePiece);
        btnSavePiece.setOnClickListener(this);
        main = new MainActivity();
    }

    @Override
    public void onClick(View view) {
        sendNewPiece();
    }

    public void sendNewPiece() {
        TextView txtPieceName = (TextView) findViewById(R.id.txtPieceName);
        SeekBar seekPlayerChances = (SeekBar) findViewById(R.id.pusherChances);
        SeekBar seekVictimChances = (SeekBar) findViewById(R.id.victimChances);
        SeekBar seekHitMax = (SeekBar) findViewById(R.id.hitMax);

        pieceName = txtPieceName.getText().toString();
        playerChances = seekPlayerChances.getProgress();
        victimChances = seekVictimChances.getProgress();
        hitMax = seekHitMax.getProgress();

//        main.addPieceButton(pieceName);
        savePiece(pieceName, playerChances, victimChances, hitMax);
    }

    public void savePiece(String pieceName, int playerChances, int victimChances, int hitMax) {
        String piece = pieceName + "," + Integer.toString(playerChances+1) + "," + Integer.toString(victimChances+6) + "," + Integer.toString(hitMax+1);

        try {
            String filePath = this.getFilesDir() + "/" + "pieces.txt";
            File root = new File(filePath);
            if (!root.exists()) {
                File file = new File(root, "pieces.txt");
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("pieces.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(piece);
            outputStreamWriter.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            isSaved = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if (isSaved) {
                Intent myIntent = new Intent(this, MainActivity.class);
                myIntent.putExtra("pName",pieceName);
                myIntent.putExtra("pChances",playerChances);
                myIntent.putExtra("vChances",victimChances);
                myIntent.putExtra("hitMax",hitMax);
                startActivity(myIntent);
            }

        }

        return super.onKeyDown(keyCode, event);
    }



}
