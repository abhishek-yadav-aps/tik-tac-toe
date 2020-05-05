package com.example.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var checker:Array<Array<Button>>
    var status=Array(3){IntArray(3)}
    var player:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checker= arrayOf(arrayOf(button1,button2,button3),arrayOf(button4,button5,button6),arrayOf(button7,button8,button9))
        for (row in checker)
        {
            for (button in row)
            {
                button.setOnClickListener(this)
            }
        }
        for(i in 0..2)
        {
            for (j in 0..2)
            {
                status[i][j]=-1
            }
        }
        resetbutton.setOnClickListener {
            for(i in 0..2)
            {
                for (j in 0..2)
                {
                    status[i][j]=-1
                }
            }
            for (i  in 0..2)
            {
                for (j in 0..2)
                {
                    checker[i][j].isEnabled=true
                    checker[i][j].text=""
                }
            }
            player =false
            turnview.text="Player X turn"


        }
    }

    override fun onClick(v: View) {
        player=!player
        when(v.id)
        {
            R.id.button1 -> updatestatus(0,0)
            R.id.button2 -> updatestatus(0,1)
            R.id.button3 -> updatestatus(0,2)
            R.id.button4 -> updatestatus(1,0)
            R.id.button5 -> updatestatus(1,1)
            R.id.button6 -> updatestatus(1,2)
            R.id.button7 -> updatestatus(2,0)
            R.id.button8 -> updatestatus(2,1)
            R.id.button9 -> updatestatus(2,2)
        }
        if(player==true)
            turnview.text="Player O turn"
        else
            turnview.text="Player X turn"
        checkwinner();
    }

    private fun checkwinner() {
        var winnerstatus:Boolean=false
        if(status[0][0]==status[1][1]&&status[1][1]==status[2][2])
        {
            if(status[0][0]==1) {
                turnview.text = "Player X wins!"
                allbutoondisable()
                winnerstatus=true
            }
            else if(status[0][0]==0)
            {
                turnview.text = "Player O wins!"
                allbutoondisable()
                winnerstatus=true
            }
        }
        if(status[0][2]==status[1][1]&&status[1][1]==status[2][0])
        {
            if(status[1][1]==1) {
                turnview.text = "Player X wins!"
                allbutoondisable()
                winnerstatus=true
            }
            else if(status[1][1]==0)
            {
                turnview.text = "Player O wins!"
                allbutoondisable()
                winnerstatus=true
            }
        }
        for(i in 0..2)
        {
            if(status[i][0]==status[i][1]&&status[i][1]==status[i][2])
            {
                if(status[i][1]==1) {
                    turnview.text = "Player X wins!"
                    allbutoondisable()
                    winnerstatus=true
                }
                else if(status[i][1]==0)
                {
                    turnview.text = "Player O wins!"
                    allbutoondisable()
                    winnerstatus=true
                }
            }

        }
        for(i in 0..2)
        {
            if(status[0][i]==status[1][i]&&status[1][i]==status[2][i])
            {
                if(status[1][i]==1) {
                    turnview.text = "Player X wins!"
                    allbutoondisable()
                    winnerstatus=true
                }
                else if(status[1][i]==0)
                {
                    turnview.text = "Player O wins!"
                    allbutoondisable()
                    winnerstatus=true
                }
            }

        }
        if(winnerstatus==false) {
            var flag: Int = 0
            for (i in 0..2) {
                for (j in 0..2) {
                    if (status[i][j] == -1) {
                        flag = 1;
                    }
                }
            }
            if (flag == 0) {
                turnview.text="DRAW"
                allbutoondisable()
            }
        }
    }

    private fun allbutoondisable() {
        for (i  in 0..2)
        {
            for (j in 0..2)
            {
                checker[i][j].isEnabled=false
            }
        }
    }

    private fun updatestatus(i: Int, j: Int) {
        if(player==true) {
            checker[i][j].text = "X"
            status[i][j]=1
        }
        else {
            checker[i][j].text = "O"
            status[i][j]=0
        }
        checker[i][j].isEnabled=false;
    }
}
