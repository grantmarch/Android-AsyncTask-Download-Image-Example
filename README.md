# Android-AsyncTask-Download-Image-Example

This code is based on the YouTube video [Android Studio Tutorial - 67 - Download Image Using AsyncTask](https://www.youtube.com/watch?v=5Bo-ESPkpxI) by Prabeesh R K.
There are a few changes in particular this line is used in place of the hardcoded "sdcard/photoalbum"

File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");

In particular this code works with JellyBean Android API 18. It will not work with Marshmallow and later because of the new runtime permissions. I will post a version that does do this shortly. 

The emulator for Jellybean Android v18 did NOT work with this code when run from Android Studio. The emulator did not work with the internet. I was not able to using the browser on the phone to surf the internet and so the AVD would timeout when trying to download the image. I had to start the emulator from the command line using my particular dns server as follows...

wherever-your-android-sdk-is-located/tools/emulator.exe -avd Nexus_5_API_18 -dns-server xxx.xxx.xxx.xxx

...where Nexus_5_API_18 is the name of your AVD file and xxx.xxx.xxx.xxx is the IP address of your domain name server. Doing this the internet on the emulator was able to work.

You can browse the filesystem of your emulator using Android Studio -> Tools -> Android -> Android Device Monitor. Here you can see that the URI "sdcard" is a link to another storage folder where you will be able to see your image that has been downloaded.

It is possible to download a file manager by dragging and a dropping an APK onto your emulator. Here is one [ES File Manager](http://es-file-explorer.en.uptodown.com/android) use at your discretion - I cannot vouch for this software.

Hope this proves useful.
