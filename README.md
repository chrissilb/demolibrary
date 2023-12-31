# demolibrary

Library demonstrator of the ESC component framework

Shows especially:
* Extensions
* Pattern Matching

## Highlights
Once the login dialog is open for more than 3 seconds a "support message" occurs within the status line:

<img src="pics/loginsupport.gif" width="400" />

Code:
```Java
@Extension(type=OpacFrameAuthentification.class, extendz=OpacFrame.class) …
public class OpacFrameAuthentificationImpl { …
   @Within(interval=3000, methodName="loginSupport")
   public boolean login() {
      LoginDialog ld = InstanceAllocator.create(LoginDialog.class, 
         core.getOpacFrameImpl(), core.getLibrary());
      ld.init();
      boolean success = ld.getUser() != null;
      if (success) {
         core.setUser(ld.getUser());
         thiz.startLowTrafficObservation();
      }
      thiz.updateView();
      return success;
   }

   public void loginSupport() {
      lblUserStatus.setText("Please enter username and password.");
   }
}
```

Once the User is inactive for more than 5 seconds a countdown starts from 10 seconds. Automatic logout happens after but can be interrupted by user activity:

<img src="pics/autologout.gif" width="400" />

Code:
```Java
@Tick(interval=1000, invocations=10, activateMethod="startCountdown", deactivateMethod="stopCountdown")
public void doCountdown() {
	if (--countdownCounter > 0)
		lblUserStatus.setText("Automatic logout in " + countdownCounter + " seconds.");
	else logout(); 
}
```

## Installation
See https://github.com/chrissilb/escframework#installation

The project is not compile-clean until "Maven generate-sources" is executed in order to generate the component interfaces.


## Usage
See https://gwasch.de/escframework.php - "Folien" (only available in German so far)

