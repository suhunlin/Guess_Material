package com.suhun.guessmaterial

import android.content.res.Resources
import android.util.Log
import java.util.Random

class SecretNumber {
    val tag = SecretNumber::class.simpleName
    var randomNumber:Int
    var count:Int = 0
    init{
        randomNumber = Random().nextInt(100) + 1
        Log.d(tag, "secret number:$randomNumber")
    }

    fun verify(r:Resources, userInput:Int):String{
        count++
        if(randomNumber>userInput){
            return "Bigger!!!"
        }else if(randomNumber<userInput){
            return "Smaller!!!"
        }else{
            return "You got it!!!"
        }
    }

    fun resetAll(){
        count = 0
        randomNumber = Random().nextInt(100) + 1
        Log.d(tag, "reset secret number:$randomNumber")
    }
}