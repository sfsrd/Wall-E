package com.sonya.wall_e.speech.constants;


import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

import java.util.ArrayList;
import java.util.List;

public class SpeechConstants {

    public static final List<String> PERMISSIONS = new ArrayList<>();

    public static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    public static String RUSSIAN_LANGUAGE_CODE = "ru-RU";
    public static String ENGLISH_LANGUAGE_CODE = "en-US";

    public static final AudioEncoding ENCODING =  AudioEncoding.LINEAR16;
}
