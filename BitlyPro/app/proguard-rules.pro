# ProGuard rules for Bitly Pro app

# Keep the Room database classes
-keep class com.bitlypro.myapp.data.database.** { *; }

# Keep the SpeedTestEntity class
-keep class com.bitlypro.myapp.data.database.SpeedTestEntity { *; }

# Keep the DAO interfaces
-keep interface com.bitlypro.myapp.data.database.SpeedTestDao { *; }

# Keep the ViewModel classes
-keep class com.bitlypro.myapp.ui.speedtest.SpeedTestViewModel { *; }
-keep class com.bitlypro.myapp.ui.charts.ChartFragment { *; }

# Keep the networking classes
-keep class com.bitlypro.myapp.data.network.** { *; }

# Keep the repository classes
-keep class com.bitlypro.myapp.data.repository.** { *; }

# Keep the utility classes
-keep class com.bitlypro.myapp.utils.** { *; }

# Keep the MainActivity class
-keep class com.bitlypro.myapp.MainActivity { *; }

# Keep all annotations
-keepattributes *Annotation*

# Keep the application class
-keep class com.bitlypro.myapp.MyApplication { *; }

# Keep the generated Room classes
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.Database { *; }
-keep class * extends androidx.room.Entity { *; }
-keep class * extends androidx.room.Dao { *; }

# Prevent obfuscation of the classes used in reflection
-keep class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep the public methods of the classes
-keepclassmembers class * {
    public <methods>;
}