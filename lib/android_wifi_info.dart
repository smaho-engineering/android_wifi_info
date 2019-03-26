import 'dart:async';

import 'package:flutter/services.dart';

class AndroidWifiInfo {
  static const MethodChannel _channel =
      const MethodChannel('android_wifi_info');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
