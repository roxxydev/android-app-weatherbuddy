-ignorewarnings
-dontskipnonpubliclibraryclasses

#-dontoptimize
#-dontpreverify
-dontobfuscate

-keepattributes SourceFile,LineNumberTable, Signature,*Annotation*

# Core and App
-keep class com.weatherbuddy.android.** { *; }
-keep interface com.weatherbuddy.android.** { *; }

# Android v4 support library
-keep class android.support.v4.** { *;}
-keep interface android.support.v4.** { *;}
-keep class com.google.android.support.v4.** { *;}
-keep interface com.google.android.support.v4.** { *;}

# Android v7 support library
-keep class com.google.android.support.v7.** { *;}
-keep interface com.google.android.support.v7.** { *;}

# Square Otto Event Bus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# Picasso image loader
-keep class com.squareup.picasso.** { *; }
-keep interface com.squareup.picasso.** { *; }