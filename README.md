最简单易懂的百度地图定位，基础地图显示以及添加覆盖物

Moudle下配置：
 sourceSets {
        main {
            jniLibs.srcDirs = ['libs']
        }
    }

dependencies {
    compile files('libs/BaiduLBS_Android.jar')
}

AndroidManifest.xml配置：
  <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
 
  <meta-data
       android:name="com.baidu.lbsapi.API_KEY"
       android:value="百度地图开放平台注册得到的key" />
  <service
       android:name="com.baidu.location.f"
       android:enabled="true"
       android:process=":remote" />

