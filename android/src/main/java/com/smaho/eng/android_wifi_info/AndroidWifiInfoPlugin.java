package com.smaho.eng.android_wifi_info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.net.wifi.WifiManager;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class AndroidWifiInfoPlugin implements MethodCallHandler {
  private final Registrar registrar;
  private WifiInfo wifiInfo;

  private AndroidWifiInfoPlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "eng.smaho.com/android_wifi_info");
    channel.setMethodCallHandler(new AndroidWifiInfoPlugin(registrar));
  }

  private WifiInfo getWifiInfo() {
    WifiManager wifiManager = (WifiManager) registrar
        .context()
        .getApplicationContext()
        .getSystemService(Context.WIFI_SERVICE);
    WifiInfo wifiInfo = null;
    if (wifiManager != null) {
      wifiInfo = wifiManager.getConnectionInfo();
    }
    return wifiInfo;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    // For now, we request the wifi info again, but it might not be needed
    // Need to check in order to verify that is could be set only one in the plugin
    // permissions, app restarts, lifecycle, etc...
    wifiInfo = getWifiInfo();
    switch (call.method) {
      case "frequencyUnits": frequencyUnits(call, result); return;
      case "linkSpeedUnits": linkSpeedUnits(call, result); return;
      case "bssid": bssid(call, result); return;
      case "detailedStateOf": detailedStateOf(call, result); return;
      case "frequency": frequency(call, result); return;
      case "ssidHidden": ssidHidden(call, result); return;
      case "ipAddress": ipAddress(call, result); return;
      case "linkSpeed": linkSpeed(call, result); return;
      case "macAddress": macAddress(call, result); return;
      case "networkId": networkId(call, result); return;
      case "rssi": rssi(call, result); return;
      case "rxLinkSpeedMbps": rxLinkSpeedMbps(call, result); return;
      case "ssid": ssid(call, result); return;
      case "supplicantState": supplicantState(call, result); return;
      case "txLinkSpeedMbps": txLinkSpeedMbps(call, result); return;
      default: result.notImplemented(); break;
    }
  }

  private void frequencyUnits(MethodCall call, Result result) {
    String frequencyUnits = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      frequencyUnits = WifiInfo.FREQUENCY_UNITS;
    }
    result.success(frequencyUnits);
  }

  private void linkSpeedUnits(MethodCall call, Result result) {
    result.success(WifiInfo.LINK_SPEED_UNITS);
  }

  private void bssid(MethodCall call, Result result) {
    String bssid = null;
    if (wifiInfo != null) {
      bssid = wifiInfo.getBSSID();
    }
    // If there are permission issues, BSSID returns "02:00:00:00:00:00"
    // which means info is missing, therefore we return null
    if (bssid != null && bssid.equals("02:00:00:00:00:00")) {
      bssid = null;
    }
    result.success(bssid);
  }

  private void detailedStateOf(MethodCall call, Result result) {
    final SupplicantState supplicantState = SupplicantState.valueOf((String) call.arguments);
    NetworkInfo.DetailedState detailedState = null;
    if (wifiInfo != null) {
      detailedState = WifiInfo.getDetailedStateOf(supplicantState);
    }
    result.success(detailedState == null ? null : detailedState.toString());
  }

  private void frequency(MethodCall call, Result result) {
    Integer frequency = null;
    if (wifiInfo != null) {
      if (Build.VERSION.SDK_INT >= 21) {
        frequency = wifiInfo.getFrequency();
      }
    }
    result.success(frequency);
  }

  private void ssidHidden(MethodCall call, Result result) {
    Boolean hiddenSSID = null;
    if (wifiInfo != null) {
      hiddenSSID = wifiInfo.getHiddenSSID();
    }
    result.success(hiddenSSID);
  }

  private void ipAddress(MethodCall call, Result result) {
    Integer ipAddress = null;
    if (wifiInfo != null) {
      ipAddress = wifiInfo.getIpAddress();
    }
    result.success(ipAddress);
  }

  private void linkSpeed(MethodCall call, Result result) {
    Integer linkSpeed = null;
    if (wifiInfo != null) {
      linkSpeed = wifiInfo.getLinkSpeed();
    }
    // The link speed returns -1 if there is no valid value.
    if (linkSpeed == -1) {
      linkSpeed = null;
    }
    result.success(linkSpeed);
  }

  @SuppressLint("HardwareIds")
  private void macAddress(MethodCall call, Result result) {
    String macAddress = null;
    if (wifiInfo != null) {
      // Using getMacAddress to get device identifiers is not recommended.
      macAddress = wifiInfo.getMacAddress();
    }
    result.success(macAddress);
  }

  private void networkId(MethodCall call, Result result) {
    Integer networkId = null;
    if (wifiInfo != null) {
      networkId = wifiInfo.getNetworkId();
    }
    // The network ID returns -1 if there is no currently connected network.
    if (networkId == -1) {
      networkId = null;
    }
    result.success(networkId);
  }

  // TODO: Use WifiManager.calculateSignalLevel(int, int) to convert this number into an absolute
  // signal level which can be displayed to a user. I need to think about how to expose this to the
  // Flutter plugin...
  private void rssi(MethodCall call, Result result) {
    Integer rssi = null;
    if (wifiInfo != null) {
      rssi = wifiInfo.getRssi();
    }
    result.success(rssi);
  }

  private void rxLinkSpeedMbps(MethodCall call, Result result) {
    // Added in Android Q which is currently in beta
    result.notImplemented();
    /*
    Integer rxLinkSpeed = null;
    if (wifiInfo != null) {
      rxLinkSpeed = wifiInfo.getRxLinkSpeedMbps();
    }
    if (rxLinkSpeed == -1) {
      rxLinkSpeed = null;
    }
    result.success(rxLinkSpeed);
    */
  }

  private void ssid(MethodCall call, Result result) {
    String ssid = null;
    if (wifiInfo != null) {
      ssid = wifiInfo.getSSID();
      // TODO: Check if there is a better way to avoid NPE in Java
      if (ssid != null) {
        // The SSID may be <unknown ssid> if there is no network currently connected,
        // or if the caller has insufficient permissions to access the SSID
        if (ssid.equals("<unknown ssid>")) {
          ssid = null;
        }
        // Prior to Build.VERSION_CODES.JELLY_BEAN_MR1, this method always returned
        // the SSID with no quotes around it.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
          if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
            ssid = ssid.substring(1, ssid.length() - 1);
          }
        }
      }

    }
    result.success(ssid);
  }

  private void supplicantState(MethodCall call, Result result) {
    SupplicantState supplicantState = null;
    if (wifiInfo != null) {
      supplicantState = wifiInfo.getSupplicantState();
    }
    result.success(supplicantState == null ? null : supplicantState.toString());
  }

  private void txLinkSpeedMbps(MethodCall call, Result result) {
    // Added in Android Q which is currently in beta
    result.notImplemented();
    /*
    Integer txLinkSpeed = null;
    if (wifiInfo != null) {
      txLinkSpeed = wifiInfo.getTxLinkSpeedMbps();
    }
    if (txLinkSpeed == -1) {
      txLinkSpeed = null;
    }
    result.success(txLinkSpeed);
    */
  }
}
