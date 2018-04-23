package tablecloth.com.capturegenerator

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CAPTURE = 1
        var projection: MediaProjection?  = null
    }

    private lateinit var mediaProjectionManager: MediaProjectionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaProjectionManager = getSystemService(Service.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), REQUEST_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CAPTURE) {
            if(resultCode == Activity.RESULT_OK) {
                projection = mediaProjectionManager.getMediaProjection(resultCode, data)
//                val intent = Intent(this, CaptureService::class.java)
//                startService(intent)
                Toast.makeText(this, "capture start service", Toast.LENGTH_SHORT).show()
            } else {
                projection = null
                Toast.makeText(this, "capture error", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }
}
