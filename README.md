# `android_wifi_info`

> Dart plugin package for accessing Android's WifiInfo from Flutter. Android-only plugin.

This is a Dart plugin package for accessing wifi information from Flutter and Dart.
This is an Android-only plugin.

The plugin wraps the [`WifiInfo`](https://developer.android.com/reference/android/net/wifi/WifiInfo)
class and provides access to all of its methods. It describes the state of any Wifi connection that
is active or is in the process of being set up.


## References

The plugin is published on Dart Pub [`pub.dartlang.org/packages/android_wifi_info`](https://pub.dartlang.org/packages/ios_network_info)

You can read the [API reference](https://pub.dartlang.org/documentation/android_wifi_info/latest/) on Dart Pub.

The source code is available on GitHub [`smaho-engineering/android_wifi_info`](https://github.com/smaho-engineering/android_wifi_info).

This Flutter plugin is created by the [SMAHO engineering team](https://github.com/smaho-engineering).

## Usage

```dart
import 'package:android_wifi_info/android_wifi_info.dart';

getNetworkInfo() async {
  final bssid = await AndroidWifiInfo.bssid;
  final ssid = await AndroidWifiInfo.ssid;
}
```

### Example app

For a working example app, see the [`example` directory](https://github.com/smaho-engineering/android_wifi_info/tree/master/example/lib/main.dart).

[To view example screen record, click here](https://github.com/smaho-engineering/android_wifi_info/raw/master/gifs/android_wifi_info_-_example_app.mp4)

### Documentation

![AndroidWifiInfo Flutter plugin's documentation](https://github.com/smaho-engineering/android_wifi_info/raw/master/gifs/android_wifi_info_-_demo_dart_docs.gif)

### Combine with other plugins to access features cross-platform

This plugin is intentionally supporting only Android. However, this doesn't mean you cannot use this
plugin to create your own cross-platform utility module for fetching the details you need for your
app.

For example, if you want to fetch the current WiFi's BSSID, you can combine it with [`ios_network_info`](https://pub.dartlang.org/packages/ios_network_info) package:

```dart
import 'dart:io';

import 'package:android_wifi_info/android_wifi_info.dart';
import 'package:ios_network_info/ios_network_info.dart';

get bssid {
  if (Platform.isAndroid) {
    return AndroidWifiInfo.bssid;
  } else if (Platform.isIOS) {
    return IosNetworkInfo.bssid;
  }
  throw Exception('WiFi BSSID is not supported on this platform');
}
```

## To do

This plugin is very much in progress.

There are some important tasks I'm still planning to do for the `1.x` version release.

* handle enums properly
* related code, eg converting RSSI to something useful (scale)
* don't forget to point users to how to convert int values to more traditional representations (ip)
* Android Q? tx, rx link speed
* could add links to the right sections in android docs
* consider adding code snippets to the documentation
* example app docs contains links and on tap could open the links in browser... but it's a bit of an overkill for an example app
* make mac address work with proper permissions or at least document it
