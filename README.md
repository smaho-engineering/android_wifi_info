# `android_wifi_info`

> Dart plugin package for accessing Android's WifiInfo from Flutter. Android-only plugin.

**This is an Android-only plugin package for Flutter and Dart.**

The plugin wraps the [`WifiInfo`](https://developer.android.com/reference/android/net/wifi/WifiInfo) class and provides access to all of its methods.
It describes the state of any Wifi connection that is active or is in the process of being set up.

## To do

* handle enums properly
* double check where to include the permissions. If have to be added on a per project basis and it cannot be included in the plugin, don't forget to document it in this README
* related code, eg converting RSSI to something useful (scale)
* example app should be awesome
* documentation for the methods and getters
    * don't forget to point users to how to convert int values to more traditional representations (ip, mac, etc...)
* Android Q? tx, rx link speed
* make sure dart pub links are all present and correct
* consider adding code snippets to the documentation
* example app docs contains links. could open it in browser...
* make mac address work with proper permissions or at least document it
