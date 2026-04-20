Mobile Automation Certification Testing Project Setup Guide Introduction This project uses Appium for mobile automation testing on Android devices. This guide will help you set up the environment and run the project successfully.

Prerequisites Ensure the following are installed on your system: • Node.js • npm (comes with Node.js) • Java JDK • Android Studio • Git (optional)

Step 1: Verify Node.js Installation Check if Node.js is installed: node -v Expected: Displays the installed version If not installed: Download from https://nodejs.org/

Step 2: Verify Appium Installation appium -v If not installed, proceed below.

Step 3: Install Appium & Appium Doctor Install Appium globally: npm install -g appium Install Appium Doctor: npm install -g appium doctor

Step 4: Verify Setup Using Appium Doctor Run: appium-doctor --android Expected Output • Node.js detected • ANDROID_HOME set • JAVA_HOME set Example Issue android, emulator could NOT be found Fix • Install Android SDK tools via Android Studio • Ensure environment variables are correctly set Important: Do not proceed until all required dependencies show ✔

Step 5: Install Android Driver (UIAutomator2) Check available drivers: appium driver list Install driver: appium driver install uiautomator2

Step 6: Start Appium Server appium To stop the server: Ctrl + C

Step 7: Android SDK Setup Install Android Studio, which includes: • Android SDK • Platform Tools (adb) • Emulator

Step 8: Connect Real Device Enable Developer Options • Go to Settings → About Phone • Tap Build Number multiple times Enable USB Debugging • Go to Developer Options • Enable USB Debugging Verify Device Connection adb devices Your device should appear in the list

Step 9: Install Appium Inspector Use Appium Inspector to locate elements. complete installation Tutorial: https://youtube.com/playlist?list=PLhW3qG5bs-L8BQaqLpjt5792e8om6IR3k

Step 10: Charles Proxy Setup Installation • Install latest Charles Proxy • Activate license (required for headless mode) Configuration • Ensure system & mobile device are on the same Wi-Fi Set proxy in: Wifi>click on the connected wifi>more>proxy> enter the ip address and port number (default port is 8888 for Charles) project > Charles > charlesManager > proxy

Step 11: Device Configuration in Project Navigate to: Driver > driverManager Update: • Device Name • OS Version

Step 12: Run the Project ./gradlew clean test

Troubleshooting Device not detected • Ensure USB debugging is ON • Reconnect device • Run adb devices Appium Doctor errors • Verify ANDROID_HOME and JAVA_HOME • Install missing SDK components Appium not starting • Check Node.js version • Reinstall Appium

Important Notes • Ensure all necessary dependencies show green tick in Appium Doctor • Keep environment variables correctly configured • Prefer real devices for stable testing

Additional Resources • Appium Docs: https://appium.io/docs/en/latest/ • Android Developer Docs: https://developer.android.com/

Core Components Base Class Path: test/java/base/base.java • This is the base class for all test cases. • Contains all prerequisite setup required before running tests. • Every test class must inherit from this class.

Charles Proxy Manager Path: test/java/charles/charlesManager.java • Handles Charles Proxy integration. • Responsible for capturing API/network logs. Configuration • Proxy = Your system IP address • Default Port = 8888 Update the proxy IP based on your system before running tests.

Flows Package Path: test/java/flows/ • Contains all Appium flows. • Each flow represents a sequence of actions for a test scenario.

Tests Package Path: test/java/tests/ • Contains test methods. • Responsible for: o Calling flows o Triggering validations

Validators Package Path: test/java/validators/ • Contains actual validation logic. • Verifies: o API responses o Expected vs actual behavior

Util Package Path: test/java/util/ • Used for log parsing. • Helps: o Extract data from Charles logs o Convert logs into structured formats (e.g., lists) • These parsed results are used in validator methods.

API Configuration Location: resources/ • Each test case should have a separate JSON file. • JSON contains: o Expected API requests/responses • These files are: 1. Parsed during execution 2. Compared against logs captured via Charles Proxy

Test Execution Flow 1. Test class extends base.java 2. Test method calls required flows 3. Charles Proxy captures API logs 4. Logs are parsed using util package 5. Expected data is read from JSON config 6. Validation is performed using validators

the vz clould apk should be installed and present in the home screen

Steps to Create a Test Case 1. Extend the Base Test All test classes must inherit from the base test class: public class SampleTest extends BaseTest { // Test implementation }

Define Test Structure Each test case should include: • Prerequisite Flow o Setup steps before executing the main test • Test Case Flow o Actual test execution steps

Create Appium Flows • Use Appium Inspector to generate flows. • It helps capture UI interactions and convert them into Appium code. 👉 Download Appium Inspector: https://github.com/appium/appium-inspector/releases • Generated flows should be organized properly and reused where possible.

Organize Test Flows All test case flows must be placed under: /flows This includes: • Prerequisite flows • Main test flows

Capture Network Logs (Charles Proxy) After executing the Appium flow: • Call the function to download Charles logs • This ensures all API calls during execution are captured TestSuiteSetup.getCharlesManager().downloadSessionAsHar(harFilePath);

Wait for HAR File Export • Ensure the .har file is fully exported before proceeding • Implement a wait mechanism if necessary

Validate API Calls Once the HAR file is ready: • Call the validator method with the HAR file path validate(harFilePath);

Validator Implementation • All validation logic must reside in: /validators • The validator method should: o Accept:  HAR file path  Expected JSON file o Compare:  API endpoints  Status codes  Expected request/response data

Test Resources Each test must define expected API behaviour in a JSON file: /resources

template.json file can be reused for writing JSON file for specific case JSON should include: • Expected API endpoints • Expected status codes • Required API calls • Method • status Eg: { "taggedFiles": [ { "name": "Tags API", "regex": "./tm/user/[^/]+/tags$", "method": "GET", "statuses": [304], "expectedCalls": 1 }, { "name": "Persons API", "regex": "./tm/user/[^/]+/persons$", "method": "GET", "statuses": [404], "expectedCalls": 1 }, { "name": "Tags Count API", "regex": "./tm/user/[^/]+/tags\?count.", "method": "GET", "statuses": [200, 304], "expectedCalls": 1 } ] }
