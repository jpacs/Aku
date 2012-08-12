/**
 *  States of the Actions that are apprehended by the system
 */
package com.xsloth.aku.input;

public enum ActionState {
	STATE_RELEASED,		//When the Input is released
	STATE_PRESSED,		//When an Input is first pressed
	STATE_TAPPED,		//When an Input is pressed and then released
	STATE_HOLDING;		//When an Input is pressed for a continued time
}
