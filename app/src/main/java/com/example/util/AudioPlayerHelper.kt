package com.example.util

import android.media.AudioAttributes
import android.media.MediaPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AudioPlayerHelper {

  private var mediaPlayer: MediaPlayer? = null

  private val _isPlaying = MutableStateFlow(false)
  val isPlaying: StateFlow<Boolean> = _isPlaying

  private val _currentPlayingId = MutableStateFlow<String?>(null)
  val currentPlayingId: StateFlow<String?> = _currentPlayingId

  private val _isLoading = MutableStateFlow(false)
  val isLoading: StateFlow<Boolean> = _isLoading

  fun playUrl(id: String, url: String, onCompletion: () -> Unit = {}) {
    if (_currentPlayingId.value == id && _isPlaying.value) {
      pause()
      return
    }

    stop()
    _isLoading.value = true
    _currentPlayingId.value = id

    try {
      mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
          AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
        )
        setDataSource(url)
        setOnPreparedListener { mp ->
          _isLoading.value = false
          _isPlaying.value = true
          mp.start()
        }
        setOnCompletionListener {
          _isPlaying.value = false
          _currentPlayingId.value = null
          onCompletion()
        }
        setOnErrorListener { _, _, _ ->
          _isLoading.value = false
          _isPlaying.value = false
          _currentPlayingId.value = null
          true
        }
        prepareAsync()
      }
    } catch (e: Exception) {
      _isLoading.value = false
      _isPlaying.value = false
      _currentPlayingId.value = null
    }
  }

  fun pause() {
    mediaPlayer?.let {
      if (it.isPlaying) {
        it.pause()
        _isPlaying.value = false
      }
    }
  }

  fun resume() {
    mediaPlayer?.let {
      it.start()
      _isPlaying.value = true
    }
  }

  fun stop() {
    mediaPlayer?.let {
      try {
        if (it.isPlaying) it.stop()
        it.reset()
        it.release()
      } catch (_: Exception) {}
    }
    mediaPlayer = null
    _isPlaying.value = false
    _currentPlayingId.value = null
    _isLoading.value = false
  }
}
