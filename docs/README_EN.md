# 📱 Android Application Documentation: AllContacts

________________________________________
🧾 General Information
**Project Name:** AllContacts  
**Author(s):** Zeev Fraiman  
**Date:** May 22, 2024  
**Language:** Java  
**Development Environment:** Android Studio  
**Android Version:** minSdk 16 / targetSdk 28  

________________________________________
🎯 Project Goal
•	Allows users to view a full list of contacts stored on the device.
•	Provides quick dialing functionality directly from the contact list.
•	Monitors incoming call states and provides notifications.
•	**Target Audience:** Users looking for a lightweight and simple contact management/dialing tool.

________________________________________
📌 Application Requirements
**Functional Requirements**
•	Request runtime permissions for reading contacts and making calls.
•	Query the system contact database for names and phone numbers.
•	Display contacts in an easy-to-read list (ListView).
•	Initiate phone calls via Intent when a contact is selected or a number is entered.
•	Show notifications during incoming calls.

**Non-Functional Requirements**
•	**Performance:** Fast loading of the contact list.
•	**Usability:** Simple, intuitive one-screen interface.
•	**Reliability:** Robust handling of permission denials and empty contact lists.

________________________________________
🧠 General Architecture
•	**Chosen Approach:** Standard Android Component-based architecture (Activity, BroadcastReceiver).
•	**Reason for Choice:** The project is straightforward and compact; more complex patterns like MVVM would add unnecessary overhead for this scale.
•	**Core System Components:**
    – `MainActivity`: Manages the user interface and main logic.
    – `PhonecallReceiver`: Handles telephony-related system broadcasts.
    – `ContactsContract`: Acts as the data source via ContentResolver.

________________________________________
🧩 UML Diagram
`[MainActivity]` –> `[ContentResolver]` –> `[Contacts Database]`
`[PhonecallReceiver]` –> `[TelephonyManager]` –> `[NotificationManager]`

________________________________________
🧩 Detailed Class Description
📌 Class: MainActivity
**Role:** The primary user interface.
**Responsibility:** Handling UI interactions, requesting permissions, and fetching/displaying contacts.
**Key Methods:**
- `onCreate()` — Initializes views and checks for permissions.
- `getContacts()` — Queries the system's contact provider.
- `viewAllContacts()` — Populates the UI list with fetched data.
- `callnow()` — Triggers an ACTION_CALL intent.

📌 Class: PhonecallReceiver
**Role:** BroadcastReceiver for telephony events.
**Reason for Use:** To monitor the device's call state (ringing, idle) and respond accordingly (e.g., showing a notification).

________________________________________
🔄 Application Workflow
1. App launch.
2. Permission request (CALL_PHONE, READ_CONTACTS).
3. User clicks "All Contacts".
4. App fetches data from the system and displays it in the list.
5. User selects a contact -> App initiates a phone call.

________________________________________
🎨 UI/UX Analysis
•	The interface uses standard Android components for familiarity.
•	**Principles:** Simplicity (single screen), Logic (action buttons placed near input fields), Accessibility (large list items).
•	**Improvements:** Add search functionality, implement RecyclerView for better performance, and add a dark mode.

________________________________________
⚙️ Threading
•	**Current Usage:** Main Thread for UI and data fetching.
•	**Prevention:** For large contact databases, move `getContacts()` to a background thread to prevent ANR (Application Not Responding) errors.

________________________________________
💾 Data Management
•	**Storage:** Uses the Android System Contacts database.
•	**Access Method:** `ContentResolver` querying `ContactsContract`.
•	**Data Integrity:** Managed by the Android OS.

________________________________________
🔐 Security
•	Respects Android's permission model. No sensitive data is transmitted outside the device.

________________________________________
🧪 Testing
•	**Unit Tests:** Validating phone number string parsing.
•	**UI Tests:** Verifying list population upon button click.

________________________________________
📊 Self-Assessment
| Criterion | Rating (1–10) |
| :--- | :--- |
| Architecture | 7 |
| Code | 8 |
| UI/UX | 7 |
| Reliability | 9 |
| Overall Level | 8 |

________________________________________
🏁 Conclusion
•	**Best Part:** Seamless integration with system contact data.
•	**Challenge:** Managing permissions across different Android versions.
•	**Skills Acquired:** Proficiency with ContentProviders, BroadcastReceivers, and Intent-based communication.
