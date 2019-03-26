import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:android_wifi_info/android_wifi_info.dart';

void main() {
  const MethodChannel channel = MethodChannel('android_wifi_info');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await AndroidWifiInfo.platformVersion, '42');
  });
}
