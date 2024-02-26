
This Chatbot helps farmer to get detailed information and knowledge about the farming using Farming AI.

## Features

Chatbots are conversational tools that perform routine tasks efficiently. People like them because they help them get through those tasks quickly so they can focus their attention on high-level, strategic, and engaging activities that require human capabilities that cannot be replicated by machines.

## Usage

TODO: Mention and get the package from pubspec.yaml file, This is the local repository used in application.

```pubspec
  chatbot:
    path: ./chatbot
```

TODO: Add the configurations to HTML pages where you want to show the chatbot

```index.html
    <!DOCTYPE html>
      <html lang="en">
      
      <head>
          <meta http-equiv="Content-Security-Policy" content="microphone 'self'">
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <!-- <title>Example Iframe</title> -->
      
          <script type="application/javascript">
              async function myJavaScriptFunction(message) {
                  // This function is invoked from Flutter
                  console.log('JavaScript function invoked with message:', message);
                  // Call the callback function defined in Flutter
                 
                  const stream = await navigator.mediaDevices.getUserMedia({ audio: true });
                  console.log('Flutter callback stream with message:', stream.addEventListener);
                  myFlutterCallback(stream);
              }
      
              async function myFlutterCallback(message) {
                  console.log('Flutter callback invoked with message:', message);
              }
          </script>
      </head>
      
      <body>
          <!-- <button id="enable-microphone">Enable Microphone</button> -->
          <style>
              .my-chat {
                  position: fixed;
                  bottom: 0;
                  right: 0;
                  padding-bottom: 2%;
              }
          </style>
          <!-- <h1>Example Iframe</h1>
          <p>Below is an iframe:</p> -->
          <script src="main.dart.js" type="application/javascript"></script>
          <iframe class="my-chat" src="http://115.124.98.61/quizweb/" width="30%" height="500" frameborder="0" allow="microphone"></iframe>
      
      </body>
      
      </html>
```

TODO: Add below line of code where you wanted to use inside the React app.

```React
  import React from 'react';
  import './App.css'; // Import your CSS file for styling
  
  function App() {
    return (
      <div className="app-container">
        <div className="content">
          {/* Your main content here */}
        </div>
        <div className="iframe-container">
          <iframe
            src="https://example.com"
            title="example iframe"
            className="iframe"
          />
        </div>
      </div>
    );
  }
  
  export default App;

```

```CSS
    .app-container {
      position: relative;
      /* Add any styles you want for your app container */
    }
    
    .content {
      /* Add styles for your main content */
    }
    
    .iframe-container {
      position: absolute;
      bottom: 0;
      right: 0;
      /* You can adjust these values as per your requirement */
    }
    
    .iframe {
      width: 300px; /* Set your desired width */
      height: 200px; /* Set your desired height */
      border: none; /* Remove iframe border */
    }

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

<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/b39036ef-3f7c-446f-b4aa-be6d05b8c1c5" width="80%" height="20%">


<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/1260a357-c8fc-4146-af5f-4d5e867bd7df" width="80%" height="20%">


<img src="https://github.com/ajinkya446/L-D-android-app-development/assets/49361315/b9733c52-75e7-4406-842e-6eb6f111834a" width="80%" height="20%">
