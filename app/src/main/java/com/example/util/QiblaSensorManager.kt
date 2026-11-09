package com.example.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class QiblaSensorManager(context: Context) : SensorEventListener {

  private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
  private val rotationSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
  private val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
  private val magnetometer = sensorManager?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

  private val _heading = MutableStateFlow(0f)
  val heading: StateFlow<Float> = _heading

  private val _hasSensors = MutableStateFlow(rotationSensor != null || (accelerometer != null && magnetometer != null))
  val hasSensors: StateFlow<Boolean> = _hasSensors

  private val gravity = FloatArray(3)
  private val geomagnetic = FloatArray(3)
  private var hasGravity = false
  private var hasGeomagnetic = false

  fun start() {
    if (rotationSensor != null) {
      sensorManager?.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_UI)
    } else {
      accelerometer?.let { sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
      magnetometer?.let { sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
    }
  }

  fun stop() {
    sensorManager?.unregisterListener(this)
  }

  override fun onSensorChanged(event: SensorEvent?) {
    if (event == null) return

    if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
      val rotationMatrix = FloatArray(9)
      SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
      val orientation = FloatArray(3)
      SensorManager.getOrientation(rotationMatrix, orientation)
      var azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()
      azimuth = (azimuth + 360f) % 360f
      _heading.value = azimuth
      return
    }

    if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
      System.arraycopy(event.values, 0, gravity, 0, 3)
      hasGravity = true
    } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
      System.arraycopy(event.values, 0, geomagnetic, 0, 3)
      hasGeomagnetic = true
    }

    if (hasGravity && hasGeomagnetic) {
      val r = FloatArray(9)
      val i = FloatArray(9)
      if (SensorManager.getRotationMatrix(r, i, gravity, geomagnetic)) {
        val orientation = FloatArray(3)
        SensorManager.getOrientation(r, orientation)
        var azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()
        azimuth = (azimuth + 360f) % 360f
        _heading.value = azimuth
      }
    }
  }

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

  companion object {
    // Kaaba Coordinates
    const val KAABA_LAT = 21.422487
    const val KAABA_LNG = 39.826206

    /**
     * Calculates the Qibla bearing in degrees from true North [0..360]
     */
    fun calculateQiblaBearing(lat: Double, lng: Double): Float {
      val latRad = Math.toRadians(lat)
      val lngRad = Math.toRadians(lng)
      val kaabaLatRad = Math.toRadians(KAABA_LAT)
      val kaabaLngRad = Math.toRadians(KAABA_LNG)

      val dLng = kaabaLngRad - lngRad
      val y = sin(dLng)
      val x = cos(latRad) * sin(kaabaLatRad) - sin(latRad) * cos(kaabaLatRad) * cos(dLng)

      var bearing = Math.toDegrees(atan2(y, x)).toFloat()
      bearing = (bearing + 360f) % 360f
      return bearing
    }

    fun calculateDistanceKm(lat: Double, lng: Double): Int {
      val r = 6371.0 // Earth radius in km
      val dLat = Math.toRadians(KAABA_LAT - lat)
      val dLng = Math.toRadians(KAABA_LNG - lng)
      val a = sin(dLat / 2) * sin(dLat / 2) +
        cos(Math.toRadians(lat)) * cos(Math.toRadians(KAABA_LAT)) *
        sin(dLng / 2) * sin(dLng / 2)
      val c = 2 * atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))
      return (r * c).toInt()
    }
  }
}
