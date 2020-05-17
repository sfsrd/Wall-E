package com.sonya.wall_e;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;
import com.sonya.wall_e.constants.DirectionConstants;
import com.sonya.wall_e.rest.client.EspClient;
import com.sonya.wall_e.speech.audio.AudioEmitter;
import com.sonya.wall_e.speech.constants.SpeechConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private EspClient _espClient;

    private AudioEmitter _audioEmitter;

    private SpeechClient _speechClient;

    private TextView speech;

    private boolean _permissionToRecord = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _espClient = new EspClient();

        speech = findViewById(R.id.textView);

        try {
            SpeechConstants.PERMISSIONS.add(Manifest.permission.RECORD_AUDIO);

            InputStream resourceStream = getResources().openRawResource(R.raw.credential);

            _audioEmitter = new AudioEmitter();

            _speechClient = SpeechClient.create(SpeechSettings.newBuilder()
                    .setCredentialsProvider(() -> GoogleCredentials.fromStream(resourceStream)).build());

            ActivityCompat.requestPermissions(this, SpeechConstants.PERMISSIONS.toArray(new String[0]),
                    SpeechConstants.REQUEST_RECORD_AUDIO_PERMISSION);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (_permissionToRecord) {

            AtomicBoolean isFirstRequest = new AtomicBoolean(true);

            _audioEmitter = new AudioEmitter();

            ApiStreamObserver requestStream = _speechClient.streamingRecognizeCallable()
                    .bidiStreamingCall(new ApiStreamObserver<StreamingRecognizeResponse>() {

                        @Override
                        public void onNext(StreamingRecognizeResponse value) {

                            runOnUiThread(() -> {

                                if (value.getResultsCount() > 0) {

                                    speech.setText(value.getResults(0).getAlternatives(0).getTranscript());
                                } else {

                                    speech.setText("Error");
                                }
                            });
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onCompleted() {

                        }
                    });

            _audioEmitter.start(bytes -> {

                StreamingRecognizeRequest.Builder builder = StreamingRecognizeRequest.newBuilder()
                        .setAudioContent(bytes);

                if (isFirstRequest.getAndSet(false)) {

                    builder.setStreamingConfig(StreamingRecognitionConfig.newBuilder()
                            .setConfig(RecognitionConfig.newBuilder()
                                    .setLanguageCode(SpeechConstants.ENGLISH_LANGUAGE_CODE)
                                    .setEncoding(SpeechConstants.ENCODING)
                                    .setSampleRateHertz(16000)
                                    .build())
                            .setInterimResults(false)
                            .setSingleUtterance(false)
                            .build()
                    );
                }

                requestStream.onNext(builder.build());

                return null;
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        _audioEmitter.stop();
        _audioEmitter = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        _speechClient.shutdown();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SpeechConstants.REQUEST_RECORD_AUDIO_PERMISSION) {
            _permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }

        if (!_permissionToRecord) {
            finish();
        }
    }

    public void moveForward(View view) {
        _espClient.move(view, DirectionConstants.FORWARD);
    }

    public void moveBackward(View view) {
        _espClient.move(view, DirectionConstants.BACKWARD);
    }

    public void moveLeft(View view) {
        _espClient.move(view, DirectionConstants.LEFT);
    }

    public void moveRight(View view) {
        _espClient.move(view, DirectionConstants.RIGHT);
    }
}
