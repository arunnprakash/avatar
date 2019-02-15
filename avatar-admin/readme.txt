Clean Andriod platform
----------------------
tns platform clean android

Run in Andriod
--------------
tns run android --bundle

Move image from local machine to Emulator
-----------------------------------------

C:\Android\android-sdk\platform-tools>adb push husky-3380548__340.jpg /sdcard/Pictures

Debug Application
------------------
tns debug android --bundle

and then open below in chrome address
 
chrome-devtools://devtools/bundled/inspector.html?experiments=true&ws=localhost:40000