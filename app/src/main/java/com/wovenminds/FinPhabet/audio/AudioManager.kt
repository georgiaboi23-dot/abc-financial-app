package com.wovenminds.FinPhabet.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.annotation.RawRes

class AudioManager (private val context: Context)
{
    private val soundPool: SoundPool
    private val soundMap = mutableMapOf<Int,Int>()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(2)
            .setAudioAttributes(audioAttributes)
            .build()
    }
    fun loadSound(@RawRes resId: Int)
    {
        val soundId = soundPool.load(context,resId,1)
        soundMap[resId] = soundId
    }

    fun playSound(@RawRes resId: Int)
    {
        val soundId = soundMap[resId] ?: return
        soundPool.play(
            soundId,
            1f,
            1f,
            1,
            0,
            1f
        )
    }

    fun release() {
        soundPool.release()
    }
}