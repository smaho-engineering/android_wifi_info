import 'dart:async';

import 'package:flutter/services.dart';

// TODO: look up valid enum values
// TODO: is there a simple and nice way to convert string to enums in Dart?
// TODO: make sure toString is correct
enum DetailedState { TODO }
enum SupplicantState { TODO }

/// Provides interface for every WifiInfo methods and constants. Android-only.
///
/// This is an Android-only Flutter plugin. This means, that all of these methods
/// and getters will return `null` on iOS.
///
/// Works on real devices. Make sure you configured your emulator properly.
class AndroidWifiInfo {
  static const _channelName = 'eng.smaho.com/android_wifi_info';
  static const MethodChannel _channel = const MethodChannel(_channelName);

  /// The unit in which frequency measurements expressed in this API.
  ///
  /// Constant Value: "MHz".
  ///
  /// As it's constant value, it doesn't really make much sense to call it all
  /// the time, but as the package decided to provide an interface to the
  /// WifiInfo class, frequencyUnits is also part of the API.
  static Future<String> get frequencyUnits {
    return _channel.invokeMethod('frequencyUnits');
  }

  /// The unit in which links speeds are expressed.
  ///
  /// Constant Value: "Mbps".
  ///
  /// As it's constant value, it doesn't really make much sense to call it all
  /// the time, but as the package decided to provide an interface to the
  /// WifiInfo class, linkSpeedUnits is also part of the API.
  static Future<String> get linkSpeedUnits {
    return _channel.invokeMethod('linkSpeedUnits');
  }

  /// Basic service set identifier (BSSID) of the current access point.
  ///
  /// Returns BSSID, in the form of a six-byte MAC address: XX:XX:XX:XX:XX:XX
  ///
  /// The BSSID may be null if there is no network currently connected.
  static Future<String> get bssid {
    return _channel.invokeMethod('bssid');
  }

  /// Map a supplicant state [supplicantState] into a fine-grained network
  /// connectivity state.
  ///
  /// Returns the corresponding [DetailedState]
  static Future<DetailedState> detailedStateOf(
      SupplicantState supplicantState) {
    // TODO: convert enums...
    return _channel.invokeMethod('detailedStateOf', '$supplicantState');
  }

  /// Current WiFi frequency in [frequencyUnits] (MHz).
  static Future<int> get frequency {
    return _channel.invokeMethod('frequency');
  }

  /// Shows whether the current network is hidden.
  ///
  /// `true` if this network does not broadcast its SSID,
  /// so an SSID-specific probe request must be used for scans.
  static Future<bool> get ssidHidden {
    return _channel.invokeMethod('ssidHidden');
  }

  /// IP Address.
  ///
  /// TODO: Add example for converting integer to e.g. dotted-decimal notation
  /// or hexadecimal representation.
  static Future<int> get ipAddress {
    return _channel.invokeMethod('ipAddress');
  }

  /// Current link speed in [linkSpeedUnits] (Mbps).
  static Future<int> get linkSpeed {
    return _channel.invokeMethod('linkSpeed');
  }

  /// MAC Address.
  ///
  /// Using these device identifiers is not recommended other than for high
  /// value fraud prevention and advanced telephony use-cases. For advertising
  /// use-cases, use AdvertisingIdClient$Info#getId and for analytics, use
  /// InstanceId#getId.
  ///
  /// More info:
  /// * https://developer.android.com/training/articles/user-data-ids.html
  /// * https://developer.android.com/about/versions/marshmallow/android-6.0-changes#behavior-hardware-id
  static Future<String> get macAddress {
    return _channel.invokeMethod('macAddress');
  }

  /// Network ID.
  ///
  /// Each configured network has a unique small integer ID, used to identify
  /// the network when performing operations on the supplicant.
  /// This method returns the ID for the currently connected network.
  static Future<int> get networkId {
    return _channel.invokeMethod('networkId');
  }

  /// Received Signal Strength Indicator.
  ///
  /// Returns the received signal strength indicator of the current 802.11
  /// network, in dBm.
  ///
  /// TODO: Example using calculateSignalLevel. Make it part of this package?
  static Future<int> get rssi {
    return _channel.invokeMethod('rssi');
  }

  /// Current receive link speed in Mbps.
  ///
  /// TODO: Android Q feature, not added to the API in Java yet.
  static Future<int> get rxLinkSpeedMbps {
    return _channel.invokeMethod('rxLinkSpeedMbps');
  }

  /// Service set identifier (SSID aka WiFi Name) of the current 802.11 network.
  static Future<String> get ssid {
    return _channel.invokeMethod('ssid');
  }

  /// Detailed state of the supplicant's negotiation with an access point.
  static Future<SupplicantState> get supplicantState {
    // TODO: convert to enum...
    return _channel.invokeMethod('supplicantState');
  }

  /// Current transmit link speed in [linkSpeedUnits] (Mbps).
  static Future<int> get txLinkSpeedMbps {
    return _channel.invokeMethod('txLinkSpeedMbps');
  }
}
