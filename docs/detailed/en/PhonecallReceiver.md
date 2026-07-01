# Detailed Description: PhonecallReceiver

## 1. General Information
*   **Class Name:** `PhonecallReceiver`
*   **Type:** BroadcastReceiver
*   **Purpose:** This class listens for system events related to phone calls. Specifically, it waits for the phone to start ringing. When it detects an incoming call, it can show a notification or perform other actions.
*   **Interaction:** It is triggered by the Android System when the "Phone State" changes. It uses the `TelephonyManager` to understand what is happening with the phone hardware.

## 2. Variables (Class Fields)
*This class does not have persistent class-level variables in its active code, as it performs its tasks immediately inside the method.*

## 3. Class Methods

### Method: `onReceive`
*   **Type:** `public`
*   **Return Value:** `void`
*   **Parameters:** 
    *   `Context context`: The environment in which the receiver is running.
    *   `Intent intent`: The object containing information about the event (e.g., that the phone is ringing).
*   **What it does:** 
    1. Obtains the `TelephonyManager` service from the system.
    2. Sets up a `PhoneStateListener`.
    3. The listener waits for the `CALL_STATE_RINGING` state (which means someone is calling).
    4. When ringing, it creates a `Notification.Builder`.
    5. It adds the incoming phone number to the notification.
    6. It uses the `NotificationManager` to display the notification on the status bar.
*   **When called:** Automatically by the Android system whenever a phone-related event occurs (like an incoming call), provided the app has the correct permissions.
*   **Important:** `BroadcastReceivers` should finish their work quickly (usually within 10 seconds). You should not perform long-running tasks here.

## 4. Lifecycle (BroadcastReceiver)
`BroadcastReceivers` do not have a complex lifecycle like Activities. 
- They only exist for the duration of the `onReceive` call. 
- Once `onReceive` finishes, the object is discarded by the system.

## 5. Interface Interaction (UI)
*   This class does not have a layout file (XML).
*   It interacts with the UI through **Notifications**, which appear outside the app in the system's status bar.

## 6. Interaction with other components
*   **System Services:** It connects to `TelephonyManager` (to monitor calls) and `NotificationManager` (to show alerts).
*   **Manifest:** This class must be registered in the `AndroidManifest.xml` file so the system knows to wake it up when a call arrives.

## 7. General Logic
The class acts as a "sleeping guard." It wakes up only when the system shouts "Phone event!". It checks if the phone is ringing. If it is, it grabs the caller's number and sends a message (Notification) to the user's screen.

## 8. Simplified Explanation
**Explanation in simple words:**
Think of `PhonecallReceiver` as a **Security Guard** sitting in a booth.
- Most of the time, the guard is asleep.
- When the **System Alarm** (`Broadcast`) goes off because a call is coming in, the guard wakes up.
- The guard looks at the screen to see who is calling.
- Then, the guard hangs a **Sticky Note** (`Notification`) on the user's door to let them know who called.
- After that, the guard goes back to sleep.
