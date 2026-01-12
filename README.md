# 🎬 Lightweight Android Video Player Application

## 📌 Project Overview

### Background
In today’s mobile-first world, users often store numerous local video files such as movies, tutorials, and short clips on their smartphones or SD cards. Despite this, many built-in or third-party video players feel cluttered, overloaded with ads, or packed with unnecessary features like cloud streaming and social sharing. These complexities reduce usability and frustrate users who simply want smooth, reliable playback.

### Problem Statement
There is a lack of a **lightweight, user-friendly, and feature-rich video player** designed for everyday users. Existing video players either overwhelm users with non-essential features or fail to deliver core functionalities such as fast seeking, subtitle support, format compatibility, and battery efficiency. Additionally, beginner developers lack a simple reference application that demonstrates reliable local video playback without complex configurations.

### Project Objective
The objective of this project is to develop a **lightweight Android video player application** that enables users to browse, play, and manage local video files using an intuitive interface. The app will support essential playback features such as play/pause, volume control, fast seeking, and full-screen mode while maintaining smooth performance across devices. The final application will be optimized for **Google Play Store deployment**, targeting users seeking an **ad-free and efficient alternative**.

---

## ⚙️ Functional Requirements

### 1. User Authentication (Optional)
- Allow guest access or simple login to store playback history.
- On first launch, display a welcome screen with:
  - App overview
  - Storage permission request

### 2. File Discovery and Browsing
- Scan internal storage and SD cards for video files.
- Supported formats:
  - MP4
  - AVI
  - MKV
  - MOV
- Display videos in list or grid view.
- Sorting options:
  - Name
  - Date
  - Size
  - Duration
- Search functionality to filter videos by name or type.
- Request and manage `READ_EXTERNAL_STORAGE` permission.

### 3. Video Playback
- Play videos in full-screen mode with landscape orientation lock.
- Core playback controls:
  - Play / Pause
  - Fast-forward / Rewind (10-second skips)
- Volume slider and mute toggle.
- Hardware acceleration support.
- Auto-pause when app moves to background.
- Resume playback from last watched position.

### 4. Media Management
- Mark videos as favorites for quick access.
- Delete or share video files.
- Playlist management:
  - Create custom playlists
  - Add or remove videos for sequential playback

---

## 🛡️ Non-Functional Requirements

### 1. Performance
- App launch time: **Less than 2 seconds**.
- Playback performance:
  - Seek time under 1 second
  - Support 1080p videos at 30–60fps without lag
- Memory usage:
  - Less than 100MB RAM during playback
  - Stable performance on low-end devices
- Battery efficiency:
  - Minimized drain during extended playback using hardware decoding (e.g., ExoPlayer)

### 2. Usability & Accessibility
- Intuitive UI following **Material Design 3** guidelines.
- Gesture-based controls:
  - Swipe for volume and brightness
- Responsive layouts for:
  - Phones (4–7 inches)
  - Tablets
- Accessibility features:
  - TalkBack screen reader support
  - High-contrast mode
  - Adjustable subtitle text size
- Localization:
  - English as default
  - Easy extension for additional languages

### 3. Security & Privacy
- Offline-first application with no data collection.
- No internet permissions unless required for updates.
- Secure file handling using scoped storage.
- Runtime permission requests with clear user explanations.

### 4. Reliability & Maintainability
- Target of **99% crash-free sessions**.
- Graceful error handling:
  - Informative dialogs for unsupported formats
- Clean and modular codebase using **MVVM architecture**.
- Unit test coverage of at least **80%**.
- Scalable design allowing future features without major rewrites.

### 5. Deployment & Compatibility
- APK size below **50MB**.
- Backward compatibility:
  - Android 7.0 (API 24) to latest versions
- Google Play Store compliant:
  - Clear privacy policy
  - No malicious behavior
- Update mechanism:
  - In-app version checking via Firebase

---

## 🚀 Conclusion
This project delivers a streamlined, efficient, and user-focused Android video player that prioritizes performance, simplicity, and privacy. By focusing on core playback features and eliminating unnecessary bloat, the application provides a reliable solution for users seeking smooth local video playback and serves as a solid reference for beginner Android developers.
