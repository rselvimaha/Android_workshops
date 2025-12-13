This repository contains a collection of Android workshop exercises developed to practice core Android application development concepts such as threading, permissions, networking, and media handling.
Each workshop is implemented as a standalone Android project and organised in its own folder.

Workshops Included
1. Image Download via Background Thread

Folder: image-download

This exercise demonstrates how to download an image from the internet using a background thread and display it in an Android application.

Key Concepts Covered

Performing network operations on a background thread

Updating the UI on the main thread

Handling internet permissions

Downloading and decoding images using BitmapFactory

Description

The application allows the user to enter an image URL and fetch the image by clicking a button. The image is downloaded in a background thread and displayed in an ImageView without blocking the UI.

2. Audio Recording with Runtime Permissions

Folder: record-audio

This exercise demonstrates how to record audio using the device microphone and play it back after recording.

Key Concepts Covered

Requesting runtime permissions

Using MediaRecorder to record audio

Using MediaPlayer to play recorded audio

Handling Android permission lifecycle correctly

Description

The application requests microphone permission at runtime. Once permission is granted, the user can start recording audio and stop the recording to play it back.

Permissions Used:

Depending on the workshop, the following Android permissions are required:

android.permission.INTERNET

android.permission.RECORD_AUDIO

Permissions are declared in AndroidManifest.xml and requested at runtime where applicable.
