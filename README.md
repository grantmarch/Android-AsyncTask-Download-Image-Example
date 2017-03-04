# Android-AsyncTask-Download-Image-Example

This code is based on the YouTube video [Android Studio Tutorial - 67 - Download Image Using AsyncTask](https://www.youtube.com/watch?v=5Bo-ESPkpxI).
There are a few changes in particular this line is used in place of the hardcoded "sdcard/photoalbum"

File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");

In particular this code works with JellyBean Android API 18. It will not work with Marshmallow and later because of the new runtime permissions. 
I will post a version that does. 

The emulator for Jellybean Android v18 did NOT work with this code when run from Android Studio. I had to start the emulator from the command line 
using my particular dns server as follows...

wherever-your-android-sdk-is-located/tools/emulator.exe -avd Nexus_5_API_18 -dns-server xxx.xxx.xxx.xxx

...where Nexus_5_API_18 is the name of your AVD file and xxx.xxx.xxx.xxx is the IP address of your domain name server.

Hope this proves useful.
