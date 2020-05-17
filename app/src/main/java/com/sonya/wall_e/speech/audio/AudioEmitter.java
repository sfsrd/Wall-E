package com.sonya.wall_e.speech.audio;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AudioEmitter {

    private static final int ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private static final int CHANNEL = AudioFormat.CHANNEL_IN_MONO;
    private static final int SAMPLE_RATE = 16000;

    private AudioRecord mAudioRecorder = new AudioRecord.Builder()
            .setAudioSource(MediaRecorder.AudioSource.MIC)
            .setAudioFormat(new AudioFormat.Builder()
                    .setEncoding(ENCODING)
                    .setSampleRate(SAMPLE_RATE)
                    .setChannelMask(CHANNEL)
                    .build())
            .build();;

    private ScheduledExecutorService mAudioExecutor = Executors.newSingleThreadScheduledExecutor();
    private byte[] mBuffer;

    public void start (Function<ByteString, NullValue> subscriber) {

        mAudioExecutor = Executors.newSingleThreadScheduledExecutor();

        mAudioRecorder = new AudioRecord.Builder()
                .setAudioSource(MediaRecorder.AudioSource.MIC)
                .setAudioFormat(new AudioFormat.Builder()
                        .setEncoding(ENCODING)
                        .setSampleRate(SAMPLE_RATE)
                        .setChannelMask(CHANNEL)
                        .build())
                .build();

        mBuffer = new byte[2 * AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL, ENCODING)];

        mAudioRecorder.startRecording();


        mAudioExecutor.scheduleAtFixedRate(() ->
        {
            int read = mAudioRecorder.read(mBuffer, 0, mBuffer.length, AudioRecord.READ_BLOCKING);

            if (read > 0) {
                subscriber.apply(ByteString.copyFrom(mBuffer, 0, read));
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    public void stop() {

        mAudioExecutor.shutdown();
        mAudioExecutor = null;

        mAudioRecorder.stop();
        mAudioRecorder.release();
        mAudioRecorder = null;
    }
}
