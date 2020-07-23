package com.example.newchatui.features.voiceNote

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.newchatui.R
import com.google.android.gms.common.util.IOUtils.toByteArray
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_voice_note.*
import java.io.File
import java.io.IOException
import java.io.InputStream


class VoiceNoteActivity : AppCompatActivity() {
    private var saved: Boolean = false
    private var sessionUri: Uri? = null
    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false
    var storage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_note)

        mediaRecorder = MediaRecorder()
        output = Environment.getExternalStorageDirectory().absolutePath + "/recording.mp3"

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)

        button_start_recording.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions,0)
            } else {
                startRecording()
            }
        }

        button_stop_recording.setOnClickListener{
            stopRecording()
        }

        button_pause_recording.setOnClickListener {
            pauseRecording()
        }
    }


    private fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "Recording started!", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun pauseRecording() {
        if(state) {
            if(!recordingStopped){
                Toast.makeText(this,"Stopped!", Toast.LENGTH_SHORT).show()
                mediaRecorder?.pause()
                recordingStopped = true
                button_pause_recording.text = "Resume"
            }else{
                resumeRecording()
            }
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun resumeRecording() {
        Toast.makeText(this,"Resume!", Toast.LENGTH_SHORT).show()
        mediaRecorder?.resume()
        button_pause_recording.text = "Pause"
        recordingStopped = false
    }

    private fun stopRecording(){
        if(state){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
            val uriAudio: Uri = Uri.fromFile(File(output).getAbsoluteFile())
            val storageRef: StorageReference = storage.getReferenceFromUrl("gs://newchatui.appspot.com")
            var time = System.nanoTime().toString()
            var filename = time +".mp3"
            val mountainsRef = storageRef.child(time+".mp3")
            val mountainImagesRef = storageRef.child(filename)
            mountainsRef.getName().equals(mountainImagesRef.getName());    // true
            mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
            val outputFile = Environment.getExternalStorageDirectory()
                .absolutePath + "/recording.3gp"

            var mReference = mountainImagesRef
            try {
                mReference.putFile(uriAudio).addOnSuccessListener {
                    mReference.downloadUrl.addOnCompleteListener {
                        val url = it.result.toString()
                        Toast.makeText(applicationContext,url,Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }

        }
        else{
            Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show()
        }
    }
}
