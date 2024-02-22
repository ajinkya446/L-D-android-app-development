<!--
This README describes the package. If you publish this package to pub.dev,
this README's contents appear on the landing page for your package.

For information about how to write a good package README, see the guide for
[writing package pages](https://dart.dev/guides/libraries/writing-package-pages).

For general information about developing packages, see the Dart guide for
[creating packages](https://dart.dev/guides/libraries/create-library-packages)
and the Flutter guide for
[developing packages and plugins](https://flutter.dev/developing-packages).
-->

This Chatbot helps farmer to get detailed information and knowledge about the farming using Farming AI.

## Features

Chatbots are conversational tools that perform routine tasks efficiently. People like them because they help them get through those tasks quickly so they can focus their attention on high-level, strategic, and engaging activities that require human capabilities that cannot be replicated by machines.

## Usage

TODO: Mention and get the package from pubspec.yaml file, This is the local repository used in application.

```pubspec
  chatbot:
    path: ./chatbot
```

TODO: Add the configurations to Android manifest

```manifest.xml
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.BLUETOOTH"/>
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT"/>

    /// Add this quesries below after your application tag
    <queries>
        <intent>
            <action android:name="android.speech.RecognitionService" />
        </intent>
    </queries>
    <queries>
        <intent>
            <action android:name="android.intent.action.TTS_SERVICE" />
        </intent>
    </queries>
```

TODO: Add below line of code where you wanted to use inside the flutter app.

```dart
@override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue
      ),
      home: ChatbotScreen(),
    );
  }
```

## Screenshots

<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/c19c75a9-4001-412b-9454-5efdb9614734" width="20%" height="20%">

<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/59b774c0-1c77-4245-a50a-c9a1fb896278" width="20%" height="20%">

<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/0b1c8848-8da8-487e-907e-0929a80a4aa6" width="20%" height="20%">

<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/e647032e-0a0d-459b-8a80-9569108e1569" width="20%" height="20%">

<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/bf105a59-c669-4f00-85ad-95a8b9c0e388" width="20%" height="20%">
