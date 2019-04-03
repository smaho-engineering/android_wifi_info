import 'package:android_wifi_info/android_wifi_info.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  const channelName = 'eng.smaho.com/android_wifi_info';
  const MethodChannel channel = MethodChannel(channelName);

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      switch (methodCall.method) {
        case 'frequencyUnits':
          return 'THz';
        case 'linkSpeedUnits':
          return 'km/h';
        case 'bssid':
          return 'c0:ff:33:c0:ff:33';
        case 'frequency':
          return 2500;
        case 'ssidHidden':
          return true;
        case 'ipAddress':
          return 12345678;
        case 'linkSpeed':
          return 2;
        case 'macAddress':
          return null;
        case 'networkId':
          return 6;
        case 'rssi':
          return -67;
        case 'ssid':
          return 'Vince Android';
      }
      // There are some methods missing, but they don't even work, so that ok...
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('frequencyUnits', () async {
    expect(await AndroidWifiInfo.frequencyUnits, 'THz');
  });
  test('linkSpeedUnits', () async {
    expect(await AndroidWifiInfo.linkSpeedUnits, 'km/h');
  });
  test('bssid', () async {
    expect(await AndroidWifiInfo.bssid, 'c0:ff:33:c0:ff:33');
  });
  test('frequency', () async {
    expect(await AndroidWifiInfo.frequency, 2500);
  });
  test('ssidHidden', () async {
    expect(await AndroidWifiInfo.ssidHidden, true);
  });
  test('ipAddress', () async {
    expect(await AndroidWifiInfo.ipAddress, 12345678);
  });
  test('macAddress', () async {
    expect(await AndroidWifiInfo.macAddress, null);
  });
  test('networkId', () async {
    expect(await AndroidWifiInfo.networkId, 6);
  });
  test('rssi', () async {
    expect(await AndroidWifiInfo.rssi, -67);
  });
  test('ssid', () async {
    expect(await AndroidWifiInfo.ssid, 'Vince Android');
  });
}
